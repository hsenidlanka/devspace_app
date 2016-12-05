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

/**
 * Created by hsenid on 11/22/16.
 */
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
            String getToppingsUrl = baseUrl + customerSearchUrl + session.getAttribute("username");
            ServerResponseMessage responseMessage = restTemplate.getForObject(getToppingsUrl, ServerResponseMessage.class);
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
        deliveryDetailsJson.add(jsonObject);
        session.setAttribute("deliveryDetails", deliveryDetailsJson);
    }
}
