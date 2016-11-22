package hsl.devspace.app.corelogic.repository.Package;

import hsl.devspace.app.corelogic.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 10/21/16.
 */
public class ReturnTypeResolver {
    private List<Map<String, Object>> itemName;
    private List<Map<String, Object>> sizes;
    private List<Map<String, Object>> category;
    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Map<String, Object>> getItemName() {
        return itemName;
    }

    public void setItemName(List<Map<String, Object>> itemName) {
        this.itemName = itemName;
    }

    public List<Map<String, Object>> getSizes() {
        return sizes;
    }

    public void setSizes(List<Map<String, Object>> sizes) {
        this.sizes = sizes;
    }

    public List<Map<String, Object>> getCategory() {
        return category;
    }

    public void setCategory(List<Map<String, Object>> category) {
        this.category = category;
    }
}
