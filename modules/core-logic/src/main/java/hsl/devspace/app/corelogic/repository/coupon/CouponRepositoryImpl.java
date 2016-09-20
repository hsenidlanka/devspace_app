package hsl.devspace.app.corelogic.repository.coupon;

import hsl.devspace.app.corelogic.domain.Coupon;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public class CouponRepositoryImpl implements CouponRepository {
    Coupon coupon=new Coupon();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(CouponRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }
    @Override
    public int add(String couponCode,String customerMobile){

        int row;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "INSERT INTO coupon" +
                "(coupon_code,rate,expire_date,status,customer_mobile) VALUES (?,10,DATE_ADD(CURRENT_DATE,INTERVAL 30 DAY),1,?)";
        row = jdbcTemplate.update(sql, new Object[]{couponCode, customerMobile});
        transactionManager.commit(stat);
        log.info(row + "new coupon inserted");
        return row;
    }

    @Override
    public int expire() {
        int row;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql="UPDATE coupon SET status=3 WHERE expire_date <=CURRENT_DATE";
        row=jdbcTemplate.update(sql);
        transactionManager.commit(stat);
        log.info("expired");
        return row;
    }

    @Override
    public  List<Map<String, Object>> viewActiveCoupons() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM coupon WHERE status = 1");
        log.info(mp);
        return mp;
    }

    @Override
    public int countActiveCoupons() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM coupon WHERE status=1");
        int count = mp.size();
        log.info(count);
        return count;
    }


}
