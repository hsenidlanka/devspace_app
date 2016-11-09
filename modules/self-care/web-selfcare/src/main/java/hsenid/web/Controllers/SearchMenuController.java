package hsenid.web.Controllers;

import hsenid.web.models.ReplyFromServer;
import hsenid.web.models.SearchItemData;
import hsenid.web.models.ServerResponseMessage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView newLoadMenu() {
        return new ModelAndView("/home/search-menu", "searchitemdata", new SearchItemData());
    }

    @RequestMapping(value = "/search-menu", method = RequestMethod.POST)
    public ModelAndView sendSearchItem(@ModelAttribute("searchitemdata") @Valid SearchItemData searchitemdata, BindingResult result, Model model) {


        // redirectAttributes.addFlashAttribute("validForm", "dd");
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

        // redirectAttributes.addFlashAttribute("validForm", "True");
        return new ModelAndView("/search-results", "searchitemdata", new SearchItemData());
    }

    @RequestMapping(value = "/search-results", method = RequestMethod.GET)
    public ModelAndView loadSearchItems(@RequestParam String searchitemdata) {
        // @ModelAttribute("searchresultsdata") ModelAndView modelAndView
        //return new ModelAndView("/search-results", "searchresultsdata", new SearchResultsData());
        ModelAndView modelAndView = new ModelAndView("home/search-results");
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + searchItemNameUrl + searchitemdata;
        ServerResponseMessage responseMessage = restTemplate.getForObject(url, ServerResponseMessage.class);
        modelAndView.addObject("items", responseMessage.getData());
        return modelAndView;
    }

}
