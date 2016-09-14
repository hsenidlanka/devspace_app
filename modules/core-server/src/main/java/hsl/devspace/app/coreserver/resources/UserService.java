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
    @Path("/add/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewUser(@PathParam("username") String userName, @PathParam("password") String password, @javax.ws.rs.core.Context UriInfo uriInfo) {
        User user = new User(userName, password);
        userRepository.add(user);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(Response.Status.CREATED.getStatusCode());
        successMessage.setMessage("user added");
        successMessage.addData("username", userName);
        successMessage.addData("password", password);
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        return Response.status(Response.Status.CREATED).entity(successMessage).build();
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
    @Path("/login/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginValidate(@PathParam("username") String userName, @PathParam("password") String password, @javax.ws.rs.core.Context UriInfo uriInfo) {
        User user = new User(userName, password);
        boolean status = userRepository.loginAuthenticate(user);
        Response response;
        if (status) {
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.OK.getStatusCode());
            successMessage.setMessage("username, password validated.");
            successMessage.addData("username", userName);
            successMessage.addData("password", password);
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            response = Response.status(Response.Status.OK).entity(successMessage).build();
        } else {
            throw new WebApplicationException(401);
        }
        return response;
    }
}
