package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.repository.category.CategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by Kasun Dinesh on 10/03/16.
 * This class handles the requests related to the sub-categories.
 */
@Path("/subcategories")
public class SubCategoryService {
    private static final Logger log = LogManager.getLogger(SubCategoryService.class);
    ApplicationContext context = Context.appContext;
    SubCategoryRepositoryImpl subcategoryRepository = (SubCategoryRepositoryImpl) context.getBean("subCategoryRepoImpl");
    final String BASE_URL = "http://localhost:2222/pizza-shefu/api/v1.0/";

    // Retrieve all the sub-categories
    @GET
    @Path("/getall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubCatogories(@javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> subcategoryList = subcategoryRepository.view();
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        if (subcategoryList.size() != 0) {
            successMessage.setMessage("sub-categories retrieved");
            for (Map<String, Object> map : subcategoryList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", map.get("name").toString());
                jsonObject.put("description", map.get("description").toString());
                successMessage.addData(jsonObject);
                successMessage.addLink(BASE_URL + "items/subcategory/" + map.get("name").toString().replaceAll(" ", "%20"), map.get("name").toString() + " items");
            }
        } else {
            successMessage.setMessage("no sub-categories to retrieve");
        }
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }

    // Retrieve sub-categories of a specific category
    @GET
    @Path("/category/{categoryName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubCatogoriesByCategory(@PathParam("categoryName") String categoryName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> subcategories = subcategoryRepository.retrieveSubcatogories(categoryName);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        if (subcategories.size() != 0) {
            successMessage.setMessage("sub-categories for " + categoryName + " retrieved");
            for (Map<String, Object> map : subcategories) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", map.get("name").toString());
                successMessage.addData(jsonObject);
            }
        } else {
            successMessage.setMessage("no sub-categories to retrieve");
        }
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }
}
