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
    int updateSizeTable(int id,List<Item> item2);
    void addItem(Item item,List<Item> item2);
    List<Map<String, Object>> getTopRatedItemsFromAllCategories();
    List<Map<String, Object>> getTopRatedItemsOfACategory(String categoryName);
    List<String> getAllCategories();
    List<Map<String, Object>> viewAllItemDetails();
}
