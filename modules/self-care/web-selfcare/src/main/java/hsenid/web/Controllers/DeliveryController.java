package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hsenid on 11/22/16.
 */
@RequestMapping(value = "/delivery")
public class DeliveryController {

    final static Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Value("${api.base.url}")
    private String baseUrl;



    @RequestMapping(value = "/get-customer-data", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getCustomerDetails() {
        RestTemplate restTemplate = new RestTemplate();
        String getToppingsUrl = baseUrl+"";
        ServerResponseMessage responseMessage = restTemplate.getForObject(getToppingsUrl, ServerResponseMessage.class);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < responseMessage.getData().size(); i++) {
            JSONObject jsonObject = responseMessage.getData().get(i);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
