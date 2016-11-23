package hsl.devspace.app.corelogic.repository.Package;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 10/21/16.
 */
public class ReturnTypeResolver {
    private List<Map<String, Object>> itemName;
    private List<Map<String, Object>> sizes;
    private List<Map<String, Object>> category;

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

    public ReturnTypeResolver(List<Map<String, Object>> itemName, List<Map<String, Object>> sizes, List<Map<String, Object>> category) {
        this.itemName = itemName;
        this.sizes = sizes;
        this.category = category;
    }

    public ReturnTypeResolver() {
    }

    @Override
    public String toString() {
        return "ReturnTypeResolver{" +
                "itemName=" + itemName +
                ", sizes=" + sizes +
                ", category=" + category +
                '}';
    }
}
