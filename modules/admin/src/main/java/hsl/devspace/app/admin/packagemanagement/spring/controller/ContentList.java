package hsl.devspace.app.admin.packagemanagement.spring.controller;


import java.util.List;

public class ContentList {
    List<PackageContent> listItems;

    public List<PackageContent> getListItems(){
        return listItems;
    }
    public void setListItems(List<PackageContent> listItems){
        this.listItems = listItems;
    }

   // ContentList itemList = new ObjectMapper().readValue(s, ContentList.class);
}
