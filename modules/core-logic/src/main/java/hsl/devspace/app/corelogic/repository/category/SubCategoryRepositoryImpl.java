package hsl.devspace.app.corelogic.repository.category;

import hsl.devspace.app.corelogic.domain.Category;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/15/16.
 */
public class SubCategoryRepositoryImpl implements CategoryRepository {
    Category category = new Category();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(SubCategoryRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }


    @Override
    public int add(Category category) {
        int row = 0;

        String cat_nm = category.getSubCategoryName();
        boolean availability = checkAvailability(cat_nm);

        if (availability == false) {
            String sql = "INSERT INTO sub_category " +
                    "(name,description,creator,category_id) VALUES (?,?,?,(SELECT id FROM category WHERE name=?))";
            row = jdbcTemplate.update(sql, new Object[]{category.getSubCategoryName(), category.getDescription(), category.getCreator(), category.getCategoryName()});
            log.info(row + "new sub category inserted");
        } else
            log.info(row + "sub category already available");

        return row;
    }

    @Override
    public boolean checkAvailability(String subCategoryName) {

        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE  name=?",subCategoryName);
        log.info(mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        log.info(result);
        return result;
    }

    @Override
    public int delete(String subCategoryName) {

        category.setSubCategoryName(subCategoryName);

        String sql = "DELETE FROM sub_category WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{category.getSubCategoryName()});
        log.info(row + " sub category deleted");
        return row;
    }

    @Override
    public List<Map<String, Object>> view() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category");
        log.info(mp);
        return mp;
    }

    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category ");
        int count = mp.size();
        log.info(count);
        return count;
    }

    @Override
    public int update(String subCategoryName,String description) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> viewList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM sub_category");
        log.info(mp);
        return mp;
    }
    public List<Map<String, Object>>  retrieveSubcatogories(String category){
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM sub_category WHERE category_id=(SELECT id FROM category WHERE NAME =?)",category);
        log.info(mp);
        return mp;
    }

    @Override
    @Transactional
    public void createCategory(Category cat) {
        add(cat);

    }

    @Override
    public void createCategory1(Category cat) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            add(cat);
            transactionManager.commit(stat);
        }
        catch (Exception e){
            transactionManager.rollback(stat);
        }

    }

    @Override
    public List<Map<String, Object>> retrieveCategoryTypes(String categoryName) {
        return null;
    }
}
