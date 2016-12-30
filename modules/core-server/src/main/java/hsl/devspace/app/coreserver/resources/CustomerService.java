package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.user.UserRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.ErrorMessage;
import hsl.devspace.app.coreserver.model.ServerModel;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.apache.commons.mail.EmailException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.MalformedURLException;
import java.sql.SQLException;

/**
 * Created by Kasun Dinesh on 6/29/16.
 * This class handles the requests related to the customers.
 */
@Path("/customers")
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    ApplicationContext context = Context.appContext;
    UserRepositoryImpl userRepository = (UserRepositoryImpl) context.getBean("userRepoImpl");
    private ServerModel serverModel = (ServerModel) context.getBean("serverModel");
    final String BASE_URL = serverModel.getBaseUrl();
    PropertyReader propertyReader = new PropertyReader("header.properties");
    EmailService emailService = new EmailService();

    // Register a new customer
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user, @javax.ws.rs.core.Context UriInfo uriInfo) {
        PlatformTransactionManager transactionManager = (PlatformTransactionManager) context.getBean("transactionManager");
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        int res = userRepository.add(user);
        Response response;
        String sentCode = "";
        if (res > 0) {
            String verificationCode = emailService.generateVerificationCode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ123456789!@#$%&", 32);
            userRepository.addVerificationCode(user.getUsername(), verificationCode);
            try {
                sentCode = emailService.sendVerificationEmail(user.getUsername(), user.getEmail(), verificationCode);
                transactionManager.commit(status);
            } catch (EmailException e) {
                log.error("Error sending verification code email. {}", e);
                transactionManager.rollback(status);
                throw new WebApplicationException(500);
            } catch (MalformedURLException e) {
                log.error("Error sending verification code email. {}", e);
                transactionManager.rollback(status);
                throw new WebApplicationException(500);
            }
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.CREATED.getStatusCode());
            successMessage.setMessage("user added to the database");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", user.getTitle());
            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("addressLine01", user.getAddressL1());
            jsonObject.put("addressLine02", user.getAddressL2());
            if (user.getAddressL3() != null) {
                jsonObject.put("addressLine03", user.getAddressL3());
            }
            jsonObject.put("mobile", user.getMobile());
            jsonObject.put("verificationCode", sentCode);
            successMessage.addData(jsonObject);

            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            successMessage.addLink(BASE_URL + "customers/verify", "verify customer");

            response = Response.status(Response.Status.CREATED).entity(successMessage).build();
        } else {
            transactionManager.rollback(status);
            throw new WebApplicationException(400);
        }
        return response;
    }

    // Validate customer login
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int status = userRepository.loginAuthenticate(user.getUsername(), user.getPassword());
        Response response;
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setMessage("username, password validated.");
        if (status == 1) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", user.getUsername());
            jsonObject.put("accountStatus", "verified");
            successMessage.addData(jsonObject);

            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            successMessage.addLink(BASE_URL + "customers/" + user.getUsername(), "profile");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
        } else if (status == 2) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", user.getUsername());
            jsonObject.put("accountStatus", "blocked");
            successMessage.addData(jsonObject);
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
        } else if (status == 3) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", user.getUsername());
            jsonObject.put("accountStatus", "not-verified");
            successMessage.addData(jsonObject);
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            successMessage.addLink(BASE_URL + "customers/verify", "verify customer");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
        } else {
            throw new WebApplicationException(401);
        }
        return response;
    }

    // Get details of a specific customer
    @GET
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@PathParam("username") String userName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        User user = userRepository.retrieveSelectedUserDetails(userName);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        JSONObject jsonObject = new JSONObject();
        if (user.getUsername() != null) {
            successMessage.setMessage("customer data retrieved");
            jsonObject.put("title", user.getTitle());
            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("addressLine01", user.getAddressL1());
            jsonObject.put("addressLine02", user.getAddressL2());
            if (user.getAddressL3() != null) {
                jsonObject.put("addressLine03", user.getAddressL3());
            }
            jsonObject.put("mobile", user.getMobile());
            jsonObject.put("registeredDate", user.getRegDate());
            successMessage.addData(jsonObject);
        } else {
            successMessage.setMessage("no customer data to retrieve");
        }

        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }

    // Update user profile
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerProfile(User user, @javax.ws.rs.core.Context UriInfo uriInfo) {
        Response response;
        try {
            int status = userRepository.update(user);
            if (status > 0) {
                SuccessMessage successMessage = new SuccessMessage();
                successMessage.setStatus("success");
                successMessage.setCode(Response.Status.OK.getStatusCode());
                successMessage.setMessage("user profile updated");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", user.getTitle());
                jsonObject.put("firstName", user.getFirstName());
                jsonObject.put("lastName", user.getLastName());
                jsonObject.put("username", user.getUsername());
                jsonObject.put("Email", user.getEmail());
                jsonObject.put("AddressLine01", user.getAddressL1());
                jsonObject.put("AddressLine02", user.getAddressL2());
                if (user.getAddressL3() != null) {
                    jsonObject.put("addressLine03", user.getAddressL3());
                }
                jsonObject.put("mobile", user.getMobile());
                successMessage.addData(jsonObject);

                String url = uriInfo.getAbsolutePath().toString();
                successMessage.addLink(url, "self");

                response = Response.status(Response.Status.CREATED).entity(successMessage).build();
            } else {
                throw new WebApplicationException(400);
            }
        } catch (SQLException e) {
            throw new WebApplicationException(500);
        }
        return response;
    }

    // Update user password
    @PUT
    @Path("/password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerPassword(JSONObject jsonObject, @javax.ws.rs.core.Context UriInfo uriInfo) {
        Response response;
        SuccessMessage successMessage = new SuccessMessage();
        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();
        String newPassword = jsonObject.get("newPassword").toString();
        int stat = userRepository.changePassword(username, password, newPassword);
        if (stat > 0) {
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.OK.getStatusCode());
            successMessage.setMessage("customer password updated successfully");
            JSONObject object = new JSONObject();
            object.put("username", username);
            successMessage.addData(object);
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
            User user = userRepository.retrieveSelectedUserDetails(username);
            String receiverEmail = user.getEmail();
            try {
                emailService.sendPasswordChangedNotificationEmail(username,receiverEmail);
            } catch (EmailException e) {
                log.error("Error sending password success notification email. {}", e);
                throw new WebApplicationException(500);
            } catch (MalformedURLException e) {
                log.error("Error sending password success notification email. {}", e);
                throw new WebApplicationException(500);
            }
        } else {
            throw new WebApplicationException(401);
        }
        return response;
    }

    // Verify customer
    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verify(JSONObject jsonObject, @javax.ws.rs.core.Context UriInfo uriInfo) {
        String username = (String) jsonObject.get("username");
        String verificationCode = (String) jsonObject.get("verificationCode");
        int codeStatus = userRepository.checkVerificationCodeAvailability(username, verificationCode);
        Response response;
        if (codeStatus == 1) {
            userRepository.changeStatusToActiveFromNotVerified(username, verificationCode);
            User user = userRepository.retrieveSelectedUserDetails(username);
            String receiverEmail = user.getEmail();
            try {
                emailService.sendVerificationSuccessEmail(username, receiverEmail);
            } catch (EmailException e) {
                log.error("Error sending verification success email. {}", e);
                throw new WebApplicationException(500);
            } catch (MalformedURLException e) {
                log.error("Error sending verification success email. {}", e);
                throw new WebApplicationException(500);
            }
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(200);
            successMessage.setMessage("user verified successfully");
            successMessage.addData(jsonObject);
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            successMessage.addLink(BASE_URL + "customers/login", "customer login");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
        } else {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setStatus("error");
            errorMessage.setErrorCode("401");
            errorMessage.setErrorMessage("invalid verification details");
            errorMessage.setDescription("entered parameters have no matching combinations.");
            response = Response.status(Response.Status.OK).entity(errorMessage).build();
        }
        return response;
    }
}
