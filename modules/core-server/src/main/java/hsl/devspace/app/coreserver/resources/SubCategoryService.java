package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.ServerModel;
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
 * Created by Kasun Dinesh on 10/03/16.
 * This class handles the requests related to the sub-categories.
 */
@Path("/subcategories")
public class SubCategoryService {
    private static final Logger log = LoggerFactory.getLogger(SubCategoryService.class);
    ApplicationContext context = Context.appContext;
    SubCategoryRepositoryImpl subcategoryRepository = (SubCategoryRepositoryImpl) context.getBean("subCategoryRepoImpl");
    private ServerModel serverModel = (ServerModel) context.getBean("serverModel");
    final String BASE_URL = serverModel.getBaseUrl();
    PropertyReader propertyReader = new PropertyReader("header.properties");

    // Retrieve all the sub-categories
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubCatogories(@javax.ws.rs.core.Context UriInfo uriInfo) {
        List subcategoryList = subcategoryRepository.selectAll();
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        Category category;
        if (subcategoryList.size() != 0) {
            successMessage.setMessage("sub-categories retrieved");
            for (int i = 0; i < subcategoryList.size(); i++) {
                category = (Category) subcategoryList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", category.getSubCategoryName());
                jsonObject.put("description", category.getCatDescription());
                successMessage.addData(jsonObject);
                successMessage.addLink(BASE_URL + "items/subcategory/" + category.getSubCategoryName().replaceAll(" ", "%20"), category.getSubCategoryName() + " items");
            }
        } else {
            successMessage.setMessage("no sub-categories to retrieve");
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }

    // Retrieve sub-categories of a specific category
    @GET
    @Path("/category/{categoryName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubCatogoriesByCategory(@PathParam("categoryName") String categoryName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<String> subcategories = subcategoryRepository.retrieveSubcatogories(categoryName);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        if (subcategories.size() != 0) {
            successMessage.setMessage("sub-categories for " + categoryName + " retrieved");
            for (int i = 0; i < subcategories.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", subcategories.get(i));
                successMessage.addData(jsonObject);
                successMessage.addLink(BASE_URL + "items/subcategory/" + subcategories.get(i).replaceAll(" ", "%20"), "items of " + subcategories.get(i));
            }
        } else {
            successMessage.setMessage("no sub-categories to retrieve");
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }
}