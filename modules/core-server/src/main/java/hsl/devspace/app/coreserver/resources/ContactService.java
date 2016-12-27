package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.ContactUs;
import hsl.devspace.app.corelogic.repository.contact_us.ContactUsRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Kasun Dinesh on 10/24/16.
 */
@Path("/contactus")
public class ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    ApplicationContext context = Context.appContext;
    ContactUsRepositoryImpl contactUsRepository = (ContactUsRepositoryImpl) context.getBean("contactUsRepoImpl");

    // Send new contact message
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGuest(ContactUs contactUs, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int res = contactUsRepository.add(contactUs);
        Response response;
        if (res > 0) {
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.CREATED.getStatusCode());
            successMessage.setMessage("new message added");

            JSONObject jsonObject = new JSONObject();
            int inquiryType = contactUs.getInquiryType();
            switch (inquiryType) {
                case 1:
                    jsonObject.put("inquiryType", "complaint");
                    break;
                case 2:
                    jsonObject.put("inquiryType", "suggestion");
                    break;
                case 3:
                    jsonObject.put("inquiryType", "clarify");
                    break;
                case 4:
                    jsonObject.put("inquiryType", "other");
                    break;
                default:
                    jsonObject.put("inquiryType", "no specification found");
            }
            jsonObject.put("title", contactUs.getTitle());
            jsonObject.put("name", contactUs.getName());
            jsonObject.put("email", contactUs.getEmail());
            jsonObject.put("mobile", contactUs.getMobile());
            jsonObject.put("message", contactUs.getMessage());
            successMessage.addData(jsonObject);

            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");

            response = Response.status(Response.Status.CREATED).entity(successMessage).build();
        } else {
            throw new WebApplicationException(400);
        }
        return response;
    }
}
