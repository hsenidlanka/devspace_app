package hsl.devspace.app.coreserver.resources;

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
@Path("/customers")
public class CustomerService {
    private static final Logger log = LogManager.getLogger(CustomerService.class);
    ApplicationContext context = Context.appContext;
    UserRepositoryImpl userRepository = (UserRepositoryImpl) context.getBean("userRepoImpl");
    final String BASE_URL = "http://localhost:2222/pizza-shefu/api/v1.0/";

    // Register a new customer
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
            successMessage.addData(user);
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            successMessage.addLink(BASE_URL+"customers/"+user.getUsername(), "profile");
            response = Response.status(Response.Status.CREATED).entity(successMessage).build();
        } else {
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
        boolean status = userRepository.loginAuthenticate(user.getUsername(), user.getPassword());
        Response response;
        if (status) {
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.OK.getStatusCode());
            successMessage.setMessage("username, password validated.");
            successMessage.addData(user.getUsername());
            successMessage.addData(user.getPassword());
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
        } else {
            throw new WebApplicationException(401);
        }
        return response;
    }

    // Get details of a specific customer
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@PathParam("username") String userName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> userData = userRepository.retrieveMultipleRowsColumns(userName);
        SuccessMessage successMessage = new SuccessMessage();
        for (Map<String, Object> map : userData) {
        }
        successMessage.addData(userData);
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }
}
