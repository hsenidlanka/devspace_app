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

import javax.servlet.http.HttpServletRequest;

@Controller
@PropertySource("classpath:config.properties")
public class ShoppingCartController {
    final static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @Value("${api.url.base.url}")
    private String baseUrl;

    @Value("${api.url.coupen.checkCoupenValid}")
    private String checkValidCoupenUrl;

    @GetMapping("/shopping-cart")
    public String shoppingCartMethod(){
        return "/shopping-cart";
    }


    @PostMapping("/shopping-cart")
    public String shoppingCartPost(){
        return "/shopping-cart";
    }

    @GetMapping("/validcoupen")
    @ResponseBody
    public String checkForValidCoupen(HttpServletRequest request){

        String completeUrl = SendStringBuilds.sendString(baseUrl, checkValidCoupenUrl);

        String coupenToCheck = request.getParameter("checkCoupon");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("couponCode", coupenToCheck);
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        String rate="0";
        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(completeUrl, httpEntity, ServerResponseMessage.class);
            rate=responseMessage.getData().get(0).get("rate").toString();
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            return rate;
        }
        logger.info("knock knock {}", coupenToCheck);
        return rate;
    }
}
