package hsl.devspace.app.coreserver.exception;

import com.sun.jersey.api.NotFoundException;
import hsl.devspace.app.coreserver.model.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by hsenid on 7/7/16.
 */

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        errorMessage.setErrorMessage("Error. " + e);
        errorMessage.setDescription("Internal server error occurred. Please try again or contact the relevant person.");

        return Response.status(500).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
    }
}
