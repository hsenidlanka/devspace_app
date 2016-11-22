package hsenid.web.Controllers.user;

import hsenid.web.models.BooleanResponse;
import hsenid.web.models.ReplyFromServer;
import hsenid.web.supportclasses.SendStringBuilds;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

@Controller
@PropertySource("classpath:config.properties")
public class RegisterController {

    @Value("${api.url.register}")
    private String registerUrl;

    @Value("${api.url.customer.search}")
    private String customerSearchUrl;

    final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    // call api to register the user
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public BooleanResponse register(HttpServletRequest regRequest) throws JSONException {

        String addressL3 = null;

        String title = regRequest.getParameter("title");
        String firstName = regRequest.getParameter("fname");
        String lastName = regRequest.getParameter("lname");
        String addressL1 = regRequest.getParameter("address1");
        String addressL2 = regRequest.getParameter("address2");
        addressL3 = regRequest.getParameter("address3");
        String mobile = regRequest.getParameter("mobileNo");
        String email = regRequest.getParameter("email");
        String password = regRequest.getParameter("password");
        String username = regRequest.getParameter("uname");

        if (addressL3.length() < 1) {
            addressL3 = null;
        }
//        logger.info("{} {} {} {} {} {} {} {} username-{} {}", title, firstName, lastName, addressL1, addressL2, addressL3, mobile, email, username, password);
//        String registerDetails = SendStringBuilds.sendString("{", "title", ":", title, "," + "firstName" + ":" + firstName + "," + "lastName" + ":" + lastName + "," + "email" + ":" + email + "," + "addressL1" + ":" + addressL1 + "," + "addressL2" + ":" + addressL2 + "," + "addressL3" + ":", addressL3, ",", "username", ":", username, ",", "password", ":", password, ",", "mobile", ":", mobile, "}");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",title);
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("email",email);
        jsonObject.put("addressL1", addressL1);
        jsonObject.put("addressL2", addressL2);
        jsonObject.put("addressL3", addressL3);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("mobile", mobile);

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        try{
            ReplyFromServer message = restTemplate.postForObject(registerUrl, httpEntity, ReplyFromServer.class);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new BooleanResponse(false);
        }

        return new BooleanResponse(true);
    }



    //    check whether chosen user is already in the system
    @RequestMapping(value = "/UniqueUser", method = RequestMethod.GET)
    @ResponseBody
    public BooleanResponse uniqueUsername(HttpServletRequest request){

        String checkName = request.getParameter("checkName");
        logger.info("unique user started");
        String urlForSearch = SendStringBuilds.sendString(customerSearchUrl, checkName);
        BooleanResponse uniqueUser;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ReplyFromServer replyFromServer = restTemplate.getForObject(urlForSearch, ReplyFromServer.class);

        String serverMsg = replyFromServer.getMessage();
        int sizeOfMsg= serverMsg.length();

        if (sizeOfMsg == 23) {
            uniqueUser = new BooleanResponse(false);
        } else {
            uniqueUser = new BooleanResponse(true);
        }

//        logger.info(replyFromServer.getMessage());

        return uniqueUser;
    }


}
