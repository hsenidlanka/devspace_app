package hsl.devspace.app.corelogic.repository.category;

import hsl.devspace.app.corelogic.domain.Category;
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
 * Created by hsenid on 9/15/16.
 */
public class CategoryRepositoryImpl  implements CategoryRepository {
    Category category = new Category();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(CategoryRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    @Override
    public int add(Category category) {
        int row = 0;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String cat_nm = category.getCategoryName();
        boolean availability = checkAvailability(cat_nm);
        if (availability == false) {
            String sql = "INSERT INTO category " +
                    "(name,description,creator) VALUES (?,?,?)";

            row = jdbcTemplate.update(sql, new Object[]{category.getCategoryName(), category.getDescription(), category.getCreator()});
            transactionManager.commit(stat);

            log.info(row + "new category inserted");
        } else
            log.info(row + "category already available");


        return row;
    }

    @Override
    public boolean checkAvailability(String categoryName) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE  name=?", categoryName);
        log.info(mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        transactionManager.rollback(stat);
        log.info(result);
        return result;

    }

    @Override
    public int delete(String categoryName){
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        category.setCategoryName(categoryName);

        String sql = "DELETE FROM category WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{category.getCategoryName()});
        transactionManager.commit(stat);
        log.info(row + " category deleted");
        return row;
    }

    @Override
    public List<Map<String, Object>> view() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category");
        log.info(mp);
        return mp;
    }

    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category ");
        int count = mp.size();
        log.info(count);
        return count;
    }

    @Override
    public int update(String categoryName,String description) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "UPDATE category SET name=? description = ? WHERE name = ? ";

        int count = jdbcTemplate.update(sql, new Object[]{category.getCategoryName(), category.getSubCategoryName(), category.getCategoryName()}, Integer.class);
        transactionManager.commit(stat);
        log.info(count);
        return count;
    }

    @Override
    public List<Map<String, Object>> viewList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM category");
        log.info(mp);
        return mp;
    }

}
