package hsl.devspace.app.admin.categorymanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;

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


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showAddCateg(){
        return  new ModelAndView("category_management/categoryAdd",  "command", new Category()); }



    @RequestMapping(value="/addCategory",method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("newCategory")  Category newCategory,
                                     @RequestParam("radioName") String userType) throws SQLIntegrityConstraintViolationException {

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
