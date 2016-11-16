package hsl.devspace.app.admin.packagemanagement.spring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.domain.Package;
import hsl.devspace.app.corelogic.repository.Package.PackageRepository;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/packages")
public class PackageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageController.class);

    @Autowired
    private ItemRepository itemRepo;

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

    /*
    * For populating category list in add package content
    **/
    @RequestMapping(value = "/getCatListContent", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONArray getCatCont() {

        List<String> catList = categoryRepository.selectCategoryNames();
        JSONArray jsonArray = new JSONArray();

        for (int p = 0; p < catList.size(); p++) {
            String list2 = catList.get(p);
            jsonArray.add(list2);
        }
        LOGGER.error("json array of catList {}", catList);
        LOGGER.trace("json array of cats2 {}", jsonArray.toString());
        LOGGER.trace("json array of cats3 {}", jsonArray);

        return jsonArray;
    }


    /**
     * controller method to load relevant item list for selected category name
     */
    @RequestMapping(value = "/getItemNames", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Item> getItemList(@RequestParam("categoryNm") String categoryNm) {

        LOGGER.error(categoryNm);
        List<Item> menuItems = categoryRepository.loadMenuItems(categoryNm);
        LOGGER.trace("Load menu items = {}", menuItems);
        LOGGER.error("Load menu items = {}", menuItems);
        return menuItems;
    }

    @RequestMapping(value = "/getSizePrice", method = RequestMethod.POST)
    public
    @ResponseBody
    String getSizeList(@RequestParam("itemName") String itemName) {

        LOGGER.trace("Item name for Size-Price = {}", itemName);
        List<Item> sizeList = itemRepo.retrieveSelectedItemSizes(itemName);

        JSONObject jsonObject = new JSONObject();
        for (int x = 0; x < sizeList.size(); x++) {
            Item list = sizeList.get(x);

            jsonObject.put(list.getSize(), list.getPrice());
        }
        LOGGER.info("Item name for edit {}", itemName, ' ', " Size list for add {}", sizeList, "  and sp = {}", jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }


    //For submitting the form for add new package
    @RequestMapping(value = "/add_package")
    public
    @ResponseBody
    ModelAndView addPackage(@ModelAttribute("command") Package newPackage,
                            HttpServletRequest request) {

        String s = request.getParameter("test");
        LOGGER.trace("test val = {}", s);

        ObjectMapper objectMapper = new ObjectMapper();
        List<PackageContent> cats = null;

        try {
            String pkgName = newPackage.getPackName();
            List<PackageContent> contList = new ArrayList<PackageContent>();

            cats = objectMapper.readValue(s, new TypeReference<List<PackageContent>>() {
            });
            for (PackageContent cont : cats) {
                LOGGER.trace("cont= {} ", cont);
                PackageContent details = new PackageContent();
                details.setItem(cont.getItem());LOGGER.trace("item detail {}",details.getItem());
                details.setSize(cont.getSize());LOGGER.trace("item detail {}", cont.getSize());
                details.setQty(cont.getQty());LOGGER.trace("item detail {}", cont.getQty());

                contList.add(details);
            }
            LOGGER.error("contlist details {}", contList.get(0).getQty());
            boolean uniquePknm = packageRepo.checkUniquePackage(pkgName);

            /*if (uniquePknm) {
                int a = packageRepo.addPackage(newPackage, contList);
                LOGGER.info("a in add package {} ", a);

                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "Server-side error. Cannot add the package !", "Error !",
                            JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("Server-side error in adding itemRepo " + pkgName);
                } else {
                    JOptionPane.showMessageDialog(null, "Added new package " + pkgName, "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Item name is already exists! " + pkgName, "Warning ",
                        JOptionPane.WARNING_MESSAGE);

                return new ModelAndView("package_management/addPackage", "command", newPackage);
            }*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured in adding package !", "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("error in package add {}", e.getMessage());
        }

        return new ModelAndView(new RedirectView("add"));
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
    public ModelAndView update(@ModelAttribute("editPackage") Package newPackg) throws SQLIntegrityConstraintViolationException {
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

    //to view the itemRepo table
    @RequestMapping(value = "/view/packageTable", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Package> viewPackage() {
        List<Package> listPkg = packageRepo.selectAll();
        LOGGER.trace("list pkgs  {}", listPkg);
        LOGGER.error("list pkgs {}", listPkg);
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
            LOGGER.info("deleted Package from database {}", packName);
        } catch (Exception ex) {
            LOGGER.error("Error in pacakage deletion {}", ex);
        }
        return packageRepo.delete(packName);
    }

}
