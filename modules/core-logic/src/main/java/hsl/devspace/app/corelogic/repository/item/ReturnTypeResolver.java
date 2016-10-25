package hsl.devspace.app.corelogic.repository.item;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 10/21/16.
 */
public class ReturnTypeResolver {
    private List<Map<String, Object>> selectedItem;
    private  List<Map<String, Object>>selectedSize;
    private int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<Map<String, Object>> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<Map<String, Object>> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<Map<String, Object>> getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(List<Map<String, Object>> selectedSize) {
        this.selectedSize = selectedSize;
    }
}
