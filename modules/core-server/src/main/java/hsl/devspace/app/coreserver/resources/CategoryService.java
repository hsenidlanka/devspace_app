package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.repository.category.CategoryRepositoryImpl;
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
 * Created by Kasun Dinesh on 9/26/16.
 * This class handles the requests related to the categories.
 */
@Path("/categories")
public class CategoryService {
    private static final Logger log = LogManager.getLogger(CategoryService.class);
    ApplicationContext context = Context.appContext;
    CategoryRepositoryImpl categoryRepository = (CategoryRepositoryImpl) context.getBean("categoryRepoImpl");
    final String BASE_URL = "http://localhost:2222/pizza-shefu/api/v1.0/";

    // Retrieve all the categories
    @GET
    @Path("/getall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(@javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> categoryList = categoryRepository.view();
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        if (categoryList.size() != 0) {
            successMessage.setMessage("categories retrieved");
            for (Map<String, Object> map : categoryList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", map.get("name").toString());
                jsonObject.put("description", map.get("description").toString());
                successMessage.addData(jsonObject);
                successMessage.addLink(BASE_URL+"subcategories/category/"+map.get("name").toString().replaceAll(" ","%20"), "subcategories of "+map.get("name").toString());
            }
        } else {
            successMessage.setMessage("no categories to retrieve");
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
