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

@Controller
@RequestMapping("/itemFilters")
public class ItemFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemFilter.class);

    @Autowired
    private ItemRepository item;

    @Autowired
    private CategoryRepository category;

    @Autowired
    private SubCategoryRepositoryImpl subCategory;


}
