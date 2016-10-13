package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.repository.feedback.FeedbackRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Kasun Dinesh on 9/26/16.
 * This class handles the requests related to the feedbacks.
 */
@Path("/feedbacks")
public class FeedbackService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    ApplicationContext context = Context.appContext;
    FeedbackRepositoryImpl feedbackRepository = (FeedbackRepositoryImpl) context.getBean("feedbackRepoImpl");
    PropertyReader propertyReader = new PropertyReader("header.properties");

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(@javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        /*if (categoryList.size() != 0) {
            successMessage.setMessage("categories retrieved");
            for (Map<String, Object> map : categoryList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", map.get("name").toString());
                jsonObject.put("description", map.get("description").toString());
                successMessage.addData(jsonObject);
            }
        } else {
            successMessage.setMessage("no categories to retrieve");
        }*/

        return Response.status(Response.Status.OK).entity(successMessage).build();
    }
}
