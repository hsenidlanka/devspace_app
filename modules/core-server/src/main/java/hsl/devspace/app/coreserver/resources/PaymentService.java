package hsl.devspace.app.coreserver.resources;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.caas.ChargingRequestSender;
import hms.kite.samples.api.caas.messages.ChargingRequestResponse;
import hms.kite.samples.api.caas.messages.DirectDebitRequest;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsResp;
import hsl.devspace.app.coreserver.common.PropertyReader;
import hsl.devspace.app.coreserver.model.ErrorMessage;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by Kasun Dinesh on 11/16/16.
 */

@Path("/payment")
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    PropertyReader propertyReader = new PropertyReader("devspace.properties");
    final String BASE_URL = propertyReader.readProperty("service.base.url");

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
        String subscriberId = jsonObject.get("subscriberId").toString();
        String amount = jsonObject.get("amount").toString();
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
                    new URL(BASE_URL + propertyReader.readProperty("caas.direct.debit.url")));
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
                    URL smsUrl = new URL(BASE_URL + propertyReader.readProperty("sms.send.url"));
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
        return response;
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
