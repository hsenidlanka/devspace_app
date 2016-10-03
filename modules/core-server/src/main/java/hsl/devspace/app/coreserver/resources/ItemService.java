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
 * Created by Kasun Dinesh on 9/30/16.
 * This class handles the requests related to the coupons.
 */
@Path("/items")
public class ItemService {
    private static final Logger log = LogManager.getLogger(ItemService.class);
    ApplicationContext context = Context.appContext;
    CategoryRepositoryImpl categoryRepository = (CategoryRepositoryImpl) context.getBean("categoryRepoImpl");
    SubCategoryRepositoryImpl subcategoryRepository = (SubCategoryRepositoryImpl) context.getBean("subCategoryRepoImpl");

    // Retrieve all items of a category
    @GET
    @Path("/category/{categoryName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsByCategory(@PathParam("categoryName") String categoryName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        try {
            List<Map<String, Object>> categoryList = categoryRepository.loadMenuItems(categoryName);
            if (categoryList.size() > 0) {
                successMessage.setMessage("items for category " + categoryName + " retrieved");
                for (Map<String, Object> map : categoryList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", map.get("name").toString());
                    jsonObject.put("type", map.get("type").toString());
                    jsonObject.put("description", map.get("description").toString());
                    jsonObject.put("imagePath", map.get("image").toString());
                    successMessage.addData(jsonObject);
                }
            } else {
                successMessage.setMessage("no items to retrieve");
            }
        } catch (NullPointerException e) {
            successMessage.setMessage("no items to retrieve");
            return Response.status(Response.Status.OK).entity(successMessage).build();
        }
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }

    // Retrieve all items of a sub-category
    @GET
    @Path("/subcategory/{subcategoryName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsBySubCategory(@PathParam("subcategoryName") String subcategoryName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        try {
            List<Map<String, Object>> categoryList = subcategoryRepository.loadMenuItems(subcategoryName);
            if (categoryList.size() > 0) {
                successMessage.setMessage("items for subcategory " + subcategoryName + " retrieved");
                for (Map<String, Object> map : categoryList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", map.get("name").toString());
                    jsonObject.put("type", map.get("type").toString());
                    jsonObject.put("description", map.get("description").toString());
                    jsonObject.put("imagePath", map.get("image").toString());
                    successMessage.addData(jsonObject);
                }
            } else {
                successMessage.setMessage("no items to retrieve");
            }
        } catch (NullPointerException e) {
            successMessage.setMessage("no items to retrieve");
            return Response.status(Response.Status.OK).entity(successMessage).build();
        }
        return Response.status(Response.Status.OK).entity(successMessage).build();
    }
}