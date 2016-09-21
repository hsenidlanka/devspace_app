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
    Item item=new Item();
    private DataSource dataSource;
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

    @Override
    public int add(Item item,List<Item> item2) {
        int row = 0;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String itm_nm = item.getItemName();
        boolean availability = checkAvailability(itm_nm);

        if (availability == false) {
            String sql = "INSERT INTO item" +
                    "(name,description,type,image,sub_category_id) VALUES (?,?,?,?,(SELECT id FROM sub_category WHERE name=?))";
            row = jdbcTemplate.update(sql, new Object[]{item.getItemName(),item.getDescription(),
                    item.getType(),item.getImage(),item.getSubCategoryName()});
            transactionManager.commit(stat);
            log.info(row + "new item inserted");
            updateSizeTable(itm_nm,item2);
        } else
            log.info(row + "item already available");

        return row;
    }
    public int updateSizeTable(String name,List<Item> item2){
        int row=0;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        for(int i=0;i<item2.size();i++) {
            String sql = "INSERT INTO size" +
                    "(size,price,item_id) VALUES (?,?,(SELECT id FROM item WHERE name=?))";
            row = jdbcTemplate.update(sql, new Object[]{item2.get(i),item2.get(i),name});
            transactionManager.commit(stat);
            log.info(row + "size updated");
        }
        return row;
    }

    @Override
    public boolean checkAvailability(String itemName) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE  name=?",itemName);
        log.info(mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        transactionManager.commit(stat);
        log.info(result);
        return result;
    }

    @Override
    public int delete(String itemName) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        item.setItemName(itemName);

        String sql = "DELETE FROM item WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{item.getItemName()});
        transactionManager.commit(stat);
        log.info(row + " item deleted");
        return row;
    }

    @Override
    public List<Map<String, Object>> view() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item");
        log.info(mp);
        return mp;
    }

    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item ");
        int count = mp.size();
        log.info(count);
        return count;
    }

    @Override
    public List<Map<String, Object>> viewList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM item");
        log.info(mp);
        return mp;
    }

    @Override
    public int update(Item item) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "UPDATE item SET name=? price=? description=? size=? type=? image=? sub_category_id=? WHERE id = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{item.getItemName(), item.getPrice(),item.getDescription(),item.getSize(),item.getType(),item.getImage(),item.getSubCategoryName(),item.getItemId()});
        transactionManager.rollback(stat);
        log.info(row + "Item details changed");
        return  row;
    }


}
