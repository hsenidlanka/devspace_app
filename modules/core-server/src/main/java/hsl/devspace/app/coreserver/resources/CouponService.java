package hsl.devspace.app.coreserver.resources;

import hsl.devspace.app.corelogic.domain.Coupon;
import hsl.devspace.app.corelogic.repository.coupon.CouponRepositoryImpl;
import hsl.devspace.app.coreserver.common.Context;
import hsl.devspace.app.coreserver.model.SuccessMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Kasun Dinesh on 9/22/16.
 * * This class handles the requests related to coupons.
 */
@Path("/coupons")
public class CouponService {
    private static final Logger log = LogManager.getLogger(CustomerService.class);
    ApplicationContext context = Context.appContext;
    CouponRepositoryImpl couponRepository = (CouponRepositoryImpl) context.getBean("couponRepoImpl");
    final String BASE_URL = "http://localhost:2222/pizza-shefu/api/v1.0/";

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewCoupon(Coupon coupon, @javax.ws.rs.core.Context UriInfo uriInfo) {
        int status = couponRepository.add(coupon.getCouponCode(), coupon.getCustomerMobile());
        Response response;
//        Genson gensonBuilder = new GensonBuilder().setSkipNull(true).create();
        if (status > 0) {
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(Response.Status.CREATED.getStatusCode());
            successMessage.setMessage("coupon added");
            successMessage.addData(coupon);
            String url = uriInfo.getAbsolutePath().toString();
            successMessage.addLink(url, "self");
            response = Response.status(Response.Status.CREATED).entity(successMessage).build();
        } else {
            throw new WebApplicationException(400);
        }
        return response;
    }
}
