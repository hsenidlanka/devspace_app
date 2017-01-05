package hsl.devspace.app.admin.categorymanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOG = LogManager.getLogger(CategoryController.class);
//    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);


    /** reading the ValidationMessages property file using annotations**/
    @Value("${insert.server.error}")
    private String insertError;

    @Value("${insert.category.success}")
    private String insertSuccess;

    @Value("${insert.subcategory.success}")
    private String insertSubSuccess;

    @Value("${update.category.success}")
    private  String updateSuccess;

    @Value("${update.category}")
    private String updateFail;

   /** reading system.properties file using annotations **/
   @Value("${admin.categoryimage.server.location}")
   private String serverPath;

    @Value("${admin.categoryimage.localmachine.location}")
    private String localPathtoUpload;

    @Autowired
    private CategoryRepository categoryRepository, subcategoryRepository;

    @Autowired
    ServletContext context;

    private int subcatAdd=0;



///////////////////////////////////////////////////// CATEGORY INSERT HANDLER METHODS  ///////////////////////////////////////


    //hahndler method to display the Category Add form page
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddCateg(Model model){

        List<String> listCat = categoryRepository.selectCategoryNames();
        LOG.info("Category Controller category list {}", listCat);
        model.addAttribute("listCat", listCat);
        model.addAttribute("command", new Category());

        return "category_management/totalAdd";
    }


    //handler method to retrieve the details of a particular banned staff user
    @RequestMapping(value = "/populateSubCategory", method = RequestMethod.GET)
    public @ResponseBody
    List<String> populateSubcategoryList(@RequestParam("categorySel") String categorySel){

        String str=categorySel.replaceAll("\\s","");
        LOG.info("str {}", str);

        List<String> outb =subcategoryRepository.retrieveSubcatogories(str);
        LOG.trace("recieved sub category list {}", outb);

        return outb;
    }

    //handler method to insert the new Category data to database
    @RequestMapping(value="/addCategory")
    public ModelAndView saveOrUpdate(@ModelAttribute("categoryObject")  Category categoryObject,
                                     @RequestParam("subcategory_name[]") String[] subcategory_name,
                                     @RequestParam("subcategory_des[]") String[] subcategory_des,
                                     HttpServletRequest request)
                                     throws SQLIntegrityConstraintViolationException {


        //set the creator in category object using session attributes
        HttpSession session = request.getSession();
        String userId =(String)session.getAttribute("username");
        LOG.error("The user logged in is: {}",userId);
        categoryObject.setCreator(userId);
        LOG.info("Add Category category object {}", categoryObject);

        //for the image file uploaded
        MultipartFile imgFile=categoryObject.getImageUrl();

            //to upload the image
                if (!imgFile.isEmpty()) {
                    //get category name
                    String catName= categoryObject.getCategoryName();
                    String realPathtoUpload = context.getRealPath(serverPath);
                    //create a directory in server and upload the image there
                    LOG.info("realPathtoUpload " + realPathtoUpload);
                    uploadFile(imgFile,realPathtoUpload,catName);

                    //create a directory in local machine and upload the image there
                    uploadFile(imgFile,localPathtoUpload,catName);

                    //now image file upload is complete
                    LOG.info("image file name  =" + imgFile.getOriginalFilename());

                    //set the image name equal to that of category name
                    categoryObject.setImage(categoryObject.getCategoryName()+".jpg");
                    int catAdd=categoryRepository.add(categoryObject);

                    if(catAdd ==1) {
                        for (int j = 0; j < subcategory_name.length; j++) {

                            String subcatName = subcategory_name[j];
                            String subcatDescription = subcategory_des[j];
                            LOG.info("subcat name {} and des:{}", subcatName, subcatDescription);

                            //set the properties for the sucategory
                            categoryObject.setSubCategoryName(subcatName);
                            categoryObject.setSubcatDescription(subcatDescription);
                            categoryObject.setCreator("admin");

                            subcatAdd = subcategoryRepository.add(categoryObject);
                            LOG.info("subcat add return is: {}", subcatAdd);
                            if (subcatAdd == 0) {
                                break;
                            }
                        }
                    }
                    if (catAdd ==0 || subcatAdd == 0)
                            JOptionPane.showMessageDialog(null, insertError);
                    else
                            JOptionPane.showMessageDialog(null, insertSuccess);

                    return new ModelAndView(new RedirectView("add"));

                }else {
                    LOG.error("You failed to upload because the file was empty.");
                    JOptionPane.showMessageDialog(null, "Please select a Category Image");

                    return new ModelAndView("category_management/totalAdd", "command",categoryObject);

                }
    }

    /**
    ****function ot upload a new Category image
    **/
    private void uploadFile( MultipartFile image, String filePath, String fileName){
        try {
            byte[] bytes = image.getBytes();
            //directory made
            File dir = new File(filePath);
            if(!dir.exists())
                dir.mkdirs();

            //file made
            File createFile = new File(dir.getAbsolutePath() + File.separator + fileName + ".jpg");//name of the image
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(createFile));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            LOG.error("error in  getting image ", e);
        }
    }


    //handler method to insert the new Category data to database
    @RequestMapping(value="/addSubCategory")
    public ModelAndView addSubCategory( @ModelAttribute("subcategoryObject") Category subcategoryObject,
                                     @RequestParam("catName") String catName,
                                     @RequestParam("subcategory_name[]") String[] subcategory_name,
                                     @RequestParam("subcategory_des[]") String[] subcategory_des) throws SQLIntegrityConstraintViolationException {

        String catName2=catName.replaceAll("\\s", "");
        LOG.info(catName2);
        LOG.info(subcategoryObject.getCategoryName());

        for (int j=0;j<subcategory_name.length;j++) {
            String subcatName=subcategory_name[j];
            String subcatDescription= subcategory_des[j];

            //set the object values
            subcategoryObject.setSubCategoryName(subcatName);
            subcategoryObject.setSubcatDescription(subcatDescription);
            subcategoryObject.setCreator("admin");
            subcategoryObject.setCategoryName(catName2);

            LOG.info("subcategory name list {}", subcategory_name[j]);
            LOG.info("subcategory des {}", subcategory_des[j]);

            int i = subcategoryRepository.add(subcategoryObject);
            LOG.info("I value {}",i);
            if(i ==1) {
                continue;
            }
            else if (i ==0)
                JOptionPane.showMessageDialog(null, insertError);
                break;
        }
        JOptionPane.showMessageDialog(null, insertSubSuccess);
        return new ModelAndView(new RedirectView("add"));
    }

///////////////////////////////////////////////////// CATEGORY VIEW HANDLER METHODS  ///////////////////////////////////////

    /** to handle category and subcategory
     * view functions**/

    //handler method to view the Category View page
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showCategoryList(){
        return new ModelAndView("category_management/categoryView", "command",new Category());

    }

///////////////////////////////////////////////////// CATEGORY VIEW PAGINATION WITH TYPEAHEAD ///////////////////////////////////////

    /*
   * typeahead function calling method for category name
   **/
    @RequestMapping(value = "/typeahedCategoryNm", method = RequestMethod.GET)
    public @ResponseBody List<String> typeaheadName(){
        return categoryRepository.selectCategoryNames();
    }


    //handler method to retrieve the details of categories to view
    @RequestMapping(value = "/view/categoryTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewCategories(@ModelAttribute("category")  Category category,
                                                                  @RequestParam("initPage") String initPage,
                                                                  @RequestParam("pgLimit") String  pgLimit) {

        LOG.trace("Inside the category table load method");

        //cast the initial page and page limits in pagination to integers
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pgLimit);

        LOG.info("page limits{} {}", initPage, limitPg);

        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();

            LOG.trace("Category name not selected");
            List<Category> categoryList2= categoryRepository.paginateSelectAll(limitPg,initPg);
            LOG.info("CATEGORY LIST WITHOUT SEARCH NAME {}", categoryList2);
            outc=unSerializeCat(category,categoryList2);
        return outc;
    }


    //handler method to retrieve the details of categories to view in typehead filter
    @RequestMapping(value = "/view/categoryTable/catName", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewCategoriesNameFiltered(@ModelAttribute("category")  Category category,
                                                                              @RequestParam("initPage") String initPage,
                                                                              @RequestParam("pgLimit") String  pgLimit,
                                                                              @RequestParam("searchCatNm") String searchCatNm) {

        LOG.trace("Inside the category table load method with name search");
        LOG.info("searchCatName{}",searchCatNm);

        //cast the initial page and page limits in pagination to integers
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pgLimit);

        LOG.info("page limits{} {}", initPage, limitPg);

        List<Map<String, Object>> outc2 = new ArrayList<Map<String, Object>>();

            LOG.trace("Category name SELECTED");
            List<Category> categoryList1= categoryRepository.selectAllTypeAhead(searchCatNm,limitPg,initPg);
            outc2=unSerializeCat(category,categoryList1);
            LOG.info("CATEGORY LIST AFTER SERIALIZE {}",outc2);
        return outc2;
    }

    private List<Map<String, Object>> unSerializeCat(Category category,  List<Category> catList ){
        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();

        for (Category aCategoryList : catList) {
            category = aCategoryList;
            //to change the visibility to meaningful word
            String visibility=category.getStatus();
            String v1=null;
            if(visibility.equals("1")) {
                v1 = "Yes";
            }else {
                v1 = "No";
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", category.getCategory_id());
            map.put("name", category.getCategoryName());
            map.put("description", category.getCatDescription());
            map.put("creator", category.getCreator());
            map.put("created_date", category.getCreatedDate());
            map.put("status", v1);

            LOG.info("newCategory {}", category);
            outc.add(map);
            LOG.info("out {}", outc);
        }
        return outc;
    }

    /*
*getting record count for loading item table with pagination
**/
    @RequestMapping(value = "/CategoryPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPagination(){

        LOG.info("Category Count is {}",categoryRepository.count());
        return categoryRepository.count();
    }





///////////////////////////////////////////////////// CATEGORY EDIT HANDLER METHODS  ///////////////////////////////////////

//handler method to retrieve the edit details in a category record
@RequestMapping(value = "/edit", method = RequestMethod.GET)
public @ResponseBody  Map<String, Object> editCategory(@RequestParam("id") int id){

    LOG.info("the selected category id{}", id);

    Category category=categoryRepository.selectCategoryDetail(id);
    LOG.info("the selected category detail{}", category);

    Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", category.getCategory_id());
        map.put("name",category.getCategoryName());
        map.put("description",category.getCatDescription());
        map.put("creator",category.getCreator());
        map.put("image",category.getImage());
        map.put("status",category.getStatus());
        LOG.error("The Category edit map{}", map);

    return map;
}

//handler method for sending customer edit form data to database
@RequestMapping(value="/editCategory",method=RequestMethod.POST)
public ModelAndView saveEditCategory(@ModelAttribute("editCategory") Category editCategory,
                                     @RequestParam("catName") String oldCatName) {

    LOG.error("OLLLLDD CAT name {}", oldCatName);
    String catName=editCategory.getCategoryName();
    LOG.error("EDIT CATEGORY name {}", catName);
    LOG.error("Edit Category object {}",editCategory);

    editCategory.setImage(editCategory.getCategoryName()+".jpg");
    String n5=editCategory.getImage();
    LOG.info("EDIT CATEGORY New set image naem {}", n5);
        //function to update the category detail except image
        int i=categoryRepository.updateCategory(editCategory);
        LOG.info("EDIT CUSTOMER i {}", i);
        if (i == 1) {
            //create a directory in server and upload the image there
            String realPathtoUpload = context.getRealPath(serverPath);
            LOG.info("realPathtoUpload " + realPathtoUpload);
            MultipartFile imgFile=editCategory.getImageUrl();
            LOG.error("image file MULTIPART {}",imgFile);
            //function to update the existing Category image name or both
           updateCatImage(realPathtoUpload, oldCatName, catName, imgFile);
            JOptionPane.showMessageDialog(null, updateSuccess);
        }else
            JOptionPane.showMessageDialog(null,updateFail);

    return new ModelAndView(new RedirectView("/admin/category/view"));
}
    /***
    *methodto update the Category image accoering to the Category update changes
     *
    ***/
    private void updateCatImage(String serverPath, String oldCatName, String newCatName, MultipartFile imageUploaded){
        //old and new files at local location
        LOG.error("inside catImage update method");
        File localFileOld = new File(localPathtoUpload + oldCatName + ".jpg");
        LOG.error("localFileOld :{}",localFileOld );
        File localFileNew = new File(localPathtoUpload + newCatName + ".jpg");
        LOG.error("localFileNew :{}",localFileNew );

        //old and new files at server location
        File serverFileOld = new File(serverPath + oldCatName + ".jpg");
        LOG.error("serverFileOld :{}",serverFileOld );
        File serverFileNew = new File(serverPath + newCatName + ".jpg");
        LOG.error("serverFileNew :{}",serverFileNew);

        //in case new file is not uploaded, only file renaming is done according to the edited category name

        if(imageUploaded== null) {
            //rename the file at local location
            boolean local = localFileOld.renameTo(localFileNew);
            LOG.error("local file rename status {}", local);
            //rename the file at the server
            boolean server = serverFileOld.renameTo(serverFileNew);
            LOG.error("server file rename status {}", server);

            //in case new file is Uploaded, initial file is deleted and upload the new file with category name
        }else {
            LOG.error("image upload original name: {}",imageUploaded.getOriginalFilename());
            //delete old one at local
            boolean localStatus=localFileOld.delete();
            if(localStatus){
                uploadFile(imageUploaded,localPathtoUpload,newCatName);
                LOG.error("local file{} uploadedsuccessfully",newCatName);
            }else
                LOG.error("local file delete operation failed");

            //delete old at server
            boolean serverStatus=serverFileOld.delete();
            if (serverStatus) {
                uploadFile(imageUploaded, serverPath, newCatName);
                LOG.error("server file{} deleted successfully",newCatName);
            }else
                LOG.error("server file delete operation failed");
        }
    }


///////////////////////////////////////////////////// CATEGORY DELETE HANDLER METHODS  ///////////////////////////////////////
@RequestMapping(value = "/delete", method = RequestMethod.GET)
public @ResponseBody int deleteCategory(@RequestParam("catName") String catName){

    LOG.info("category name to delete:{}", catName);

    //retrieve the subcategory list for that category
    List<String> subcatList=categoryRepository.viewSubCategories(catName);
    LOG.info("subcategory list:{}", subcatList);

    //delete the subcategory list obtained
    for (int i=0; i< subcatList.size();i++){
        int subcatReturn=subcategoryRepository.delete(subcatList.get(i));
        if(subcatReturn==0)
            break;
    }
    //finally delete the category
    int catdeleteReturn=categoryRepository.delete(catName);
    LOG.info("delete result:{}", catdeleteReturn);

    File localFileOld = new File(localPathtoUpload + catName + ".jpg");
    File serverFileOld = new File(serverPath + catName + ".jpg");

    if(catdeleteReturn ==1){
        boolean d1=localFileOld.delete();
        if(d1 == true){
            LOG.info("local category image deleted successfully");
        }else
            LOG.error("local category image deletion failed");

        boolean d2=serverFileOld.delete();
        if (d2== true)
            LOG.info("server category image deleted successfully");
        else
            LOG.error("server category image deletion failed");
    }else
        LOG.error("Error in deleting category form the database");

    return catdeleteReturn;
}
}