package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.user.UserRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.ServerModel;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Kasun Dinesh on 12/27/16.
 * This class handles the requests related to the email sending processes.
 */
@Path("/email")
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    ApplicationContext context = Context.appContext;
    private ServerModel serverModel = (ServerModel) context.getBean("serverModel");
    final String BASE_URL = serverModel.getBaseUrl();
    PropertyReader headerPropertyReader = new PropertyReader("header.properties");
    PropertyReader emailPropertyReader = new PropertyReader("email.properties");
    UserRepositoryImpl userRepository = (UserRepositoryImpl) context.getBean("userRepoImpl");

    @POST
    @Path("/verification-code")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmailWithVerificationCode(JSONObject jsonObject, @javax.ws.rs.core.Context UriInfo uriInfo) {
        String verificationCode = generateVerificationCode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ123456789!@#$%&", 32);
        String url = uriInfo.getAbsolutePath().toString();
        Response response;
        try {
            String username = jsonObject.get("username").toString();
            User user = userRepository.retrieveSelectedUserDetails(username);
            String receiverEmail = user.getEmail();
            userRepository.addVerificationCode(username, verificationCode);
            sendVerificationEmail(username, receiverEmail, verificationCode);
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.CREATED.getStatusCode());
            successMessage.setMessage("new verification code sent");
            JSONObject successDataJsonObj = new JSONObject();
            successDataJsonObj.put("email", receiverEmail);
            successDataJsonObj.put("verificationCode", verificationCode);
            successMessage.addData(successDataJsonObj);
            successMessage.addLink(url, "self");
            successMessage.addLink(BASE_URL + "customers/verify", "verify customer");
            response = Response.status(Response.Status.OK).entity(successMessage).
                    header("Access-Control-Allow-Origin", headerPropertyReader.readProperty("Access-Control-Allow-Origin")).build();
        } catch (EmailException e) {
            log.error("Error sending verification code email. {}", e);
            throw new WebApplicationException(500);
        } catch (MalformedURLException e) {
            log.error("Error sending verification code email. {}", e);
            throw new WebApplicationException(500);
        }
        return response;
    }

    // Process of sending an email containing the verification code
    public String sendVerificationEmail(String username, String receiverEmail, String verificationCode) throws EmailException, MalformedURLException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(emailPropertyReader.readProperty("host.name"));
        email.setSmtpPort(Integer.parseInt(emailPropertyReader.readProperty("smtp.port")));
        email.setAuthenticator(new DefaultAuthenticator(emailPropertyReader.readProperty("authenticator.account.email"), emailPropertyReader.readProperty("authenticator.account.password")));
        email.setSSLOnConnect(true);
        email.setFrom(emailPropertyReader.readProperty("authenticator.account.email"), emailPropertyReader.readProperty("verification.account.name"));
        email.setSubject(emailPropertyReader.readProperty("verification.message.subject"));

        URL url = new URL("http://localhost:8081/web-selfcare/resources/images/logo.png");
        String cid = email.embed(url, "Pizza Shefu logo");

        // set the html message
        String htmlMsg = "<html><div style=\"text-align:center\"><img width=\"150px\" height=\"150px\" src=\"cid:" + cid + "\"></div>";
        htmlMsg += "<h2 style='color:#ff8000;'>Please verify your email address.</h2>";
        htmlMsg += "<p style='font-size:14px;'>Dear " + username + ",<br>";
        htmlMsg += "<p style='font-size:14px;'>Thank you for registering with Pizza Shefu.<br>";
        htmlMsg += "To proceed with login you need to verify your account. Please use the following code to verify your account.</p>";
        htmlMsg += "<h2 style='color: #0040ff;'>" + verificationCode + "</h2><br>";
        htmlMsg += "<p style='font-size:14px;'>Thank you.</p>";
        htmlMsg += "<p style='font-size:14px;'><strong><i>Support-Pizza Shefu</i></strong></p>";
        htmlMsg += "</html>";

        // Set the html message
        email.setHtmlMsg(htmlMsg);

        // Set the alternative message if the email client does not support HTML messages
        email.setTextMsg("Please use the following code to activate your code.\n" + verificationCode);

        email.addTo(receiverEmail);
        email.send();
        return verificationCode;
    }

    // Generate a new verification code
    public String generateVerificationCode(String combinations, int length) {
        // Possible characters in the coupon code
        String stringCombinations = combinations;
        // Get possible characters to a char array
        char[] charCombinations = stringCombinations.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        // length: Length of the coupon code
        for (int i = 0; i < length; i++) {
            char randomChar = charCombinations[random.nextInt(charCombinations.length)];
            stringBuilder.append(randomChar);
        }
        String verificationCode = stringBuilder.toString();
        return verificationCode;
    }

    // Process of sending an email notifying a successful account activation
    public String sendVerificationSuccessEmail(String username, String receiverEmail) throws EmailException, MalformedURLException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(emailPropertyReader.readProperty("host.name"));
        email.setSmtpPort(Integer.parseInt(emailPropertyReader.readProperty("smtp.port")));
        email.setAuthenticator(new DefaultAuthenticator(emailPropertyReader.readProperty("authenticator.account.email"), emailPropertyReader.readProperty("authenticator.account.password")));
        email.setSSLOnConnect(true);
        email.setFrom(emailPropertyReader.readProperty("authenticator.account.email"), emailPropertyReader.readProperty("verification.account.name"));
        email.setSubject(emailPropertyReader.readProperty("verification.success.message.subject"));

        URL url = new URL("http://localhost:8081/web-selfcare/resources/images/logo.png");
        String cid = email.embed(url, "Pizza Shefu logo");

        // set the html message
        String htmlMsg = "<html><div style=\"text-align:center\"><img width=\"150px\" height=\"150px\" src=\"cid:" + cid + "\"></div>";
        htmlMsg += "<h2 style='color:green;'>Account activated.</h2>";
        htmlMsg += "<p style='font-size:14px;'>Dear " + username + ",<br>";
        htmlMsg += "<p style='font-size:14px;'>Thank you for registering with Pizza Shefu.<br>";
        htmlMsg += "Your account has activated successfully.</p><br>";
        htmlMsg += "<p style='font-size:14px;'>Thank you.</p>";
        htmlMsg += "<p style='font-size:14px;'><strong><i>Support-Pizza Shefu</i></strong></p>";
        htmlMsg += "</html>";

        // Set the html message
        email.setHtmlMsg(htmlMsg);

        // Set the alternative message if the email client does not support HTML messages
        email.setTextMsg("Your account has activated successfully.");

        email.addTo(receiverEmail);
        email.send();
        return "";
    }

    // Process of sending an email notifying a successful account activation
    public String sendPasswordChangedNotificationEmail(String username, String receiverEmail) throws EmailException, MalformedURLException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(emailPropertyReader.readProperty("host.name"));
        email.setSmtpPort(Integer.parseInt(emailPropertyReader.readProperty("smtp.port")));
        email.setAuthenticator(new DefaultAuthenticator(emailPropertyReader.readProperty("authenticator.account.email"), emailPropertyReader.readProperty("authenticator.account.password")));
        email.setSSLOnConnect(true);
        email.setFrom(emailPropertyReader.readProperty("authenticator.account.email"), emailPropertyReader.readProperty("verification.account.name"));
        email.setSubject(emailPropertyReader.readProperty("password.changed.message.subject"));

        URL url = new URL("http://localhost:8081/web-selfcare/resources/images/logo.png");
        String cid = email.embed(url, "Pizza Shefu logo");

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        // set the html message
        String htmlMsg = "<html><div style=\"text-align:center\"><img width=\"150px\" height=\"150px\" src=\"cid:" + cid + "\"></div>";
        htmlMsg += "<h2 style='color:#ff8000;'>Password changed.</h2>";
        htmlMsg += "<p style='font-size:14px;'>Dear " + username + ",<br>";
        htmlMsg += "<p style='font-size:14px;'>Your password has changed on " + ft.format(dNow) + ". Please contact support if you did not change the password.<br>";
        htmlMsg += "We are glad to help you.</p><br>";
        htmlMsg += "<p style='font-size:14px;'>Thank you.</p>";
        htmlMsg += "<p style='font-size:14px;'><strong><i>Support-Pizza Shefu</i></strong></p>";
        htmlMsg += "</html>";

        // Set the html message
        email.setHtmlMsg(htmlMsg);

        // Set the alternative message if the email client does not support HTML messages
        email.setTextMsg("Yout account password changed. Contact support if you did not change it.");

        email.addTo(receiverEmail);
        email.send();
        return "";
    }
}
