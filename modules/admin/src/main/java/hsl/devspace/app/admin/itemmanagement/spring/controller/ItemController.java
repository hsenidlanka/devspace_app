/*
package hsl.devspace.app.admin.itemmanagement.spring.controller;

import hsl.devspace.app.admin.itemmanagement.spring.model.Item;
import hsl.devspace.app.admin.itemmanagement.spring.repository.ItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

    private static final Logger LOG = LogManager.getLogger(ItemController.class);

    @Autowired
    private ItemRepository itemRepository;

    */
/**
     * Add new item view
     **//*

    @RequestMapping(value = "/add_item")
    public ModelAndView addItem(@ModelAttribute Item item){
        item = new Item();
        return new ModelAndView("addItem");
    }

    */
/**
     * View Item details view
     **//*

    @RequestMapping(value = "/view_item")
    public ModelAndView viewItem(@ModelAttribute Item item){
        item = new Item();
        return new ModelAndView("viewItem");
    }

    */
/**
     * Edit Item view
     **//*

    @RequestMapping(value = "/edit_item")
    public ModelAndView editItem(@ModelAttribute Item item){
        item = new Item();
        return new ModelAndView("editItem");
    }
}
*/
