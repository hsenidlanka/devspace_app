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
}
