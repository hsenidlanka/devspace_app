package hsl.devspace.app.admin.itemmanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import hsl.devspace.app.corelogic.repository.item.ReturnTypeResolver;
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
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

    //private static final Logger LOGGER = LogManager.getLogger(ItemController.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Value("${insert.server.error}")
    private String insertServerError;

    @Value("${insert.item.success}")
    private String insertSuccess;

    @Value("${insert.item.error}")
    private String insertError;

    @Value("${insert.item.unique.error}")
    private String insertUniqueErr;

    @Value("${update.item.success}")
    private String updateSuccess;

    @Value("${update.item.sever.error}")
    private String updateServerErr;

    @Autowired
    private ItemRepository item;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepositoryImpl subCategoryRepository;

    @Autowired
    ServletContext context;

    @Value("${admin.itemimage.server.location}")
    private String serverPath;

    @Value("${admin.itemimage.localmachine.location}")
    private String localPathtoUpload;

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
    public ModelAndView addItem(@ModelAttribute("newItem") Item newItem,
                                HttpServletRequest request) {

        try {
            //set the creator in category object using session attributes
            HttpSession session = request.getSession();
            String userId =(String)session.getAttribute("username");
            LOGGER.error("The user logged in is: {}",userId);
            newItem.setCreator(userId);
            LOGGER.trace("item added vals = {}", newItem);

            String itemNm = newItem.getItemName();
            String itemPrice = newItem.getPrice();
            String itemSize = newItem.getSize();

            MultipartFile imgFile = newItem.getImageUrl();
            LOGGER.trace("multipart file itemMgt =, {}", imgFile);

            String imgFileNm2 = itemNm.replaceAll("\\s", "");
            LOGGER.error("after remove spaces {}", imgFileNm2);

            LOGGER.trace("Item price =, {} ", itemPrice);
            LOGGER.trace("Item size = , {}", itemSize);
            List<String> sizelist = new ArrayList<String>(Arrays.asList(itemSize.split(",")));
            List<String> pricelist = new ArrayList<String>(Arrays.asList(itemPrice.split(",")));

            List<Item> items = new ArrayList<Item>();

            for (int s = 0; s < sizelist.size(); s++) {

                Item listItem = new Item();
                listItem.setSize(sizelist.get(s));
                listItem.setPrice(pricelist.get(s));
                items.add(listItem);
            }
            newItem.setImage(itemNm + ".jpg");
            boolean uniqueItemNm = item.checkAvailability(itemNm);
            if (!uniqueItemNm) {
                int a = item.addItem(newItem, items);
                if (a != 1) {
                    JOptionPane.showMessageDialog(null, insertServerError, "Error",
                            JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("Server-side error in adding item " + itemNm);
                } else {
                    JOptionPane.showMessageDialog(null, insertSuccess + itemNm, "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    /*
                    * save images to directory
                    **/
                    if (!imgFile.isEmpty()) {
                        try {


                            // Creating the directory to store file in server
                            String realPathtoUpload = context.getRealPath(serverPath);
                            LOGGER.trace("realPathtoUpload = , {} ", realPathtoUpload);
                            uploadFile(imgFile, realPathtoUpload, imgFileNm2);

                            //create a directory in local machine and upload imGE
                            uploadFile(imgFile, localPathtoUpload, imgFileNm2);
                        } catch (Exception ex) {
                            LOGGER.error("error in  getting image {}", ex);
                        }
                    } else {
                        LOGGER.error("You failed to upload {}", imgFileNm2, " because the file was empty.");
                    }

                    LOGGER.info("added New Item to database {}", itemNm);
                }
            } else {
                JOptionPane.showMessageDialog(null, insertUniqueErr + itemNm, "Warning ",
                        JOptionPane.WARNING_MESSAGE);

                newItem.setPrice(" ");
                LOGGER.trace("after clear price = {}", itemPrice);
                newItem.setSize(" ");
                LOGGER.trace("after clear size = {}", itemSize);
                return new ModelAndView("item_management/addItem", "command", newItem);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, insertError, "Error !",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.error("Error in add item {}", ex.getMessage());
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
        LOGGER.trace("list itemmgt = {}", listCatEdit);

        return modelView;
    }

    //to view the item table
    @RequestMapping(value = "/view/itemTable", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> viewItem() {
        LOGGER.info("View all item details {} ", (item.viewAllItemDetails()));
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
    String retrieveEditItem(@RequestParam("itemId") int itemId) {

        ReturnTypeResolver e = item.selectItemAndSize(itemId);
        List<Item> lst = e.getSelectedSize();
        JSONObject object = new JSONObject();
        LOGGER.info("content of lst  {}", lst);
        LOGGER.info("length of lst {}", lst.size());

        for (int i = 0; i < lst.size(); i++) {
            Item it = lst.get(i);

            object.put(it.getSize(), it.getPrice());
        }
        LOGGER.info("ItemId for edit {}", itemId, ' ', "e for edit {}", e, "  and sp = {}", object.toJSONString());
        return object.toJSONString();
    }

    @RequestMapping(value = "/update_item", method = RequestMethod.POST)
    public ModelAndView updateItem(@ModelAttribute("itemUpdate") Item itemUpdate,
                                   HttpServletRequest request) {

        try {
            //set the creator in category object using session attributes
            HttpSession session = request.getSession();
            String userId =(String)session.getAttribute("username");
            LOGGER.error("The user logged in is: {}",userId);
            itemUpdate.setCreator(userId);
            LOGGER.info("updated item {}", itemUpdate);

            String itemNm = itemUpdate.getItemName();

            MultipartFile imgFile = itemUpdate.getImageUrl();
            LOGGER.trace("realPathtoUpload item img = {}", imgFile);
            LOGGER.trace("item objct-img url {}", itemUpdate.getImageUrl());

            String imageName = itemNm + ".jpg";
            itemUpdate.setImage(imageName);

            String imgFileNm2 = itemNm.replaceAll("\\s", "");
            LOGGER.info("after remove spaces itm edit {}", imgFileNm2);

            int itmId = itemUpdate.getItemId();
            String itmName = itemUpdate.getItemName();
            String itmPriceEdit = itemUpdate.getPrice();
            String itmSizeEdit = itemUpdate.getSize();

            LOGGER.trace("Item Name and ID in edit {}", itmName, " ID ={}", itmId);
            LOGGER.trace("Item price {}", itmPriceEdit);
            LOGGER.trace("Item size {}", itmSizeEdit);

            List<String> sizelistEdit = new ArrayList<String>(Arrays.asList(itmSizeEdit.split(",")));
            List<String> pricelistEdit = new ArrayList<String>(Arrays.asList(itmPriceEdit.split(",")));

            LOGGER.trace("AsList sizeList {}", sizelistEdit);
            LOGGER.trace("AsList size {}", sizelistEdit.size());
            LOGGER.trace("AsList price {}", pricelistEdit);

            List<Item> edittedList = new ArrayList<Item>();

            for (int a = 0; a < sizelistEdit.size(); a++) {
                Item itemList = new Item();
                itemList.setSize(sizelistEdit.get(a));
                itemList.setPrice(pricelistEdit.get(a));
                edittedList.add(itemList);
            }
            LOGGER.trace("edittedList {}", edittedList);
            LOGGER.trace("itemUpdate object {}", itemUpdate);
            LOGGER.trace("itemUpdate category {}", itemUpdate.getCategoryName());
            LOGGER.trace("itemUpdate sub-category {}", itemUpdate.getSubCategoryName());

            int i = item.updateItem(itemUpdate, edittedList);
            LOGGER.trace(String.valueOf(i), "  info i edit ", i);
            LOGGER.error("error in i edit item {}", i);
            if (i != 1) {
                JOptionPane.showMessageDialog(null, updateServerErr, "Error !",
                        JOptionPane.ERROR_MESSAGE);
                LOGGER.error("Server-side error in updating item " + itmName);
            } else {
                JOptionPane.showMessageDialog(null, updateSuccess + itmName, "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                LOGGER.trace("ItemUpdate category after {}", itemUpdate.getCategoryName());
                LOGGER.trace("ItemUpdate sub-category after {}", itemUpdate.getSubCategoryName());
                LOGGER.info("Updated item successfully {}", itmName);


                if (!imgFile.isEmpty()) {

                    LOGGER.error("empty file ?? {}", !imgFile.isEmpty());
                    try {
                        // Creating the directory to store file in server
                        String realPathtoUpload = context.getRealPath(serverPath);
                        LOGGER.trace("realPathtoUpload item img = , {} ", realPathtoUpload);
                        uploadFile(imgFile, realPathtoUpload, imgFileNm2);

                        //create a directory in local machine and upload imGE
                        uploadFile(imgFile, localPathtoUpload, imgFileNm2);
                        LOGGER.trace("localpathtoupload item image = , {} ", localPathtoUpload);
                    } catch (Exception ex) {
                        LOGGER.error("error in  getting image {}", ex);
                    }
                } else {
                    LOGGER.error("You failed to upload {}", imgFileNm2, " because the file was empty.");
                }
            }
        } catch (Exception er) {
            LOGGER.error("Exception " + er.getMessage());
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

        LOGGER.info("deleted Item from database {}", itemName);
        return item.deleteItem(itemName);
    }

    /**
     * controller method to load relevant subcategory for ajax calls
     */
    @RequestMapping(value = "/getSubcats", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> getSubcatList(@RequestParam("categoryNm") String categoryNm) {

        return subCategoryRepository.retrieveSubcatogories(categoryNm);
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
            File createFile = new File(dir.getAbsolutePath() + File.separator + fileName + ".jpg");//name of the image
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(createFile));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            LOGGER.error("error in  getting image ", e);
        }
    }


    /*
    * typeahead function calling method for item name
    **/
    @RequestMapping(value = "/typeahedItmNm", method = RequestMethod.GET)
    public
    @ResponseBody
    List<String> typeaheadName(HttpServletRequest request) {

        String cat = request.getParameter("cat");
        String subcat = request.getParameter("subcat");
        List<String> resultList = new ArrayList<String>();

        LOGGER.trace("cat in filter {}",cat);
        LOGGER.trace("subcat in filter {}",subcat);

        try {
            if ((subcat==null) || (subcat.equals(""))){
               if ((cat==null) || (cat.equals(""))){
                   resultList = item.selectNameList();
               }
               else
               {
                   List<Item> catList;
                   catList = categoryRepository.loadMenuItems(cat);
                   LOGGER.error("catList in filter {}", catList);

                   for (int a = 0; a < catList.size(); a++) {
                       String nm;
                       nm = catList.get(a).getItemName();
                       LOGGER.trace("nm in filtert {}", nm);
                       resultList.add(nm);
                   }
                   LOGGER.trace("resultList in item-cat filter : {}", resultList);
               }
            }
            else {
                List<Item> subcatList;
                subcatList = categoryRepository.loadMenuItems(subcat);
                LOGGER.error("subcatList in filter {}",subcatList);

                for (int b = 0; b < subcatList.size(); b++) {
                    String nm2 ;
                    nm2 = subcatList.get(b).getItemName();
                    LOGGER.error("nm in filtert {}",nm2);
                    resultList.add(nm2);
                    LOGGER.error("resultttt in {}",resultList);
                }

                LOGGER.error("resultList in item-subcat filter : {}",resultList);
            }
        } catch (Exception e) {
            LOGGER.error("error in typeahead: {}", e);
        }
        return resultList;
    }


    /*
   *reloading item table view on search & paginating basis
   * */
    @RequestMapping(value = "/loadSearchItem", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> loadSearchItem(HttpServletRequest request) {

        List<Map<String, Object>> itemDetails = null;
        try {
            String itmNm = request.getParameter("srchItmNm");
            String pgInit = request.getParameter("initPage");
            int initPg = Integer.parseInt(pgInit);
            String pgLimt = request.getParameter("pgLimit");
            int pgLimit = Integer.parseInt(pgLimt);

            LOGGER.trace("load item name 1 {}", itmNm);

            if (itmNm != null) {
                itemDetails = item.paginateSelectedItemDetails(itmNm, pgLimit, initPg);
                LOGGER.trace("selected item {}", itemDetails);
            } else {
                itemDetails = item.viewAllItemDetails(pgLimit, initPg);
                LOGGER.trace("load item {}", itemDetails);
            }
        } catch (Exception e) {
            LOGGER.error("error in loading item details : {}", e);
        }

        return itemDetails;
    }


    /*
    *getting record count for loading item table with pagination
    **/
    @RequestMapping(value = "/itemPaginationTable", method = RequestMethod.GET)
    public
    @ResponseBody
    int loadPagination() {
        LOGGER.trace("total item record count{}", item.count());
        return item.count();
    }

    /*
    *getting record count for loading item table with pagination
    **/
    @RequestMapping(value = "/itemSearchCount", method = RequestMethod.GET)
    public
    @ResponseBody
    int getSearchCount(@RequestParam("srchItmNm") String itmName) {

        LOGGER.trace("searched item record count{}", item.countSearchKey(itmName));
        return item.countSearchKey(itmName);
    }

    /*
    *getting record count for loading items by category with pagination
    **/
    @RequestMapping(value = "/catSearchCount", method = RequestMethod.GET)
    public
    @ResponseBody
    int getCatSearchCount(@RequestParam("catName") String catName) {

        LOGGER.trace("searched items by category record count{}", item.countItemDetailsByCategory(catName));
        return item.countItemDetailsByCategory(catName);
    }

    /*
    *getting record count for loading items by category with pagination
    **/
    @RequestMapping(value = "/subCatSearchCount", method = RequestMethod.GET)
    public
    @ResponseBody
    int getsubCatSearchCount(@RequestParam("subCatName") String subCatName) {

        LOGGER.trace("searched items by category record count{}", item.countItemDetailsBySubCategory(subCatName));
        return item.countItemDetailsBySubCategory(subCatName);
    }

   /*
   *reloading item table by filtering category search & paginating basis
   * */
    @RequestMapping(value = "/loadItemByCat", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> loadItemByCategory(HttpServletRequest request) {

        List<Map<String, Object>> itemDetailsCat = null;
        try {
            String itmSubcat = request.getParameter("srchItmSubcat");
            String pgInit = request.getParameter("initPage");
            int initPg = Integer.parseInt(pgInit);
            String pgLimt = request.getParameter("pgLimit");
            int pgLimit = Integer.parseInt(pgLimt);

            LOGGER.trace("load item name by subCat {}", itmSubcat);

            if (itmSubcat != null) {
                itemDetailsCat = item.retrieveItemDetailsBySubCategory(itmSubcat, pgLimit, initPg);
                LOGGER.trace("selected item by subCat {}", itemDetailsCat);
            } else {
                itemDetailsCat = item.viewAllItemDetails(pgLimit, initPg);
                LOGGER.trace("load item by subcat {}", itemDetailsCat);
            }
        } catch (Exception e) {
            LOGGER.error("error in loading item details : {}", e);
        }

        return itemDetailsCat;
    }
}
