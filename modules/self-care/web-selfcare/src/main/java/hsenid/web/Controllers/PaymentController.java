package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class PaymentController {
    final static Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Value("${api.url.base.url}")
    private String baseUrl;

    @Value("${api.url.payment.process}")
    private String paymentProcessUrl;

    @RequestMapping(value = "/payment/get-total", method = RequestMethod.GET)
    @ResponseBody
    public String getDeliveryDetails(HttpSession session) {
        return session.getAttribute("cartTotal").toString();
    }

    @RequestMapping(value = "/payment/checkout", method = RequestMethod.POST)
    @ResponseBody
    public String checkout(HttpServletRequest request, HttpSession session) {
        String paymentUrl = baseUrl + paymentProcessUrl;
        JSONObject jsonObject = new JSONObject();
        String mobileNo = request.getParameter("mobileNo").toString().replaceFirst("0", "94");
        String username;
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            username = request.getParameter("mobileNo");
        } else {
            username = session.getAttribute("username").toString();
        }
        jsonObject.put("username", username);
        jsonObject.put("subscriberId", mobileNo);
        jsonObject.put("amount", request.getParameter("totalAmount"));
        jsonObject.put("paymentMethod", "Mobile");
        if (session.getAttribute("couponCode") == null || session.getAttribute("couponCode") == "") {
            jsonObject.put("couponCode", "");
        } else {
            jsonObject.put("couponCode", session.getAttribute("couponCode"));
        }

        // Add item data to the request
        List<Map<String, String>> itemsListMap = (List<Map<String, String>>) session.getAttribute("cartItems");
        JSONArray itemsJsonArray = new JSONArray();
        for (Map<String, String> itemsMap : itemsListMap) {
            JSONObject itemsJsonObj = new JSONObject();
            for (Map.Entry<String, String> itemEntry : itemsMap.entrySet()) {
                String key = itemEntry.getKey();
                Object value;
                switch (key) {
                    case "itemTitle":
                        value = itemEntry.getValue();
                        itemsJsonObj.put("itemName", value);
                        break;
                    case "itemSize":
                        value = itemEntry.getValue();
                        itemsJsonObj.put("size", value);
                        break;
                    case "itemQty":
                        value = itemEntry.getValue();
                        itemsJsonObj.put("quantity", value);
                        break;
                    case "itemTopping1":
                        value = itemEntry.getValue();
                        if (value.equals("select a topping")) {
                            value = "";
                        }
                        itemsJsonObj.put("topping_name1", value);
                        break;
                    case "itemTopping2":
                        value = itemEntry.getValue();
                        if (value.equals("select a topping")) {
                            value = "";
                        }
                        itemsJsonObj.put("topping_name2", value);
                        break;
                }
            }
            itemsJsonArray.add(itemsJsonObj);
        }
        jsonObject.put("items", itemsJsonArray);

        String paymentMethod = session.getAttribute("deliveryMethod").toString();
        if (paymentMethod.equals("Door Step")) {
            JSONArray jsonArray = (JSONArray) session.getAttribute("deliveryDetails");
            JSONObject deliveryData = (JSONObject) jsonArray.get(0);
            JSONObject deliveryJsonObj = new JSONObject();
            String recepientName;
            String recepientAddress;
            String lastName = deliveryData.get("lastName").toString();
            String address03 = deliveryData.get("addressLine03").toString();
            if (lastName == null || lastName == "") {
                recepientName = deliveryData.get("firstName").toString();
            } else {
                recepientName = deliveryData.get("firstName").toString() + " " + lastName;
            }
            if (address03 == null || address03 == "") {
                recepientAddress = deliveryData.get("addressLine01").toString() + "," + deliveryData.get("addressLine02").toString();
            } else {
                recepientAddress = deliveryData.get("addressLine01").toString() + "," + deliveryData.get("addressLine02").toString() + "," + address03;
            }
            deliveryJsonObj.put("recepientName", recepientName);
            deliveryJsonObj.put("recepientAddress", recepientAddress);
            deliveryJsonObj.put("date", deliveryData.get("date").toString());
            logger.info("DATE {}",deliveryData.get("date").toString());
            deliveryJsonObj.put("time", deliveryData.get("time").toString());
            if (deliveryData.get("description") == null || deliveryData.get("description") == "") {
                deliveryJsonObj.put("description", "");
            } else {
                deliveryJsonObj.put("description", deliveryData.get("description").toString());
            }
            deliveryJsonObj.put("method", "Door Step");
            jsonObject.put("delivery", deliveryJsonObj);
        } else if (paymentMethod.equals("pickup")) {
            JSONArray jsonArray = (JSONArray) session.getAttribute("pickupDetails");
            JSONObject pickupData = (JSONObject) jsonArray.get(0);
            JSONObject pickupJsonObj = new JSONObject();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();

            pickupJsonObj.put("recepientName", "");
            pickupJsonObj.put("recepientAddress", "");
            pickupJsonObj.put("date", dtf.format(localDate));
            pickupJsonObj.put("time", pickupData.get("time"));
            pickupJsonObj.put("description", "");
            pickupJsonObj.put("method", "pickup");

            jsonObject.put("delivery", pickupJsonObj);
        }

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);
        String code = "0";
        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(paymentUrl, httpEntity, ServerResponseMessage.class);
            code = responseMessage.getCode() + "";
            if (code.equals("200")) {
                /*session.removeAttribute("cartItems");
                session.removeAttribute("cartTotal");
                session.removeAttribute("couponCode");*/
            }
        } catch (RestClientException e) {
            logger.error("Error processing the payment.", e);
            return code;
        } catch (Exception ex) {
            logger.error("Error processing the payment.", ex);
            return code;
        }
        return code;
    }
}
