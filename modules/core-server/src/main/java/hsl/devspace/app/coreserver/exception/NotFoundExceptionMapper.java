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
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus("error");
        errorMessage.setErrorCode(Response.Status.NOT_FOUND.getStatusCode());
        errorMessage.setErrorMessage(e.getMessage());
        errorMessage.setDescription("make sure the url is correct, check the passed parameters and try again.");
        return Response.status(404).entity(errorMessage).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
