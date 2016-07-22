package hsl.devspace.app.coreserver.exception;

import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.model.ErrorModel;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Kasun Dinesh on 7/7/16.
 * This class is works as a mapper to handle exceptions.
 */

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        WebApplicationExceptionHandler webApplicationExceptionHandler=new WebApplicationExceptionHandler();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus("error");
        errorModel.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        errorModel.setErrorMessage(exception.toString());
        errorModel.setDescription("internal server error occurred.");
        Response response500 = Response
                .serverError()
                .entity(errorModel)
                .build();

        if (exception instanceof WebApplicationException) {
            return webApplicationExceptionHandler.handleWebApplicationException(exception, response500);
        }
        return response500;
    }


}