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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/itemFilters")
public class ItemFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemFilter.class);

    @Autowired
    private ItemRepository item;

    @Autowired
    private CategoryRepository categoryRepository ;

    @Autowired
    private SubCategoryRepositoryImpl subCategory;


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
    List<String> gatSubCatList(@RequestParam("catName")String categoryNm) {

        List<String> subCat = subCategory.retrieveSubcatogories(categoryNm);
        LOGGER.trace("get Sub-cat List in item filter{}", subCat);
        return subCat;
    }
}
