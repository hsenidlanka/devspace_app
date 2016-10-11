package hsl.devspace.app.corelogic.repository.item;

import hsl.devspace.app.corelogic.domain.Item;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
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
    private static org.apache.log4j.Logger log = Logger.getLogger(ItemRepositoryImpl.class);

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
            log.info(row + "new item inserted");
       // } else
            //log.info(row + "item already available");

        return id;
    }

    /*insert sizes of a specific item*/
    @Override
    public int updateSizeTable(int id, List<Item> item2) {
        int row = 0;
        for (int i = 0; i < item2.size(); i++) {
            double price = item2.get(i).getPrice();
            String size = item2.get(i).getSize();
            String sql = "INSERT INTO size" +
                    "(size,price,item_id) VALUES (?,?,?)";
            row = jdbcTemplate.update(sql, new Object[]{size, price, id});
            log.info(row + "size updated");
        }
        return row;
    }

    /*check if item is already available*/
    @Override
    public boolean checkAvailability(String itemName) {

        boolean result;
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE  name=?", itemName);
        log.info(mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        log.info(result);
        return result;
    }

    /*delete an item*/
    @Override
    public int delete(String itemName) {

        item.setItemName(itemName);
        String sql = "DELETE FROM item WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{item.getItemName()});
        log.info(row + " item deleted");
        return row;
    }

    /*view all item details*/
    @Override
    public List<Map<String, Object>> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item");
        log.info(mp);
        return mp;
    }

    /*retrieve no.of items*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item ");
        int count = mp.size();
        log.debug(count);
        return count;
    }

    /*retrieve list of item names*/
    @Override
    public List<Map<String, Object>> selectNameList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM item");
        log.info(mp);
        return mp;
    }

    /*update item details(((((((((have to modify)))))*/
    @Override
    public int update(Item item) {

        String sql = "UPDATE item SET name=? price=? description=? size=? type=? image=? sub_category_id=? WHERE id = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{item.getItemName(), item.getPrice(), item.getDescription(), item.getSize(), item.getType(), item.getImage(), item.getSubCategoryName(), item.getItemId()});
        log.info(row + "Item details changed");
        return row;
    }

    /*Add new item*/
    @Override
    public void addItem(Item item, List<Item> item2) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        int id = item.getItemId();
        try {
            add(item);
            updateSizeTable(id, item2);
            transactionManager.commit(stat);
        } catch (Exception e) {
            transactionManager.rollback(stat);
        }
    }

    /*get top rated items of a given category*/
    @Override
    public List<Map<String, Object>> getTopRatedItemsOfACategory(String categoryName) {

        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT sum(number_of_stars) AS total_stars, i.name AS item_name, sc.name AS subcategory_name," +
                " c.name AS category_name FROM feedback f, item i, sub_category sc, category c WHERE f.item_id=i.id AND i.sub_category_id=sc.id" +
                " AND sc.category_id=c.id AND c.name=? GROUP BY i.name ORDER BY total_stars desc LIMIT 2", categoryName);
        log.info(mp1);
        return mp1;

    }

    @Override
    public List<Map<String, Object>> getAllCategories() {
        List<Map<String,Object>> mp=jdbcTemplate.queryForList("SELECT name FROM category");
        log.info(mp);
        return mp;
    }

    /*retrieve all item details as a view of selected fields*/
    @Override
    public List<Map<String, Object>> viewAllItemDetails() {
        int row = jdbcTemplate.update("CREATE VIEW  item_details AS SELECT i.id,i.name AS item_name,c.name AS category_name," +
                "s.name AS sub_category_name,t.name AS type,i.description,i.image FROM item i INNER JOIN sub_category s " +
                "ON i.sub_category_id=s.id INNER JOIN type t ON i.type_id=t.type_id INNER JOIN category c ON c.id=s.category_id");
        List<Map<String, Object>> itemDetails = jdbcTemplate.queryForList("SELECT * FROM item_details ");
        log.info(itemDetails);
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
            log.info(mp1);
        }
        log.info(mp);
        return mp;
    }


    }


