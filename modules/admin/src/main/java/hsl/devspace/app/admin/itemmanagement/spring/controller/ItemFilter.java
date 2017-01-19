package hsl.devspace.app.admin.itemmanagement.spring.controller;

/*
 * Controller classes for handling filters of item searching
 **/

import hsl.devspace.app.corelogic.repository.category.CategoryRepository;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/itemFilters")
public class ItemFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemFilter.class);

    @Autowired
    private ItemRepository item;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepositoryImpl subCategoryRepository;


    /*
    * load category list from database
    **/
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public
    @ResponseBody
    List<String> gatCatList() {
        LOGGER.trace("get Cat List in item filter{}", categoryRepository.selectCategoryNames());
        List<String> listCat = categoryRepository.selectCategoryNames();
        return listCat;
    }


    /*
    * load sub-category list from database
    * */
    @RequestMapping(value = "/subcategoryList", method = RequestMethod.GET)
    public
    @ResponseBody
    List<String> gatSubCatList(HttpServletRequest request) {

        String categoryNm = request.getParameter("catName");
        LOGGER.trace("category name for item filter: {}", categoryNm);

        List<String> subCat = null;
        try {
            if (categoryNm == null) {
                subCat = subCategoryRepository.selectCategoryNames();
                LOGGER.trace("Sub-cat List in item filter 2{}", subCat);
            } else {
                subCat = subCategoryRepository.retrieveSubcatogories(categoryNm);
                LOGGER.trace("Sub-cat List in item filter with category name{}", subCat);
            }
        } catch (Exception e) {
            LOGGER.error("Error in loading sub-cat list {}", e);
        }
        return subCat;
    }

    /*
    *reloading item table view on search & paginating basis
    * */
    @RequestMapping(value = "/loadSearchItem2", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> loadSearchItem2(HttpServletRequest request) {

        List<Map<String, Object>> itemDetails = null;
        try {
            String itmNm = request.getParameter("srchItmNm");
            String catNm = request.getParameter("cat");
            String subCat = request.getParameter("subcat");

            String pgInit = request.getParameter("initPage");
            int initPg = Integer.parseInt(pgInit);
            String pgLimt = request.getParameter("pgLimit");
            int pgLimit = Integer.parseInt(pgLimt);

            LOGGER.trace("load item, cat, subcat name 2 {}", itmNm+" "+catNm+" "+subCat);

            if((subCat == null) || (subCat.equals(""))) {
                if((itmNm==null) || (itmNm.equals(""))){
                    itemDetails = item.retrieveItemDetailsByCategory(catNm,pgLimit,initPg);
                }
                else{
                    itemDetails = item.retrieveItemDetailsForSearchByCategory(itmNm,catNm,pgLimit, initPg);
                }
                LOGGER.trace("selected item details with cat {}", itemDetails);
            }
            else {
                if((itmNm==null) || (itmNm.equals(""))){
                    itemDetails = item.retrieveItemDetailsBySubCategory(subCat,pgLimit,initPg);
                }
                else{
                    itemDetails = item.retrieveItemDetailsForSearchBySubCategory(itmNm, subCat, pgLimit, initPg);
                }
                LOGGER.trace("selected item details with sub-cat {}", itemDetails);
            }
        }
        catch (Exception e) {
            LOGGER.error("error in loading item details with cat and subcat : {}", e);
        }

        return itemDetails;
    }

}
