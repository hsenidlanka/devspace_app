package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.Feedback;
import hsl.devspace.app.corelogic.repository.feedback.FeedbackRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by Kasun Dinesh on 9/26/16.
 * This class handles the requests related to the feedbacks.
 */
@Path("/feedbacks")
public class FeedbackService {
    private static final Logger log = LoggerFactory.getLogger(FeedbackService.class);
    ApplicationContext context = Context.appContext;
    FeedbackRepositoryImpl feedbackRepository = (FeedbackRepositoryImpl) context.getBean("feedbackRepoImpl");
    PropertyReader propertyReader = new PropertyReader("header.properties");

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFeedback(Feedback feedback, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int affectedRows = feedbackRepository.add(feedback);
        SuccessMessage successMessage = new SuccessMessage();
        if (affectedRows > 0) {
            successMessage.setCode(Response.Status.CREATED.getStatusCode());
            successMessage.setStatus("success");
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            successMessage.setMessage("feedback added successfully.");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("comment", feedback.getComment());
            jsonObject.put("numberOfStars", feedback.getNumberOfStars());
            jsonObject.put("itemName", feedback.getItemName());
            jsonObject.put("customerUserName", feedback.getCustomerUserName());

            successMessage.addData(jsonObject);
        } else {
            throw new WebApplicationException(400);
        }
        return Response.status(Response.Status.OK).entity(successMessage).header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin")).build();
    }

    @GET
    @Path("/view/{item}/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFeedback(@PathParam("item") String itemName, @PathParam("username") String username, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> feedbackList = feedbackRepository.selectFeedbacksByCustomerAndItem(username, itemName);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(200);
        if (feedbackList.size() > 0) {
            for (int i = 0; i < feedbackList.size(); i++) {
                successMessage.setMessage("feedback found");
                Map<String, Object> feedbackMap = feedbackList.get(i);
                JSONObject feedbackJson = new JSONObject();
                feedbackJson.put("comment", feedbackMap.get("comment"));
                feedbackJson.put("stars", feedbackMap.get("number_of_stars"));
                successMessage.addData(feedbackJson);
            }
        } else {
            successMessage.setMessage("no feedback found");
        }

        String selfUrl = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(selfUrl, "self");
        return Response.status(200).entity(successMessage).header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin")).build();
    }
}
