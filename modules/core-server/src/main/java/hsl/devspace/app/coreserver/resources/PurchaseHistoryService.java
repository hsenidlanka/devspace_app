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
import java.time.LocalDate;
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

    // Retrieve orders placed by a given customer
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
            successMessage.setMessage("order details for the customer retrieved");
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

    // Retrieve number of orders placed by a customer
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

    // Retrieve items of an order
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

    // Retrieve delivery details of a order
    @GET
    @Path("/delivery/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderDeliveryData(@PathParam("orderid") String orderId, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> deliveryDataList = shoppingCartRepository.selectDeliveryDetails(orderId);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(200);
        successMessage.setStatus("success");
        if (deliveryDataList.size() > 0) {
            successMessage.setMessage("delivery data retrieved for the order " + orderId);
            Map<String, Object> deliveryDataMap = deliveryDataList.get(0);
            JSONObject deliveryDataJson = new JSONObject();
            deliveryDataJson.put("deliveryDate", deliveryDataMap.get("delivery_date"));
            deliveryDataJson.put("deliveryTime", deliveryDataMap.get("delivery_time"));

            String recepientName;
            if (deliveryDataMap.get("recepient_name") == null || deliveryDataMap.get("recepient_name") == "") {
                recepientName = "";
            } else {
                recepientName = deliveryDataMap.get("recepient_name").toString();
            }

            String recepientAddress;
            if (deliveryDataMap.get("recepient_address") == null || deliveryDataMap.get("recepient_address") == "") {
                recepientAddress = "";
            } else {
                recepientAddress = deliveryDataMap.get("recepient_address").toString();
            }

            String description;
            if (deliveryDataMap.get("description") == null || deliveryDataMap.get("description") == "") {
                description = "";
            } else {
                description = deliveryDataMap.get("description").toString();
            }

            deliveryDataJson.put("recipientName", recepientName);
            deliveryDataJson.put("recipientAddress", recepientAddress);
            deliveryDataJson.put("description", description);
            deliveryDataJson.put("deliveryMethod", deliveryDataMap.get("delivery_method"));
            deliveryDataJson.put("deliveryStatus", deliveryDataMap.get("delivery_status"));
            successMessage.addData(deliveryDataJson);
        } else {
            successMessage.setMessage("no delivery data found for the order " + orderId);
        }
        String selfUrl = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(selfUrl, "self");
        return Response.status(200).entity(successMessage).header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin")).build();
    }

    // Retrieve payment details of a order
    @GET
    @Path("/payment/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderPaymentData(@PathParam("orderid") String orderId, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> paymentDataList = shoppingCartRepository.selectPaymentDetails(orderId);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(200);
        successMessage.setStatus("success");
        if (paymentDataList.size() > 0) {
            successMessage.setMessage("payment data retrieved for the order " + orderId);
            Map<String, Object> paymentDataMap = paymentDataList.get(0);
            JSONObject paymentDataJson = new JSONObject();
            paymentDataJson.put("paymentDate", paymentDataMap.get("transaction_date"));
            paymentDataJson.put("paymentTime", paymentDataMap.get("transaction_time"));
            paymentDataJson.put("amount", numberFormat.format(Double.parseDouble(paymentDataMap.get("amount").toString())));
            paymentDataJson.put("paymentType", paymentDataMap.get("order_type"));
            paymentDataJson.put("paymentMethod", paymentDataMap.get("name"));
            successMessage.addData(paymentDataJson);
        } else {
            successMessage.setMessage("no payment data found for the order " + orderId);
        }
        String selfUrl = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(selfUrl, "self");
        return Response.status(200).entity(successMessage).header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin")).build();
    }

    // Retrieve orders placed by a given customer filtered by order placed date
    @GET
    @Path("/{username}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchaseHistoryOfCustomerByDate(@PathParam("username") String username, @PathParam("date") String date, @QueryParam("page") int page, @QueryParam("records") int records, @javax.ws.rs.core.Context UriInfo uriInfo) {
        List<Map<String, Object>> purchaseHistoryDataList = shoppingCartRepository.getOrderDetailsByDate(username, LocalDate.parse(date), records, (page - 1) * records);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setCode(200);
        successMessage.setStatus("success");
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        if (purchaseHistoryDataList.size() > 0) {
            successMessage.setMessage("order details for the customer retrieved");
            for (int i = 0; i < purchaseHistoryDataList.size(); i++) {
                Map<String, Object> purchasedDataMap = purchaseHistoryDataList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderId", purchasedDataMap.get("order_id"));
                jsonObject.put("date", purchasedDataMap.get("order_date"));
                jsonObject.put("time", purchasedDataMap.get("order_time"));
                jsonObject.put("netCost", numberFormat.format(Double.parseDouble(purchasedDataMap.get("net_cost").toString())));
                successMessage.addData(jsonObject);
            }
        } else {
            successMessage.setMessage("no order details found");
        }
        String selfUrl = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(selfUrl, "self");
        return Response.status(200).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }

    // Retrieve count of orders of a customer filtered by date
    @GET
    @Path("/count/{username}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchaseHistoryCountOfCustomerByDate(@PathParam("username") String username, @PathParam("date") String date, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int count = shoppingCartRepository.countOrdersForCustomerByDate(username, LocalDate.parse(date));
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(200);

        JSONObject jsonObject = new JSONObject();
        if (count > 0) {
            successMessage.setMessage("records found");
            jsonObject.put("count", count);
            successMessage.addData(jsonObject);
        } else {
            successMessage.setMessage("no records found");
            jsonObject.put("count", 0);
            successMessage.addData(jsonObject);
        }
        String selfUrl = uriInfo.getAbsolutePath().toString();
        successMessage.addLink(selfUrl, "self");

        return Response.status(200).entity(successMessage)
                .header("Access-Control-Allow-Origin", propertyReader.readProperty("Access-Control-Allow-Origin"))
                .build();
    }
}
