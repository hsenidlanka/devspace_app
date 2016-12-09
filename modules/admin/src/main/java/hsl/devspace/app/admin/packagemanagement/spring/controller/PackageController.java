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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/packages")
public class PackageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageController.class);

    @Value("${insert.server.error}")
    private String insertServerError;

    @Value("${insert.package.success}")
    private String insertSuccess;

    @Value("${insert.package.error}")
    private String insertError;

    @Value("${insert.package.unique.error}")
    private String insertUniqueErr;

    @Value("${update.package.success}")
    private String updateSuccess;

    @Value("${update.package.error}")
    private String updateError;

    @Value("${update.package.sever.error}")
    private String updateServerErr;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private PackageRepository packageRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepositoryImpl subCategoryRepository;

    @Autowired
    ServletContext context;

    @Value("${admin.packgageimage.server.location}")
    private String serverPath;

    @Value("${admin.packageimage.localmachine.location}")
    private String localPathtoUpload;

    List<Package> contList = new ArrayList<Package>();
    List<Package> contListEdit = new ArrayList<Package>();


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
        LOGGER.trace("json array of catList {}", catList, "json array of cats3 {}", jsonArray);

        return jsonArray;
    }


    /**
     * controller method to load relevant item list for selected category name
     */
    @RequestMapping(value = "/getItemNames")
    public
    @ResponseBody
    List<Item> getItemList(@RequestParam("categoryNm") String categoryNm) {

        LOGGER.trace(categoryNm);
        List<Item> menuItems = categoryRepository.loadMenuItems(categoryNm);
        LOGGER.trace("Load menu items = {}", menuItems);
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

    //For setting the content for add new package
    @RequestMapping(value = "/add_content", method = RequestMethod.GET)
    public @ResponseBody String addContent(@ModelAttribute("newPackage") Package newPackage,
                              HttpServletRequest request) {

        LOGGER.error("package content 1 {}", newPackage);
        try {
            String s = request.getParameter("test");
            LOGGER.trace("test val = {}", s);
            newPackage.setContent(s);

            ObjectMapper objectMapper = new ObjectMapper();
            List<Package> cats;

            cats = objectMapper.readValue(s, new TypeReference<List<Package>>() {
            });
            LOGGER.error("cats add.... {}",cats);
            for (Package cont : cats) {
                LOGGER.trace("cont= {} ", cont);

                Package details = new Package(cont.getItemName(), cont.getQuantity(), cont.getSize());
                contList.add(details);
            }
            LOGGER.error("contlist details qy {}", contList.get(0).getQuantity());
            LOGGER.error("contlist details size {}", contList.get(0).getSize());
            LOGGER.error("contlist details itmnm {}", contList.get(0).getItemName());
            LOGGER.error("contlist details2 {}", contList);

            LOGGER.trace("package ocnt 123 {}", newPackage);
            return  "added";
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "content gettign error", "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("error in content getting error {}", e);
        }
        return "failed";
    }


    //For submitting the form for add new package
    @RequestMapping(value = "/add_package", method = RequestMethod.POST)
    public ModelAndView addPackage(@ModelAttribute("newPackage") Package newPackage) {

        LOGGER.error("package objct11 {}", newPackage);
        LOGGER.error("package objct11c1 {}", newPackage.getContent());
        LOGGER.error("package objct11c2 {}", newPackage.getItemName());

        try {
            MultipartFile imgFile = newPackage.getImageUrl();
            LOGGER.trace("realPathtoUpload package = {}",imgFile);
            LOGGER.trace("package objct-img url {}", newPackage.getImageUrl());

            String s = newPackage.getContent();
            LOGGER.trace("test val = {}", s);

            String pkgName = newPackage.getPackName();

            boolean uniquePknm = packageRepo.checkUniquePackage(pkgName);
            LOGGER.trace("unique ad pkg {}", uniquePknm);

            if (uniquePknm) {
                String imageName = pkgName+".jpg";
                newPackage.setImage(imageName);
                LOGGER.error("image Name .. {}",newPackage.getImage());

                int a = packageRepo.addPackage(newPackage, contList);
                LOGGER.info("a in add package {} ", a);
                LOGGER.trace("item name from pkg controller {}", contList.get(0).getItemName());

                if (a != 1) {
                    JOptionPane.showMessageDialog(null, insertServerError, "Error !",
                            JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("Server-side error in adding package " + pkgName);

                } else {
                    JOptionPane.showMessageDialog(null, insertSuccess + pkgName, "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    /*
                    * save images to directory
                    **/
                    if (!imgFile.isEmpty()) {
                        LOGGER.error("empty file ?? {}",!imgFile.isEmpty());
                        try {
                            // Creating the directory to store file in server
                            String realPathtoUpload = context.getRealPath(serverPath);
                            LOGGER.trace("realPathtoUpload package = , {} ", realPathtoUpload);
                            uploadFile(imgFile, realPathtoUpload, imageName);

                            //create a directory in local machine and upload imGE
                            uploadFile(imgFile,localPathtoUpload, imageName);
                            LOGGER.trace("localpathtoupload package = , {} ", localPathtoUpload);
                        }
                        catch (Exception ex) {
                            LOGGER.error("error in  getting image {}", ex);
                        }
                    }else {
                        LOGGER.error("You failed to upload {}" , imgFile ," because the file was empty.");
                    }
                    LOGGER.trace("added new package {}", pkgName);
                }

            } else {
                JOptionPane.showMessageDialog(null, insertUniqueErr + pkgName, "Warning ",
                        JOptionPane.WARNING_MESSAGE);

                return new ModelAndView("package_management/addPackage", "command", newPackage);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, insertError, "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("error in package add {}", e);
        }

        return new ModelAndView(new RedirectView("add"));
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

    //to view the package table
    @RequestMapping(value = "/view/packageTable", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Package> viewPackage() {
        List<Package> listPkg = packageRepo.selectAll();
        LOGGER.trace("list pkgs  {}", listPkg);
        return listPkg;
    }


    /**
     * Edit package view
     */

    //For setting the content for add new package
    @RequestMapping(value = "/edit_content", method = RequestMethod.GET)
    public @ResponseBody String editContent(@ModelAttribute("newPackage") Package newPackage,
                                           HttpServletRequest request) {

        LOGGER.error("package content edit {}", newPackage);
        try {
            String s = request.getParameter("test2");
            LOGGER.trace("test2 val = {}", s);
            newPackage.setContent(s);

            ObjectMapper objectMapper = new ObjectMapper();
            List<Package> cats;

            cats = objectMapper.readValue(s, new TypeReference<List<Package>>() {
            });
            LOGGER.error("cats edit... {}",cats);
            for (Package cont : cats) {
                LOGGER.trace("cont= {} ", cont);

                Package details = new Package(cont.getItemName(), cont.getQuantity(), cont.getSize());
                contListEdit.add(details);
            }
            LOGGER.error("contlist details qy {}", contListEdit.get(0).getQuantity());
            LOGGER.error("contlist details size {}", contListEdit.get(0).getSize());
            LOGGER.error("contlist details itmnm {}", contListEdit.get(0).getItemName());
            LOGGER.error("contlist details2 {}", contListEdit);

            LOGGER.trace("package cont edit {}", newPackage);
            return  "added edits";
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "content getting error", "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("error in content getting error {}", e);
        }
        return "failed edits";
    }

    //
    //retrieving values of a selected category-content to edit form
    //
    @RequestMapping(value = "/edit_package", method = RequestMethod.POST)
    public ModelAndView retrievePkgCont(@ModelAttribute("editPackage") Package editPackage) {

        try {
            MultipartFile imgFile = editPackage.getImageUrl();
            LOGGER.trace("realPathtoUpload package = {}",imgFile);
            LOGGER.trace("package objct-img url {}", editPackage.getImageUrl());

            String imageName = editPackage.getPackName()+".jpg";
            editPackage.setImage(imageName);

            String s = editPackage.getContent();
            LOGGER.trace("test val = {}", s);

            String pkgName = editPackage.getPackName();
            int i =packageRepo.updatePackage(editPackage, contListEdit);

            if(i != 1 ){
                JOptionPane.showMessageDialog(null, updateError, "Error !",
                        JOptionPane.ERROR_MESSAGE);
                LOGGER.error("Server-side error in updateing package " + pkgName);

            }
            else{
                JOptionPane.showMessageDialog(null, updateSuccess + pkgName, "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                /*
                    * save images to directory
                    **/
                if (!imgFile.isEmpty()) {
                    LOGGER.error("empty file ?? {}",!imgFile.isEmpty());
                    try {
                        // Creating the directory to store file in server
                        String realPathtoUpload = context.getRealPath(serverPath);
                        LOGGER.trace("realPathtoUpload package = , {} ", realPathtoUpload);
                        uploadFile(imgFile, realPathtoUpload, imageName);

                        //create a directory in local machine and upload imGE
                        uploadFile(imgFile,localPathtoUpload, imageName);
                        LOGGER.trace("localpathtoupload package = , {} ", localPathtoUpload);
                    }
                    catch (Exception ex) {
                        LOGGER.error("error in  getting image {}", ex);
                    }
                }else {
                    LOGGER.error("You failed to upload {}" , imgFile ," because the file was empty.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, updateServerErr, "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("Error in edit package {}",e);
        }

        return new  ModelAndView(new RedirectView("view")) ;
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


    /*
   * Method for saving images to directories
   **/
    private void uploadFile(MultipartFile image, String filePath, String fileName) {
        try {
            byte[] bytes = image.getBytes();
            //directory made
            File dir = new File(filePath);
            if (!dir.exists())
                dir.mkdirs();

            //file made
            File createFile = new File(dir.getAbsolutePath() + File.separator + fileName);//name of the image
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(createFile));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            LOGGER.error("error in  getting image ", e);
        }
    }


    /*
    * typeahead function for package name
    **/
    @RequestMapping(value = "/typeahedPkgNm", method = RequestMethod.GET)
    public @ResponseBody List<String> typeaheadPkgName(){

        return packageRepo.getPackageNameList();
    }

    @RequestMapping(value = "/loadSearchPackage", method = RequestMethod.GET)
    public @ResponseBody
    List<Package> loadSearchItem(@RequestParam("srchPkgNm") String pkgName){

        LOGGER.trace("load search Item "+ packageRepo.selectAllByNamePattern(pkgName));
        return  packageRepo.selectAllByNamePattern(pkgName);
    }
}
