package hsenid.web.Controllers.user;

import hsenid.web.models.BooleanResponse;
import hsenid.web.models.ReplyFromServer;
import hsenid.web.models.User;
import hsenid.web.supportclasses.SendStringBuilds;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
* This class controls requests come for login and user registrations including ajax requests
* */
@Controller
@PropertySource("classpath:config.properties")
public class LoginController {

    final static String title= "title";
    final static String username="username";
    final static String password="password";

    User user = new User();

    static String email2;
    static String name2;
    static String mobile2;
    static String firstName2;
    static String lastName2;
    static String addr12;
    static String addr22;
    static String addr33;
    static String title2;

    //    Defining logger
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

//    Setting properties from config.properties file
    @Value("${api.url.register}")
    private String registerUrl;

    @Value("${api.url.login}")
    private String loginUrl;

    @Value("${api.url.customer.search}")
    private String customerSearchUrl;


//    Checking whether a user is blocked or not
   @RequestMapping(value = "chechBlocked", produces = "application/json")
   @ResponseBody
   public BooleanResponse checkBlockedUser(HttpServletRequest request){
       String uname = request.getParameter("checkName");
//       String uname = "kkalla";
       String pword = "";

       JSONObject jsonObject = new JSONObject();
       jsonObject.put(username, uname);
       jsonObject.put(password, pword);

       MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
       headers.add("Content-Type", "application/json");
       HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

       RestTemplate restTemplate = new RestTemplate();

       try {

           ReplyFromServer replyFromServer = restTemplate.postForObject(loginUrl, jsonObjectHttpEntity, ReplyFromServer.class);

       } catch (RestClientException e) {

           String msg = e.getMessage().trim();
           String[] splited = msg.split("\\s+");

           if (splited[0].equals("403")){
               logger.error("Blocked Username={} Entered", username);
               return new BooleanResponse(false);

           }
       }

       logger.info("Username is not blocked");
       return new BooleanResponse(true);

   }

//  check given user credentials are valid or not
//  if valide set session and sent it.
    @RequestMapping(value = "/login")
    @ResponseBody
    public BooleanResponse login(HttpSession session,HttpServletRequest request){
        String uname = request.getParameter(username);
        String pword = request.getParameter(password);

//        logger.info(uname+pword);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(username, uname);
        jsonObject.put(password, pword);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        RestTemplate restTemplate = new RestTemplate();
        ReplyFromServer replyFromServer = null;
        try {
            replyFromServer = restTemplate.postForObject(loginUrl, jsonObjectHttpEntity, ReplyFromServer.class);

            if(replyFromServer.getStatus().equals("success")){

                RestTemplate restTemplate1 = new RestTemplate();
                String userDataUrl = replyFromServer.getLinks().get(1).getLink();
                logger.info(userDataUrl);
                ReplyFromServer replyFromServer1 = restTemplate1.getForObject(userDataUrl, ReplyFromServer.class);

                user.setTitle(replyFromServer1.getData().get(0).getTitle());
                user.setFirstName(replyFromServer1.getData().get(0).getFirstName());
                user.setLastName(replyFromServer1.getData().get(0).getLastName());
                user.setEmail(replyFromServer1.getData().get(0).getEmail());
                user.setMobile(replyFromServer1.getData().get(0).getMobile());
                user.setAddressLine01(replyFromServer1.getData().get(0).getAddressLine01());
                user.setAddressLine02(replyFromServer1.getData().get(0).getAddressLine02());
                user.setAddressLine03(replyFromServer1.getData().get(0).getAddressLine03());

//                name2 = SendStringBuilds.sendString(firstName2, " ", lastName2);

            }

//            logger.info(replyFromServer.getData().get(0).getEmail());

        } catch (RestClientException e) {
            logger.error(e.getMessage());
            return new BooleanResponse(false);
        }

//            Adding attributes to the session
        session.setAttribute(title, title2);
        session.setAttribute("firstName", firstName2);
        session.setAttribute("lastName", lastName2);
        session.setAttribute("username", username);
        session.setAttribute("email", email2);
        session.setAttribute("name", SendStringBuilds.sendString(user.getFirstName(), " ", user.getLastName()));
        session.setAttribute("mobile", mobile2);
        session.setAttribute("addr1", addr12);
        session.setAttribute("addr2", addr22);
        session.setAttribute("addr3", addr33);

        return new BooleanResponse(true);
    }

}
