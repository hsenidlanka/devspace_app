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

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }

    public String getSubCategory(){
        return subCategory;
    }
    public void setSubCategory(String subCategory){
        this.subCategory=subCategory;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }

    public  String getSize(){ return  size; }
    public  void setSize(String size){ this.size=size;}

    public String getType(){ return  type;}
    public void setType(String type){ this.type=type;}

    public String getImgLocation(){ return  imgLocation;}
    public void setImgLocation(String imgLocation){ this.imgLocation=imgLocation; }

    public String getDescript(){ return  descript;}
    public void setDescript(String descript){ this.descript=descript; }
}
