package hsl.devspace.app.admin.categorymanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepositoryImpl subCategoryRepository;


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
    List<String> viewBannedStaff(@ModelAttribute("subCategory") Category subCategory,
                                 @RequestParam("catName") String catName){

        LOG.error("Category selected in populateCategry {}",catName);
        List<String> outb = categoryRepository.viewSubCategories(subCategory.getCategoryName());
        LOG.error("recieved sub category list",outb);

        return outb;
    }

    @RequestMapping(value="/addCategory",method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("newCategory")  Category newCategory
                                     ) throws SQLIntegrityConstraintViolationException {

        int i=categoryRepository.add(newCategory);
        if (i == 0)
            JOptionPane.showMessageDialog(null, insertError);// put separate error pages
        else {
//                    return new ModelAndView(new RedirectView("successPage"));
            JOptionPane.showMessageDialog(null,insertSuccess);
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
