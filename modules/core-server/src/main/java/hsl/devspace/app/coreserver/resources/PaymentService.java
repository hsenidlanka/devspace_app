package hsl.devspace.app.coreserver.resources;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsResp;
import hsl.devspace.app.corelogic.domain.Delivery;
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

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

/**
 * Created by Kasun Dinesh on 11/16/16.
 */

@Path("/payment")
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    ApplicationContext context = Context.appContext;
    ShoppingCartRepositoryImpl shoppingCartRepository = (ShoppingCartRepositoryImpl) context.getBean("shoppingCartRepoImpl");
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
    public int purchase(JSONObject jsonObject, @javax.ws.rs.core.Context UriInfo uriInfo) {
        SuccessMessage successMessage = new SuccessMessage();
        ErrorMessage errorMessage = new ErrorMessage();
        Response response;
        try {
            String url = uriInfo.getAbsolutePath().toString();
            String username = jsonObject.get("username").toString();
            String subscriberId = jsonObject.get("subscriberId").toString();
            String amount = jsonObject.get("amount").toString();
            String paymentMethod = jsonObject.get("paymentMethod").toString();
            String couponCode = jsonObject.get("couponCode").toString();

//            log.info("username: {}", username);
//            log.info("subscriberId: {}", subscriberId);
//            log.info("amount: {}", amount);
//            log.info("paymentMethod: {}", paymentMethod);
//            log.info("couponCode: {}", couponCode);

            List<Map<String, Object>> itemsListMap = (List<Map<String, Object>>) jsonObject.get("items");
            Map<String, String> deliveryDataMap = (Map<String, String>) jsonObject.get("delivery");

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            log.info("DATE: {}",deliveryDataMap.get("date"));
            LocalDate date = LocalDate.parse(deliveryDataMap.get("date").replaceAll(" ", ""), formatter);
            formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(deliveryDataMap.get("time"), formatter);
//            log.info("TIME: {}",deliveryDataMap.get("time"));

            Delivery delivery = new Delivery();
            delivery.setRecepientName(deliveryDataMap.get("recepientName"));
            delivery.setRecepientAddress(deliveryDataMap.get("recepientAddress"));
            delivery.setDeliveryDate(date);
            delivery.setDeliveryTime(time);
            delivery.setDescription(deliveryDataMap.get("description"));
            delivery.setDeliveryMethod(deliveryDataMap.get("method"));


//        log.info("itemsList: {}", itemsListMap);
//        log.info("deliveryData: {}", deliveryDataMap);
log.info("start to flush");
            shoppingCartRepository.generateCartProcess(Double.parseDouble(amount), username, itemsListMap, delivery, paymentMethod);
            log.info("end flush");
        } catch (Exception e) {
            log.error("EXCEPTION PAY: {}", e);
        }
        return 200;
        /*String applicationId = propertyReader.readProperty("service.application.id");
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

            switch (paymentStatusCode) {
                case "S1000":
                    successMessage.setStatus("success");
                    successMessage.setCode(Response.Status.OK.getStatusCode());
                    successMessage.addLink(url, "self");
                    JSONObject dataObj = new JSONObject();
                    dataObj.put("subscriberId", subscriberId);
                    dataObj.put("paymentAmount", amount);
                    dataObj.put("internalTrxId", chargingRequestResponse.getInternalTrxId());
                    dataObj.put("timeStamp", chargingRequestResponse.getTimeStamp());
                    successMessage.addData(dataObj);
                    URL smsUrl = new URL(DEVSPACE_BASE_URL + propertyReader.readProperty("sms.send.url"));
                    MtSmsResp smsResponse = sendPaymentSmsNotification(subscriberId, amount, applicationId, password, smsUrl);
                    successMessage.setMessage("payment succeeded");
                    if (!String.valueOf(smsResponse.getStatusCode()).equals("S1000")) {
                        successMessage.setMessage("payment succeeded but failed to send the sms notification");
                    }
                    log.info("sms status: {}", smsResponse.getStatusCode());
                    response = Response.status(Response.Status.OK).entity(successMessage).build();
                    break;
                case "E1308":
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1308");
                    errorMessage.setErrorMessage("no enough credit");
                    errorMessage.setDescription("there is not enough balance in the user's account.");
                    response = Response.status(Response.Status.OK).entity(errorMessage).build();
                    break;
                case "E1313":
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1313");
                    errorMessage.setErrorMessage("authentication failed");
                    errorMessage.setDescription("no such active application with given applicationId" +
                            " or no active service provider or the given password in the request is invalid.");
                    response = Response.status(Response.Status.OK).entity(errorMessage).build();
                    break;
                case "E1326":
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1326");
                    errorMessage.setErrorMessage("insufficient balance");
                    errorMessage.setDescription("account balance is not sufficient to perform the transaction.");
                    response = Response.status(Response.Status.OK).entity(errorMessage).build();
                    break;
                default:
                    errorMessage.setStatus("error");
                    errorMessage.setErrorCode("E1601");
                    errorMessage.setErrorMessage("unexpected error");
                    errorMessage.setDescription("system experienced an unexpected error.");
                    response = Response.status(Response.Status.OK).entity(errorMessage).build();
            }
        } catch (MalformedURLException e) {
            log.error("Error processing json response ", e);
            throw new WebApplicationException(500);
        } catch (SdpException e) {
            log.error("Error processing json response ", e);
            throw new WebApplicationException(500);
        }
        return response;*/
    }

    // Method to send a sms notification upon a successful payment
    private MtSmsResp sendPaymentSmsNotification(String subscriberId, String amount, String applicationId, String password, URL url) throws MalformedURLException, SdpException {
        MtSmsReq mtSmsReq = new MtSmsReq();
        subscriberId = "tel:" + subscriberId;
        mtSmsReq.setDestinationAddresses(Arrays.asList(subscriberId));
        mtSmsReq.setMessage("Thank you for the payment of Rs." + amount);
        mtSmsReq.setApplicationId(applicationId);
        mtSmsReq.setPassword(password);
        SmsRequestSender requestSender = new SmsRequestSender(url);
        MtSmsResp smsResp = requestSender.sendSmsRequest(mtSmsReq);
        return smsResp;
    }
}
