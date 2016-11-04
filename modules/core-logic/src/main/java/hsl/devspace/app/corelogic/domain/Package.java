package hsl.devspace.app.corelogic.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by hsenid on 9/20/16.
 */
public class Package {
    private String packName;
    private String content;
    private double price;
    private String image;
    private int packageId;
    private MultipartFile imageUrl;
    private String categoryName;
    private String itemName;
    private List<Item> sizePrice;

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Item> getSizePrice() {
        return sizePrice;
    }

    public void setSizePrice(List<Item> sizePrice) {
        this.sizePrice = sizePrice;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Package(String packName, String content, double price, String image) {
        this.packName = packName;
        this.content = content;
        this.price = price;
        this.image = image;
    }

    public Package(String packName, String content, double price, MultipartFile imageUrl) {
        this.packName = packName;
        this.content = content;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Package() {
    }

    @Override
    public String toString() {
        return "Package{" +
                "packName='" + packName + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", packageId=" + packageId +
                ", imageUrl=" + imageUrl +
                ", categoryName='" + categoryName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", sizePrice=" + sizePrice +
                '}';
    }
}
