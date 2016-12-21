package hsl.devspace.app.coreserver.resources;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.caas.ChargingRequestSender;
import hms.kite.samples.api.caas.messages.ChargingRequestResponse;
import hms.kite.samples.api.caas.messages.DirectDebitRequest;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsResp;
import hsl.devspace.app.corelogic.domain.Delivery;
import hsl.devspace.app.corelogic.repository.coupon.CouponRepositoryImpl;
import hsl.devspace.app.corelogic.repository.shopping_cart.ShoppingCartRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.ErrorMessage;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Kasun Dinesh on 11/16/16.
 * This class handles the requests related to the checkout process.
 */

@Path("/payments")
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    ApplicationContext context = Context.appContext;
    ShoppingCartRepositoryImpl shoppingCartRepository = (ShoppingCartRepositoryImpl) context.getBean("shoppingCartRepoImpl");
    CouponRepositoryImpl couponRepository = (CouponRepositoryImpl) context.getBean("couponRepoImpl");
    private ServerModel serverModel = (ServerModel) context.getBean("serverModel");
    final String BASE_URL = serverModel.getBaseUrl();
    PropertyReader propertyReader = new PropertyReader("devspace.properties");
    final String DEVSPACE_BASE_URL = propertyReader.readProperty("service.base.url");
    PropertyReader headerPropertyReader = new PropertyReader("header.properties");

    // Method to accept mobile payments from customers using DevSpace CAAS API
    @POST
    @Path("/pay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response purchase(JSONObject jsonObject, @javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        ErrorMessage errorMessage = new ErrorMessage();
        Response response;

        String url = uriInfo.getAbsolutePath().toString();
        String username = jsonObject.get("username").toString();
        String subscriberId = jsonObject.get("subscriberId").toString();
        String amount = jsonObject.get("amount").toString();
        String paymentMethod = jsonObject.get("paymentMethod").toString();
        String couponCode = jsonObject.get("couponCode").toString();

//        log.info("username: {}", username);
//        log.info("subscriberId: {}", subscriberId);
//        log.info("amount: {}", amount);
//        log.info("paymentMethod: {}", paymentMethod);
//        log.info("couponCode: {}", couponCode);

        List<Map<String, Object>> itemsListMap = (List<Map<String, Object>>) jsonObject.get("items");
        Map<String, String> deliveryDataMap = (Map<String, String>) jsonObject.get("delivery");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        log.info("DATE: {}", deliveryDataMap.get("date"));
        LocalDate date = LocalDate.parse(deliveryDataMap.get("date").replaceAll(" ", ""), formatter);
        formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime time = LocalTime.parse(deliveryDataMap.get("time"), formatter);
//        log.info("TIME: {}", deliveryDataMap.get("time"));

        Delivery delivery = new Delivery();
        delivery.setRecepientName(deliveryDataMap.get("recepientName"));
        delivery.setRecepientAddress(deliveryDataMap.get("recepientAddress"));
        delivery.setDeliveryDate(date);
        delivery.setDeliveryTime(time);
        delivery.setDescription(deliveryDataMap.get("description"));
        delivery.setDeliveryMethod(deliveryDataMap.get("method"));

//        log.info("itemsList: {}", itemsListMap);
//        log.info("deliveryData: {}", deliveryDataMap);

        String applicationId = propertyReader.readProperty("service.application.id");
        String password = propertyReader.readProperty("service.password");
        String externalTrxId = String.valueOf(System.currentTimeMillis());

        DirectDebitRequest directDebitRequest = new DirectDebitRequest();
        directDebitRequest.setApplicationId(applicationId);
        directDebitRequest.setPassword(password);
        directDebitRequest.setSubscriberId(subscriberId);
        directDebitRequest.setAmount(amount);
        directDebitRequest.setExternalTrxId(externalTrxId);
        try {
            ChargingRequestSender chargingRequestSender = new ChargingRequestSender(
                    new URL(DEVSPACE_BASE_URL + propertyReader.readProperty("caas.direct.debit.url")));
            ChargingRequestResponse chargingRequestResponse = chargingRequestSender
                    .sendChargingRequest(directDebitRequest);

            String paymentStatusCode = chargingRequestResponse.getStatusCode();
            log.info("payment status: {}", paymentStatusCode);
            switch (paymentStatusCode) {
                case "S1000":
                    try {
                        String orderId = shoppingCartRepository.generateCartProcess(Double.parseDouble(amount), username, itemsListMap, delivery, paymentMethod);
                        if (orderId.equals("0") || orderId == null || orderId.equals("")) {
                            throw new WebApplicationException(500);
                        } else {
                            log.info("new checkout processed successfully.");
                            if (couponCode != null || !couponCode.equals("")) {
                                couponRepository.changeStatusToUsed(couponCode);
                            }
                            // Generate a new coupon code
                            String newCouponCode;
                            int newCouponStatus = 0;
                            while (true) {
                                newCouponCode = generateCouponCode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ123456789", 20);
                                newCouponStatus = couponRepository.add(newCouponCode, subscriberId.replaceFirst("94", "0"));
                                // Iteration continues if the generated coupon code exists in the database
                                if (newCouponStatus != 2 && newCouponStatus > 0) {
                                    // Break the iteration if the generated coupon code is unique and valid
                                    break;
                                }
                            }
                            successMessage.setStatus("success");
                            successMessage.setCode(Response.Status.OK.getStatusCode());
                            successMessage.addLink(url, "self");
                            JSONObject dataObj = new JSONObject();
                            dataObj.put("subscriberId", subscriberId);
                            dataObj.put("paymentAmount", amount);
                            dataObj.put("internalTrxId", chargingRequestResponse.getInternalTrxId());
                            dataObj.put("timeStamp", chargingRequestResponse.getTimeStamp());
                            dataObj.put("orderId", orderId);
                            dataObj.put("couponCode", newCouponCode);
                            successMessage.addData(dataObj);
                            URL smsUrl = new URL(DEVSPACE_BASE_URL + propertyReader.readProperty("sms.send.url"));
                            MtSmsResp smsResponse = sendPaymentSmsNotification(subscriberId, amount, orderId, applicationId, password, smsUrl);
                            successMessage.setMessage("payment succeeded");
                            if (!String.valueOf(smsResponse.getStatusCode()).equals("S1000")) {
                                successMessage.setMessage("payment succeeded but failed to send the sms notification");
                            }
                            log.info("sms status: {}", smsResponse.getStatusCode());
                            response = Response.status(Response.Status.OK).entity(successMessage).
                                    header("Access-Control-Allow-Origin", headerPropertyReader.readProperty("Access-Control-Allow-Origin")).build();
                        }
                    } catch (Exception ex) {
                        log.error("error while processing the payment. {}", ex);
                        throw new WebApplicationException(500);
                    }
                    break;
                case "E1308":
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1308");
                    errorMessage.setErrorMessage("no enough credit");
                    errorMessage.setDescription("there is not enough balance in the user's account.");
                    response = Response.status(Response.Status.OK).entity(errorMessage).
                            header("Access-Control-Allow-Origin", headerPropertyReader.readProperty("Access-Control-Allow-Origin")).build();
                    break;
                case "E1313":
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1313");
                    errorMessage.setErrorMessage("authentication failed");
                    errorMessage.setDescription("no such active application with given applicationId" +
                            " or no active service provider or the given password in the request is invalid.");
                    response = Response.status(Response.Status.OK).entity(errorMessage).
                            header("Access-Control-Allow-Origin", headerPropertyReader.readProperty("Access-Control-Allow-Origin")).build();
                    break;
                case "E1326":
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1326");
                    errorMessage.setErrorMessage("insufficient balance");
                    errorMessage.setDescription("account balance is not sufficient to perform the transaction.");
                    response = Response.status(Response.Status.OK).entity(errorMessage).
                            header("Access-Control-Allow-Origin", headerPropertyReader.readProperty("Access-Control-Allow-Origin")).build();
                    break;
                default:
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1601");
                    errorMessage.setErrorMessage("unexpected error");
                    errorMessage.setDescription("system experienced an unexpected error.");
                    response = Response.status(Response.Status.OK).entity(errorMessage)
                            .header("Access-Control-Allow-Origin", headerPropertyReader.readProperty("Access-Control-Allow-Origin")).build();
            }
        } catch (MalformedURLException e) {
            log.error("Error processing json response ", e);
            throw new WebApplicationException(500);
        } catch (SdpException e) {
            log.error("Error processing json response ", e);
            throw new WebApplicationException(500);
        }
        return response;
    }

    // Method to send a sms notification upon a successful payment
    private MtSmsResp sendPaymentSmsNotification(String subscriberId, String amount, String orderId, String applicationId, String password, URL url) throws MalformedURLException, SdpException {
        MtSmsReq mtSmsReq = new MtSmsReq();
        subscriberId = "tel:" + subscriberId;
        mtSmsReq.setDestinationAddresses(Arrays.asList(subscriberId));
        mtSmsReq.setMessage("Thank you for the payment of Rs." + amount + ". Order id: " + orderId+".");
        mtSmsReq.setApplicationId(applicationId);
        mtSmsReq.setPassword(password);
        SmsRequestSender requestSender = new SmsRequestSender(url);
        MtSmsResp smsResp = requestSender.sendSmsRequest(mtSmsReq);
        return smsResp;
    }

    // Generate a new coupon code
    private String generateCouponCode(String combinations, int length) {
        // Possible characters in the coupon code
        String stringCombinations = combinations;
        // Get possible characters to a char array
        char[] charCombinations = stringCombinations.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        // 20: Length of the coupon code
        for (int i = 0; i < length; i++) {
            char randomChar = charCombinations[random.nextInt(charCombinations.length)];
            stringBuilder.append(randomChar);
        }
        String couponCode = stringBuilder.toString();
        return couponCode;
    }
}
