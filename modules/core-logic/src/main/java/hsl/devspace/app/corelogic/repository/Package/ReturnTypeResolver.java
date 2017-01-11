package hsl.devspace.app.corelogic.repository.Package;

import java.util.List;

/**
 * Created by hsenid on 10/21/16.
 */
public class ReturnTypeResolver {

    private String item;
    private List<String> size;
    private String categoryName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ReturnTypeResolver(String item, List<String> size, String categoryName, int quantity) {
        this.item = item;
        this.size = size;
        this.categoryName = categoryName;
        this.quantity = quantity;
    }

    public ReturnTypeResolver() {
    }

    @Override
    public String toString() {
        return "ReturnTypeResolver{" +
                "item='" + item + '\'' +
                ", size=" + size +
                ", categoryName='" + categoryName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
