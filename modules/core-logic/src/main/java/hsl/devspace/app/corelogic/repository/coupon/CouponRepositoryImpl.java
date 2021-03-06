package hsl.devspace.app.corelogic.repository.coupon;

import hsl.devspace.app.corelogic.domain.Coupon;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public class CouponRepositoryImpl implements CouponRepository {
    Coupon coupon=new Coupon();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
   // private static org.apache.log4j.Logger log = Logger.getLogger(CouponRepositoryImpl.class);
   org.slf4j.Logger log = LoggerFactory.getLogger(CouponRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new coupon*/
    @Override
    public int add(String couponCode,String customerMobile){
        int row;
        boolean unique = checkUniqueCoupon(couponCode);
        if (unique) {
            String sql = "INSERT INTO coupon" +
                    "(coupon_code,rate,expire_date,status,customer_mobile) VALUES (?,10,DATE_ADD(CURRENT_DATE,INTERVAL 30 DAY),1,?)";
            row = jdbcTemplate.update(sql, new Object[]{couponCode, customerMobile});
            log.info("{} new coupon inserted", row);
        } else {
            log.info("{} coupon code already available");
            row = 2;
        }
        return row;
    }

    /*invalidate all the coupons that are expired by today*/
    @Override
    public int expire() {
        int row;
        String sql="UPDATE coupon SET status=3 WHERE expire_date <=CURRENT_DATE";
        row=jdbcTemplate.update(sql);
        log.info("expired");
        return row;
    }

    /*change status when used*/
    @Override
    public int changeStatusToUsed(String couponCode) {
        int row;
        String sql = "UPDATE coupon SET status=2 WHERE coupon_code=?";
        row = jdbcTemplate.update(sql, new Object[]{couponCode});
        log.info("change status to used");
        return row;
    }

    /*view all the details of active(valid) coupons*/
    @Override
    public  List<Coupon> selectActiveCoupons() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM coupon WHERE status = 1");
        List<Coupon> couponsActive=new ArrayList<Coupon>();
        for (int i=0;i<mp.size();i++){
            Coupon coupon=new Coupon();
            coupon.setCouponId(Integer.parseInt(mp.get(i).get("id").toString()));
            coupon.setCouponCode(mp.get(i).get("coupon_code").toString());
            coupon.setRate(Double.parseDouble(mp.get(i).get("rate").toString()));
            coupon.setExpireDate(Date.valueOf(mp.get(i).get("expire_date").toString()));
            coupon.setStatus(mp.get(i).get("status").toString());
            coupon.setCustomerMobile(mp.get(i).get("customer_mobile").toString());
            couponsActive.add(coupon);
        }
        log.info("{}",couponsActive);
        return couponsActive;
    }

    /*retrieve no.of coupons that are in 'active' status*/
    @Override
    public int countActiveCoupons() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM coupon WHERE status=1");
        int count = mp.size();
        log.info("{}",count);
        return count;
    }

    /*check if a coupon is expired*/
    @Override
    public boolean ifExpired(String couponCode) {
        boolean ifExpired=false;

        return false;
    }

    /*validate coupon whether it belongs to the correct user and active*/
    @Override
    public Coupon validateCoupon(String couponCode) {
        Coupon coupon1 = new Coupon();
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM coupon WHERE BINARY coupon_code= ? AND status='active'", couponCode);
       log.info("{}",mp);

        if (mp.size() != 0) {
            coupon1.setCouponCode(mp.get(0).get("coupon_code").toString());
            coupon1.setRate(Double.parseDouble(mp.get(0).get("rate").toString()));
            coupon1.setExpireDate(Date.valueOf(mp.get(0).get("expire_date").toString()));
            coupon1.setCustomerMobile(mp.get(0).get("customer_mobile").toString());
            log.info("coupon is valid");
        }
        else{
            log.info("coupon not valid ");
        }
        log.info("{}", coupon1);
        return coupon1;
    }

    public boolean checkUniqueCoupon(String couponCode) {
        boolean result = true;
        String sql = "SELECT count(*) FROM coupon WHERE coupon_code = ? ";

        int count = jdbcTemplate.queryForObject(
                sql, new Object[]{couponCode}, Integer.class);
        if (count > 0) {
            result = false;
            log.info("coupon already available");
        }
        log.info("{}", result);
        return result;


    }


}
