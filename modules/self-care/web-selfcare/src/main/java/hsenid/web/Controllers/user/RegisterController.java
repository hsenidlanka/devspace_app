package hsenid.web.Controllers.user;

import hsenid.web.models.RegisterModal;
import hsenid.web.models.ServerResponseMessage;
import hsenid.web.supportclasses.SendStringBuilds;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@PropertySource("classpath:config.properties")
@RequestMapping("/register")
public class RegisterController {

    @Value("${api.url.register}")
    private String registerUrl;

    @Value("${api.url.customer.search}")
    private String customerSearchUrl;

    final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sendRegister(){
        return new ModelAndView("home/register", "register", new RegisterModal());
    }


    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("register") @Valid RegisterModal registerModal, BindingResult result, Model model){
        if (result.hasErrors()){
            return "/home/register";
        }

        String addr3 = SendStringBuilds.getValueOrDefault(registerModal.getAddressLine03(), " ");
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("title", registerModal.getTitle());
        jsonObject.put("firstName", registerModal.getFirstName());
        jsonObject.put("lastName", registerModal.getLastName());
        jsonObject.put("username", registerModal.getUsername());
        jsonObject.put("password", registerModal.getPassword());
        jsonObject.put("email", registerModal.getEmail());
        jsonObject.put("addressL1", registerModal.getAddressLine01());
        jsonObject.put("addressL2", registerModal.getAddressLine02());
        jsonObject.put("addressL3", addr3);
        jsonObject.put("mobile", registerModal.getMobile());
        logger.info(String.valueOf(jsonObject));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(registerUrl, httpEntity, ServerResponseMessage.class);
//            logger.error(registerUrl);
            String regSuccessUrl = SendStringBuilds.sendString("redirect:", "/email-verification?username=", registerModal.getUsername());
//            logger.error(regSuccessUrl);
            return regSuccessUrl;
        } catch (RestClientException e) {
            logger.error("User registration failed reason -> {}.", e.getMessage());
        }

        return "redirect:/register";
    }

   /* // call api to register the user

    @ResponseBody
    public BooleanResponse register(HttpServletRequest regRequest) throws JSONException {

        String addressL3 = "";

        String title = regRequest.getParameter("title");
//        String title = "Mr";
        String firstName = regRequest.getParameter("fname");
//        String firstName = "First";
        String lastName = regRequest.getParameter("lname");
//        String lastName = "Last";
        String addressL1 = regRequest.getParameter("address1");
//        String addressL1 = "add1";
        String addressL2 = regRequest.getParameter("address2");
//        String addressL2 = "addr2";
        addressL3 = regRequest.getParameter("address3");
//        addressL3 = "Addr3";
        String mobile = regRequest.getParameter("mobileNo");
//        String mobile ="0717456123";
        String email = regRequest.getParameter("email");
//        String email = "mail@mail.com";
        String password = regRequest.getParameter("password");
//        String password = "test123";
        String username = regRequest.getParameter("uname");
//        String username = "Uone5";

        try {
            if (addressL3.length() < 1) {
                addressL3 = "sfvzxcvz";
            }
        } catch (Exception e) {
            logger.error("Adddr 3 fail {}", e.getMessage());
            addressL3 = "";
        }
        String mobileRegex = "^0[0-9]{9}$";
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        *//*if (!(title.length() > 1) && !(firstName.length() > 1) && !(lastName.length() > 1) && !(addressL1.length() > 2) && !(addressL2.length() > 2) && !email.matches(emailRegex) && !mobile.matches(mobileRegex) && (username.length() > 2)) {
            logger.error("RegisterController recieved invalid details");
            return new BooleanResponse(false);
        }*//*

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("title", title);
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("email", email);
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

        try {
            ServerResponseMessage serverResponseMessage = restTemplate.postForObject(registerUrl, httpEntity, ServerResponseMessage.class);
        } catch (Exception e) {
            logger.error("User registration failed. Reason -> {}", e.getMessage());
            return new BooleanResponse(false);
        }

        return new BooleanResponse(true);
    }*/


    //    check whether chosen user is already in the system
    /*@RequestMapping(value = "/UniqueUser", method = RequestMethod.GET)
    @ResponseBody
    public BooleanResponse uniqueUsername(HttpServletRequest request) {

        String checkName = request.getParameter("checkName");
        logger.info("unique user started");
        String urlForSearch = SendStringBuilds.sendString(customerSearchUrl, checkName);
        BooleanResponse uniqueUser;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ReplyFromServer replyFromServer = restTemplate.getForObject(urlForSearch, ReplyFromServer.class);

        String serverMsg = replyFromServer.getMessage();
        int sizeOfMsg = serverMsg.length();

        if (sizeOfMsg == 23) {
            uniqueUser = new BooleanResponse(false);
        } else {
            uniqueUser = new BooleanResponse(true);
        }
        return uniqueUser;
    }*/


}
