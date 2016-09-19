package hsl.devspace.app.admin.itemmanagement.spring.model;


public class Item {

    private int id;
    private String category;
    private String subCategory;
    private String name;
    private double price;
    private String size;
    private String type;
    private String imgLocation;
    private String descript;

    public Item() {
    }

    public Item(int id, String category, String subCategory, String name, double price, String size, String type,
                String imgLocation, String descript){

        this.id = id;
        this.category = category;
        this.subCategory =subCategory;
        this.name = name;
        this.price = price;
        this.size = size;
        this.type = type;
        this.imgLocation = imgLocation;
        this.descript = descript;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return  name;
    }
    public void setName(String name){
        this.name=name;
    }
}
