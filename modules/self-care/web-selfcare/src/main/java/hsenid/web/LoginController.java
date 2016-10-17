package hsenid.web;

import hsenid.web.bindclasses.BooleanResponse;
import hsenid.web.bindclasses.ReplyFromServer;
import hsenid.web.supportclasses.SendStringBuilds;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


@Controller
@PropertySource("classpath:config.properties")
public class LoginController {
    @Value("${api.url.register}")
    private String registerUrl;

    @Value("${api.url.login}")
    private String loginUrl;

    @Value("${api.url.customer.search}")
    private String customerSearchUrl;

    @Value("${api.connect.timeout}")
    private int connectTimeout;

    @Value("${api.read.timeout}")
    private int readTimeout;

    final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    String toLoginJsonString = null;


    @RequestMapping("login")
    public JSONObject login(HttpServletRequest request) throws JSONException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String loginDetailString = SendStringBuilds.sendString("{", "username", ":", username, ",", "password", ":", password, "}");

        JSONObject jsonObject = new JSONObject(loginDetailString);

        try {
            URL url = new URL(loginUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ttt = null;
            StringBuilder sb = new StringBuilder();

            while ((ttt = in.readLine()) != null) {
                sb.append(ttt);
            }

            in.close();

            JSONObject reply = new JSONObject(sb.toString());
            int status = reply.getInt("code");
            if (status == 200) {
            } else if (status == 401) {
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        JSONObject authorizeJson = new JSONObject(loginDetailString);

        return authorizeJson;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json")
//    @ResponseBody
    public String register(HttpServletRequest regRequest) throws JSONException {

        String addressL3 = null;

        String title = regRequest.getParameter("title");
        String firstName = regRequest.getParameter("fname");
        String lastName = regRequest.getParameter("lname");
        String username = regRequest.getParameter("username");
        String email = regRequest.getParameter("email");
        String password = regRequest.getParameter("password");
        String addressL1 = regRequest.getParameter("address1");
        String addressL2 = regRequest.getParameter("address2");
        addressL3 = regRequest.getParameter("address3");
        String mobile = regRequest.getParameter("mobileNo");

        if (addressL3.length() < 1) {
            addressL3 = null;
        }


        String registerDetails = SendStringBuilds.sendString("{","title",":",title,"," + "firstName" + ":" + firstName + "," + "lastName" + ":" + lastName + "," + "email" + ":" + email + "," + "addressL1" + ":" + addressL1 + "," + "addressL2" + ":" + addressL2 + "," + "addressL3" + ":",addressL3,",","username",":",username,",","password",":",password,",","mobile",":",mobile,"}");
        JSONObject jsonObject = new JSONObject(registerDetails);

        try {
            URL url = new URL(registerUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ttt = null;
            StringBuilder sb = new StringBuilder();

            while ((ttt = in.readLine()) != null) {
                sb.append(ttt);
            }

            in.close();

            JSONObject reply = new JSONObject(sb.toString());
            int status = reply.getInt("code");
            if (status == 201) {
                return "locations";
            }

        } catch (Exception e) {
            logger.error("Exception occur. Reason -> " + e.getMessage());
        }

        return "/home/aboutus";
    }


    @RequestMapping("/ltest")
    public String ltest(HttpServletRequest request) throws JSONException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String loginDetailString = SendStringBuilds.sendString("{","username",":",username,",","password",":",password, "}");

        JSONObject jsonObject = new JSONObject(loginDetailString);

        try {
            URL url = new URL(loginUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ttt = null;
            StringBuilder sb = new StringBuilder();

            while ((ttt = in.readLine()) != null) {
                sb.append(ttt);
            }
            in.close();

//            toLoginJsonString = "{" + "username" + ":" + username + "," + "password" + ":" + password + "}";

            JSONObject reply = new JSONObject(sb.toString());
            int status = reply.getInt("code");
            if (status == 200) {
                return "aboutus";
            }

        } catch (Exception e) {
            logger.error("Exception occur. Reason -> " + e.getMessage());
        }
        return "location";
    }

    @RequestMapping(value = "/UniqueUser", method = RequestMethod.GET)
    public @ResponseBody
    BooleanResponse uniqueUsername(HttpServletRequest request){

        String checkName = request.getParameter("checkName");
//        String checkName = "testree";
        logger.info("unique user started");
        logger.info("check name "+ checkName);
        String urlForSearch = SendStringBuilds.sendString(customerSearchUrl, checkName);
        BooleanResponse uniqueUser;

        logger.info(urlForSearch);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ReplyFromServer replyFromServer = restTemplate.getForObject(urlForSearch, ReplyFromServer.class);

        String serverMsg = replyFromServer.getMessage();
        int sizeOfMsg= serverMsg.length();

        if (sizeOfMsg == 23){
            uniqueUser = new BooleanResponse(false);
        }else{
            uniqueUser = new BooleanResponse(true);
        }


        logger.info(String.valueOf(serverMsg.length()));

        logger.info(replyFromServer.getMessage());

        return uniqueUser;
    }



}
