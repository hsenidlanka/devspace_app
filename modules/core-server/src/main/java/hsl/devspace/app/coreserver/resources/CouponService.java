package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.Coupon;
import hsl.devspace.app.corelogic.repository.coupon.CouponRepositoryImpl;
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

/**
 * Created by Kasun Dinesh on 9/22/16.
 * This class handles the requests related to the coupons.
 */
@Path("/coupons")
public class CouponService {
    private static final Logger log = LoggerFactory.getLogger(CouponService.class);
    ApplicationContext context = Context.appContext;
    CouponRepositoryImpl couponRepository = (CouponRepositoryImpl) context.getBean("couponRepoImpl");
    private ServerModel serverModel = (ServerModel) context.getBean("serverModel");
    final String BASE_URL = serverModel.getBaseUrl();

    // Adding a new coupon to the system
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewCoupon(Coupon coupon, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int status = couponRepository.add(coupon.getCouponCode(), coupon.getCustomerMobile());
        Response response;
        if (status > 0) {
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.CREATED.getStatusCode());
            successMessage.setMessage("coupon added");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("couponCode", coupon.getCouponCode());
            jsonObject.put("customerMobile", coupon.getCustomerMobile());
            successMessage.addData(jsonObject);

            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");

            response = Response.status(Response.Status.CREATED).entity(successMessage).build();
        } else {
            throw new WebApplicationException(400);
        }
        return response;
    }

    // Adding a new coupon to the system
    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateCoupon(JSONObject jsonObjectRequest, @javax.ws.rs.core.Context UriInfo uriInfo) {
        Response response;
        try {
            Coupon coupon = couponRepository.validateCoupon(jsonObjectRequest.get("couponCode").toString());
            if (coupon.getCouponCode() != null) {
                SuccessMessage successMessage = new SuccessMessage();
                successMessage.setStatus("success");
                successMessage.setCode(Response.Status.OK.getStatusCode());
                successMessage.setMessage("coupon validated");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("couponCode", coupon.getCouponCode());
                jsonObject.put("rate", coupon.getRate());
                jsonObject.put("expireDate", coupon.getExpireDate());
                jsonObject.put("customerMobile", coupon.getCustomerMobile());
                successMessage.addData(jsonObject);

                String url = uriInfo.getAbsolutePath().toString();
                successMessage.addLink(url, "self");

                response = Response.status(Response.Status.CREATED).entity(successMessage).build();
            } else {
                throw new WebApplicationException(401);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WebApplicationException(401);
        }
        return response;
    }
}