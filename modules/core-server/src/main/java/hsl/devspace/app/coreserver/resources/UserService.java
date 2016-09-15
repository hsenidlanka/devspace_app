package hsl.devspace.app.coreserver.resources;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.UserRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by Kasun Dinesh on 6/29/16.
 * This class handles the requests related to users.
 */
@Path("/users")
public class UserService {
    private static final Logger log = LogManager.getLogger(UserService.class);
    ApplicationContext context = Context.appContext;
    UserRepositoryImpl userRepository = (UserRepositoryImpl) context.getBean("userRepoImpl");

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int res = userRepository.add(user);
        Response response;
        if (res > 0) {
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.CREATED.getStatusCode());
            successMessage.setMessage("user added");
            successMessage.addData("Title", user.getTitle());
            successMessage.addData("FirstName", user.getFirstName());
            successMessage.addData("LastName", user.getLastName());
            successMessage.addData("Username", user.getUsername());
            successMessage.addData("Password", user.getPassword());
            successMessage.addData("Email", user.getEmail());
            successMessage.addData("Address line 01", user.getAddressL1());
            successMessage.addData("Address line 02", user.getAddressL2());
            successMessage.addData("Address line 03", user.getAddressL3());
            successMessage.addData("Mobile", user.getMobile());
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            response = Response.status(Response.Status.CREATED).entity(successMessage).build();
        } else {
            throw new WebApplicationException(400);
        }
        return response;
    }

    @DELETE
    @Path("/delete/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String userName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        userRepository.delete(userName);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setMessage("user deleted");
        successMessage.addData("username", userName);
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user, @javax.ws.rs.core.Context UriInfo uriInfo) {
        boolean status = userRepository.loginAuthenticate(user.getUsername(), user.getPassword());
        Response response;
        if (status) {
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.OK.getStatusCode());
            successMessage.setMessage("username, password validated.");
            successMessage.addData("username", user.getUsername());
            successMessage.addData("password", user.getPassword());
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
        } else {
            throw new WebApplicationException(401);
        }
        return response;
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@PathParam("username") String userName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> userData = userRepository.retrieveMultipleRowsColumns(userName);
        SuccessMessage successMessage = new SuccessMessage();

        JsonArray jsonArray = new JsonArray();
        for (Map<String, Object> idname : userData) {
            String title = (String) idname.get("title");
            String username = (String) idname.get("username");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("title", title);
            jsonObject.addProperty("username", username);
            jsonArray.add(jsonObject);
        }

        successMessage.addData("user", jsonArray);
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }
}
