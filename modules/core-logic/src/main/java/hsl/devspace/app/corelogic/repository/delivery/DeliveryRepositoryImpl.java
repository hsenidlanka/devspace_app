package hsl.devspace.app.corelogic.repository.delivery;

import hsl.devspace.app.corelogic.domain.Delivery;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

/**
 * Created by hsenid on 9/19/16.
 */
public class DeliveryRepositoryImpl implements DeliveryRepository {
    Delivery del=new Delivery();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(DeliveryRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    @Override
    public int add(Delivery delivery) {
        int row;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "INSERT INTO delivery " +
                "(agent_name,recepient_name,recepient_address,delivery_date,delivery_time,delivery_status,description,staff_id,delivery_method_id) VALUES (?,?,?,?,?,?,?,(SELECT id FROM staff WHERE name=?),(SELECT id FROM delivery_method WHERE name=?))";

        row = jdbcTemplate.update(sql, new Object[]{del.getAgentName(), del.getRecepientName(), del.getRecepientAddress(),
                del.getDeliveryDate(), del.getDeliveryTime(), del.getDeliveryStatus(), del.getDescription(), del.getAgentName(), del.getDeliveryMethod()});
        transactionManager.commit(stat);
        log.info(row + "new delivery inserted");
        return row;
    }

    @Override
    public int changeStatus(int id) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "UPDATE delivery SET delivery_status='Delivered' WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{id});
        transactionManager.commit(stat);
        log.info(row + "delivered");
        return row;
    }

    @Override
    public int addDeliveryMethod(String deliveryMethod) {
        int row;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "INSERT INTO delivery_method " +
                "(name) VALUES (?)";

        row = jdbcTemplate.update(sql, new Object[]{deliveryMethod});
        transactionManager.commit(stat);
        log.info(row + "new delivery method inserted");
        return row;
    }

    @Override
    public int deleteDeliveryMethod(String deliveryMethod) {
        int row;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "DELETE FROM delivery_method WHERE name=?";

        row = jdbcTemplate.update(sql, new Object[]{deliveryMethod});
        transactionManager.commit(stat);
        log.info(row + " delivery method deleted");
        return row;
    }
}
