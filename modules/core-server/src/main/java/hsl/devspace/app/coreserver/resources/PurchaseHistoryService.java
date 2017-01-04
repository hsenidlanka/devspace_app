package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.repository.shopping_cart.ShoppingCartRepositoryImpl;
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
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Kasun Dinesh on 1/2/17.
 * This class handles the requests related to the purchase history.
 */
@Path("/purchase-history")
public class PurchaseHistoryService {
    private static final Logger log = LoggerFactory.getLogger(PurchaseHistoryService.class);
    ApplicationContext context = Context.appContext;
    ShoppingCartRepositoryImpl shoppingCartRepository = (ShoppingCartRepositoryImpl) context.getBean("shoppingCartRepoImpl");
    PropertyReader propertyReader = new PropertyReader("header.properties");

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchaseHistoryOfCustomer(@PathParam("username") String username, @QueryParam("page") int page, @QueryParam("records") int records, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> purchaseHistoryDataList = shoppingCartRepository.selectOrderDetails(username, records, (page - 1) * records);
        Response response;
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(200);
        successMessage.setStatus("success");
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        if (purchaseHistoryDataList.size() > 0) {
            successMessage.setMessage("order details for a customer retrieved");
            for (int i = 0; i < purchaseHistoryDataList.size(); i++) {
                Map<String, Object> purchasedDataMap = purchaseHistoryDataList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderId", purchasedDataMap.get("order_id"));
                jsonObject.put("date", purchasedDataMap.get("order_date"));
                jsonObject.put("time", purchasedDataMap.get("order_time"));
                jsonObject.put("netCost", format.format(Double.parseDouble(purchasedDataMap.get("net_cost").toString())));
                successMessage.addData(jsonObject);
            }
        } else {
            successMessage.setMessage("no order details found");
        }
        String selfUrl = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(selfUrl, "self");
        response = Response.status(200).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
        return response;
    }

    @GET
    @Path("/count/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchaseHistoryCountOfCustomer(@PathParam("username") String username, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int count = shoppingCartRepository.countOrderDetails(username);
        Response response;
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(200);
        successMessage.setStatus("success");
        successMessage.setMessage("count retrieved");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        successMessage.addData(jsonObject);
        String url = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(url, "self");
        response = Response.status(200).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
        return response;
    }

    @GET
    @Path("/items/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderItems(@PathParam("orderid") String orderId, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<List<Map<String, Object>>> itemDataList = shoppingCartRepository.selectItemDetailsOfOrder(orderId);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(200);
        successMessage.setStatus("success");
        String selfUrl = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(selfUrl, "self");
        if (itemDataList.size() > 0) {
            successMessage.setMessage("items retrieved for the order: " + orderId);
            for (int i = 0; i < itemDataList.size(); i++) {
                List<Map<String, Object>> innerList = itemDataList.get(i);
                for (int j = 0; j < innerList.size(); j++) {
                    Map<String, Object> itemsMap = innerList.get(j);

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("itemName", itemsMap.get("name"));
                    jsonObject.put("size", itemsMap.get("size"));
                    jsonObject.put("quantity", itemsMap.get("quantity"));
                    if (itemsMap.get("topping1") == null || itemsMap.get("topping1") == "") {
                        jsonObject.put("topping1", "");
                    } else {
                        jsonObject.put("topping1", itemsMap.get("topping1"));
                    }
                    if (itemsMap.get("topping2") == null || itemsMap.get("topping2") == "") {
                        jsonObject.put("topping2", "");
                    } else {
                        jsonObject.put("topping2", itemsMap.get("topping2"));
                    }
                    if (itemsMap.get("instructions") == null || itemsMap.get("instructions") == "") {
                        jsonObject.put("instructions", "");
                    } else {
                        jsonObject.put("instructions", itemsMap.get("instructions"));
                    }
                    successMessage.addData(jsonObject);
                }
            }
        } else {
            successMessage.setMessage("no items retrieved for the order: " + orderId);
        }
        return Response.status(200).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }
}
