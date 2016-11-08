package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ModelAndView loadMenuPage(HttpSession session) {
        if (session.getAttribute("cartItems") == null || session.getAttribute("cartItems") == "") {
            List<Map<String, String>> cartItems = new ArrayList<Map<String, String>>();
            session.setAttribute("cartItems", cartItems);
        }
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
        modelAndView.addObject("subcategories", subcategoriesResponse.getData());

        for (int i = 0; i < subcategoriesResponse.getData().size(); i++) {
            ServerResponseMessage itemsResponse = restTemplate.getForObject(getItemsUrl + subcategoriesResponse.getData().get(i).get("name"), ServerResponseMessage.class);
//            subcategoriesResponse.getData().get(i).get("name") -> subcategory of a given category
            modelAndView.addObject(subcategoriesResponse.getData().get(i).get("name").toString().replaceAll(" ", ""), itemsResponse.getData());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/menu/add-to-cart", method = RequestMethod.POST)
    @ResponseBody
    public void addToCart(HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("cartItems") == null || session.getAttribute("cartItems") == "") {
            List<Map<String, String>> cartItems = new ArrayList<Map<String, String>>();
            session.setAttribute("cartItems", cartItems);
        }
        List<Map<String, String>> cartItems = (List<Map<String, String>>) session.getAttribute("cartItems");
        Map<String, String> map = new HashMap<String, String>();

        String itemTitle = request.getParameter("itemTitle");
        String itemDescription = request.getParameter("itemDescription");
        String itemSize = request.getParameter("itemSize");
        String itemTopping1 = request.getParameter("itemTopping1");
        String itemTopping2 = request.getParameter("itemTopping2");
        String itemInstructs = request.getParameter("itemInstructs");
        String itemQty = request.getParameter("itemQty");
        String itemPrice = request.getParameter("itemPrice");
        String itemTotal = request.getParameter("itemTotal");

        map.put("itemTitle", itemTitle);
        map.put("itemDescription", itemDescription);
        map.put("itemSize", itemSize);
        map.put("itemTopping1", itemTopping1);
        map.put("itemTopping2", itemTopping2);
        map.put("itemInstructs", itemInstructs);
        map.put("itemQty", itemQty);
        map.put("itemPrice", itemPrice);
        map.put("itemTotal", itemTotal);
        cartItems.add(map);
        session.setAttribute("cartItems", cartItems);
    }
}
