package hsenid.web.Controllers;

import hsenid.web.models.ReplyFromServer;
import hsenid.web.models.SearchItemData;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by hsenid on 11/2/16.
 */

@Controller
public class SearchMenuController {

    final static Logger logger = LoggerFactory.getLogger(SearchMenuController.class);

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.url.subcategory.items}")
    private String searchItemNameUrl;


    @RequestMapping(value = "/search-menu", method = RequestMethod.GET)
    public ModelAndView newLoadMenu() {
        return new ModelAndView("/home/search-menu", "searchitemdata", new SearchItemData());
    }

    @RequestMapping(value = "/search-menu", method = RequestMethod.POST)
    public String sendSearchItem(@ModelAttribute("searchitemdata") @Valid SearchItemData searchitemdata, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/home/contactus";
        }

        redirectAttributes.addFlashAttribute("validForm", "dd");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("searchItem", searchitemdata.getSearchItem());


        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        String searchUrl = baseUrl + searchItemNameUrl;

        try {
            ReplyFromServer message = restTemplate.postForObject(searchUrl, httpEntity, ReplyFromServer.class);
        } catch (Exception e) {
            logger.info(e.getMessage());

        }
        redirectAttributes.addFlashAttribute("validForm", "True");
        return "redirect:/search-results";
    }
}
