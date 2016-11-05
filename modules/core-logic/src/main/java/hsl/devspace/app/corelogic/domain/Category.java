package hsl.devspace.app.corelogic.domain;

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

    public Category(){

    }
    //category
    public Category(String categoryName,String description,String image,String creator,String status){
        this.categoryName=categoryName;
        this.catDescription=description;
        this.image=image;
        this.creator=creator;
        this.status=status;
    }
    //sub category
    public Category(String subCategoryName,String description,String creator,String categoryName){
        this.subCategoryName=subCategoryName;
        this.subcatDescription=description;
        this.creator=creator;
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
                ", description='" + catDescription + '\'' +
                ",image='" + image +'\'' +
                ", creator='" + creator + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", category_id=" + category_id +
                ", subCategoryId=" + subCategoryId +
                ", status=" +status +
                '}';
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
}
