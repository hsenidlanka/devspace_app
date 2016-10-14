package hsl.devspace.app.admin.itemmanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;
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

        List<Map<String, Object>> listCat = categoryRepository.viewCategoryList();
        model.addAttribute("listCat", listCat);
        model.addAttribute("command", new Item());
        return "item_management/addItem";
    }

    //For submitting the add new item
    @RequestMapping(value = "/add_item")
    public ModelAndView addItem(@ModelAttribute("newItem") Item newItem) throws SQLIntegrityConstraintViolationException {

    String itemNm = newItem.getItemName();
        boolean uniqueItemNm = item.checkAvailability(itemNm);
        if (!uniqueItemNm) {
            int a = item.add(newItem);
            if (a == 1)
                JOptionPane.showMessageDialog(null, "Added new item " + itemNm, "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Server-side error. Cannot add the item !", "Error !",
                        JOptionPane.ERROR_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(null,
                    "Item name is already exists !" + itemNm, "Warning ",
                    JOptionPane.WARNING_MESSAGE);
           //return new ModelAndView("item_management/addItem", "command", newItem);
            return new ModelAndView(new RedirectView("add"));
        }

        return new ModelAndView(new RedirectView("add"));
    }

    /**
     * View Item details view
     */

    //For view the search item form
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showItemView() {
        return new ModelAndView("itemView", "viewItem", new Item());
    }

    //to view the item table
    @RequestMapping(value = "/view/itemTable", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> viewItem() {
        return item.viewAllItemDetails();
    }

    @RequestMapping(value = "/view_item")
    public ModelAndView view(@ModelAttribute("viewItem") Item viewItem) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();
        List<Map<String, Object>> x = item.view();

        if (x != null)
            model.setViewName("item_management/viewItem");
        else
            System.out.println("Error in viewing item");

        return model;
    }

    /**
     * Edit Item view
     */

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView showEditItem() {
        return new ModelAndView("item_management/itemEdit", "editItem", new Item());
    }

    @RequestMapping(value = "/edit_item")
    public ModelAndView editItem(@ModelAttribute("editItem") Item editItem) throws SQLIntegrityConstraintViolationException {

        ModelAndView model = new ModelAndView();
        List<Map<String, Object>> listCatEdit = categoryRepository.viewCategoryList();
        model.addObject("listCatEdit", listCatEdit);
        model.addObject("editItem", new Item());

        model.setViewName("item_management/editItem");

        return model;
    }

    /**
     * Delete Item view
     *//*
   /* @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView showDeleteItem(){ return  new ModelAndView("itemDelete", "deleteItem",new Item()); }
    @RequestMapping(value = "view_item")
    public ModelAndView deleteItem(@ModelAttribute("deleteItem") hsl.devspace.app.corelogic.domain.Item deleteItem) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();
        model.setViewName("");
    }*/


    //controller method to get relevant subcategory
    @RequestMapping(value = "/getSubcats", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Map<String, Object>> getSubcatList(@RequestParam("categoryNm") String categoryNm) {

        return subCategoryRepository.retrieveSubcatogories(categoryNm);
    }

    //controller method to get the rel
}
