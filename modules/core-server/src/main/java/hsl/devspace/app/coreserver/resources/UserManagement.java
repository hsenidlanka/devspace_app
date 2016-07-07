package hsl.devspace.app.coreserver.resources;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.jersey.api.NotFoundException;
import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.UserRepositoryImpl;
import hsl.devspace.app.coreserver.common.DatabaseConnection;
import hsl.devspace.app.coreserver.model.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by hsenid on 6/29/16.
 */
@Path("/user")
public class UserManagement {
    private static final Logger log = LogManager.getLogger(UserManagement.class);
    UserRepositoryImpl userImpl = new UserRepositoryImpl();

    @GET
    @Path("/add/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewUser(@PathParam("username") String userName, @PathParam("password") String password) {
//        User user = new User(userName, password);
//        userImpl.addUser(user);
        return Response.status(200).entity("hhh").build();
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getUser(@PathParam("id") int id) {

        return 0;
    }
}
