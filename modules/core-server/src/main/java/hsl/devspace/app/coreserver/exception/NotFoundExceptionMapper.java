package hsl.devspace.app.coreserver.exception;

import com.sun.jersey.api.NotFoundException;
import hsl.devspace.app.coreserver.model.ErrorModel;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by hsenid on 7/7/16.
 * This class is mapped to execute when programs occurs a 404 (NOT FOUND) error.
 */


public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        // Creating an error message to return as a response.
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus("error");
        errorModel.setErrorCode(Response.Status.NOT_FOUND.getStatusCode());
        errorModel.setErrorMessage(e.getMessage());
        errorModel.setDescription("make sure the url is correct, check the passed parameters and try again.");
        // Returning a response with created error details.
        return Response.status(404).entity(errorModel).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
