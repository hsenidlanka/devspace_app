package hsl.devspace.app.coreserver.resources;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.caas.ChargingRequestSender;
import hms.kite.samples.api.caas.messages.ChargingRequestResponse;
import hms.kite.samples.api.caas.messages.DirectDebitRequest;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kasun Dinesh on 11/16/16.
 */

@Path("/payment")
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    @POST
    @Path("/pay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response purchase(JSONObject jsonObject, @javax.ws.rs.core.Context UriInfo uriInfo) {
        log.info("Start reading json response");
        SuccessMessage successMessage = new SuccessMessage();
        Response response;
        String url = uriInfo.getAbsolutePath().toString();

        String subscriberId = jsonObject.get("subscriberId").toString();
        String amount = jsonObject.get("amount").toString();
        String applicationId = jsonObject.get("applicationId").toString();
        String password = jsonObject.get("password").toString();
        String externalTrxId = jsonObject.get("externalTrxId").toString();
//        String paymentInstrumentName=jsonObject.get("paymentInstrumentName").toString();

        DirectDebitRequest directDebitRequest = new DirectDebitRequest();
        directDebitRequest.setApplicationId(applicationId);
        directDebitRequest.setPassword(password);
        directDebitRequest.setSubscriberId(subscriberId);
        directDebitRequest.setAmount(amount);
        directDebitRequest.setExternalTrxId(externalTrxId);
//        directDebitRequest.setPaymentInstrumentName(paymentInstrumentName);
        try {
            ChargingRequestSender chargingRequestSender = new ChargingRequestSender(
                    new URL("http://localhost:7000/caas/direct/debit"));
            ChargingRequestResponse chargingRequestResponse = chargingRequestSender
                    .sendChargingRequest(directDebitRequest);

            String paymentStatusCode = chargingRequestResponse.getStatusCode();

            switch (paymentStatusCode) {
                case "S1000":
                    /*MtSmsReq mtSmsReq = new MtSmsReq();
                mtSmsReq.setDestinationAddresses(Arrays.asList(subscriberId));
                mtSmsReq.setMessage("Thank you for the payment of Rs." + amount);
                mtSmsReq.setApplicationId(applicationId);
                mtSmsReq.setPassword(password);
                SmsRequestSender requestSender = new SmsRequestSender(new URL("http://localhost:7000/sms/send"));
                MtSmsResp smsResp = requestSender.sendSmsRequest(mtSmsReq);*/
                    successMessage.setStatus("success");
                    successMessage.setMessage("payment succeeded");
                    successMessage.setCode(Response.Status.OK.getStatusCode());
                    successMessage.addLink(url, "self");
                    JSONObject dataObj = new JSONObject();
                    dataObj.put("subscriberId", subscriberId);
                    dataObj.put("paymentAmount", amount);
                    dataObj.put("internalTrxId", chargingRequestResponse.getInternalTrxId());
                    dataObj.put("timeStamp", chargingRequestResponse.getTimeStamp());
                    successMessage.addData(dataObj);
                    break;
                case "E1308":
                    successMessage.setMessage("no enough credit");
                    break;
                default:
                    successMessage.setMessage("default case");
            }
        } catch (MalformedURLException e) {
            successMessage.setMessage(e.getMessage());
            log.error("Error reading json response ", e);
        } catch (SdpException e) {
            successMessage.setMessage(e.getMessage());
            log.error("Error reading json response ", e);
        }
        response = Response.status(Response.Status.OK).entity(successMessage).build();
        return response;
    }
}
