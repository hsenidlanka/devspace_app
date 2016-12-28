package hsl.devspace.app.corelogic.repository.category;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.domain.Item;
import org.slf4j.Logger;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    Category category = new Category();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    //private static org.apache.log4j.Logger log = Logger.getLogger(CategoryRepositoryImpl.class);
    Logger log = LoggerFactory.getLogger(CategoryRepositoryImpl.class);


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
        if (category.getCategoryName() != null && category.getCatDescription() != null && category.getImage() != null && category.getCreator() != null && category.getStatus() != null) {
            String catNm = category.getCategoryName();

            boolean availability = checkAvailability(catNm);
            if (availability == false) {
          /*  MultipartFile img = category.getImageUrl();
            String ims = img.getOriginalFilename();*/
                String sql = "INSERT INTO category " +
                        "(name,description,image,creator,created_date,status) VALUES (?,?,?,?,NOW(),?)";

                row = jdbcTemplate.update(sql, new Object[]{category.getCategoryName(), category.getCatDescription(), category.getImage(), category.getCreator(), category.getStatus()});

                log.info("{} new category inserted", row);
            } else
                log.info("{} category already available", row);
        } else {
            log.info("Values cannot be null");
        }


        return row;
    }

    /*check if category is already available*/
    @Override
    public boolean checkAvailability(String categoryName) {
        boolean result;
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE name=?", categoryName);
        log.info("{} {}", mp, mp.size());

        if (mp.size() != 0) {
            result = true;
        } else {
            result = false;
        }
        log.info("{} availability checking", result);
        return result;

    }

    /*check availability on update name*/
    @Override
    public boolean checkAvailabilityOnUpdate(int id, String categoryName) {
        boolean result;

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE  name=? AND id !=?", categoryName, id);
        log.info("{}", mp);

        if (mp.size() != 0) {
            result = true;
        } else result = false;
        log.info("{} availability checking", result);
        return result;

    }

    /*Delete category*/
    @Override
    public int delete(String categoryName) {
        // category.setCategoryName(categoryName);

        String sql = "DELETE FROM category WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{categoryName});
        log.info("{} category deleted", row);
        return row;
    }

    @Override
    public List<Category> viewSubCategoriesforCategory(String catName, int limit, int page) {
        return null;
    }

    /*view all category details */
    @Override
    public List<Category> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category ORDER BY created_date DESC ");
        List<Category> categories = new ArrayList<Category>();

        for (Map<String, Object> aMp : mp) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setCategory_id(Integer.parseInt(aMp.get("id").toString()));
            category.setCategoryName(aMp.get("name").toString());
            category.setCatDescription(aMp.get("description").toString());
            if (aMp.get("image") != null) {
                category.setImage(aMp.get("image").toString());
            }
            category.setCreator(aMp.get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(aMp.get("created_date").toString()));
            category.setStatus(aMp.get("status").toString());
            categories.add(category);
            // }
        }
        log.info("msg {}", categories);
        return categories;
    }

    @Override
    public List<Category> selectAllTypeAhead(String catName, int limit, int page) {
        String key = "%" + catName + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE name LIKE ? ORDER BY created_date DESC LIMIT ? OFFSET ? ", key, limit, page);
        List<Category> categories = new ArrayList<Category>();

        for (Map<String, Object> aMp : mp) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setCategory_id(Integer.parseInt(aMp.get("id").toString()));
            category.setCategoryName(aMp.get("name").toString());
            category.setCatDescription(aMp.get("description").toString());
            if (aMp.get("image") != null) {
                category.setImage(aMp.get("image").toString());
            }
            category.setCreator(aMp.get("creator").toString());
            category.setCreatedDate(Date.valueOf(aMp.get("created_date").toString()));
            category.setStatus(aMp.get("status").toString());
            categories.add(category);
            // }
        }
        log.info("msg {}", categories);
        return categories;
    }

    @Override
    public int countAllTypeAhead(String catName) {
        String key = "%" + catName + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS count FROM category WHERE name LIKE ?", key);
        return Integer.parseInt(mp.get(0).get("count").toString());
    }

    /*retrieve the details of a given category */
    @Override
    public Category selectCategoryDetail(int categoryId) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE id = ?", categoryId);

        Category category = new Category();
        category.setCategory_id(Integer.parseInt(mp.get(0).get("id").toString()));
        category.setCategoryName(mp.get(0).get("name").toString());
        category.setCatDescription(mp.get(0).get("description").toString());
        if (mp.get(0).get("image") != null) {
            category.setImage(mp.get(0).get("image").toString());
        }
        category.setCreator(mp.get(0).get("creator").toString());
        category.setCreatedDate(Timestamp.valueOf(mp.get(0).get("created_date").toString()));
        category.setStatus(mp.get(0).get("status").toString());


        log.info("msg {}", category);
        return category;
    }

    /*retrieve the details of a chosen sub-category */
    @Override
    public Category selectSubCategoryDetail(int subcategoryId) {
        return null;
    }

    @Override
    public List<Category> selectAllVisible() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE status=1");
        List<Category> categories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setCategory_id(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setCategoryName(mp.get(i).get("name").toString());
            category.setCatDescription(mp.get(i).get("description").toString());
            if (mp.get(i).get("image") != null) {
                category.setImage(mp.get(i).get("image").toString());
            }
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(mp.get(i).get("created_date").toString()));
            category.setStatus(mp.get(i).get("status").toString());

            categories.add(category);
            // }

        }
        log.info("{}", categories);
        return categories;
    }

    public List<Category> selectAllVisibleTypeAhead(String catName, int limit, int page) {
        String key = "%" + catName + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE status=1 LIKE ? ORDER BY created_date DESC LIMIT ? OFFSET ? ", key, limit, page - 1);
        List<Category> categories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setCategory_id(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setCategoryName(mp.get(i).get("name").toString());
            category.setCatDescription(mp.get(i).get("description").toString());
            if (mp.get(i).get("image") != null) {
                category.setImage(mp.get(i).get("image").toString());
            }
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(mp.get(i).get("created_date").toString()));
            category.setStatus(mp.get(i).get("status").toString());

            categories.add(category);
            // }

        }
        log.info("{}", categories);
        return categories;
    }

    @Override
    public int countAllVisibleTypeAhead(String catName) {
        String key = "%" + catName + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS count FROM category WHERE status=1 LIKE ? ", key);
        return Integer.parseInt(mp.get(0).get("count").toString());
    }

    @Override
    public List<Category> selectSubCategoryForCategoryTypeAhead(String catName, String subCategoryKey, int limit, int page) {
        return null;
    }

    /*retrieve total no.of categories*/
    @Override
    public int count() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT count(*) AS total FROM category ");
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

    public int countForCat(String catId) {
        return 0;
    }

    /*update category details-name,description*/
    @Override
    public int update(String categoryName, String description) {

        String sql = "UPDATE category SET description = ? WHERE name = ? ";

        int count = jdbcTemplate.update(sql, new Object[]{description, categoryName});
        log.info("{}", count);
        return count;
    }


    //this method is for update from UI(to be changed)
    @Override
    public int updateCategory(Category cat) {
        int id = cat.getCategory_id();
        String name = cat.getCategoryName();
        String desc = cat.getCatDescription();
        String image=cat.getImage();
        String status = cat.getStatus();

        String sql = "UPDATE category SET name=?, description = ?, image = ?, status = ? WHERE id = ? ";

        int count = jdbcTemplate.update(sql, new Object[]{name, desc,image, status, id});
        log.info("{}", count);
        return count;
    }

    /*view name and description of all categories*/
    @Override
    public List<Category> selectNameAndDescription() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name,description FROM category");
        List<Category> categories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            Category category = new Category();
            category.setCategoryName(mp.get(i).get("name").toString());
            category.setCatDescription(mp.get(i).get("description").toString());
            categories.add(category);
        }
        log.info("{}", categories);
        return categories;
    }

    /*retrieve types of a specific category*/
    public List<String> retrieveCategoryTypes(String categoryName) {
        List<String> subCatName = new ArrayList<String>();

        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT id FROM category WHERE name=?", categoryName);
        if (mp1.size() != 0) {
            List<Map<String, Object>> mp2 = jdbcTemplate.queryForList("SELECT type_id FROM category_type WHERE category_id=?", mp1.get(0).get("id"));
            List<Map<String, Object>> mp = null;
            List<Map<String, Object>> another = new ArrayList<Map<String, Object>>();

            int i = 0;
            while (i < mp2.size()) {
                mp = jdbcTemplate.queryForList("SELECT name FROM type WHERE type_id=?", mp2.get(i).get("type_id"));
                another.addAll(mp);
                log.info("{}", mp);
                i++;
            }
            //log.info("another"+another);
            for (int j = 0; j < another.size(); j++) {
                String nm = another.get(j).get("name").toString();
                subCatName.add(nm);
            }
        }
        log.info("{}", subCatName);
        return subCatName;
        /*List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM type WHERE type_id=" +"(SELECT type_id FROM category_type WHERE category_id=" + "(SELECT id FROM category WHERE name=?))",categoryName);
        log.info(mp);*/

    }

    /*retrieve all items in a specific category*/
    @Override
    public List<Item> loadMenuItems(String catName) {
        // List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=(SELECT id FROM sub_category WHERE category_id=(SELECT id FROM category WHERE name=?))",catName);
        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT id FROM category WHERE name=?", catName);
        List<Map<String, Object>> mp = null;
        List<Map<String, Object>> another = new ArrayList<Map<String, Object>>();
        List<Item> itemList = new ArrayList<Item>();

        if (mp1.size() > 0) {
            List<Map<String, Object>> mp2 = jdbcTemplate.queryForList("SELECT id FROM sub_category WHERE category_id=?", mp1.get(0).get("id"));
            if (mp2.size() > 0) {
                int i = 0;
                while (i < mp2.size()) {
                    mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=?", mp2.get(i).get("id"));
                    another.addAll(mp);
                    log.info("{}", mp);
                    i++;
                }
            } else {
                log.info("no valid sub categories found");
            }
        } else {
            log.info("not a valid category name");
        }

        log.info("mp" + mp);
        log.info("another" + another);
        for (int j = 0; j < another.size(); j++) {
            Item item = new Item();
            item.setItemId(Integer.parseInt(another.get(j).get("id").toString()));
            item.setItemName(another.get(j).get("name").toString());
            item.setDescription(another.get(j).get("description").toString());
            item.setType(another.get(j).get("type_id").toString());
            if (another.get(j).get("image") != null) {
                item.setImage(another.get(j).get("image").toString());
            }
            List<Map<String, Object>> mp5 = jdbcTemplate.queryForList("SELECT name FROM sub_category WHERE id=?", another.get(j).get("sub_category_id"));
            //item.setSubCategoryId(Integer.parseInt(another.get(j).get("sub_category_id").toString()));
            item.setSubCategoryName(mp5.get(0).get("name").toString());

            itemList.add(item);


        }
        log.info("{}", itemList);
        return itemList;
    }

    @Override
    public List<Item> loadMenuItemsTypeAhead(String catName, String itemKey) {
        String key = "%" + itemKey + "%";
        // List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=(SELECT id FROM sub_category WHERE category_id=(SELECT id FROM category WHERE name=?))",catName);
        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT id FROM category WHERE name=?", catName);
        List<Map<String, Object>> mp = null;
        List<Map<String, Object>> another = new ArrayList<Map<String, Object>>();
        List<Item> itemList = new ArrayList<Item>();

        if (mp1.size() > 0) {
            List<Map<String, Object>> mp2 = jdbcTemplate.queryForList("SELECT id FROM sub_category WHERE category_id=?", mp1.get(0).get("id"));
            if (mp2.size() > 0) {
                int i = 0;
                while (i < mp2.size()) {
                    mp = jdbcTemplate.queryForList("SELECT * FROM item WHERE sub_category_id=? AND name LIKE ?", mp2.get(i).get("id"), key);
                    another.addAll(mp);
                    log.info("{}", mp);
                    i++;
                }
            } else {
                log.info("no valid sub categories found");
            }
        } else {
            log.info("not a valid category name");
        }

        log.info("mp" + mp);
        log.info("another" + another);
        for (int j = 0; j < another.size(); j++) {
            Item item = new Item();
            item.setItemId(Integer.parseInt(another.get(j).get("id").toString()));
            item.setItemName(another.get(j).get("name").toString());
            item.setDescription(another.get(j).get("description").toString());
            item.setType(another.get(j).get("type_id").toString());
            if (another.get(j).get("image") != null) {
                item.setImage(another.get(j).get("image").toString());
            }
            List<Map<String, Object>> mp5 = jdbcTemplate.queryForList("SELECT name FROM sub_category WHERE id=?", another.get(j).get("sub_category_id"));
            //item.setSubCategoryId(Integer.parseInt(another.get(j).get("sub_category_id").toString()));
            item.setSubCategoryName(mp5.get(0).get("name").toString());

            itemList.add(item);


        }
        log.info("{}", itemList);
        return itemList;
    }

    /*view all sub categories of a specific category*/
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

    /*retrieve list of category names*/
    @Override
    public List<String> selectCategoryNames() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name FROM category");
        List<String> catName = new ArrayList<String>();
        for (int i = 0; i < mp.size(); i++) {
            String nm = mp.get(i).get("name").toString();
            catName.add(nm);
        }
        log.info("{}", catName);
        return catName;
    }


    @Override
    public List<String> retrieveSubcatogories(String category) {
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
        } catch (Exception e) {
            transactionManager.rollback(stat);
        }
    }

    public List<Category> paginateSelectAll(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category ORDER BY created_date DESC LIMIT ? OFFSET ?", limit, page);
        List<Category> categories = new ArrayList<Category>();

        for (Map<String, Object> aMp : mp) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setCategory_id(Integer.parseInt(aMp.get("id").toString()));
            category.setCategoryName(aMp.get("name").toString());
            category.setCatDescription(aMp.get("description").toString());
            if (aMp.get("image") != null) {
                category.setImage(aMp.get("image").toString());
            }
            category.setCreator(aMp.get("creator").toString());
            category.setCreatedDate(Date.valueOf(aMp.get("created_date").toString()));
            category.setStatus(aMp.get("status").toString());
            categories.add(category);
            // }
        }
        log.info("msg {}", categories);
        return categories;
    }

    public List<Category> paginateSelectAllVisible(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM category WHERE status=1 ORDER BY created_date DESC LIMIT ? OFFSET ?", limit, page - 1);
        List<Category> categories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            //for(int j=0;j<mp.get(i).size();j++) {
            Category category = new Category();
            category.setCategory_id(Integer.parseInt(mp.get(i).get("id").toString()));
            category.setCategoryName(mp.get(i).get("name").toString());
            category.setCatDescription(mp.get(i).get("description").toString());
            if (mp.get(i).get("image") != null) {
                category.setImage(mp.get(i).get("image").toString());
            }
            category.setCreator(mp.get(i).get("creator").toString());
            category.setCreatedDate(Timestamp.valueOf(mp.get(i).get("created_date").toString()));
            category.setStatus(mp.get(i).get("status").toString());

            categories.add(category);
            // }
        }
        log.info("{}", categories);
        return categories;
    }

    @Override
    public int countAllVisible() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS COUNT FROM category WHERE status=1");
        return Integer.parseInt(mp.get(0).get("count").toString());
    }

    @Override
    public int countSubCategoriesForCategory(String catName) {
        return 0;
    }

    @Override
    public int SubCategoryForCategoryTypeAhead(String catName, String subCategoryKey) {
        return 0;
    }

    public List<Category> paginateSelectNameAndDescription(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT name,description FROM category LIMIT ? OFFSET ?", limit, page - 1);
        List<Category> categories = new ArrayList<Category>();

        for (int i = 0; i < mp.size(); i++) {
            Category category = new Category();
            category.setCategoryName(mp.get(i).get("name").toString());
            category.setCatDescription(mp.get(i).get("description").toString());
            categories.add(category);
        }
        log.info("{}", categories);
        return categories;
    }


}