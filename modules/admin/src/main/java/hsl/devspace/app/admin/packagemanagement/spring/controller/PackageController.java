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
import org.springframework.web.multipart.MultipartFile;
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

    ////////////////////////////

  /*  @RequestMapping(value = "/createContPkg")
    public
    @ResponseBody
    List<Package> createPkgCont(HttpServletRequest request){

        String s = request.getParameter("test");
        LOGGER.trace("test val = {}", s);

        ObjectMapper objectMapper1 = new ObjectMapper();
        List<Package> cats;
        List<Package> contList1 = new ArrayList<Package>();

        try {
            cats = objectMapper1.readValue(s, new TypeReference<List<Package>>() {});

            for (Package cont : cats) {
                LOGGER.trace("cont= {} ", cont);
                Package details = new Package(cont.getItemName(),cont.getQuantity(),cont.getSize());
                contList1.add(details);
            }
            LOGGER.error("contlist details {}", contList1.get(0).getQuantity()+contList1.get(0).getSize()+contList1.get(0).getItemName());
            LOGGER.error("contlist details2 {}", contList1);

        } catch (IOException e) {
          LOGGER.error("errorr  {}",e.getMessage());
        }
        return contList1;
    }*/

/*

    //For submitting the form for add new package
    @RequestMapping(value = "/add_package", method = RequestMethod.POST)
    public ModelAndView addPackage(@ModelAttribute("newPackage") Package newPackage) {
LOGGER.trace("invokingg {}",newPackage);
        try {
            LOGGER.trace("package objct {}",newPackage);
            LOGGER.error("package objct {}",newPackage);
            String pkgName = newPackage.getPackName();

            LOGGER.trace("pkg name {}",pkgName);
            LOGGER.error("pkg name {}",pkgName);
        }catch (Exception e){
            LOGGER.error("error in package add {}", e.getMessage());
        }

        //int count = categoryRepository.count();

       */
/* try {
            LOGGER.trace("new packg name {}",pkgName);
            List<Package> contList = new ArrayList<Package>();

            boolean uniquePknm = packageRepo.checkUniquePackage(pkgName);
            LOGGER.trace("unique ad pkg {}",uniquePknm);
            LOGGER.error("unique ad pkg {}",uniquePknm);

            if (uniquePknm) {
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
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured in adding package !", "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("error in package add {}", e.getMessage());
        }*//*


        return new ModelAndView("item_management/addItem", "command", newPackage);
        //return new ModelAndView(new RedirectView("add"));
    }

*/


    //For submitting the form for add new package
    @RequestMapping(value = "/add_package", method = RequestMethod.POST)
    public ModelAndView addPackage(@ModelAttribute("newPackage") Package newPackage,
                                  /* @RequestParam("pkgImg") MultipartFile packImg,*/
                                   HttpServletRequest request) {

        //Package newPackage = new Package();
        LOGGER.error("package objct11 {}", newPackage);
        try {
         String packNm =  request.getParameter("pkgName");
         double packPrc = Double.parseDouble((request.getParameter("pkgPrice")));
         String packImg =  request.getParameter("pkgImg");

            MultipartFile img = newPackage.getImageUrl();
            newPackage.setPackName(packNm);
            newPackage.setPrice(packPrc);
            newPackage.setImage(packImg);

        //    newPackage.setImageUrl(packImg);
            LOGGER.error("package objct22 {}", newPackage);
            LOGGER.error("package objct-img url {}", newPackage.getImageUrl());
            LOGGER.error("package objct-img url2 {}", img);


            String s = request.getParameter("test");
            LOGGER.trace("test val = {}", s);

            ObjectMapper objectMapper = new ObjectMapper();
            List<Package> cats = null;
            String pkgName = newPackage.getPackName();

            LOGGER.trace("new packg name {}", pkgName);
            List<Package> contList = new ArrayList<Package>();

            cats = objectMapper.readValue(s, new TypeReference<List<Package>>() {
            });
            for (Package cont : cats) {
                LOGGER.trace("cont= {} ", cont);

                Package details = new Package(cont.getItemName(), cont.getQuantity(), cont.getSize());
                contList.add(details);
            }
            LOGGER.error("contlist details {}", contList.get(0).getQuantity(), contList.get(0).getSize(), contList.get(0).getItemName());
            LOGGER.error("contlist details2 {}", contList);

            boolean uniquePknm = packageRepo.checkUniquePackage(pkgName);
            LOGGER.trace("unique ad pkg {}", uniquePknm);

            if (uniquePknm) {
                int a = packageRepo.addPackage(newPackage, contList);
                LOGGER.info("a in add package {} ", a);

                if (a != 1) {

                    JOptionPane.showMessageDialog(null, "Server-side error. Cannot add the package !", "Error !",
                            JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("Server-side error in adding itemRepo " + pkgName);

                } else {
                    JOptionPane.showMessageDialog(null, "Added new package " + pkgName, "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    LOGGER.trace("added new package {}", pkgName);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Item name is already exists! " + pkgName, "Warning ",
                        JOptionPane.WARNING_MESSAGE);

                return new ModelAndView("package_management/addPackage", "command", newPackage);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured in adding package !", "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("error in package add {}", e.getLocalizedMessage());
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
