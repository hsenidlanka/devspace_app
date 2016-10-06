package hsl.devspace.app.corelogic.repository.category;

import hsl.devspace.app.corelogic.domain.Category;
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
 * Created by hsenid on 9/15/16.
 */
public class CategoryRepositoryImpl  implements CategoryRepository {
    Category category = new Category();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(CategoryRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
       this.transactionManager = transactionManager;

    }

    /*Add new category*/
    @Override
    public int add(Category category) {
        int row = 0;
        String catNm = category.getCategoryName();
        boolean availability = checkAvailability(catNm);
        if (availability == false) {
            String sql = "INSERT INTO category " +
                    "(name,description,creator) VALUES (?,?,?)";

            row = jdbcTemplate.update(sql, new Object[]{category.getCategoryName(), category.getDescription(), category.getCreator()});

            log.info(row + "new category inserted");
        } else
            log.info(row + "category already available");


        return row;
    }

    /*check if category is already available*/
    @Override
    public boolean checkAvailability(String categoryName) {
        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE  name=?", categoryName);
        log.info(mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        log.info(result +"availability checking");
        return result;

    }

    /*Delete category*/
    @Override
    public int delete(String categoryName){
        category.setCategoryName(categoryName);

        String sql = "DELETE FROM category WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{category.getCategoryName()});
        log.info(row + " category deleted");
        return row;
    }

    /*view all category details */
    @Override
    public List<Map<String, Object>> view() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category");
        log.info(mp);
        return mp;
    }

    /*retrieve total no.of categories*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category ");
        int count = mp.size();
        log.info(count);
        return count;
    }

    /*update category details-name,description*/
    @Override
    public int update(String categoryName,String description) {

        String sql = "UPDATE category SET name=? description = ? WHERE name = ? ";

        int count = jdbcTemplate.update(sql, new Object[]{category.getCategoryName(), category.getSubCategoryName(), category.getCategoryName()}, Integer.class);
        log.info(count);
        return count;
    }

    /*view name and description of all categories*/
    @Override
    public List<Map<String, Object>> viewList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name,description FROM category");
        log.info(mp);
        return mp;
    }

    /*retrieve types of a specific category*/
    public List<Map<String, Object>> retrieveCategoryTypes(String categoryName){

        List<Map<String, Object>> mp1=jdbcTemplate.queryForList("SELECT id FROM category WHERE name=?", categoryName);
        List<Map<String, Object>> mp2=jdbcTemplate.queryForList("SELECT type_id FROM category_type WHERE category_id=?",mp1.get(0).get("id"));
        List<Map<String, Object>> mp=null;
        List<Map<String,Object>> another=new ArrayList<Map<String, Object>>();

        int i=0;
        while ( i<mp2.size()){
           mp =  jdbcTemplate.queryForList("SELECT name FROM type WHERE type_id=?", mp2.get(i).get("type_id"));
            another.addAll(mp);
            log.info(mp);
            i++;
        }
        log.info("another"+another);
        return another;

        /*List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM type WHERE type_id=" +"(SELECT type_id FROM category_type WHERE category_id=" + "(SELECT id FROM category WHERE name=?))",categoryName);
        log.info(mp);*/

    }

    /*retrieve all items in a specific category*/
    @Override
    public List<Map<String, Object>> loadMenuItems(String catName) {
       // List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=(SELECT id FROM sub_category WHERE category_id=(SELECT id FROM category WHERE name=?))",catName);
        List<Map<String, Object>> mp1=jdbcTemplate.queryForList("SELECT id FROM category WHERE name=?", catName);
        List<Map<String, Object>> mp=null;
        List<Map<String,Object>> another=new ArrayList<Map<String, Object>>();
        if(mp1.size()>0){
            List<Map<String, Object>> mp2=jdbcTemplate.queryForList("SELECT id FROM sub_category WHERE category_id=?",mp1.get(0).get("id"));
            if(mp2.size()>0){
                int i=0;
                while ( i< mp2.size()){
                    mp =  jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=?", mp2.get(i).get("id"));
                    another.addAll(mp);
                    log.info(mp);
                    i++;
                }
            }
            else {
                log.info("no valid sub categories found");
            }
        }
        else{
            log.info("not a valid category name");
        }

        log.info("mp"+mp);
        log.info("another"+another);
        return another;
    }

/*view all sub categories of a specific category*/
    @Override
    public List<Map<String, Object>> viewSubCategories(String catName) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM category WHERE category_id=(SELECT id FROM category WHERE NAME =?)",catName);
        log.info(mp);
        return mp;
    }

    /*retrieve list of category names*/
    @Override
    public List<Map<String, Object>> viewCategoryList() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM category");
        log.info(mp);
        return mp;
    }

    @Override
    public List<Map<String, Object>> retrieveSubcatogories(String category) {
        return null;
    }

    /*Add new category*/
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


}
