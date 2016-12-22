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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DeliveryController {

    final static Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.url.customers.search}")
    private String customerSearchUrl;

    @RequestMapping(value = "/delivery/get-delivery-data", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getDeliveryDetails(HttpSession session) {
        if (session.getAttribute("deliveryDetails") == null || session.getAttribute("deliveryDetails") == "") {
            JSONArray deliveryDetails = new JSONArray();
            session.setAttribute("deliveryDetails", deliveryDetails);
        }
        JSONArray deliveryDetailsJson = (JSONArray) session.getAttribute("deliveryDetails");
        if (deliveryDetailsJson.size() > 0) {
            return deliveryDetailsJson;
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String getCustomerDetailsUrl = baseUrl + customerSearchUrl + session.getAttribute("username");
            logger.error("url: {}",getCustomerDetailsUrl);
            ServerResponseMessage responseMessage = restTemplate.getForObject(getCustomerDetailsUrl, ServerResponseMessage.class);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < responseMessage.getData().size(); i++) {
                JSONObject jsonObject = responseMessage.getData().get(i);
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        }
    }

    @RequestMapping(value = "/delivery/save-delivery-data", method = RequestMethod.POST)
    @ResponseBody
    public void saveDeliveryDetails(HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("deliveryDetails") == null || session.getAttribute("deliveryDetails") == "") {
            JSONArray deliveryDetails = new JSONArray();
            session.setAttribute("deliveryDetails", deliveryDetails);
        }
        if (session.getAttribute("deliveryMethod") == null || session.getAttribute("deliveryMethod") == "") {
            session.setAttribute("deliveryMethod", "");
        }
        session.setAttribute("deliveryMethod", "Door Step");
        JSONArray deliveryDetailsJson = (JSONArray) session.getAttribute("deliveryDetails");
        deliveryDetailsJson.clear();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", request.getParameter("firstName"));
        jsonObject.put("lastName", request.getParameter("lastName"));
        jsonObject.put("addressLine01", request.getParameter("address01"));
        jsonObject.put("addressLine02", request.getParameter("address02"));
        jsonObject.put("addressLine03", request.getParameter("address03"));
        jsonObject.put("email", request.getParameter("email"));
        jsonObject.put("mobile", request.getParameter("contactNo"));
        jsonObject.put("description", request.getParameter("description"));
        jsonObject.put("date", request.getParameter("date"));
        jsonObject.put("time", request.getParameter("time"));
        deliveryDetailsJson.add(jsonObject);
        session.setAttribute("deliveryDetails", deliveryDetailsJson);
    }

    @RequestMapping(value = "/delivery/save-pickup-data", method = RequestMethod.POST)
    @ResponseBody
    public void savePickupDetails(HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("pickupDetails") == null || session.getAttribute("pickupDetails") == "") {
            JSONArray pickupDetails = new JSONArray();
            session.setAttribute("pickupDetails", pickupDetails);
        }
        if (session.getAttribute("deliveryMethod") == null || session.getAttribute("deliveryMethod") == "") {
            session.setAttribute("deliveryMethod", "");
        }
        session.setAttribute("deliveryMethod", "pickup");
        JSONArray pickupDetailsJson = (JSONArray) session.getAttribute("pickupDetails");
        pickupDetailsJson.clear();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", request.getParameter("time"));
        jsonObject.put("branch", request.getParameter("branch"));
        pickupDetailsJson.add(jsonObject);
        session.setAttribute("pickupDetails", pickupDetailsJson);
    }

    @RequestMapping(value = "/delivery/get-pickup-data", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getPickupDetails(HttpSession session) {
        if (session.getAttribute("pickupDetails") == null || session.getAttribute("pickupDetails") == "") {
            JSONArray pickupDetails = new JSONArray();
            session.setAttribute("pickupDetails", pickupDetails);
        }
        JSONArray pickupDetailsJson = (JSONArray) session.getAttribute("pickupDetails");
        return pickupDetailsJson;
    }
}
