package hsl.devspace.app.coreserver.exception;

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
        errorMessage.setStatus("error");
        errorMessage.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        errorMessage.setErrorMessage(e.toString());
        errorMessage.setDescription("internal server error occurred. please try again or contact the relevant person.");
        return Response.status(500).entity(errorMessage).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
