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
import java.sql.Date;
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
        int id = item.getItemId();
        /*MultipartFile img=item.getImageUrl();
        String ims=img.getOriginalFilename();*/
        if (item.getItemName() != null && item.getType() != "" && item.getCreator() != null && item.getSubCategoryName() != null) {
            String sql = "INSERT INTO item" +
                    "(name,description,type_id,image,sub_category_id,created_date,creator) VALUES (?,?,(SELECT type_id FROM type WHERE name=? ),?,(SELECT id FROM sub_category WHERE name=?),CURRENT_DATE ,?)";
            row = jdbcTemplate.update(sql, new Object[]{item.getItemName(), item.getDescription(),
                    item.getType(), item.getImage(), item.getSubCategoryName(), item.getCreator()});
            log.info("{} new item inserted", row);
        } else {
            log.info("values cannot be empty");
        }
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
            log.info("{} size updated", row);
        }
        return row;
    }

    /*check if item is already available*/
    @Override
    public boolean checkAvailability(String itemName) {

        boolean result;
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE  name=?", itemName);
        log.info("{}", mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        log.info("{}", result);
        return result;
    }

    /*delete an item*/
    @Override
    public int delete(String itemName) {
        //item.setItemName(itemName);
        String sql = "DELETE FROM item WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{itemName});
        log.info("{} item deleted", row);
        return row;
    }

    /**
     * delete from size when item deleted
     */
    @Override
    public int deleteFromSize(String itemName) {
        String sql = "DELETE FROM `size` WHERE item_id =(SELECT id FROM item WHERE name=?)";
        int row = jdbcTemplate.update(sql, new Object[]{itemName});
        log.info("{} sizes deleted", row);
        return row;
    }


    /*view all item details*/
    @Override
    public List<Item> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item");
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < mp.size(); i++) {
            Item item = new Item();
            item.setItemId(Integer.parseInt(mp.get(i).get("id").toString()));
            item.setItemName(mp.get(i).get("name").toString());
            item.setDescription(mp.get(i).get("description").toString());
            item.setTypeId(Integer.parseInt(mp.get(i).get("type_id").toString()));
            item.setImage(mp.get(i).get("image").toString());
            item.setSubCategoryId(Integer.parseInt(mp.get(i).get("sub_category_id").toString()));
            item.setCreatedDate(Date.valueOf(mp.get(i).get("created_date").toString()));
            item.setCreator(mp.get(i).get("creator").toString());
            items.add(item);
        }
        log.info("{}", items);
        return items;
    }

    /*retrieve no.of items*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM item ");
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

    /*retrieve list of item names*/
    @Override
    public List<String> selectNameList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM item");
        List<String> itemNames = new ArrayList<String>();
        for (int j = 0; j < mp.size(); j++) {
            String nm = mp.get(j).get("name").toString();
            itemNames.add(nm);
        }
        log.info("{}", itemNames);
        return itemNames;
    }

    /*update item details(((((((((have to modify)))))*/
    @Override
    public int update(Item item) {

        String sql = "UPDATE item SET description=?, type_id=(SELECT type_id FROM `type` WHERE `name`=?) ,image=?, sub_category_id=(SELECT id FROM sub_category WHERE name=?) WHERE name= ? ";
        int row = jdbcTemplate.update(sql, new Object[]{item.getDescription(), item.getType(), item.getImage(), item.getSubCategoryName(), item.getItemName()});
        log.info("{} Item details changed", row);
        return row;
    }

    /**
     * update size table(prices) according to the updates of item table
     */
    @Override
    public int updatePriceList(int id, List<Item> item2) {
        int row = 0;
        for (int i = 0; i < item2.size(); i++) {
            String price = item2.get(i).getPrice();
            String size = item2.get(i).getSize();
            String sql = "UPDATE size SET price=? WHERE size=? AND item_id=?";
            row = jdbcTemplate.update(sql, new Object[]{price, size, id});
            log.info("{} prices updated", row);
        }
        return row;

    }

    /**
     * update item table and size table
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateItem(Item item, List<Item> item2) {
        int j = 0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            update(item);
            List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM item WHERE `name`=?", item.getItemName());
            int id2 = Integer.parseInt(mp.get(0).get("id").toString());
            updatePriceList(id2, item2);
            transactionManager.commit(stat);
            j = 1;
        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

    /**
     * Add new item
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addItem(Item item, List<Item> item2) {
        int j = 0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        //int id = item.getItemId();
        try {
            add(item);
            List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM item WHERE `name`=?", item.getItemName());
            int id2 = Integer.parseInt(mp.get(0).get("id").toString());
            updateSizeTable(id2, item2);
            transactionManager.commit(stat);
            j = 1;
        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

    /**
     * Delete item
     */
    @Override
    public int deleteItem(String itemName) {
        int j = 0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            deleteFromSize(itemName);
            delete(itemName);
            transactionManager.commit(stat);
            j = 1;
        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
            log.error("rollbacked");
        }
        return j;
    }

    /**
     * retrieve item details by id
     */
    @Override
    public List<Map<String, Object>> retrieveSelectedItemDetails(int id) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.id=? ORDER BY i.id DESC ", id);
        log.info("{}", itemDetails);
        return itemDetails;
    }

    @Override
    public List<Map<String, Object>> retrieveSelectedItemDetails(String name) {
        String key = "%" + name + "%";
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.name LIKE ? ORDER BY i.id DESC ", key);
        log.info("{}", itemDetails);
        return itemDetails;
    }

    /**
     * retrieve sizes of a item  by item id
     */
    @Override
    public List<Item> retrieveSelectedItemSizes(int id) {
        List<Map<String, Object>> size = jdbcTemplate.queryForList("SELECT size,price FROM size WHERE item_id=?", id);
        List<Item> items = new ArrayList<Item>();
        for (int k = 0; k < size.size(); k++) {
            log.info("{}", size.get(k));
            Item item = new Item();
            item.setSize(size.get(k).get("size").toString());
            item.setPrice(size.get(k).get("price").toString());
            items.add(item);
        }
        log.info("**ITEMS{}", items);
        return items;
    }

    /*search item details and size price for a given category and a search key*/

    /**
     * Don't change this method
     */
    @Override
    public List<List<Map<String, Object>>> retrieveItemDetailsForSearch(String itemkey, String category) {
        String key = "%" + itemkey + "%";
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.name LIKE ? AND c.name=?", key, category);
        List<List<Map<String, Object>>> listPrice = new ArrayList<List<Map<String, Object>>>();
        for (int k = 0; k < itemDetails.size(); k++) {
            List<Map<String, Object>> size = jdbcTemplate.queryForList("SELECT size,price FROM size WHERE item_id=?", itemDetails.get(k).get("id"));
            log.info("{}", size);
            List<Map<String, Object>> sizeList = new ArrayList<Map<String, Object>>();

            sizeList.add(itemDetails.get(k));
            listPrice.add(sizeList);
            listPrice.add(size);
        }


        log.info("{}=listPrice", listPrice);
        return listPrice;
    }

    @Override
    public List<Map<String, Object>> retrieveItemDetailsForSearchByCategory(String itemkey, String category, int limit, int page) {
        String key = "%" + itemkey + "%";
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.name LIKE ? AND c.name=? LIMIT ? OFFSET ?", key, category, limit, page);
        return itemDetails;
    }

    @Override
    public List<Map<String, Object>> retrieveItemDetailsForSearchBySubCategory(String itemkey, String subCategory, int limit, int page) {
        String key = "%" + itemkey + "%";
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.name LIKE ? AND s.name=? LIMIT ? OFFSET ?", key, subCategory, limit, page);
        return itemDetails;
    }

    @Override
    public List<Map<String, Object>> retrieveItemDetailsByCategory(String category, int limit, int page) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE c.name=? LIMIT ? OFFSET ?", category, limit, page);
        return itemDetails;
    }

    public int countItemDetailsByCategory(String category) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE c.name=?", category);
        return itemDetails.size();

    }
    @Override
    public List<Map<String, Object>> retrieveItemDetailsBySubCategory(String subCategory, int limit, int page) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE s.name=? LIMIT ? OFFSET ?", subCategory, limit, page);
        return itemDetails;
    }

    @Override
    public int countItemDetailsBySubCategory(String subCategory) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT COUNT(*) FROM item WHERE sub_category_id=(SELECT id FROM sub_category WHERE name=?)", subCategory);
        return itemDetails.size();

    }


    @Override
    public List<String> searchItemNameByCategory(String itemkey, String category) {
        String key = "%" + itemkey + "%";
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.name AS item_name FROM item i INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN category c ON c.id=s.category_id WHERE i.name LIKE ? AND c.name=? ", key, category);
        List<String> nameList = new ArrayList<String>();
        for (int k = 0; k < itemDetails.size(); k++) {
            String name = itemDetails.get(k).get("item_name").toString();
            nameList.add(name);
        }
        System.out.println(nameList);
        return nameList;
    }

    @Override
    public int countSearchItemNameByCategory(String itemkey, String category) {
        List<String> items = searchItemNameByCategory(itemkey, category);
        return items.size();

    }

    @Override
    public List<String> searchItemNameBySubCategory(String itemkey, String subCategory) {
        String key = "%" + itemkey + "%";
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.name AS item_name FROM item i INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN category c ON c.id=s.category_id WHERE i.name LIKE ? AND s.name=? ", key, subCategory);
        List<String> nameList = new ArrayList<String>();
        for (int k = 0; k < itemDetails.size(); k++) {
            String name = itemDetails.get(k).get("item_name").toString();
            nameList.add(name);
        }
        System.out.println(nameList);
        return nameList;
    }

    @Override
    public int countSearchItemNameBySubCategory(String itemkey, String category) {
        List<String> items = searchItemNameBySubCategory(itemkey, category);
        return items.size();
    }


    /**
     * retrieve sizes of a item  by item name
     */
    @Override
    public List<Item> retrieveSelectedItemSizes(String name) {
        List<Map<String, Object>> size = jdbcTemplate.queryForList("SELECT size,price FROM size WHERE item_id=(SELECT id FROM item WHERE name=?)", name);
        log.info("{}", size);
        List<Item> items = new ArrayList<Item>();
        for (int k = 0; k < size.size(); k++) {
            log.info("{}", size.get(k));
            Item item = new Item();
            item.setSize(size.get(k).get("size").toString());
            item.setPrice(size.get(k).get("price").toString());
            items.add(item);
        }
        log.info("**ITEMS{}", items);
        return items;
    }

    /**
     * retrieve selected data from item and size
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ReturnTypeResolver selectItemAndSize(int id) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        ReturnTypeResolver rv = new ReturnTypeResolver();
        try {
            List<Map<String, Object>> selectedItem = retrieveSelectedItemDetails(id);
            List<Item> selectedSize = retrieveSelectedItemSizes(id);
            transactionManager.commit(stat);
            rv.setSelectedItem(selectedItem);
            rv.setSelectedSize(selectedSize);
            rv.setRow(1);

        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
        }
        log.info("ITEM{}", rv.getSelectedItem());
        log.info("SIZE{}", rv.getSelectedSize());
        log.info("ROW{}", rv.getRow());
        return rv;
    }


    /**
     * get top rated items of a given category
     */
    @Override
    public List<Map<String, Object>> getTopRatedItemsOfACategory(String categoryName) {

        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT sum(number_of_stars) AS total_stars, i.name AS item_name,i.created_date,i.creator, sc.name AS subcategory_name," +
                " c.name AS category_name FROM feedback f, item i, sub_category sc, category c WHERE f.item_id=i.id AND i.sub_category_id=sc.id" +
                " AND sc.category_id=c.id AND c.name=? GROUP BY i.name ORDER BY total_stars desc LIMIT 2", categoryName);
        log.info("{}", mp1);
        return mp1;

    }

    /**
     * select all available category names
     */
    @Override
    public List<String> getAllCategories() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM category");
        List<String> categoryList = new ArrayList<String>();
        for (int j = 0; j < mp.size(); j++) {
            String nm = mp.get(j).get("name").toString();
            categoryList.add(nm);
        }
        log.info("{}", categoryList);
        return categoryList;
    }

    /*retrieve all item details as a view of selected fields*/
    @Override
    public List<Map<String, Object>> viewAllItemDetails() {
       /* int row = jdbcTemplate.update("CREATE VIEW  item_details AS SELECT i.id,i.name AS item_name,c.name AS category_name," +
                "s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i INNER JOIN sub_category s " +
                "ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id INNER JOIN category c ON c.id=s.category_id");
        */
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id ORDER BY i.id DESC ");

        log.info("{}", itemDetails);
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
            log.info("{}", mp1);
        }
        log.info("{}", mp);
        return mp;
    }

    /*select name and price of toppings*/
    @Override
    public List<Map<String, Object>> getToppings() {

        List<String> catid = new ArrayList<String>();
        List<Map<String, Object>> another = new ArrayList<Map<String, Object>>();

        List<Map<String, Object>> cat = jdbcTemplate.queryForList("SELECT id FROM sub_category WHERE category_id= (SELECT id FROM category WHERE name ='Topping')");
        if (cat.size() > 0) {
            for (int i = 0; i < cat.size(); i++) {
                String id = cat.get(i).get("id").toString();
                catid.add(id);
            }

            for (int j = 0; j < catid.size(); j++) {
                List<Map<String, Object>> sub = jdbcTemplate.queryForList("SELECT i.name,si.price FROM item i,size si WHERE i.id=si.item_id" +
                        " AND i.sub_category_id=? ", catid.get(j));
                another.addAll(sub);
            }
        } else {
            log.info("No sub-categories found");
        }

        log.info("result" + another);
        return another;
    }

    @Override
    public int paginate(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item LIMIT ? OFFSET ?", limit, page);
        int count = mp.size();
        log.info("{}", count);
        return count;

    }

    @Override
    public List<Map<String, Object>> paginateSelectedItemDetails(String name, int limit, int page) {
        String key = "%" + name + "%";
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id WHERE i.name LIKE ? ORDER BY i.id DESC LIMIT ? OFFSET ?", key, limit, page);
        log.info("{}", itemDetails);
        return itemDetails;
    }

    @Override
    public List<Map<String, Object>> viewAllItemDetails(int limit, int page) {
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT i.id,i.name AS item_name,i.created_date,i.creator,c.name " +
                "AS category_name, s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i" +
                " INNER JOIN sub_category s ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id " +
                "INNER JOIN category c ON c.id=s.category_id  ORDER BY i.id DESC LIMIT ? OFFSET ?", limit, page);

        log.info("{}", itemDetails);
        return itemDetails;
    }

    @Override
    public int countSearchKey(String itemKey) {
        String key = "%" + itemKey + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) FROM item WHERE name LIKE ?", key);
        return Integer.parseInt(mp.get(0).get("COUNT(*)").toString());
    }



}