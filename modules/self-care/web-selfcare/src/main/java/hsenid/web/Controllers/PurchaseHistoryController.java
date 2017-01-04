package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PurchaseHistoryController {
    final static Logger logger = LoggerFactory.getLogger(PurchaseHistoryController.class);

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.url.puchase.history}")
    private String purchaseHistoryUrl;

    @Value("${api.url.puchase.history.count}")
    private String purchaseHistoryCountUrl;

    @Value("${api.url.puchase.history.order.items}")
    private String purchaseHistoryOrderItemsUrl;

    @RequestMapping(value = "/purchase-history", method = RequestMethod.GET)
    public ModelAndView loadPurchaseHistoryPage() {
        ModelAndView modelAndView = new ModelAndView("includes/purchase-history");
        return modelAndView;
    }

    @RequestMapping(value = "/purchase-history/orders", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getPurchaseHistory(HttpSession session, HttpServletRequest request) {
        String url = baseUrl + purchaseHistoryUrl + session.getAttribute("username").toString() + "?page=" + request.getParameter("page") + "&records=" + request.getParameter("records");
        RestTemplate restTemplate = new RestTemplate();
        ServerResponseMessage responseMessage = restTemplate.getForObject(url, ServerResponseMessage.class);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < responseMessage.getData().size(); i++) {
            JSONObject jsonObject = responseMessage.getData().get(i);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @RequestMapping(value = "purchase-history/order-count", method = RequestMethod.GET)
    @ResponseBody
    public int getOrdersCount(HttpSession session) {
        String url = baseUrl + purchaseHistoryCountUrl + session.getAttribute("username").toString();
        RestTemplate restTemplate = new RestTemplate();
        ServerResponseMessage responseMessage = restTemplate.getForObject(url, ServerResponseMessage.class);
        return Integer.parseInt(responseMessage.getData().get(0).get("count").toString());
    }

    @RequestMapping(value = "purchase-history/order-items", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getOrderItems(HttpServletRequest request) {
        String url = baseUrl + purchaseHistoryOrderItemsUrl + request.getParameter("orderId");
        RestTemplate restTemplate = new RestTemplate();
        ServerResponseMessage responseMessage = restTemplate.getForObject(url, ServerResponseMessage.class);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < responseMessage.getData().size(); i++) {
            JSONObject jsonObject = responseMessage.getData().get(i);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}

