package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.url.category.list}")
    private String categoryListUrl;

    @Value("${api.url.subcategory}")
    private String subcategoryListUrl;

    @Value("${api.url.subcategory.items}")
    private String subcategoryItemsUrl;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView loadMenuPage() {
        ModelAndView modelAndView = new ModelAndView("home/menu");
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + categoryListUrl;
        ServerResponseMessage responseMessage = restTemplate.getForObject(url, ServerResponseMessage.class);
        modelAndView.addObject("categories", responseMessage.getData());
        return modelAndView;
    }

    @RequestMapping(value = "/menu/{category}", method = RequestMethod.GET)
    public ModelAndView generateRelevantMenu(@PathVariable String category) {
        ModelAndView modelAndView = new ModelAndView("home/menu-category");
        modelAndView.addObject("category", category);

        RestTemplate restTemplate = new RestTemplate();
        String getSubcatUrl = baseUrl + subcategoryListUrl + category;
        String getItemsUrl = baseUrl + subcategoryItemsUrl;

        ServerResponseMessage subcategoriesResponse = restTemplate.getForObject(getSubcatUrl, ServerResponseMessage.class);
        modelAndView.addObject("subcategories",subcategoriesResponse.getData());

        for (int i = 0; i < subcategoriesResponse.getData().size(); i++) {
            ServerResponseMessage itemsResponse = restTemplate.getForObject(getItemsUrl + subcategoriesResponse.getData().get(i).get("name"), ServerResponseMessage.class);
//            subcategoriesResponse.getData().get(i).get("name") -> subcategory of a given category
            modelAndView.addObject(subcategoriesResponse.getData().get(i).get("name").toString().replaceAll(" ",""), itemsResponse.getData());
        }
        return modelAndView;
    }
}
