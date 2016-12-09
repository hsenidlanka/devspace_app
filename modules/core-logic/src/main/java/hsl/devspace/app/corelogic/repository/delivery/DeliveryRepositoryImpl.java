package hsl.devspace.app.corelogic.repository.delivery;

import hsl.devspace.app.corelogic.domain.Delivery;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by hsenid on 9/19/16.
 */
public class DeliveryRepositoryImpl implements DeliveryRepository {
    Delivery del=new Delivery();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
   // private static org.apache.log4j.Logger log = Logger.getLogger(DeliveryRepositoryImpl.class);
   org.slf4j.Logger log = LoggerFactory.getLogger(DeliveryRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new delivery*/
    @Override
    public int add(Delivery delivery) {
        int row;
        String sql = "INSERT INTO delivery " +
                "(agent_name,recepient_name,recepient_address,delivery_date,delivery_time,delivery_status,description,staff_id,delivery_method_id) VALUES (?,?,?,?,?,?,?,(SELECT id FROM staff WHERE name=?),(SELECT id FROM delivery_method WHERE name=?))";

        row = jdbcTemplate.update(sql, new Object[]{delivery.getAgentName(), delivery.getRecepientName(), delivery.getRecepientAddress(),
                delivery.getDeliveryDate(), delivery.getDeliveryTime(), delivery.getDeliveryStatus(), delivery.getDescription(), delivery.getAgentName(), delivery.getDeliveryMethod()});
        log.info("{} new delivery inserted",row);
        return row;
    }

    /*change delivery status*/
    @Override
    public int changeStatus(int id) {

        String sql = "UPDATE delivery SET delivery_status='Delivered' WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{id});
        log.info("{} delivered",row);
        return row;
    }

    /*Add new delivery method*/
    @Override
    public int addDeliveryMethod(String deliveryMethod) {
        int row;
        String sql = "INSERT INTO delivery_method " +
                "(name) VALUES (?)";

        row = jdbcTemplate.update(sql, new Object[]{deliveryMethod});
        log.info("{} new delivery method inserted",row);
        return row;
    }

    /*remove delivery method*/
    @Override
    public int deleteDeliveryMethod(String deliveryMethod) {
        int row;

        String sql = "DELETE FROM delivery_method WHERE name=?";
        row = jdbcTemplate.update(sql, new Object[]{deliveryMethod});
        log.info("{} delivery method deleted",row);
        return row;
    }
}
