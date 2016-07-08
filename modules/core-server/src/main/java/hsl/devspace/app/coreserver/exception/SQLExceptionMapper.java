package hsl.devspace.app.coreserver.exception;

import hsl.devspace.app.coreserver.model.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

/**
 * Created by hsenid on 7/8/16.
 */

@Provider
public class SQLExceptionMapper implements ExceptionMapper<SQLException> {
    @Override
    public Response toResponse(SQLException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus("error");
        errorMessage.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        errorMessage.setErrorMessage(e.getMessage());
        errorMessage.setDescription("Errors occurred while executing database related tasks.");
        return Response.status(500).entity(errorMessage).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
