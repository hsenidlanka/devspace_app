package hsl.devspace.app.corelogic.repository.item;

import hsl.devspace.app.corelogic.domain.Item;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
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
        String itmNm = item.getItemName();
        int id = item.getItemId();
        boolean availability = checkAvailability(itmNm);

        if (availability == false) {
            String sql = "INSERT INTO item" +
                    "(name,description,type_id,image,sub_category_id) VALUES (?,?,(SELECT type_id FROM type WHERE name=? ),?,(SELECT id FROM sub_category WHERE name=?))";
            row = jdbcTemplate.update(sql, new Object[]{item.getItemName(), item.getDescription(),
                    item.getType(), item.getImage(), item.getSubCategoryName()});
            log.info(row + "new item inserted");
        } else
            log.info(row + "item already available");

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
    public List<Map<String, Object>> view() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item");
        log.info(mp);
        return mp;
    }

    /*retrieve no.of items*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item ");
        int count = mp.size();
        log.info(count);
        return count;
    }

    /*retrieve list of item names*/
    @Override
    public List<Map<String, Object>> viewList() {
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

    /*retrieve top rated items from each category*/
    @Override
    public void getTopRatedItemsFromAllCategories() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM category WHERE name='Desserts'");
        log.info(mp);
       // for (int i = 0; i < mp.size(); i++) {
            List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT id FROM sub_category WHERE category_id=?", mp.get(0).get("id"));
            log.info(mp1+"mp1");
            for (int j = 0; j < mp1.size(); j++) {
                List<Map<String, Object>> mp2 = jdbcTemplate.queryForList("SELECT id FROM item WHERE sub_category_id=?", mp1.get(j).get("id"));
                log.info(mp2+"mp2");
                for (int k = 0; k < mp2.size()-1 ; k++) {
                    List<Map<String, Object>> mp3 = jdbcTemplate.queryForList("SELECT AVG (number_of_stars) FROM feedback WHERE item_id=? ", mp2.get(j).get("id"));
                    log.info("mp3" + mp3);
                    if (Double.parseDouble(mp3.get(0).get("AVG (number_of_stars)").toString()) >=2) {
                        List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT name FROM item WHERE id=(SELECT item_id FROM feedback WHERE number_of_stars=?) ", Double.parseDouble(mp3.get(j).get("AVG (number_of_stars)").toString()));
                        log.info("mp4" + mp4);

                    }
                }

            }

        //}


    }

    @Override
    public List<Map<String, Object>> getTopRatedItems() {
        List<Map<String, Object>> mp3 = jdbcTemplate.queryForList("SELECT * FROM item WHERE id=(SELECT item_id FROM feedback WHERE number_of_stars=5)");
        log.info(mp3);
        return mp3;

    }

    public void getTopRatedItemsFromAllCategoriesTest() {
        List<Map<String, Object>> mp = null;
        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT sum(number_of_stars) AS total_stars, i.name, sc.name," +
                " c.name FROM feedback f, item i, sub_category sc, category c WHERE f.item_id=i.id AND i.sub_category_id=sc.id" +
                " AND sc.category_id=c.id AND c.name='pizza' GROUP BY i.name ORDER BY total_stars desc LIMIT 2");
       log.info(mp1);

    }
}
