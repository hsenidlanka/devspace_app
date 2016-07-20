package hsl.devspace.app.coreserver.exception;

import hsl.devspace.app.coreserver.model.ErrorModel;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by hsenid on 7/7/16.
 * This class is mapped to execute when programs occurs an exception
 * which can not be caught by other exception mappers.
 */

/*@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        // Creating an error message to return as a response.
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus("error");
        errorModel.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        errorModel.setErrorMessage(e.toString());
        errorModel.setDescription("internal server error occurred.");
        // Returning a response with created error details.
        return Response.status(500).entity(errorModel).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}*/
@Provider
public class GenericExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
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

    private Response handleWebApplicationException(RuntimeException exception, Response response500) {
        WebApplicationException webAppException = (WebApplicationException) exception;
        int statusCode = webAppException.getResponse().getStatus();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatus("error");
        errorModel.setErrorCode(405);
        errorModel.setErrorMessage(exception.toString());
        errorModel.setDescription("wrong http method used. use the proper http method as described in the documentation.");

        ErrorModel errorModel2 = new ErrorModel();
        errorModel2.setStatus("error");
        errorModel2.setErrorCode(Response.Status.NOT_FOUND.getStatusCode());
        errorModel2.setErrorMessage(exception.toString());
        errorModel2.setDescription("make sure the url is correct, check the passed parameters and try again.");


        switch(statusCode) {
            case 405: return Response.status(statusCode).entity(errorModel).type(MediaType.APPLICATION_JSON).build();
            case 404: return Response.status(statusCode).entity(errorModel2).type(MediaType.APPLICATION_JSON).build();
        }
        return response500;
    }
}

