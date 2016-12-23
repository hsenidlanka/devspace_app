package hsl.devspace.app.corelogic.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public class Item {
    private String itemName;
    private String price;
    private String description;
    private String size;
    private String type;
    private String image;
    private MultipartFile imageUrl;
    private String subCategoryName;
    private int itemId;
    private String categoryName;
    private int typeId;
    private int subCategoryId;
    List<Map<String, Object>> sizePrice;
    private String creator;
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Map<String, Object>> getSizePrice() {
        return sizePrice;
    }

    public void setSizePrice(List<Map<String, Object>> sizePrice) {
        this.sizePrice = sizePrice;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Item() {

    }

    public Item(String itemName, String description, String size, String type, String image, String subCategoryName, List<Map<String, Object>> sizePrice) {
        this.itemName = itemName;
        this.description = description;
        this.size = size;
        this.type = type;
        this.image = image;
        this.subCategoryName = subCategoryName;
        this.sizePrice = sizePrice;
    }

    public Item(String itemName, String description, String type,MultipartFile imageUrl, String subCategoryName, int itemId) {
        this.itemName = itemName;
        this.description = description;
        this.type = type;
        this.imageUrl = imageUrl;
        this.subCategoryName = subCategoryName;
        this.itemId = itemId;
    }

    public Item(String itemName, String description, String type, String image, String subCategoryName, Date createdDate, String creator, int itemId) {
        this.itemName = itemName;
        this.description = description;
        this.type = type;
        this.image = image;
        this.subCategoryName = subCategoryName;
        this.createdDate = createdDate;
        this.creator = creator;
        this.itemId = itemId;
    }

    public Item(String itemName, String description, String type, String image, String subCategoryName, String creator) {
        this.itemName = itemName;
        this.description = description;
        this.type = type;
        this.image = image;
        this.subCategoryName = subCategoryName;
        this.creator = creator;
    }

    public Item(String price, String size) {
        this.price = price;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl=" + imageUrl +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", itemId=" + itemId +
                ", categoryName='" + categoryName + '\'' +
                ", typeId=" + typeId +
                ", subCategoryId=" + subCategoryId +
                ", sizePrice=" + sizePrice +
                ", createdDate=" + createdDate +
                ", creator='" + creator + '\'' +
                '}';
    }
}