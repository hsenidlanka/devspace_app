package hsl.devspace.app.corelogic.repository.category;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.domain.Item;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by hsenid on 9/15/16.
 */
public interface CategoryRepository {

    public int add(Category category) throws SQLIntegrityConstraintViolationException, DuplicateKeyException;
    boolean checkAvailability(String categoryName);
    public int delete(String categoryName);
    List<Category> selectAll();
    List<Category> selectAllVisible();
    int count();
    int update(String name,String description);
    List<Category> selectNameAndDescription();
    void createCategory1(Category cat);
    List<String> retrieveCategoryTypes(String categoryName);
    List<Item> loadMenuItems(String catName);
    List<String> viewSubCategories(String catName);
    List<String> selectCategoryNames();
    List<String>  retrieveSubcatogories(String category);








}
