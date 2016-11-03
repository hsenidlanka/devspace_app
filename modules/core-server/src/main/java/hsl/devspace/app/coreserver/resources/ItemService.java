package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.repository.category.CategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.category.SubCategoryRepositoryImpl;
import hsl.devspace.app.corelogic.repository.item.ItemRepositoryImpl;
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
 * Created by Kasun Dinesh on 9/30/16.
 * This class handles the requests related to the coupons.
 */
@Path("/items")
public class ItemService {
    private static final Logger log = LoggerFactory.getLogger(ItemService.class);
    ApplicationContext context = Context.appContext;
    CategoryRepositoryImpl categoryRepository = (CategoryRepositoryImpl) context.getBean("categoryRepoImpl");
    SubCategoryRepositoryImpl subcategoryRepository = (SubCategoryRepositoryImpl) context.getBean("subCategoryRepoImpl");
    ItemRepositoryImpl itemRepository = (ItemRepositoryImpl) context.getBean("itemRepoImpl");
    PropertyReader propertyReader = new PropertyReader("header.properties");

    // Retrieve all items of a category
    @GET
    @Path("/category/{categoryName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsByCategory(@PathParam("categoryName") String categoryName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        Item item;

        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        try {
            List itemList = categoryRepository.loadMenuItems(categoryName);
            if (itemList.size() != 0) {
                successMessage.setMessage("items for category " + categoryName + " retrieved");
                for (int i = 0; i < itemList.size(); i++) {
                    item = (Item) itemList.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", item.getItemName());
                    jsonObject.put("type", item.getType());
                    jsonObject.put("description", item.getDescription());
                    jsonObject.put("imagePath", item.getImageUrl());
                    successMessage.addData(jsonObject);
                }
            } else {
                successMessage.setMessage("no items to retrieve");
            }
        } catch (NullPointerException e) {
            successMessage.setMessage("no items to retrieve"+e.toString());
            return Response.status(Response.Status.OK).entity(successMessage).build();
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }

    // Retrieve all items of a sub-category
    @GET
    @Path("/subcategory/{subcategoryName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsBySubCategory(@PathParam("subcategoryName") String subcategoryName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        Item item;
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        try {
            List itemList = subcategoryRepository.loadMenuItems(subcategoryName);
            if (itemList.size() > 0) {
                successMessage.setMessage("items for subcategory " + subcategoryName + " retrieved");
                for (int i = 0; i < itemList.size(); i++) {
                    item = (Item) itemList.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", item.getItemName());
                    jsonObject.put("type", item.getType());
                    jsonObject.put("description", item.getDescription());
                    jsonObject.put("imagePath", item.getImageUrl());
                    successMessage.addData(jsonObject);
                }
            } else {
                successMessage.setMessage("no items to retrieve");
            }
        } catch (NullPointerException e) {
            successMessage.setMessage("no items to retrieve");
            return Response.status(Response.Status.OK).entity(successMessage)
                    .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                    .build();
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // Retrieve top rated items of a category
    @GET
    @Path("/category/{categoryName}/toprated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopItemsByCategory(@PathParam("categoryName") String categoryName, @javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(Response.Status.OK.getStatusCode());
        successMessage.setStatus("success");
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        try {
            List<Map<String, Object>> categoryList = itemRepository.getTopRatedItemsOfACategory(categoryName);
            if (categoryList.size() > 0) {
                successMessage.setMessage("top rated items for category " + categoryName + " retrieved");
                for (Map<String, Object> map : categoryList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", map.get("item_name").toString());
                    jsonObject.put("subCategory", map.get("subcategory_name").toString());
                    successMessage.addData(jsonObject);
                }
            } else {
                successMessage.setMessage("no items to retrieve");
            }
        } catch (NullPointerException e) {
            successMessage.setMessage("no items to retrieve");
            return Response.status(Response.Status.OK).entity(successMessage)
                    .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                    .build();
        }
        return Response.status(Response.Status.OK).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }
}