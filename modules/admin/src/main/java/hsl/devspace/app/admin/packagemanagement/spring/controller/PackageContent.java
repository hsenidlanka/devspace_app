package hsl.devspace.app.admin.packagemanagement.spring.controller;

public class PackageContent {

    public String item;
    public String size;
    public int qty;
public PackageContent(){}
    public String getItem(){ return item; }

    public void setItem(String item1){ this.item = item1; }

    public String getSize(){ return size; }

    public void setSize(String size1) { this.size = size1; }

    public int getQty(){ return qty; }

    public void setQty(int qty1) { this.qty = qty1; }

    public PackageContent(String item, String size, int qty){
        this.item=item;
        this.size=size;
        this.qty=qty;
    }
}
