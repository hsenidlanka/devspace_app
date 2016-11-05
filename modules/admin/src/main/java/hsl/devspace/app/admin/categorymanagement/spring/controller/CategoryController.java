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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOG = LogManager.getLogger(CategoryController.class);

    /** reading the ValidationMessages property file using annotations**/
    @Value("${insert.server.error}")
    private String insertError;

    @Value("${insert.category.success}")
    private String insertSuccess;

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
        LOG.error("str {}",str);

        List<String> outb =subcategoryRepository.retrieveSubcatogories(str);
        LOG.error("recieved sub category list {}",outb);

        return outb;
    }

    //handler method to insert the new Category data to database
    @RequestMapping(value="/addCategory")
    public ModelAndView saveOrUpdate(@ModelAttribute("categoryObject")  Category categoryObject,
                                     @ModelAttribute("subcategoryObject") Category subcategoryObject
                                    )
                                     throws SQLIntegrityConstraintViolationException {

        categoryObject.setCreator("admin");
        LOG.trace("Add Category category object {}", categoryObject);

        //for the image file uploaded
        MultipartFile imgFile=categoryObject.getImageUrl();
        String imgFileName=imgFile.getOriginalFilename();
        LOG.error("multipart file  =" + imgFile);

        int i=categoryRepository.add(categoryObject);

        if (i == 0)
            JOptionPane.showMessageDialog(null, insertError);// put separate error pages
        else {
            subcategoryObject.setCreator("admin");
            LOG.trace("Add Category Sub-category object {}", subcategoryObject);

            int j=subcategoryRepository.add(subcategoryObject);
            if (j ==1) {
                JOptionPane.showMessageDialog(null, insertSuccess);

                   /*
                    * save images to directory*/
                if (!imgFile.isEmpty()) {
                    try {
                        byte[] bytes = imgFile.getBytes();

                        // Creating the directory to store file
                        String rootPath = "/themes/hsenid/images/categories/";
                        LOG.error("root path for img =" + rootPath);

                        String realPathtoUpload = context.getRealPath(rootPath);
                        LOG.error("realPathtoUpload " + realPathtoUpload);

                        File dir = new File(realPathtoUpload);
                        if(!dir.exists())
                            dir.mkdirs();

                        LOG.error("Dir.... =", dir.getAbsolutePath());
                        LOG.error("made dir " + dir);

                        // create local directory place
                        String localPathtoUpload ="/home/hsenid/Documents/MAVEN/devspace_app/modules/admin/src/main/" +
                                "webapp/themes/hsenid/images/categories/";
                        LOG.error("realPathtoUpload " + localPathtoUpload);

                        File dir2 = new File(localPathtoUpload);
                        if(!dir2.exists())
                            dir2.mkdirs();

                        LOG.error("made dir " + dir2);


                        //get category name
                        String catName= categoryObject.getCategoryName();

                        //create the file on server
                        File serverFile = new File(dir.getAbsolutePath() + File.separator + catName + ".png");//name of the image
                        LOG.error("serverFile = " + serverFile);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        stream.close();

                        LOG.error("image save to server, Location= " + serverFile.getAbsolutePath());


                        //create the file on local machine
                        File localFile = new File(dir2.getAbsolutePath() + File.separator + catName + ".png");//name of the image
                        LOG.error("serverFile = " + localFile);
                        BufferedOutputStream stream2 = new BufferedOutputStream(
                                new FileOutputStream(localFile));
                        stream2.write(bytes);
                        stream2.close();

                        LOG.error("image save to local, Location= " + localFile.getAbsolutePath());
                        LOG.error("You successfully uploaded file= " + imgFileName);

                    }
                    catch (Exception ex) {
                        LOG.error("error in  getting image ", ex);
                    }
                }else {
                    LOG.error("You failed to upload " + imgFileName + " because the file was empty.");
                }
            }
        }
        return new ModelAndView(new RedirectView("add"));

    }

///////////////////////////////////////////////////// CATEGORY VIEW HANDLER METHODS  ///////////////////////////////////////

    /** to handle the active users
     * includes active staff and customer users**/

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showCategoryList(){
        return new ModelAndView("category_management/categoryView", "command",new Category());

    }


}
