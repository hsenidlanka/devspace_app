package hsl.devspace.app.corelogic.repository.item;

import hsl.devspace.app.corelogic.domain.Item;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public class ItemRepositoryImpl implements ItemRepository {
    Item item = new Item();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    //private static org.apache.log4j.Logger log = Logger.getLogger(ItemRepositoryImpl.class);
    org.slf4j.Logger log = LoggerFactory.getLogger(ItemRepositoryImpl.class);


    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /*Add new item*/
    @Override
    public int add(Item item) {
        int row = 0;
        //String itmNm = item.getItemName();
        int id = item.getItemId();
        //boolean availability = checkAvailability(itmNm);

        // if (availability == false) {
        String sql = "INSERT INTO item" +
                "(name,description,type_id,image,sub_category_id) VALUES (?,?,(SELECT type_id FROM type WHERE name=? ),?,(SELECT id FROM sub_category WHERE name=?))";
        row = jdbcTemplate.update(sql, new Object[]{item.getItemName(), item.getDescription(),
                item.getType(), item.getImage(), item.getSubCategoryName()});
        log.info("{} new item inserted",row);
        // } else
        //log.info(row + "item already available");

        return id;
    }

    /*insert sizes of a specific item*/
    @Override
    public int updateSizeTable(int id, List<Item> item2) {
        int row = 0;
        for (int i = 0; i < item2.size(); i++) {
            String price = item2.get(i).getPrice();
            String size = item2.get(i).getSize();
            String sql = "INSERT INTO size" +
                    "(size,price,item_id) VALUES (?,?,?)";
            row = jdbcTemplate.update(sql, new Object[]{size, price, id});
            log.info("{} size updated",row);
        }
        return row;
    }

    /*check if item is already available*/
    @Override
    public boolean checkAvailability(String itemName) {

        boolean result;
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE  name=?", itemName);
        log.info("{}",mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        log.info("{}",result);
        return result;
    }

    /*delete an item*/
    @Override
    public int delete(String itemName) {
        //item.setItemName(itemName);
        String sql = "DELETE FROM item WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{itemName});
        log.info("{} item deleted",row);
        return row;
    }

    /**delete from size when item deleted*/
    @Override
    public int deleteFromSize(String itemName){
        String sql = "DELETE FROM `size` WHERE item_id =(SELECT id FROM item WHERE name=?)";
        int row = jdbcTemplate.update(sql, new Object[]{itemName});
        log.info("{} sizes deleted",row);
        return row;
    }


    /*view all item details*/
    @Override
    public List<Item> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item");
        List<Item> items=new ArrayList<Item>();
        for (int i=0;i<mp.size();i++){
            Item item = new Item();
            item.setItemId(Integer.parseInt(mp.get(i).get("id").toString()));
            item.setItemName(mp.get(i).get("name").toString());
            item.setDescription(mp.get(i).get("description").toString());
            item.setTypeId(Integer.parseInt(mp.get(i).get("type_id").toString()));
            item.setImage(mp.get(i).get("image").toString());
            item.setSubCategoryId(Integer.parseInt(mp.get(i).get("sub_category_id").toString()));
            items.add(item);

        }
        log.info("{}",items);
        return items;
    }

    /*retrieve no.of items*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item ");
        int count = mp.size();
        log.debug("{}",count);
        return count;
    }

    /*retrieve list of item names*/
    @Override
    public List<String> selectNameList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM item");
        List<String> itemNames=new ArrayList<String>();
        for (int j=0;j<mp.size();j++){
            String nm=mp.get(j).get("name").toString();
            itemNames.add(nm);
        }
        log.info("{}",itemNames);
        return itemNames;
    }

    /*update item details(((((((((have to modify)))))*/
    @Override
    public int update(Item item) {

        String sql = "UPDATE item SET description=?, type_id=(SELECT type_id FROM `type` WHERE `name`=?) ,image=?, sub_category_id=(SELECT id FROM sub_category WHERE name=?) WHERE name= ? ";
        int row = jdbcTemplate.update(sql, new Object[]{item.getDescription(), item.getType(), item.getImage(), item.getSubCategoryName(), item.getItemName()});
        log.info("{} Item details changed",row);
        return row;
    }

    /**update size table(prices) according to the updates of item table*/
    @Override
    public int updatePriceList(int id, List<Item> item2){
        int row = 0;
        for (int i = 0; i < item2.size(); i++) {
            String price = item2.get(i).getPrice();
            String size = item2.get(i).getSize();
            String sql= "UPDATE size SET price=? WHERE size=? AND item_id=?";
            row = jdbcTemplate.update(sql, new Object[]{price,size,id});
            log.info("{} prices updated",row);
        }
        return row;

    }

    /**update item table and size table*/
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int updateItem(Item item, List<Item> item2) {
        int j=0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            update(item);
            List<Map<String,Object>> mp=jdbcTemplate.queryForList("SELECT id FROM item WHERE `name`=?",item.getItemName());
            int id2=Integer.parseInt(mp.get(0).get("id").toString());
            updatePriceList(id2, item2);
            transactionManager.commit(stat);
            j=1;
        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

    /**Add new item*/
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int addItem(Item item,List<Item> item2) {
        int j=0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        //int id = item.getItemId();
        try {
            add(item);
            List<Map<String,Object>> mp=jdbcTemplate.queryForList("SELECT id FROM item WHERE `name`=?",item.getItemName());
            int id2=Integer.parseInt(mp.get(0).get("id").toString());
            updateSizeTable(id2, item2);
            transactionManager.commit(stat);
            j=1;
        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

    /**Delete item*/
    @Override
    public int deleteItem(String itemName){
        int j=0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try{
            deleteFromSize(itemName);
            delete(itemName);
            transactionManager.commit(stat);
            j=1;
        }catch (Exception e){
            log.info(e.getMessage());
            transactionManager.rollback(stat);
            log.error("rollbacked");
        }
        return j;
    }

    /**retrieve item details by id*/
    @Override
    public List<Map<String, Object>> retrieveSelectedItemDetails(int id) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.id=?",id);
        log.info("{}",itemDetails);
        return itemDetails;
    }

    @Override
    public List<Map<String, Object>> retrieveSelectedItemDetails(String name) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.name=?",name);
        log.info("{}",itemDetails);
        return itemDetails;
    }

    /**retrieve sizes of a item  by item id*/
    @Override
    public List<Item> retrieveSelectedItemSizes(int id) {
        List<Map<String, Object>> size=jdbcTemplate.queryForList("SELECT size,price FROM size WHERE item_id=?",id);
        List<Item> items=new ArrayList<Item>();
        for (int k=0;k<size.size();k++){
            log.info("{}",size.get(k));
            Item item=new Item();
            item.setSize(size.get(k).get("size").toString());
            item.setPrice(size.get(k).get("price").toString());
            items.add(item);
        }
        log.info("**ITEMS{}",items);
        return items;
    }

    /**retrieve selected data from item and size*/
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public ReturnTypeResolver selectItemAndSize(int id) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        ReturnTypeResolver rv=new ReturnTypeResolver();
        try {
            List<Map<String, Object>> selectedItem=retrieveSelectedItemDetails(id);
            List<Item> selectedSize=retrieveSelectedItemSizes(id);
            transactionManager.commit(stat);
            rv.setSelectedItem(selectedItem);
            rv.setSelectedSize(selectedSize);
            rv.setRow(1);

        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
        }
        log.info("ITEM{}",rv.getSelectedItem());
        log.info("SIZE{}",rv.getSelectedSize());
        log.info("ROW{}",rv.getRow());
        return rv;
    }


    /**get top rated items of a given category*/
    @Override
    public List<Map<String, Object>> getTopRatedItemsOfACategory(String categoryName) {

        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT sum(number_of_stars) AS total_stars, i.name AS item_name, sc.name AS subcategory_name," +
                " c.name AS category_name FROM feedback f, item i, sub_category sc, category c WHERE f.item_id=i.id AND i.sub_category_id=sc.id" +
                " AND sc.category_id=c.id AND c.name=? GROUP BY i.name ORDER BY total_stars desc LIMIT 2", categoryName);
        log.info("{}",mp1);
        return mp1;

    }

    /**select all available category names*/
    @Override
    public List<String> getAllCategories() {
        List<Map<String,Object>> mp=jdbcTemplate.queryForList("SELECT name FROM category");
        List<String> categoryList=new ArrayList<String>();
        for (int j=0;j<mp.size();j++){
            String nm=mp.get(j).get("name").toString();
            categoryList.add(nm);
        }
        log.info("{}",categoryList);
        return categoryList;
    }

    /*retrieve all item details as a view of selected fields*/
    @Override
    public List<Map<String, Object>> viewAllItemDetails() {
       /* int row = jdbcTemplate.update("CREATE VIEW  item_details AS SELECT i.id,i.name AS item_name,c.name AS category_name," +
                "s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i INNER JOIN sub_category s " +
                "ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id INNER JOIN category c ON c.id=s.category_id");
        */
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id");

        log.info("{}",itemDetails);
        return itemDetails;
    }

    /*retrieve top rated items from each category*/
    @Override
    public List<Map<String, Object>> getTopRatedItemsFromAllCategories() {
        List<Map<String, Object>> mp = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> category = jdbcTemplate.queryForList("SELECT name FROM category ");
        log.info(category + "category");
        for (int i = 0; i < category.size(); i++) {
            List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT sum(number_of_stars) AS total_stars, i.name AS item_name, sc.name AS subcategory_name," +
                    " c.name AS category_name FROM feedback f, item i, sub_category sc, category c WHERE f.item_id=i.id AND i.sub_category_id=sc.id" +
                    " AND sc.category_id=c.id AND c.name=? GROUP BY i.name ORDER BY total_stars desc LIMIT 2", category.get(i).get("name"));
            mp.addAll(mp1);
            log.info("{}",mp1);
        }
        log.info("{}",mp);
        return mp;
    }



}