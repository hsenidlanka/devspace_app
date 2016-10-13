package hsl.devspace.app.corelogic.repository.category;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.domain.Item;
import org.slf4j.LoggerFactory;
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
public class SubCategoryRepositoryImpl implements CategoryRepository {
    Category category = new Category();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
   // private static org.apache.log4j.Logger log = Logger.getLogger(SubCategoryRepositoryImpl.class);
   org.slf4j.Logger log = LoggerFactory.getLogger(SubCategoryRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

/*Add new  subcategory*/
    @Override
    public int add(Category category) {
        int row = 0;

        String catNm=category.getSubCategoryName();
        boolean availability = checkAvailability(catNm);

        if (availability == false) {
            String sql = "INSERT INTO sub_category " +
                    "(name,description,creator,category_id) VALUES (?,?,?,(SELECT id FROM category WHERE name=?))";
            row = jdbcTemplate.update(sql, new Object[]{category.getSubCategoryName(), category.getDescription(), category.getCreator(), category.getCategoryName()});
            log.info("{} new sub category inserted",row);
        } else
            log.info("{} sub category already available",row);

        return row;
    }

    /*check if subcategory is already available*/
    @Override
    public boolean checkAvailability(String subCategoryName) {

        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE  name=?", subCategoryName);
        log.info("{}",mp);

        if (mp.size() != 0) {
            result = true;
        } else
            result = false;
        log.info("{}",result);
        return result;
    }

    /*delete subcategory*/
    @Override
    public int delete(String subCategoryName) {

        category.setSubCategoryName(subCategoryName);

        String sql = "DELETE FROM sub_category WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{category.getSubCategoryName()});
        log.info("{} sub category deleted",row);
        return row;
    }

    /*view all details of subcategory*/
    @Override
    public List<Category> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category");
        List<Category> subCategories=new ArrayList<Category>();

        for (int i=0;i<mp.size();i++){
            //for(int j=0;j<mp.get(i).size();j++) {
                Category category = new Category();
                category.setSubCategoryId(Integer.parseInt(mp.get(i).get("id").toString()));
                category.setSubCategoryName(mp.get(i).get("name").toString());
                category.setDescription(mp.get(i).get("description").toString());
                category.setCreator(mp.get(i).get("creator").toString());
                category.setCategory_id(Integer.parseInt(mp.get(i).get("category_id").toString()));

                subCategories.add(category);
           // }

        }
        log.info("{}",subCategories);
        return subCategories;
    }

    /*retrieve total no.of subcategories*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category ");
        int count = mp.size();
        log.info("{}",count);
        return count;
    }

    /*update subcategory details*/
    @Override
    public int update(String subCategoryName,String description) {
        return 0;
    }

    /*retrieve all subcategory names*/
    @Override
    public List<Category> selectNameAndDescription() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name,description FROM sub_category");
        List<Category> subCategories=new ArrayList<Category>();

        for (int i=0;i<mp.size();i++){
            Category category = new Category();
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setDescription(mp.get(i).get("description").toString());
            subCategories.add(category);


        }
        log.info("{}",subCategories);
        return subCategories;
    }

    /*retrieve subcategory names list in a specific category*/
    @Override
    public List<String>  retrieveSubcatogories(String category){
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM sub_category WHERE category_id=(SELECT id FROM category WHERE NAME =?)",category);
        List<String> subCatName=new ArrayList<String>();
        for (int i=0;i<mp.size();i++){
            String nm=mp.get(i).get("name").toString();
            subCatName.add(nm);
        }
        log.info("{}",subCatName);
        return subCatName;
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

    @Override
    public List<String> retrieveCategoryTypes(String categoryName) {
        return null;

    }

    /*retrieve all items in a specific subcategory*/
    @Override
    public List<Item> loadMenuItems(String subCatName) {
        List<Map<String, Object>> another = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=(SELECT id FROM sub_category WHERE name=?)",subCatName);
        List<Item> itemList=new ArrayList<Item>();

        log.info("{}",another);
        for (int j=0;j<another.size();j++){
            Item item=new Item();
            item.setItemId(Integer.parseInt(another.get(j).get("id").toString()));
            item.setItemName(another.get(j).get("name").toString());
            item.setDescription(another.get(j).get("description").toString());
            item.setType(another.get(j).get("type").toString());
            item.setImage(another.get(j).get("image").toString());
            item.setSubCategoryId(Integer.parseInt(another.get(j).get("sub_category_id").toString()));
            itemList.add(item);


        }
        log.info("{}",itemList);
        return itemList;
    }

    @Override
    public List<String> viewSubCategories(String catName) {
        return null;
    }

    @Override
    public List<String> selectCategoryNames() {
        return null;
    }
}
