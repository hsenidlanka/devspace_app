package hsl.devspace.app.admin.categorymanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/items")
public class CategoryController {

    private static final Logger LOG = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryRepository categoryRepository;

  /*  @Autowired
    private ItemRepository item;*/


 /*   @RequestMapping(value = "/add", method = RequestMethod.GET)
   public ModelAndView showAddItem(ModelAndView modelAndView){
        Map<String, Object> model = new HashMap<String, Object>();
        List<Map<String, Object>> listCat = categoryRepository.viewCategoryList();
        model.put("listCat",listCat);
        model.put("command",new Item());
        return new ModelAndView("addItem", "model", model);
    }*/

   /* @RequestMapping(value = "/viewList", method = RequestMethod.GET)
    public ModelAndView getCatList() {
        ModelAndView model = new ModelAndView("index");
        List<Map<String, Object>> listCat = category.viewCategoryList();
        model.addObject("listCat", listCat);
        return  new ModelAndView("catList", "getCategory", new Item()); }
*/
    /*
    * Add new category
    **/

    @RequestMapping(value = "/addCat", method = RequestMethod.GET)
    public ModelAndView showAddCateg(){
       /* ModelAndView model = new ModelAndView();
        List<Map<String, Object>> listCat = category.viewCategoryList();
        model.addObject("listCat", listCat);*/
        return  new ModelAndView("categAdd", "addCategory", new Category()); }

    @RequestMapping(value = "add_category")
    public ModelAndView addCategory(@ModelAttribute("addCategory") Category newCateg) throws SQLIntegrityConstraintViolationException {

        ModelAndView model = new ModelAndView();

        model.setViewName("addCategory");

        return model;
    }

    /*
    * Get category list
    **/
   // @RequestMapping(value = "getCatLis")
    /*public ModelAndView retrieveCategoryTypes(@ModelAttribute("viewCatg") Category viewCat) throws SQLIntegrityConstraintViolationException {
       ModelAndView model = new ModelAndView("addItem");

        List<Map<String, Object>> listCat = category.viewCategoryList();
      //  model.addObject("list", categoryName);
      //  model.addObject(String.valueOf(listCat), viewCat.getCategoryName() );
        model.addObject("list", listCat );

        //return listCat;
       return model;
    }*/
}
