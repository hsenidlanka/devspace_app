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

    ApplicationContext context = Context.appContext;

    @Override
    public Response toResponse(Exception exception) {
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
            return handleWebApplicationException(exception, response500);
        }
        return response500;
    }

    // This method will generate a relevant response according to the WebApplicationException occurred.
    private Response handleWebApplicationException(Exception exception, Response response500) {
        WebApplicationException webAppException = (WebApplicationException) exception;
        // Get the status code of the exception
        int statusCode = webAppException.getResponse().getStatus();

        // Check statusCode
        switch (statusCode) {
            // According to the status code, specific error message will be generated.
            // Error message format for status codes are defined in spring-config-error-msgs.xml file.
            // If the statusCode matches with the cases of the switch, relevant response will return.
            case 401:
                ErrorModel error401 = (ErrorModel) context.getBean("response401");
                return Response.status(statusCode).entity(error401).type(MediaType.APPLICATION_JSON).build();
            case 404:
                ErrorModel error404 = (ErrorModel) context.getBean("response404");
                return Response.status(statusCode).entity(error404).type(MediaType.APPLICATION_JSON).build();
            case 405:
                ErrorModel error405 = (ErrorModel) context.getBean("response405");
                return Response.status(statusCode).entity(error405).type(MediaType.APPLICATION_JSON).build();
            case 408:
                ErrorModel error408 = (ErrorModel) context.getBean("response408");
                return Response.status(statusCode).entity(error408).type(MediaType.APPLICATION_JSON).build();
            case 409:
                ErrorModel error409 = (ErrorModel) context.getBean("response409");
                return Response.status(statusCode).entity(error409).type(MediaType.APPLICATION_JSON).build();
            case 414:
                ErrorModel error414 = (ErrorModel) context.getBean("response414");
                return Response.status(statusCode).entity(error414).type(MediaType.APPLICATION_JSON).build();
            case 415:
                ErrorModel error415 = (ErrorModel) context.getBean("response415");
                return Response.status(statusCode).entity(error415).type(MediaType.APPLICATION_JSON).build();
        }
        // If the status code did not match with switch case,
        // already defined internal server error message will be returned.
        return response500;
    }
}

