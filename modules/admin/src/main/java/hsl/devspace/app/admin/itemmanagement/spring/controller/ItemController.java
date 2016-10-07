package hsl.devspace.app.admin.itemmanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

    private static final Logger LOG = LogManager.getLogger(ItemController.class);

    @Autowired
    private ItemRepository item;

    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * Add new item view
     */
    //For viewing the add item form
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddItem(Model model) {

        List<Map<String, Object>> listCat = categoryRepository.viewCategoryList();
        model.addAttribute("listCat",listCat);
        model.addAttribute("command", new Item());
        return "addItem";
    }

    //For submitting the add new item
    @RequestMapping(value = "/add_item")
    public String addItem(@ModelAttribute("newItem") Item newItem) throws SQLIntegrityConstraintViolationException {

        System.out.println("First Name:" + newItem.getItemName());

        int a = item.add(newItem);
        if (a == 1)
            return "redirect:add";
            // model.setViewName("addItem");
        else

            // System.out.println("Error in item add");
            return "redirect:add";
        //  return model;
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
    public @ResponseBody List<Map<String, Object>> viewItem(HttpServletRequest request){
        List<Map<String, Object>> itemList = item.view();

        return itemList;
    }
    @RequestMapping(value = "/view_item")
    public ModelAndView view(@ModelAttribute("viewItem") Item viewItem) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();
        List<Map<String, Object>> x = item.view();

        if (x != null)
            model.setViewName("viewItem");
        else
            System.out.println("Error in viewing item");

        return model;
    }

    /**
     * Edit Item view
     */

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView showEditItem() {
        return new ModelAndView("itemEdit", "editItem", new Item());
    }

    @RequestMapping(value = "/edit_item")
    public ModelAndView editItem(@ModelAttribute("editItem") Item editItem) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();
        /*int y = item.update(editItem);
            if (y==1)*/

        model.setViewName("editItem");
        //            else
        System.out.println("Error in updating item");

        return model;
    }

/**
 * Delete Item view
 **//*
   /* @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView showDeleteItem(){ return  new ModelAndView("itemDelete", "deleteItem",new Item()); }
    @RequestMapping(value = "view_item")
    public ModelAndView deleteItem(@ModelAttribute("deleteItem") hsl.devspace.app.corelogic.domain.Item deleteItem) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();
        model.setViewName("");
    }*/



    //controller method to get relevant subcategory
    @RequestMapping(value="/getSubcats",method=RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> getSubcatList(@RequestParam("categoryNm") String categoryNm){

        return categoryRepository.retrieveSubcatogories(categoryNm);
    }

/*@RequestMapping(value="/getSubcats",method=RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getSubcatList(@RequestParam("categoryNm") Category categoryNm){

    return categoryRepository.retrieveSubcatogories(categoryNm.getCategoryName());
}*/




}