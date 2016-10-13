package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.Category;
import hsl.devspace.app.corelogic.repository.category.CategoryRepositoryImpl;
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
 * Created by Kasun Dinesh on 9/26/16.
 * This class handles the requests related to the categories.
 */
@Path("/categories")
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    ApplicationContext context = Context.appContext;
    CategoryRepositoryImpl categoryRepository = (CategoryRepositoryImpl) context.getBean("categoryRepoImpl");
    private ServerModel serverModel = (ServerModel) context.getBean("serverModel");
    final String BASE_URL = serverModel.getBaseUrl();
    PropertyReader propertyReader=new PropertyReader("header.properties");

    // Retrieve all the categories
    @GET
    @Path("/getall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(@javax.ws.rs.core.Context UriInfo uriInfo) {
        List categoryList = categoryRepository.selectAll();
        Category category;
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");

        if (categoryList.size() != 0) {
            successMessage.setMessage("categories retrieved");
            for (int i=0; i<categoryList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                category= (Category) categoryList.get(i);
                jsonObject.put("name", category.getCategoryName());
                jsonObject.put("description", category.getDescription());
                successMessage.addData(jsonObject);
                successMessage.addLink(BASE_URL+"subcategories/category/"+category.getCategoryName().replaceAll(" ","%20"), "subcategories of "+category.getCategoryName().toString());
            }
        } else {
            successMessage.setMessage("no categories to retrieve");
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }
}
