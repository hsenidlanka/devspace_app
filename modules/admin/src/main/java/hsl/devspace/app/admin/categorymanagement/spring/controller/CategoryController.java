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

    /** reading the ValidationMessages property file using annotations**/
    @Value("${insert.server.error}")
    private String insertError;

    @Value("${insert.category.success}")
    private String insertSuccess;

    @Value("${insert.subcategory.success}")
    private String insertSubSuccess;

   /** reading system.properties file using annotations **/
   @Value("${admin.categoryimage.server.location}")
   private String serverPath;

    @Value("{admin.categoryimage.localmachine.location}")
    private String localPathtoUpload;

    @Autowired
    private CategoryRepository categoryRepository, subcategoryRepository;

    @Autowired
    ServletContext context;



///////////////////////////////////////////////////// CATEGORY INSERT HANDLER METHODS  ///////////////////////////////////////


    //hahndler method to display the Category Add form page
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddCateg(Model model){

        List<String> listCat = categoryRepository.selectCategoryNames();
        LOG.error("Category Controller category list {}",listCat);
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
                                     @RequestParam("subcategory_des[]") String[] subcategory_des
                                    ) throws SQLIntegrityConstraintViolationException {

        //set the creator in category object
        categoryObject.setCreator("admin");
        LOG.error("Add Category category object {}", categoryObject);

        //for the image file uploaded
        MultipartFile imgFile=categoryObject.getImageUrl();
        String imgFileName=imgFile.getOriginalFilename();
        LOG.error("image file name  =" + imgFile.getOriginalFilename());

        int catAdd=categoryRepository.add(categoryObject);
        if(catAdd ==1) {
            for (int j=0;j< subcategory_name.length;j++) {

                String subcatName=subcategory_name[j];
                String subcatDescription= subcategory_des[j];
                LOG.info("subcat name {} and des:{}",subcatName,subcatDescription);

                //set the properties for the sucategory
                categoryObject.setSubCategoryName(subcatName);
                categoryObject.setSubcatDescription(subcatDescription);
                categoryObject.setCreator("admin");

                int subcatAdd=subcategoryRepository.add(categoryObject);
                LOG.info("subcat add return is: {}",subcatAdd);
                if (subcatAdd ==0) {
                    JOptionPane.showMessageDialog(null, insertError);
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, insertSuccess);

            //to upload the image
                if (!imgFile.isEmpty()) {
                    //get category name
                    String catName= categoryObject.getCategoryName();

                    //create a directory in server and upload the image there
                    String realPathtoUpload = context.getRealPath(serverPath);
                    LOG.info("realPathtoUpload " + realPathtoUpload);
                    uploadFile(imgFile,realPathtoUpload,catName);

                    //create a directory in local machine and upload the image there
                    uploadFile(imgFile,localPathtoUpload,catName);

                }else {
                    LOG.error("You failed to upload " + imgFileName + "because the file was empty.");
                }
        } else if(catAdd == 0)
            JOptionPane.showMessageDialog(null, insertError);// put separate error pages

    return new ModelAndView(new RedirectView("add"));
    }

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

    //handler method to view the Category View page
    @RequestMapping(value = "/view/subCategory", method = RequestMethod.GET)
    public ModelAndView showSubCategoryList(){
        return new ModelAndView("category_management/subcategoryView", "command",new Category());

    }

    //handler method to retrieve the details of categories to view
    @RequestMapping(value = "/view/categoryTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewCategories(@ModelAttribute("category")  Category category){

        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();
        List<Category> categoryList= categoryRepository.selectAll();

        for (int i=0;i<categoryList.size();i++){
            category=categoryList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", category.getCategory_id());
            map.put("name", category.getCategoryName());
            map.put("description",category.getCatDescription());
            map.put("creator", category.getCreator());
            map.put("status", category.getStatus());


            LOG.info("newCategory {}", category);
            outc.add(map);
            LOG.info("out {}",outc);
        }
        return outc;
    }

    //handler method to retrieve the details of sub-categories to view
    @RequestMapping(value = "/view/subcategoryTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewSubCategories(@ModelAttribute("subcategory")  Category subcategory){

        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();
        List<Category> subcategoryList= subcategoryRepository.selectAll();

        for (int i=0;i<subcategoryList.size();i++){
            subcategory=subcategoryList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", subcategory.getCategory_id());
            map.put("name", subcategory.getCategoryName());
            map.put("description", subcategory.getCatDescription());
            map.put("creator", subcategory.getCreator());
            map.put("status", subcategory.getStatus());


            LOG.info("newCategory {}", subcategory);
            outc.add(map);
            LOG.info("out {}",outc);
        }
        return outc;
    }


///////////////////////////////////////////////////// CATEGORY EDIT HANDLER METHODS  ///////////////////////////////////////

//handler method to delete a category record
@RequestMapping(value = "/edit", method = RequestMethod.GET)
public @ResponseBody int editCategory(@RequestParam("name") String name, @RequestParam("description") String description){

    int i=categoryRepository.update(name,description);
    return i;
}
}
