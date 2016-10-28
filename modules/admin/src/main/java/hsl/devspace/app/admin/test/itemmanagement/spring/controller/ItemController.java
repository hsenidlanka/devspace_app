package hsl.devspace.app.admin.test.itemmanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import hsl.devspace.app.corelogic.repository.item.ReturnTypeResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

    private static final Logger LOGGER = LogManager.getLogger(ItemController.class);

    @Autowired
    private ItemRepository item;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepositoryImpl subCategoryRepository;

    /**
     * Add new item view
     */
    //For viewing the add item form
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddItem(Model model) {

        List<String> listCat = categoryRepository.selectCategoryNames();

        model.addAttribute("listCat", listCat);
        model.addAttribute("command", new Item());
        return "item_management/addItem";
    }

    //For submitting the add new item
    @RequestMapping(value = "/add_item")
    public ModelAndView addItem(@ModelAttribute("newItem") Item newItem) {

        try {
            String itemNm = newItem.getItemName();
            String itemPrice = newItem.getPrice();
            String itemSize = newItem.getSize();

            LOGGER.trace(itemPrice + " item price");
            LOGGER.trace(itemSize + " item size");
            List<String> sizelist = new ArrayList<String>(Arrays.asList(itemSize.split(",")));
            List<String> pricelist = new ArrayList<String>(Arrays.asList(itemPrice.split(",")));

            List<Item> items = new ArrayList<Item>();

            for (int s = 0; s < sizelist.size(); s++) {

                Item listItem = new Item();
                listItem.setSize(sizelist.get(s));
                listItem.setPrice(pricelist.get(s));
                items.add(listItem);
            }

            boolean uniqueItemNm = item.checkAvailability(itemNm);
            if (!uniqueItemNm) {
                int a = item.addItem(newItem, items);
                if (a != 1) {
                    JOptionPane.showMessageDialog(null, "Server-side error. Cannot add the item !", "Error !",
                            JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("Server-side error in adding item " + itemNm);
                } else {
                    JOptionPane.showMessageDialog(null, "Added new item " + itemNm, "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    LOGGER.info("added New Item to database " + itemNm);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Item name is already exists! " + itemNm, "Warning ",
                        JOptionPane.WARNING_MESSAGE);

                newItem.setPrice(" ");
                LOGGER.trace("after clear price" + itemPrice);
                newItem.setSize(" ");
                LOGGER.trace("after clear size" + itemSize);
                return new ModelAndView("item_management/addItem", "command", newItem);
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error occured in adding item !", "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("Error in add item "+ ex.getMessage());
        }
        return new ModelAndView(new RedirectView("add"));
    }

    /**
     * View Item details view
     */

    //For view the item form
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showItemView(ModelAndView modelView) {

        List<String> listCatEdit = categoryRepository.selectCategoryNames();
        modelView.addObject("listCatEdit", listCatEdit);
        modelView.addObject("command", new Item());
        modelView.setViewName("/item_management/viewItem");
        LOGGER.trace("list usermanagement "+ listCatEdit);

        return modelView;
    }

    //to view the item table
    @RequestMapping(value = "/view/itemTable", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> viewItem() {
        LOGGER.info((item.viewAllItemDetails()) + " ffsf ");
        return item.viewAllItemDetails();
    }


    /**
     * Edit Item view
     */

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView showEditItem() {

        return new ModelAndView("item_management/editItem", "command", new Item());
    }

    //
    //retrieving values of a selected item to edit form from the view table
    //
    @RequestMapping(value = "/edit_item", method = RequestMethod.POST)
    public
    @ResponseBody
    String retieveEditItem(@RequestParam("itemId") int itemId) {

        ReturnTypeResolver e = item.selectItemAndSize(itemId);
        List<Item> lst = e.getSelectedSize();
        JSONObject object=new JSONObject();
        LOGGER.info("content of lst  " + lst);
        LOGGER.info("length of lst  " + lst.size());

        for (int i = 0; i < lst.size(); i++) {
            Item it=lst.get(i);

            object.put(it.getSize(),it.getPrice());
        }
        LOGGER.info("itemId for edit " + itemId + ' ' + "e for edit " + e+ "  and sp = " + object.toJSONString());
        LOGGER.info("e for edit " + e);
        LOGGER.info("sp object = " + object);
        LOGGER.info("sp  object.toJSONString = " + object.toJSONString());

      return object.toJSONString();
    }

    @RequestMapping(value = "/update_item", method = RequestMethod.POST)
    public ModelAndView updateItem(@ModelAttribute("itemUpdate") Item itemUpdate) {

        try {
            int itmId = itemUpdate.getItemId();
            String itmName = itemUpdate.getItemName();
            String itmPriceEdit = itemUpdate.getPrice();
            String itmSizeEdit = itemUpdate.getSize();

            LOGGER.trace("itm Name and ID in edit " + itmName + " ID =" + itmId);
            LOGGER.trace("itm price " + itmPriceEdit);
            LOGGER.trace("itm size " + itmSizeEdit);

            List<String> sizelistEdit = new ArrayList<String>(Arrays.asList(itmSizeEdit.split(",")));
            List<String> pricelistEdit = new ArrayList<String>(Arrays.asList(itmPriceEdit.split(",")));

            LOGGER.trace("asList size" + sizelistEdit);
            LOGGER.trace("asList size()" + sizelistEdit.size());
            LOGGER.trace("asList price" + pricelistEdit);

            List<Item> edittedList = new ArrayList<Item>();

            for (int a = 0; a < sizelistEdit.size(); a++) {
                Item itemList = new Item();
                itemList.setSize(sizelistEdit.get(a));
                itemList.setPrice(pricelistEdit.get(a));
                edittedList.add(itemList);
            }
            LOGGER.trace("edittedList " + edittedList);
            LOGGER.trace("itemUpdate object " + itemUpdate);
            LOGGER.error("edittedList err " + edittedList);
            LOGGER.error("itemUpdate err " + itemUpdate);
            LOGGER.trace("itemUpdate category " + itemUpdate.getCategoryName());
            LOGGER.trace("itemUpdate sub-category " + itemUpdate.getSubCategoryName());

            int i = item.updateItem(itemUpdate, edittedList);
            LOGGER.trace(String.valueOf(i),"  info i edit ", i);
            LOGGER.error("  error in i edit", i);
            if (i != 1) {
                JOptionPane.showMessageDialog(null, "Server-side error. Cannot update the item !", "Error !",
                        JOptionPane.ERROR_MESSAGE);
                LOGGER.error("Server-side error in updating item " + itmName);
            }
            else {
                JOptionPane.showMessageDialog(null, "Updated item details" + itmName, "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                LOGGER.trace("itemUpdate category after" + itemUpdate.getCategoryName());
                LOGGER.trace("itemUpdate sub-category after" + itemUpdate.getSubCategoryName());
                LOGGER.info("updated item successfully " + itmName);
            }
        }catch (Exception er){
            LOGGER.error("Exception "+er.getMessage());
        }
        return new ModelAndView(new RedirectView("view"));
    }


    /**
     * Delete Item view
     */

    @RequestMapping(value = "/delete_item", method = RequestMethod.POST)
    public
    @ResponseBody
    int deleteItem(@RequestParam("itemName") String itemName) throws SQLIntegrityConstraintViolationException {

        LOGGER.info("deleted Item from database " + itemName);
        return item.deleteItem(itemName);
    }

    /**
    * controller method to load relevant subcategory for ajax calls
    **/
    @RequestMapping(value = "/getSubcats", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> getSubcatList(@RequestParam("categoryNm") String categoryNm) {

        return subCategoryRepository.retrieveSubcatogories(categoryNm);
    }

    /*
    * typeahead function for item name
    **/
/*
    @RequestMapping(value = "/typeahedItmNm", method = RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>>
*/

    //controller method to get size and price of selected item
 /*   @RequestMapping(value = "/getPriceSize", method = RequestMethod.POST)
    public
    @ResponseBody
    ReturnTypeResolver getPriceSize(@RequestParam("itemId") int itemId) {

       *//* LOGGER.trace("itemId for price"+ itemId);
        return item.selectItemAndSize(itemId);*//*

        LOGGER.trace("itemId for price" + itemId);
        return item.selectItemAndSize(itemId);
    }*/

}
