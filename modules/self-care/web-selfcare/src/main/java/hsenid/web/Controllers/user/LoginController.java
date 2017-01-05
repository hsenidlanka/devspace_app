package hsenid.web.Controllers.user;

import hsenid.web.models.*;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/*
* This class controls requests come for login and user registrations including ajax requests
* */
@Controller
@PropertySource("classpath:config.properties")
public class LoginController {

    //    Defining logger
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    //    Setting properties from config.properties file
    @Value("${api.url.register}")
    private String registerUrl;

    @Value("${api.url.login}")
    private String loginUrl;

    @Value("${api.url.customer.search}")
    private String customerSearchUrl;

    @Value("${api.url.base.url}")
    private String baseUrl;

    final static String title = "title";
    final static String username = "username";
    final static String password = "password";
    RestTemplate restTemplate = new RestTemplate();
    User user = new User();
    Verification verification = new Verification();

//  check given user credentials are valid or not
//  if valide set session and sent it.

    @RequestMapping(value = "/login")
    @ResponseBody
    public Verification login(HttpSession session, HttpServletRequest request) {
        String uname = request.getParameter(username);
        String pword = request.getParameter(password);
        User user = new User();
        ReplyFromServer replyFromServer;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(username, uname);
        jsonObject.put(password, pword);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        ServerResponseMessage responseMessage;

        try {
            responseMessage = restTemplate.postForObject(loginUrl, jsonObjectHttpEntity, ServerResponseMessage.class);
            String status = (String) responseMessage.getData().get(0).get("accountStatus");
            if (status.equals("verified")) {
                try {
                    String fullCustomerSearchUrl = SendStringBuilds.sendString(customerSearchUrl, uname);
                    replyFromServer = restTemplate.getForObject(fullCustomerSearchUrl, ReplyFromServer.class);
                    user.setTitle(replyFromServer.getData().get(0).getTitle());
                    user.setFirstName(replyFromServer.getData().get(0).getFirstName());
                    user.setLastName(replyFromServer.getData().get(0).getLastName());
                    user.setEmail(replyFromServer.getData().get(0).getEmail());
                    user.setMobile(replyFromServer.getData().get(0).getMobile());
                    user.setUsername(replyFromServer.getData().get(0).getUsername());
                    user.setAddressLine01(replyFromServer.getData().get(0).getAddressLine01());
                    user.setAddressLine02(replyFromServer.getData().get(0).getAddressLine02());
                    user.setAddressLine03(replyFromServer.getData().get(0).getAddressLine03());

                    session.setAttribute("name", SendStringBuilds.sendString(user.getFirstName(), " ", user.getLastName()));
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute(username, uname);
                    session.setAttribute("userStatus", verification.getUserStatus());
                } catch (Exception e) {
                    logger.error("Login Controller, inner try failed. reason {}", e.getMessage());
                }
                verification.setUserStatus(status);
                return verification;
            } else if (status.equals("notVerified")) {
                if (session != null) {
                    session.invalidate();
                }
            }
            verification.setUserStatus(status);

        } catch (RestClientException e) {
            logger.error(e.getMessage());
            verification.setUserStatus("unauthorized");
        }
        return verification;
    }

    @GetMapping("/checkNotUser")
    @ResponseBody
    public Verification checkAccess(HttpServletRequest request){

        Verification verification = new Verification();
        String uname = request.getParameter(username);
        String pword = request.getParameter(password);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(username, uname);
        jsonObject.put(password, pword);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(loginUrl, jsonObjectHttpEntity, ServerResponseMessage.class);
            verification.setUserStatus((String) responseMessage.getData().get(0).get("accountStatus"));

        } catch (RestClientException e) {
            e.printStackTrace();
            verification.setUserStatus("unauthorized");
            return verification;
        }
        return verification;
    }


    @RequestMapping("/blockedUserRedirect")
    public String BlockedUserEntered(){
        return "/error-pages/error500";
    }



}
