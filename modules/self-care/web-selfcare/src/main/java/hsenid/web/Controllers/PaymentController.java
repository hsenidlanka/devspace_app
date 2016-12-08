package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
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
        jsonObject.put("subscriberId", mobileNo);
        jsonObject.put("amount", request.getParameter("totalAmount"));

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);
        String code = "0";
        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(paymentUrl, httpEntity, ServerResponseMessage.class);
            code = responseMessage.getCode() + "";
            if (code.equals("200")) {
                session.removeAttribute("cartItems");
                session.removeAttribute("cartTotal");
                session.removeAttribute("couponCode");
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
