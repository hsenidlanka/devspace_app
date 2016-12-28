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
import java.sql.Date;
import java.sql.Timestamp;
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
        if (category.getSubCategoryName() != null && category.getSubcatDescription() != null && category.getCreator() != null && category.getCategoryName() != null) {
            String catNm = category.getSubCategoryName();
            System.out.println(catNm);
            boolean availability = checkAvailability(catNm);

            if (availability == false) {
                String sql = "INSERT INTO sub_category " +
                        "(name,description,creator,created_date,category_id) VALUES (?,?,?,NOW(),(SELECT id FROM category WHERE name=?))";
                row = jdbcTemplate.update(sql, new Object[]{category.getSubCategoryName(), category.getSubcatDescription(),
                        category.getCreator(), category.getCategoryName()});
                log.info("{} new sub category inserted", row);
            } else {
                log.info("{} sub category already available", row);

            }
        } else
            log.info("value cannot be null");

        return row;
    }

    /*check if subcategory is already available*/
    @Override
    public boolean checkAvailability(String subCategoryName) {

        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE  name=?", subCategoryName);
        log.info("{}", mp);

        if (mp.size() != 0) {
            result = true;
        } else
            result = false;
        log.info("{}", result);
        return result;
    }

    /*delete subcategory*/
    @Override
    public int delete(String subCategoryName) {

        category.setSubCategoryName(subCategoryName);

        String sql = "DELETE FROM sub_category WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{category.getSubCategoryName()});
        log.info("{} sub category deleted", row);
        return row;
    }

    /*view all details of subcategory*/
    @Override
    public List<Category> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category ORDER BY created_date DESC ");
        List<Category> subCategories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setSubCategoryId(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setSubcatDescription(mp.get(i).get("description").toString());
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(mp.get(i).get("created_date").toString()));
            category.setCategory_id(Integer.parseInt(mp.get(i).get("category_id").toString()));

            subCategories.add(category);
            // }

        }
        log.info("{}", subCategories);
        return subCategories;
    }

    /*view all details of subcategories for a given category name*/
    @Override
    public List<Category> viewSubCategoriesforCategory(String catName, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE category_id=(SELECT id FROM category WHERE NAME =? LIMIT ? OFFSET ?) ORDER BY created_date DESC", catName, limit, page);
        List<Category> subCategories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            Category category = new Category();
            category.setSubCategoryId(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setSubcatDescription(mp.get(i).get("description").toString());
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Date.valueOf(mp.get(i).get("created_date").toString()));
            category.setCategory_id(Integer.parseInt(mp.get(i).get("category_id").toString()));

            subCategories.add(category);
        }
        log.info("{}", subCategories);
        return subCategories;
    }

    @Override
    public int countSubCategoriesForCategory(String catName) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS count FROM sub_category WHERE category_id=(SELECT id FROM category WHERE NAME =?)", catName);
        return Integer.parseInt(mp.get(0).get("count").toString());
    }


    /*retrieve the details of a given category */
    @Override
    public Category selectCategoryDetail(int categoryId) {
        return null;
    }

    /*retrieve the details of a chosen sub-category */
    @Override
    public Category selectSubCategoryDetail(int subcategoryId) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE id = ?", subcategoryId);


        Category category = new Category();
        category.setSubCategoryId(Integer.parseInt(mp.get(0).get("id").toString()));
        category.setSubCategoryName(mp.get(0).get("name").toString());
        category.setSubcatDescription(mp.get(0).get("description").toString());
        category.setCreator(mp.get(0).get("creator").toString());
        category.setCreatedDate(Date.valueOf(mp.get(0).get("created_date").toString()));


        log.info("msg {}", category);
        return category;
    }


    @Override
    public List<Category> selectAllVisible() {
        return null;
    }

    /*retrieve total no.of subcategories*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM sub_category ");
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

    @Override
    public int countForCat(String catId) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM sub_category " +
                "WHERE category_id=(SELECT id FROM category WHERE name =?)", catId);
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

    /*update subcategory details*/
    @Override
    public int update(String subCategoryName, String description) {
        String sql = "UPDATE sub_category SET description = ? WHERE name = ? ";

        int count = jdbcTemplate.update(sql, new Object[]{description, subCategoryName});
        log.info("{}", count);
        return count;
    }

    @Override
    public int updateCategory(Category cat) {
        int id = cat.getSubCategoryId();
        String name = cat.getSubCategoryName();
        String desc = cat.getSubcatDescription();

        String sql = "UPDATE sub_category SET name=?, description = ? WHERE id = ? ";

        int count = jdbcTemplate.update(sql, new Object[]{name, desc, id});
        log.info("{}", count);
        return count;
    }

    /*retrieve all subcategory names*/
    @Override
    public List<Category> selectNameAndDescription() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name,description FROM sub_category");
        List<Category> subCategories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            Category category = new Category();
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setSubcatDescription(mp.get(i).get("description").toString());
            subCategories.add(category);


        }
        log.info("{}", subCategories);
        return subCategories;
    }

    /*retrieve subcategory names list in a specific category*/
    @Override
    public List<String> retrieveSubcatogories(String category) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM sub_category WHERE category_id=(SELECT id FROM category WHERE NAME =?)", category);
        List<String> subCatName = new ArrayList<String>();
        for (int i = 0; i < mp.size(); i++) {
            String nm = mp.get(i).get("name").toString();
            subCatName.add(nm);
        }
        log.info("{}", subCatName);
        return subCatName;
    }

    @Override
    public boolean checkAvailabilityOnUpdate(int id, String categoryName) {
        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE  name=? AND id !=?", categoryName, id);
        log.info("{}", mp);

        if (mp.size() != 0) {
            result = true;
        } else
            result = false;
        log.info("{}", result);
        return result;
    }

    @Override
    public List<Category> paginateSelectAll(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category ORDER BY created_date DESC LIMIT ? OFFSET ?", limit, page);
        List<Category> subCategories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setSubCategoryId(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setSubcatDescription(mp.get(i).get("description").toString());
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(mp.get(i).get("created_date").toString()));
            category.setCategory_id(Integer.parseInt(mp.get(i).get("category_id").toString()));

            subCategories.add(category);
            // }

        }
        log.info("{}", subCategories);
        return subCategories;
    }

    @Override
    public List<Category> paginateSelectAllVisible(int limit, int page) {
        return null;
    }

    @Override
    public List<Category> paginateSelectNameAndDescription(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name,description FROM sub_category ORDER BY created_date DESC LIMIT ? OFFSET ?", limit, page);
        List<Category> subCategories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            Category category = new Category();
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setSubcatDescription(mp.get(i).get("description").toString());
            subCategories.add(category);


        }
        log.info("{}", subCategories);
        return subCategories;
    }

    @Override
    public List<Category> selectAllTypeAhead(String catName, int limit, int page) {
        String key = "%" + catName + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE name LIKE ? ORDER BY created_date DESC LIMIT ? OFFSET ?", key, limit, page);
        List<Category> subCategories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setSubCategoryId(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setSubcatDescription(mp.get(i).get("description").toString());
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(mp.get(i).get("created_date").toString()));
            category.setCategory_id(Integer.parseInt(mp.get(i).get("category_id").toString()));

            subCategories.add(category);
            // }

        }
        log.info("{}", subCategories);
        return subCategories;
    }

    @Override
    public List<Category> selectAllVisibleTypeAhead(String catName, int limit, int page) {
        return null;
    }

    @Override
    public List<Category> selectSubCategoryForCategoryTypeAhead(String catName, String subCategoryKey, int limit, int page) {
        String key = "%" + subCategoryKey + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM sub_category WHERE category_id=(SELECT id FROM category WHERE NAME =?) AND name LIKE ? ORDER BY created_date DESC LIMIT ? OFFSET ?", catName, key, limit, page);
        List<Category> subCategories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            Category category = new Category();
            category.setSubCategoryId(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setSubCategoryName(mp.get(i).get("name").toString());
            category.setSubcatDescription(mp.get(i).get("description").toString());
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(mp.get(i).get("created_date").toString()));
            category.setCategory_id(Integer.parseInt(mp.get(i).get("category_id").toString()));

            subCategories.add(category);
        }
        log.info("{}", subCategories);
        return subCategories;
    }

    /*Add new category*/
    @Override
    public void createCategory1(Category cat) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            add(cat);
            transactionManager.commit(stat);
        } catch (Exception e) {
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
        List<Map<String, Object>> another = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=(SELECT id FROM sub_category WHERE name=?)", subCatName);
        List<Item> itemList = new ArrayList<Item>();

        log.info("{}", another);
        for (int j = 0; j < another.size(); j++) {
            Item item = new Item();
            item.setItemId(Integer.parseInt(another.get(j).get("id").toString()));
            List<Map<String, Object>> size = jdbcTemplate.queryForList("SELECT size,price FROM size WHERE item_id=?", another.get(j).get("id"));
            item.setSizePrice(size);
            item.setItemName(another.get(j).get("name").toString());
            item.setDescription(another.get(j).get("description").toString());
            List<Map<String, Object>> type = jdbcTemplate.queryForList("SELECT name FROM type WHERE type_id=?", another.get(j).get("type_id"));
            item.setType(type.get(0).get("name").toString());
            item.setImage(another.get(j).get("image").toString());
            item.setSubCategoryId(Integer.parseInt(another.get(j).get("sub_category_id").toString()));
            itemList.add(item);

        }
        log.info("ItemList{}", itemList);
        return itemList;
    }

    @Override
    public List<Item> loadMenuItemsTypeAhead(String subCatName, String itemKey) {
        String key = "%" + itemKey + "%";
        List<Map<String, Object>> another = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=(SELECT id FROM sub_category WHERE name=?) AND name LIKE ?", subCatName, key);
        List<Item> itemList = new ArrayList<Item>();

        log.info("{}", another);
        for (int j = 0; j < another.size(); j++) {
            Item item = new Item();
            item.setItemId(Integer.parseInt(another.get(j).get("id").toString()));
            List<Map<String, Object>> size = jdbcTemplate.queryForList("SELECT size,price FROM size WHERE item_id=?", another.get(j).get("id"));
            item.setSizePrice(size);
            item.setItemName(another.get(j).get("name").toString());
            item.setDescription(another.get(j).get("description").toString());
            List<Map<String, Object>> type = jdbcTemplate.queryForList("SELECT name FROM type WHERE type_id=?", another.get(j).get("type_id"));
            item.setType(type.get(0).get("name").toString());
            item.setImage(another.get(j).get("image").toString());
            item.setSubCategoryId(Integer.parseInt(another.get(j).get("sub_category_id").toString()));
            itemList.add(item);

        }
        log.info("ItemList{}", itemList);
        return itemList;
    }

    @Override
    public List<String> viewSubCategories(String catName) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM sub_category WHERE category_id=(SELECT id FROM category WHERE name =?)", catName);
        List<String> subCatName = new ArrayList<String>();
        for (int i = 0; i < mp.size(); i++) {
            String nm = mp.get(i).get("name").toString();
            subCatName.add(nm);
        }
        log.info("{}", subCatName);
        return subCatName;
    }

    @Override
    public List<String> selectCategoryNames() {
        return null;
    }
    /*retrieve list of category names*/


    @Override
    public int countAllTypeAhead(String catName) {
        String key = "%" + catName + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS count FROM sub_category WHERE name LIKE ?", key);
        return Integer.parseInt(mp.get(0).get("count").toString());
    }

    @Override
    public int countAllVisibleTypeAhead(String catName) {
        return 0;
    }

    @Override
    public int countAllVisible() {
        return 0;
    }

    @Override
    public int SubCategoryForCategoryTypeAhead(String catName, String subCategoryKey) {
        String key = "%" + subCategoryKey + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS count FROM sub_category WHERE category_id=(SELECT id FROM category WHERE NAME =?) AND name LIKE ? ", catName, key);
        return Integer.parseInt(mp.get(0).get("count").toString());


    }

}