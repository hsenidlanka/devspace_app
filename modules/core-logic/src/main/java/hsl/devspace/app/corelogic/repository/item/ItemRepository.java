package hsl.devspace.app.corelogic.repository.item;

import hsl.devspace.app.corelogic.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public interface ItemRepository {
    int add(Item item);

    boolean checkAvailability(String itemName);

    int delete(String itemName);

    List<Item> selectAll();

    int count();

    List<String> selectNameList();

    int update(Item item);

    int updateSizeTable(int id, List<Item> item2);

    int addItem(Item item, List<Item> item2);

    List<Map<String, Object>> getTopRatedItemsFromAllCategories();

    List<Map<String, Object>> getTopRatedItemsOfACategory(String categoryName);

    List<String> getAllCategories();

    List<Map<String, Object>> viewAllItemDetails();

    int updatePriceList(int id, List<Item> item2);

    int updateItem(Item item, List<Item> item2);

    int deleteFromSize(String itemName);

    int deleteItem(String itemName);

    List<Map<String, Object>> retrieveSelectedItemDetails(int id);

    List<Map<String, Object>> retrieveSelectedItemDetails(String name);

    List<Item> retrieveSelectedItemSizes(int id);

    List<Item> retrieveSelectedItemSizes(String name);

    ReturnTypeResolver selectItemAndSize(int id);

    List<Map<String, Object>> getToppings();

    int paginate(int limit, int page);

    List<Map<String, Object>> paginateSelectedItemDetails(String name, int limit, int page);

    List<Map<String, Object>> viewAllItemDetails(int limit, int page);

    int countSearchKey(String itemKey);

    List<List<Map<String, Object>>> retrieveItemDetailsForSearch(String itemkey, String category);

    List<Map<String, Object>> retrieveItemDetailsForSearchBySubCategory(String itemkey, String subCategory, int limit, int page);
    List<String> searchItemNameByCategory(String itemkey, String category);

    List<String> searchItemNameBySubCategory(String itemkey, String subCategory);

    List<Map<String, Object>> retrieveItemDetailsForSearchByCategory(String itemkey, String category, int limit, int page);

    List<Map<String, Object>> retrieveItemDetailsByCategory(String category, int limit, int page);

    List<Map<String, Object>> retrieveItemDetailsBySubCategory(String subCategory, int limit, int page);

    int countItemDetailsByCategory(String category);

    int countItemDetailsBySubCategory(String subCategory);

    int countSearchItemNameByCategory(String itemkey, String category);

    int countSearchItemNameBySubCategory(String itemkey, String category);
}