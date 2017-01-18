package hsl.devspace.app.corelogic.repository.delivery;

import hsl.devspace.app.corelogic.domain.Delivery;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public class DeliveryRepositoryImpl implements DeliveryRepository {
    Delivery del=new Delivery();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
   // private static org.apache.log4j.Logger log = Logger.getLogger(DeliveryRepositoryImpl.class);
//   org.slf4j.Logger log = LoggerFactory.getLogger(DeliveryRepositoryImpl.class);
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

    @Override
    public List<Map<String, Object>> selectDeliveryDetails(int limit, int offset) {
        log.info("inside delivery data table load method");
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT shopping_cart.order_id,delivery.agent_name,delivery.recepient_name,delivery.recepient_address,delivery.delivery_date,delivery.delivery_time,delivery.delivery_status FROM delivery INNER JOIN shopping_cart ON shopping_cart.id=delivery.cart_id WHERE delivery.delivery_method_id=2 LIMIT ? OFFSET ? ", limit, offset);
        log.info("delivery details {}",mp);
        return mp;
    }

    @Override
    public int countDeliveryDetails() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT shopping_cart.order_id,delivery.agent_name,delivery.recepient_name,delivery.recepient_address,delivery.delivery_date,delivery.delivery_time,delivery.delivery_status FROM delivery INNER JOIN shopping_cart ON shopping_cart.id=delivery.cart_id WHERE delivery.delivery_method_id=2 ");
        return mp.size();
    }

    @Override
    public List<String> selectDeliveryAgents(String branch) {
        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT agent_name FROM delivery WHERE delivery_status<>'processing' AND agent_name IS NOT NULL ");
        List<String> agents = new ArrayList<String>();
        for (int j = 0; j < mp1.size(); j++) {
            List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT username FROM staff WHERE branch=? AND status='active' AND username=?", branch, mp1.get(j).get("username"));
            for (int i = 0; i < mp.size(); i++) {
                agents.add(mp.get(0).get("username").toString());
            }
        }

        log.info("{}", agents);
        return agents;
    }



   /* public List<String> selectDeliveryAgentsTest(String branch) {
        List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT agent_name FROM delivery WHERE delivery_status<>'processing' AND agent_name IS NOT NULL ");
        List<String> agents = new ArrayList<String>();
        for (int j=0;j<mp1.size();j++){
            List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT username FROM staff WHERE branch=? AND status='active' AND username=?", branch,mp1.get(j).get("username"));
            for (int i = 0; i < mp.size(); i++) {
                agents.add(mp.get(0).get("username").toString());
            }
        }

        log.info("{}", agents);
        return agents;
    }

    /*List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT username FROM staff WHERE designation='Agent' AND branch=? AND status='active'", branch);
    List<String> agents = new ArrayList<String>();
    for (int i = 0; i < mp.size(); i++) {
        agents.add(mp.get(i).get("username").toString());
    }
    log.info("{}", agents);
    return agents;*/
}
