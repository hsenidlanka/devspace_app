package hsl.devspace.app.admin.packagemanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.domain.Package;
import hsl.devspace.app.corelogic.repository.Package.PackageRepository;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping(value = "/packages")
public class PackageController {

    private static final Logger LOGGER = LogManager.getLogger(PackageController.class);

    @Autowired
    private ItemRepository item;

    @Autowired
    private PackageRepository packageRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepositoryImpl subCategoryRepository;

    /**
     * Add new package view
     */

    //For viewing the add package form
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddPackage(Model model) {

        model.addAttribute("command", new Package());

        return "package_management/addPackage";
    }

    /**
     * controller method to load relevant item list for selected category name
     */
    @RequestMapping(value = "/getItemNames", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Item> getItemList(@RequestParam("categoryNm") String categoryNm) {

        List<Item> menutITems = categoryRepository.loadMenuItems(categoryNm);
        LOGGER.trace("loadmenu items = "+ menutITems);
        LOGGER.error("loadmenu items = " + menutITems);
        return menutITems;
    }

    @RequestMapping(value = "/getSizePrice",method = RequestMethod.POST)
    public @ResponseBody String getSizeList(@RequestParam("itemName") String itemName){

        LOGGER.trace("itemNm for sizePrice = "+itemName);
        List<Item> sizeList = item.retrieveSelectedItemSizes(itemName);

        JSONObject jsonObject = new JSONObject();
        for (int x = 0; x <sizeList.size() ; x++) {
            Item list = sizeList.get(x);

            jsonObject.put(list.getSize(), list.getPrice());
        }
        LOGGER.info("itemnm for edit " + itemName + ' ' + "size list for add " + sizeList + "  and sp = " + jsonObject.toJSONString());
        LOGGER.info("sp  object.toJSONString = " + jsonObject.toJSONString());
        LOGGER.trace("load size list = "+sizeList);
        return jsonObject.toJSONString();
    }


    //For submitting the add new package
    @RequestMapping(value = "/add_package")
    public ModelAndView add(@ModelAttribute("command") Package newPackage) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();

/* int p = itemPackage.add(newPackg);
        if(p == 1)

            model.setViewName("addPackage");
  else

            System.out.println("Error in package add");*/

        return model;
    }

    /**
     * Edit package view
     */


    //For viewing the edit package form
    @RequestMapping(value = "/edtPkg", method = RequestMethod.GET)
    public ModelAndView showEditPackage() {
        return new ModelAndView("pkgedt", "editPackage", new Package());
    }

    //For submitting the add new package
    @RequestMapping(value = "/edit_package")
    public ModelAndView update(@ModelAttribute("editPackage")Package newPackg) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();

/*int p = itemPackage.updatePackage();
        if(p == 1)*/

        model.setViewName("editPckgPage");
/*  else*/

        System.out.println("Error in package update");

        return model;
    }

    /*
    * View Package
    **/

    //To view Package view page
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewPackage(ModelAndView modelAndView) {

        modelAndView.addObject("command", new Package());
        modelAndView.setViewName("/package_management/viewPackage");
        return modelAndView;
    }

    //to view the item table
    @RequestMapping(value = "/view/packageTable", method = RequestMethod.GET)
    public
    @ResponseBody List<Package> viewPackage() {
        List<Package> listPkg = packageRepo.selectAll();
        LOGGER.trace("list pkgs  "+listPkg);
        LOGGER.error("list pkgs  "+listPkg);
        return listPkg;
    }

    /*
    * Delete Package
    **/
    @RequestMapping(value = "/delete_package", method = RequestMethod.POST)
    public
    @ResponseBody
    int deletePackage(@RequestParam("pkgName") String packName) {
        try {
            LOGGER.info("deleted Package from database " + packName);
        } catch (Exception ex) {
            LOGGER.error("Error in pacakage deletion " + ex);
        }
        return packageRepo.delete(packName);
    }

}
