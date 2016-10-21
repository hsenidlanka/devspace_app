package hsl.devspace.app.corelogic.repository.item;

import hsl.devspace.app.corelogic.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 10/21/16.
 */
public class ReturnTypeResolver {
    private List<Map<String, Object>> selectedItem;
    private  List<Item> selectedSize;

    public List<Map<String, Object>> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<Map<String, Object>> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<Item> getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(List<Item> selectedSize) {
        this.selectedSize = selectedSize;
    }
}
