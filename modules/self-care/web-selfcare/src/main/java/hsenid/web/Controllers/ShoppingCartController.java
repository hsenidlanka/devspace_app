package hsenid.web.Controllers;


import hsenid.web.models.BooleanResponse;
import hsenid.web.models.ReplyFromServer;
import hsenid.web.models.ServerResponseMessage;
import hsenid.web.supportclasses.SendStringBuilds;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@PropertySource("classpath:config.properties")
public class ShoppingCartController {
    final static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @Value("${api.url.base.url}")
    private String baseUrl;

    @Value("${api.url.coupen.checkCoupenValid}")
    private String checkValidCoupenUrl;

    @RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
    public ModelAndView loadShoppingCartPage() {
        ModelAndView modelAndView = new ModelAndView("/home/shopping-cart");
        return modelAndView;
    }

    @RequestMapping(value = "/shopping-cart/validatecoupon", method = RequestMethod.POST)
    @ResponseBody
    public String checkForValidCoupon(HttpServletRequest request) {

        String completeUrl = SendStringBuilds.sendString(baseUrl, checkValidCoupenUrl);

        String couponToCheck = request.getParameter("checkCoupon");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("couponCode", couponToCheck);
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        String rate = "0";
        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(completeUrl, httpEntity, ServerResponseMessage.class);
            rate = responseMessage.getData().get(0).get("rate").toString();
        } catch (RestClientException e) {
            logger.error("Error validating the coupon.", e);
            return rate;
        }
        return rate;
    }

    @RequestMapping(value = "/shopping-cart/removeitem", method = RequestMethod.POST)
    @ResponseBody
    public String removeItemFromCart(HttpSession session, HttpServletRequest request) {
        List<Map<String, String>> cartItemList = (List<Map<String, String>>) session.getAttribute("cartItems");
        /*String itemTitle = request.getParameter("itemTitle");
        String itemPrice = request.getParameter("itemPrice");
        String itemQty = request.getParameter("itemQty");*/
        String itemIndex = request.getParameter("itemIndex");

        /*for (int i = 0; i < cartItemList.size(); i++) {
            Map<String, String> cartItemMap = cartItemList.get(i);
            boolean isItemNameMatch = cartItemMap.get("itemTitle").equals(itemTitle);
            boolean isItemPriceMatch = cartItemMap.get("itemPrice").equals(itemPrice);
            boolean isItemQtyMatch = cartItemMap.get("itemQty").equals(itemQty);
            if(isItemNameMatch==true && isItemNameMatch==true && isItemQtyMatch==true){
                cartItemList.remove(i);
                return true;
            }
        }*/
        cartItemList.remove(Integer.parseInt(itemIndex));
        session.setAttribute("cartItems", cartItemList);
        return itemIndex;
    }

    @RequestMapping(value = "/shopping-cart/updateitem", method = RequestMethod.POST)
    @ResponseBody
    public void updateCartSessionData(HttpSession session, HttpServletRequest request) {
        List<Map<String, String>> cartItemList = (List<Map<String, String>>) session.getAttribute("cartItems");
        String itemIndex = request.getParameter("ItemIndex");
        String itemQty = request.getParameter("itemQty");
        cartItemList.get(Integer.parseInt(itemIndex)).put("itemQty", itemQty);
        session.setAttribute("cartItems", cartItemList);
    }
}
