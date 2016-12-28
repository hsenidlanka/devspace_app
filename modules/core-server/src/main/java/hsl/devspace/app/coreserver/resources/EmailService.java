package hsl.devspace.app.coreserver.resources;

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

    @POST
    @Path("/verification")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmail(JSONObject jsonObject, @javax.ws.rs.core.Context UriInfo uriInfo) {
        String verificationCode = generateVerificationCode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ123456789!@#$%&", 32);
        String url = uriInfo.getAbsolutePath().toString();
        Response response;
        try {
            String username = jsonObject.get("username").toString();
            String receiverEmail = jsonObject.get("email").toString();
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
        htmlMsg += "<h2>Please verify your email address.</h2>";
        htmlMsg += "<p>Dear " + username + ",<br>";
        htmlMsg += "<p>Thank you for registering with Pizza Shefu.<br>";
        htmlMsg += "To proceed with login you need to verify your account. Please use the following code to verify your account.</p>";
        htmlMsg += "<h3>" + verificationCode + "</h3>";
        htmlMsg += "<p>Thank you.</p>";
        htmlMsg += "<p><i>Support-Pizza Shefu</i></p>";
        htmlMsg += "</html>";
        email.setHtmlMsg(htmlMsg);

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
}