package hsl.devspace.app.corelogic.repository.coupon;

import hsl.devspace.app.corelogic.domain.Coupon;

import java.util.List;

/**
 * Created by hsenid on 9/19/16.
 */
public interface CouponRepository {

     int add(String couponCode,String customerMobile);
    int expire();
    List<Coupon> selectActiveCoupons();
    int countActiveCoupons();
    boolean ifExpired(String couponCode);

    Coupon validateCoupon(String couponCode);

    int changeStatusToUsed(String couponCode);
}
