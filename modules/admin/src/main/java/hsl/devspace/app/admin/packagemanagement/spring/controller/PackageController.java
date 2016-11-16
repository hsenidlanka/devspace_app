package hsl.devspace.app.admin.packagemanagement.spring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.domain.Package;
import hsl.devspace.app.corelogic.repository.Package.PackageRepository;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jose4j.json.internal.json_simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
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

    /**
     * controller method to load relevant itemRepo list for selected category name
     */
    @RequestMapping(value = "/getItemNames", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Item> getItemList(@RequestParam("categoryNm") String categoryNm) {

        List<Item> menuItems = categoryRepository.loadMenuItems(categoryNm);
        LOGGER.trace("Load menu items = {}", menuItems);
        LOGGER.error("Load menu items = {}", menuItems);
        return menuItems;
    }

    @RequestMapping(value = "/getSizePrice",method = RequestMethod.POST)
    public @ResponseBody String getSizeList(@RequestParam("itemName") String itemName){

        LOGGER.trace("Item name for Size-Price = {}",itemName);
        List<Item> sizeList = itemRepo.retrieveSelectedItemSizes(itemName);

        JSONObject jsonObject = new JSONObject();
        for (int x = 0; x <sizeList.size() ; x++) {
            Item list = sizeList.get(x);

            jsonObject.put(list.getSize(), list.getPrice());
        }
        LOGGER.info("Item name for edit {}" , itemName , ' ' , " Size list for add {}" , sizeList, "  and sp = {}" , jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

  /*  @RequestMapping(value = "/pkg_content")
    public @ResponseBody String addContent(@RequestParam("test") String g,
                                          HttpServletRequest request,
                                          @ResponseBody PackageContent[] content){

        return "d";
    }*/


    //For submitting the add new package
    @RequestMapping(value = "/add_package")
    public @ResponseBody
    String addPackage(@ModelAttribute("command") Package newPackage,
                                    // @RequestBody PackageContent[] content,
                                    HttpServletRequest request) {

        PackageContent packageContent = new PackageContent();
        String s = request.getParameter("test");

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject obj = new JSONObject();
        PackageContent[] listFromJson = new PackageContent[0];
        List<PackageContent> cats = null;
        ContentList itemList = null;
        try {
           // obj = objectMapper.readValue(s, JSONObject.class);

            cats = objectMapper.readValue(s, new TypeReference<List<PackageContent>>() {
            });
            LOGGER.trace("Some bullshit {}", "kk");
            for(PackageContent cont : cats){
                //System.out.println(cont.getItem());
                LOGGER.trace("cont.getitem = {}",cont.getItem());
            }


        } catch (Exception e) {
            LOGGER.error("error {}", e);
        }

        //return obj.toString();

        return cats.toString();

        /*try{


            String pkgName = newPackage.getPackName();
            List<Package> contList = new ArrayList<Package>();

            boolean uniquePknm = packageRepo.checkUniquePackage(pkgName);
            if(uniquePknm){
                int a = packageRepo.addPackage(newPackage, contList);

                if (a == 1){
                    JOptionPane.showMessageDialog(null, "Server-side error. Cannot add the package !", "Error !",
                            JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("Server-side error in adding itemRepo " + pkgName);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Added new package " + pkgName, "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Item name is already exists! " + pkgName, "Warning ",
                        JOptionPane.WARNING_MESSAGE);

                return new ModelAndView("package_management/addPackage", "command", newPackage);
            }
        }catch (Exception ex){
            LOGGER.error("error in add new pkg {}",ex);
        }*/


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

    //to view the itemRepo table
    @RequestMapping(value = "/view/packageTable", method = RequestMethod.GET)
    public
    @ResponseBody List<Package> viewPackage() {
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
