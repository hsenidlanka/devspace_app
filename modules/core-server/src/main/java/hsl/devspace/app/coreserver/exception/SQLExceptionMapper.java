package hsl.devspace.app.coreserver.exception;

import hsl.devspace.app.coreserver.model.ErrorModel;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

/**
 * Created by hsenid on 7/8/16.
 * This class is mapped to execute when programs occurs an internal server error.
 */

@Provider
public class SQLExceptionMapper implements ExceptionMapper<SQLException> {
    @Override
    public Response toResponse(SQLException e) {
        // Creating an error message to return as a response.
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus("error");
        errorModel.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        errorModel.setErrorMessage(e.getMessage());
        errorModel.setDescription("Errors occurred while executing database related tasks.");
        // Returning a response with created error details.
        return Response.status(500).entity(errorModel).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
