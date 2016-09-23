package hsl.devspace.app.admin.itemmanagement.spring.controller;

import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

    private static final Logger LOG = LogManager.getLogger(ItemController.class);

    @Autowired
    private ItemRepository item;

/**
     * Add new item view
     **/

    @RequestMapping(value = "/add_item")

    public ModelAndView addItem(@ModelAttribute("addItem")hsl.devspace.app.corelogic.domain.Item newItem) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView() ;
        /*List<Item> items=new ArrayList<Item>();
        items.add(newItem);*/
        int a = item.add(newItem);

        if(a==1)
            model.setViewName("addItem");
        else
            System.out.println("Error in item add");

      return model;
    }

/**
     * View Item details view
     **/

    @RequestMapping(value = "/view_item")
    public ModelAndView viewItem(@ModelAttribute("viewItem")hsl.devspace.app.corelogic.domain.Item viewItem) throws SQLIntegrityConstraintViolationException {
       ModelAndView model = new ModelAndView();
        List<Map<String, Object>> x = item.view();

        if(x != null)
            model.setViewName("viewItem");
        else
            System.out.println("Error in viewing item");

        return model;
    }

/**
     * Edit Item view
     **/

    @RequestMapping(value = "/edit_item")
    public ModelAndView editItem(@ModelAttribute("editItem")hsl.devspace.app.corelogic.domain.Item editItem) throws SQLIntegrityConstraintViolationException {

        ModelAndView model = new ModelAndView();
        /*int y = item.update(editItem.n);*/


        return model;
    }

/**
     * Delete Item view
     **/

}

