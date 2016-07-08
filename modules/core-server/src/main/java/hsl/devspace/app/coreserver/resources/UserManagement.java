package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.UserRepositoryImpl;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hsenid on 6/29/16.
 */
@Path("/users")
public class UserManagement {
    private static final Logger log = LogManager.getLogger(UserManagement.class);
    UserRepositoryImpl userImpl = new UserRepositoryImpl();

    @POST
    @Path("/add/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewUser(@PathParam("username") String userName, @PathParam("password") String password) {
        User user = new User(userName, password);
//        userImpl.addUser(user);
        Map<String, Object> userData = new HashMap<String, Object>();
        userData.put("username", user.getUsername());
        userData.put("password", user.getPassword());
        SuccessMessage successMessage = new SuccessMessage("success", Response.Status.CREATED.getStatusCode(), "user added", userData);
        return Response.status(Response.Status.CREATED).entity(successMessage).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getUser(@PathParam("id") int id) {

        return 0;
    }
}
