package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.UserRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.model.ErrorModel;
import hsl.devspace.app.coreserver.model.SuccessModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hsenid on 6/29/16.
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
    public Response addNewUser(@PathParam("username") String userName, @PathParam("password") String password) {
        User user = new User(userName, password);
        userRepository.add(user);
        Map<String, Object> userData = new HashMap<String, Object>();
        userData.put("username", user.getUsername());
        userData.put("password", user.getPassword());
        SuccessModel successModel = new SuccessModel("success", Response.Status.CREATED.getStatusCode(), "user added", userData);
        return Response.status(Response.Status.CREATED).entity(successModel).build();
    }

    @DELETE
    @Path("/delete/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {
        userRepository.delete(username);
        Map<String, Object> userData = new HashMap<String, Object>();
        userData.put("username", username);
        SuccessModel successModel = new SuccessModel("success", Response.Status.OK.getStatusCode(), "user deleted", userData);
        return Response.status(Response.Status.OK).entity(successModel).build();
    }

    @POST
    @Path("/login/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginValidate(@PathParam("username") String userName, @PathParam("password") String password) {
        User user = new User(userName, password);
        boolean status = userRepository.loginAuthenticate(user);
        Response response;
        if (status) {
            Map<String, Object> userData = new HashMap<String, Object>();
            userData.put("username", user.getUsername());
            userData.put("password", user.getPassword());
            SuccessModel successModel = new SuccessModel("success",Response.Status.OK.getStatusCode(),"username, password validated.",userData);
            response = Response.status(Response.Status.OK).entity(successModel).build();
        } else {
            ErrorModel errorModel = new ErrorModel("unauthorized",Response.Status.UNAUTHORIZED.getStatusCode(),"unauthorized user","username, password not matched. Check username and password.");
            response = Response.status(Response.Status.UNAUTHORIZED).entity(errorModel).build();
        }
        return response;
    }
}
