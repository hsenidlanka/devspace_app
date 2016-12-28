package hsl.devspace.app.corelogic.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by hsenid on 9/15/16.
 */
public class Category {
    private String categoryName;
    private String catDescription;
    private String subcatDescription;
    private String creator;
    private String subCategoryName;
    private int category_id;
    private int subCategoryId;
    private String status;
    private String image;
    private MultipartFile imageUrl;
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }



    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public String getSubcatDescription() {
        return subcatDescription;
    }

    public void setSubcatDescription(String subcatDescription) {
        this.subcatDescription = subcatDescription;
    }

    public Category(){

    }
    //category
    public Category(String categoryName, String description, MultipartFile imageUrl, String creator, String status) {
        this.categoryName=categoryName;
        this.catDescription=description;
        this.imageUrl = imageUrl;
        this.creator=creator;
        this.status=status;
    }

    public Category(String categoryName, String description, String image, String creator, String status) {
        this.categoryName = categoryName;
        this.catDescription = description;
        this.image = image;
        this.creator = creator;
        this.status = status;
    }
    //category(for admin category update )
   /* public Category(int category_id,String categoryName, String description,String status, String image) {
        this.category_id = category_id;
        this.categoryName=categoryName;
        this.catDescription=description;
        this.status=status;
        this.image=image;
    }*/
    //sub category
    public Category(String subCategoryName,String description,String creator,String categoryName){
        this.subCategoryName=subCategoryName;
        this.subcatDescription=description;
        this.creator=creator;
        this.categoryName=categoryName;
    }
    //sub category for subcategory details for a given category
    public Category(int subcategory_id, int category_id, String subCategoryName, String description, String creator, Date createdDate, String categoryName) {
        this.subCategoryId=subcategory_id;
        this.subCategoryName=subCategoryName;
        this.subcatDescription=description;
        this.creator=creator;
        this.createdDate = createdDate;
        this.category_id=category_id;
        this.categoryName=categoryName;
    }
    //category with Id (for update method)
    public Category(int category_id,String categoryName, String description,String image,String creator) {
        this.category_id = category_id;
        this.categoryName = categoryName;
        this.catDescription = description;
        this.image=image;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", catDescription='" + catDescription + '\'' +
                ", subcatDescription='" + subcatDescription + '\'' +
                ", creator='" + creator + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", category_id=" + category_id +
                ", subCategoryId=" + subCategoryId +
                ", status='" + status + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl=" + imageUrl +
                ", createdDate=" + createdDate +
                '}';
    }
}
