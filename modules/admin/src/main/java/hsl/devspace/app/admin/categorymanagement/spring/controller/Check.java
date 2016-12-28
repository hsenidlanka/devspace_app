package hsl.devspace.app.admin.categorymanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * subCategory view page operations are included
 * in this Controller class
 */
@Controller
public class Check {
//    private static final Logger LOG = LoggerFactory.getLogger(Check.class);
    private static final Logger LOG = LogManager.getLogger(CategoryController.class);


    /** reading the ValidationMessages property file using annotations**/
    @Value("${update.subcategory.success}")
    private  String updateSuccess;

    @Value("${update.subcategory.error}")
    private String updateFail;

    @Autowired
    private CategoryRepository  subcategoryRepository;


    //handler method invoked as the Category name in the table is clicked
    @RequestMapping(value = "/subCategory/{name}", method = RequestMethod.GET)
    public String showSubCategoryList(@PathVariable String name,Map<String, Object> model){
        LOG.info("path variable: {}",name);


        model.put("command",new Category());
        model.put("catName",name);
        return "category_management/subcategoryView";
    }



    //handler method to retrieve the edit details in a sub-category record
    @RequestMapping(value = "/subcategory/edit", method = RequestMethod.GET)
    public @ResponseBody  Map<String, Object> editSubCategory(@RequestParam("subcatId") String subcatId,
                                                              @RequestParam("catValue") String catValue){

        LOG.error("category value in sub-category view page:{}",catValue);
        int subcatIdInt = Integer.parseInt(subcatId);
        Category category=subcategoryRepository.selectSubCategoryDetail(subcatIdInt);
        LOG.info("the selected SUB category detail{}", category);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", category.getSubCategoryId());
        map.put("name",category.getSubCategoryName());
        map.put("description",category.getSubcatDescription());
        map.put("creator",category.getCreator());
        map.put("catName",catValue);

        LOG.info("the SUB CAT map in edit form{}", map);
        return map;
    }



    //handler method for sending the updated details of a subcategory edited to the database
    @RequestMapping(value="/editSubCategory",method=RequestMethod.POST)
    public ModelAndView saveEditSubCategory(@ModelAttribute("editSubCategory") Category editSubCategory) {

        String n1=editSubCategory.getSubCategoryName();
        String n2=editSubCategory.getSubcatDescription();
        int n3=editSubCategory.getSubCategoryId();


        LOG.info("EDIT SUB_CATEGORY name {}", n1);
        LOG.info("EDIT SUB_CATEGORY description {}", n2);
        LOG.info("EDIT SUB_ CATEGORY id {}", n3);
        LOG.info("EDIT SUB_CATEGORY object {}", editSubCategory);

        //function to update the category detail except image
        int i=subcategoryRepository.updateCategory(editSubCategory);
        LOG.info("EDIT SUB_CATEGORY i {}", i);

        if (i == 1)
            JOptionPane.showMessageDialog(null, updateSuccess);
        else
            JOptionPane.showMessageDialog(null,updateFail);

        return new ModelAndView(new RedirectView("/admin/subCategory/"+editSubCategory.getCategoryName()));
    }


    //handler method to delete the subcategory
    @RequestMapping(value = "/subcategory/delete", method = RequestMethod.GET)
    public @ResponseBody int deleteCategory(@RequestParam("subcatName") String subcatName){

        LOG.info("subcategory name to delete:{}", subcatName);

        //delete the subcategory list obtained
        int subcatReturn=subcategoryRepository.delete(subcatName);
        return subcatReturn;
    }

///////////////////////////////////////////////////// SUB-CATEGORY VIEW PAGINATION WITH TYPEAHEAD ///////////////////////////////////////


    /*
   * typeahead function calling method for sub-category name
   **/
    @RequestMapping(value = "/typeahedSubCategoryNm", method = RequestMethod.GET)
    public @ResponseBody List<String> typeaheadName(@RequestParam("catName") String catName){
        LOG.error("SUBCAT typeahead catName {}",catName);
        List<String> subcatList= subcategoryRepository.viewSubCategories(catName);
        LOG.error("SUBCAT typeahead namelist {}",subcatList);
        return subcatList;
    }


    /** handler method to retrieve the details of sub-categories to view
     * based on category selected
     * @param data
     * @return  List<Map<String, Object>>
     */

    @RequestMapping(value = "/view/subcategoryTable/{data}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewSubCategoriesAll(@PathVariable String data,
                                                                        @RequestParam("initPage") String initPage,
                                                                        @RequestParam("pgLimit") String  pgLimit){

        LOG.error("Inside the subcategory table load method");
        LOG.info("The selected SUB-CATEGORY transferred to controller{}", data);

        //cast the initial page and page limits in pagination to integers
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pgLimit);

        LOG.error("page limits{} {}", initPage, limitPg);

        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();
        Category subcategory = null;

            LOG.error("SubCategory name not selected");
            List<Category> categoryList2= subcategoryRepository.viewSubCategoriesforCategory(data, limitPg, initPg);
            LOG.error("sub category list returned from query{}",categoryList2);
            outc=unSerializeSubCat(subcategory, categoryList2);

        return outc;
    }

    @RequestMapping(value = "/view/subcategoryTable/search/{data}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewSubCategoriesAll(@PathVariable String data,
                                                                        @RequestParam("initPage") String initPage,
                                                                        @RequestParam("pgLimit") String  pgLimit,
                                                                        @RequestParam("searchSubCatNm") String searchSubCatNm){

        LOG.error("Inside the subcategory search data load method");
        LOG.info("The selected SUB-CATEGORY transferred to controller{}", data);

        LOG.error("searchSubCatName {}",searchSubCatNm);

        //cast the initial page and page limits in pagination to integers
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pgLimit);

        LOG.error("page limits{} {}", initPage, limitPg);

        List<Map<String, Object>> outcs = new ArrayList<Map<String, Object>>();
        Category subcategory = null;

            LOG.error("SubCategory name SELECTED");
            List<Category> categoryList1= subcategoryRepository.selectSubCategoryForCategoryTypeAhead(data, searchSubCatNm,limitPg,initPg);
            LOG.error("subcategory with name selected {}",categoryList1);
            outcs=unSerializeSubCat(subcategory, categoryList1);

        return outcs;
    }


    private List<Map<String, Object>> unSerializeSubCat(Category subcategory,  List<Category> subcategoryList ){
        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();

        for (Category aSubcategoryList : subcategoryList) {
            subcategory = aSubcategoryList;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", subcategory.getSubCategoryId());
            map.put("name", subcategory.getSubCategoryName());
            map.put("description", subcategory.getSubcatDescription());
            map.put("creator", subcategory.getCreator());
            map.put("created_date", subcategory.getCreatedDate());
            map.put("cat_id", subcategory.getCategory_id());
            outc.add(map);
        }
        return outc;
    }

    /*
*getting record count for loading item table with pagination
**/
    @RequestMapping(value = "/SubCategoryPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPagination(@RequestParam("catName") String catName){

        LOG.error("Category Count is {}",subcategoryRepository.countForCat(catName));
        return subcategoryRepository.countForCat(catName);
    }




}
