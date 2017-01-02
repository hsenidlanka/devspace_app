package hsl.devspace.app.coreserver.exception;

import hsl.devspace.app.coreserver.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Kasun Dinesh on 7/7/16.
 * This class is works as a mapper to handle exceptions.
 */

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        WebApplicationExceptionHandler webApplicationExceptionHandler = new WebApplicationExceptionHandler();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus("error");
        errorMessage.setErrorCode(String.valueOf(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
        errorMessage.setErrorMessage(exception.toString());
        errorMessage.setDescription("internal server error occurred.");
        Response response500 = Response
                .serverError()
                .entity(errorMessage)
                .build();
        if (exception instanceof WebApplicationException) {
            return webApplicationExceptionHandler.handleWebApplicationException(exception, response500);
        }
        log.error("Exception occurred: ", exception);
        return response500;
    }
}