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

    @Value("${api.url.category.list}")
    private String categoryListUrl;

    @Value("${api.url.subcategory}")
    private String subcategoryListUrl;

    @Value("${api.url.subcategory.items}")
    private String subcategoryItemsUrl;

    @Value("${api.url.subcategory}")
    private String toppingsUrl;

    @Value("${api.url.category.list}")
    private String catList;

    @Value("${admin.url.base.image.category}")
    private String imageBaseUrl;

    @Value("${admin.url.base.image.item}")
    private String itemImageBaseUrl;

    @RequestMapping(value = "/search-menu", method = RequestMethod.GET)
    public ModelAndView loadSearchMenuPage() {
        return new ModelAndView("/home/search-menu");
    }

    @RequestMapping(value = "menu/{searchItem}/{category}", method = RequestMethod.GET)
    //@ResponseBody
    public ModelAndView generateSearchItem(@PathVariable String searchItem, @PathVariable String category) {
        ModelAndView modelAndView = new ModelAndView("search-results");
        RestTemplate restTemplate = new RestTemplate();

        String getItemUrl = SendStringBuilds.sendString(baseUrl, searchItemNameUrl, searchItem, "/", category);
        modelAndView.addObject("category", category);
        modelAndView.addObject("searchKey", searchItem);
        ServerResponseMessage searchItemResponse = restTemplate.getForObject(getItemUrl, ServerResponseMessage.class);
        modelAndView.addObject("it", searchItemResponse.getData());
        modelAndView.addObject("itemImageUrl", itemImageBaseUrl);

        return modelAndView;

    }

   /* @RequestMapping(value = "/search-menu/add-to-cart", method = RequestMethod.POST)
    @ResponseBody
    public boolean addToCart(HttpSession session, HttpServletRequest request) {
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
        String itemTopping1Price = "0";
        if (request.getParameter("itemTopping1Price") != null) {
            itemTopping1Price = request.getParameter("itemTopping1Price");
        }
        String itemTopping2 = request.getParameter("itemTopping2");
        String itemTopping2Price = "0";
        if (request.getParameter("itemTopping2Price") != null) {
            itemTopping2Price = request.getParameter("itemTopping2Price");
        }
        String itemInstructs = request.getParameter("itemInstructs");
        String itemQty = request.getParameter("itemQty");
        String itemPrice = request.getParameter("itemPrice");
        String itemTotal = request.getParameter("itemTotal");

        map.put("itemTitle", itemTitle);
        map.put("itemDescription", itemDescription);
        map.put("itemSize", itemSize);
        map.put("itemTopping1", itemTopping1);
        map.put("itemTopping2", itemTopping2);
        double toppingsTotal = Double.parseDouble(itemTopping1Price) + Double.parseDouble(itemTopping2Price);
        map.put("itemToppingsTotal", toppingsTotal + "");
        map.put("itemInstructs", itemInstructs);
        map.put("itemQty", itemQty);
        map.put("itemPrice", itemPrice);
        map.put("itemTotal", itemTotal);
        cartItems.add(map);
        session.setAttribute("cartItems", cartItems);
        return true;
    }

    // Get the details of available topping to customize the pizza before adding to the shopping cart.
    @RequestMapping(value = "/search-menu/toppings", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getToppings() {
        RestTemplate restTemplate = new RestTemplate();
        String getToppingsUrl = baseUrl + toppingsUrl + "topping";
        ServerResponseMessage responseMessage = restTemplate.getForObject(getToppingsUrl, ServerResponseMessage.class);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < responseMessage.getData().size(); i++) {
            JSONObject jsonObject = responseMessage.getData().get(i);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }*/


}