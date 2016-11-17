package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import hsenid.web.supportclasses.SendStringBuilds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView loadSearchMenuPage() {
        return new ModelAndView("/home/search-menu");
    }

    @RequestMapping(value = {"web-selfcare/search-menu/{searchItem}", "{searchItem}?searchItem={searchItem}"}, method = RequestMethod.GET)
    //@ResponseBody
    public ModelAndView generateSearchItem(@PathVariable String searchItem) {
        ModelAndView modelAndView = new ModelAndView("search-results");
        logger.info(searchItem);

        // modelAndView.addObject("searchItem", searchItem);
        RestTemplate restTemplate = new RestTemplate();
        String getItemUrl = SendStringBuilds.sendString(baseUrl, searchItemNameUrl, searchItem);

        ServerResponseMessage searchItemResponse = restTemplate.getForObject(getItemUrl, ServerResponseMessage.class);
        modelAndView.addObject("it", searchItemResponse.getData());
        modelAndView.addObject("test", searchItem);
        return modelAndView;

    }

    /*@RequestMapping(value = "/search-results", method = RequestMethod.GET)
    public ModelAndView newLoadSearch() {
        return new ModelAndView("/search-results", "searchitemdata", new SearchItemData());
    }*/



   /* @RequestMapping(value = "/search-menu", method = RequestMethod.GET)
    public ModelAndView sendSearchItem(@ModelAttribute("searchitemdata") @Valid SearchItemData searchitemdata, BindingResult result, Model model) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("searchItem", searchitemdata.getSearchItem());
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);
        String searchUrl = baseUrl + searchItemNameUrl;
        try {
            ReplyFromServer data = restTemplate.postForObject(searchUrl, httpEntity, ReplyFromServer.class);
            model.addAttribute("items", data);
            logger.info("{}", data);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return new ModelAndView("/search-results", "searchitemdata", new SearchItemData());
    }
*/


}
