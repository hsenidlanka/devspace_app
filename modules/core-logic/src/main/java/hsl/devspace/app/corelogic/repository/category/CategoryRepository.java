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

    public List<Category> viewSubCategoriesforCategory(String catName, int limit, int page);

    public Category selectCategoryDetail(int categoryId);

    public Category selectSubCategoryDetail(int subcategoryId);

    List<Category> selectAllVisible();

    int count();

    public int countForCat(String catId);

    int update(String name, String description);

    public int updateCategory(Category cat);

    List<Category> selectNameAndDescription();

    void createCategory1(Category cat);

    List<String> retrieveCategoryTypes(String categoryName);

    List<Item> loadMenuItems(String catName);

    List<String> viewSubCategories(String catName);

    List<String> selectCategoryNames();

    List<String> retrieveSubcatogories(String category);

    boolean checkAvailabilityOnUpdate(int id, String categoryName);

    List<Category> paginateSelectAll(int limit, int page);

    List<Category> paginateSelectAllVisible(int limit, int page);

    List<Category> paginateSelectNameAndDescription(int limit, int page);

    List<Category> selectAllTypeAhead(String catName, int limit, int page);

    List<Category> selectAllVisibleTypeAhead(String catName, int limit, int page);

    List<Category> selectSubCategoryForCategoryTypeAhead(String catName, String subCategoryKey, int limit, int page);

    int countAllTypeAhead(String catName);

    int countAllVisibleTypeAhead(String catName);

    int countAllVisible();

    int countSubCategoriesForCategory(String catName);

    int SubCategoryForCategoryTypeAhead(String catName, String subCategoryKey);


}