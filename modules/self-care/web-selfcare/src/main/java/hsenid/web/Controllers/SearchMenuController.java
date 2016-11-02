package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hsenid on 11/2/16.
 */

@Controller
public class SearchMenuController {

    final static Logger logger = LoggerFactory.getLogger(SearchMenuController.class);

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.url.items.searchName}")
    private String searchItemNameUrl;

    @RequestMapping(value = "/search-menu", method = RequestMethod.GET)
    public ModelAndView loadSearchMenuPage(HttpServletRequest keyName) {
        ModelAndView modelAndView = new ModelAndView("home/search-menu");
        RestTemplate restTemplate = new RestTemplate();
        String key=keyName.getParameter("txt-menu-search");
        String searchResponseUrl = baseUrl + searchItemNameUrl + key;

        ServerResponseMessage responseMessage = restTemplate.getForObject(searchResponseUrl, ServerResponseMessage.class);
        modelAndView.addObject("items", responseMessage.getData());
        return modelAndView;
    }


}
