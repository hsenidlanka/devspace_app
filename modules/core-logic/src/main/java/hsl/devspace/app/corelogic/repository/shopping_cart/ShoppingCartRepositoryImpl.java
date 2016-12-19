package hsl.devspace.app.corelogic.repository.shopping_cart;

import hsl.devspace.app.corelogic.domain.Delivery;
import hsl.devspace.app.corelogic.domain.Item;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/23/16.
 */
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    // private static org.apache.log4j.Logger log = Logger.getLogger(ShoppingCartRepositoryImpl.class);
    org.slf4j.Logger log = LoggerFactory.getLogger(ShoppingCartRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new cart*/
    @Override
    public int addCart(double netCost, String username) {
        List<Map<String, Object>> usernameCustomer = jdbcTemplate.queryForList("SELECT id FROM customer WHERE username=?", username);
        if (usernameCustomer.size() != 0) {
            String sql = "INSERT INTO shopping_cart " +
                    "(net_cost,customer_id,guest_id) VALUES (?,?,NULL)";
            jdbcTemplate.update(sql, new Object[]{netCost, usernameCustomer.get(0).get("id")});
            log.info("{} new shopping cart added");


        } else {
            List<Map<String, Object>> usernameGuest = jdbcTemplate.queryForList("SELECT id FROM guest WHERE mobile=?", username);
            if (usernameGuest.size() != 0) {
                String sql = "INSERT INTO shopping_cart " +
                        "(net_cost,customer_id,guest_id) VALUES (?,NULL ,?)";
                jdbcTemplate.update(sql, new Object[]{netCost, usernameGuest.get(0).get("id")});
                log.info("{} new shopping cart added");

            } else {
                String sql = "INSERT INTO guest " +
                        "(mobile) VALUES (?)";
                jdbcTemplate.update(sql, new Object[]{username});
                List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM guest");
                String sql2 = "INSERT INTO shopping_cart (net_cost,customer_id,guest_id) VALUES (?,NULL,?)";
                jdbcTemplate.update(sql2, new Object[]{netCost, mp4.get(0).get("MAX(id)")});
                log.info("{} new shopping cart added");

            }
        }

        List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM shopping_cart");
        int id = Integer.parseInt(mp4.get(0).get("MAX(id)").toString());
        List<Map<String, Object>> mp5 = jdbcTemplate.queryForList("SELECT * FROM shopping_cart WHERE id=?", id);

        log.info("{}", id);
        log.info("mp5 {}", mp5);

        return id;
    }

    /*Add products in the cart to table*/
    @Override
    public List<Integer> addProductsToCart(List<Map<String, Object>> items) {
        List<Integer> productList = new ArrayList<Integer>();
        int id;
        String type;
        int toppingId1;
        int toppingId2;
        for (int i = 0; i < items.size(); i++) {
            String productName = items.get(i).get("itemName").toString();
            int quantity = Integer.parseInt(items.get(i).get("quantity").toString());
            String size = items.get(i).get("size").toString();

            List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM item WHERE name=?", productName);
            if (mp.size() != 0) {
                id = Integer.parseInt(mp.get(0).get("id").toString());
                type = "item";
            } else {
                List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT id FROM package WHERE name=?", productName);
                id = Integer.parseInt(mp1.get(0).get("id").toString());
                type = "package";
            }

            List<Map<String, Object>> top1 = jdbcTemplate.queryForList("SELECT id FROM item WHERE name=?", items.get(i).get("topping_name1").toString());
            List<Map<String, Object>> top2 = jdbcTemplate.queryForList("SELECT id FROM item WHERE name=?", items.get(i).get("topping_name2").toString());

            if (top1.size() != 0 && top2.size() != 0) {
                toppingId1 = Integer.parseInt(top1.get(0).get("id").toString());
                toppingId2 = Integer.parseInt(top2.get(0).get("id").toString());
                String sql = "INSERT INTO product " +
                        "(type,type_id,size,quantity,topping_id1,topping_id2) VALUES (?,?,?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity, toppingId1, toppingId2});
            } else if (top1.size() != 0) {
                toppingId1 = Integer.parseInt(top1.get(0).get("id").toString());
                String sql = "INSERT INTO product " +
                        "(type,type_id,size,quantity,topping_id1) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity, toppingId1});
            } else if (top2.size() != 0) {
                toppingId2 = Integer.parseInt(top2.get(0).get("id").toString());
                String sql = "INSERT INTO product " +
                        "(type,type_id,size,quantity,topping_id2) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity, toppingId2});
            } else {
                String sql = "INSERT INTO product " +
                        "(type,type_id,size,quantity) VALUES (?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity});
            }

            List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM product");
            id = Integer.parseInt(mp4.get(0).get("MAX(id)").toString());
            productList.add(id);
        }
        return productList;

    }

    /*update cart and the products contain*/
    @Override
    public void updateCartProductTable(int cartId, List<Integer> productIdList) {
        for (int i = 0; i < productIdList.size(); i++) {
            int productId = productIdList.get(i);
            String sql = "INSERT INTO shopping_cart_product " +
                    "(shopping_cart_id,product_id) VALUES (?,?)";
            jdbcTemplate.update(sql, new Object[]{cartId, productId});
        }

    }

    /*Add shopping cart to payment process*/
    @Override
    public int add(String paymentMethodName, int cartId, int deliveryId) {
        int row;
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM shopping_cart WHERE id=?", cartId);
        if (mp.get(0).get("customer_id") != null) {
            String sql = "INSERT INTO payment_transaction " +
                    "(date,time,amount,payment_status,order_type,customer_id,payment_method_id,delivery_id)" +
                    " VALUES (CURRENT_DATE,CURRENT_TIME ,(SELECT net_cost FROM shopping_cart WHERE id=?),?,?,?,(SELECT id FROM payment_method WHERE name=?),?)";

            row = jdbcTemplate.update(sql, new Object[]{cartId, "pending", "online", mp.get(0).get("customer_id").toString(), paymentMethodName, deliveryId});
            log.info("{} payment inserted", row);
        } else {
            String sql = "INSERT INTO payment_transaction " +
                    "(date,time,amount,payment_status,order_type,payment_method_id,delivery_id,guest_id)" +
                    " VALUES (CURRENT_DATE,CURRENT_TIME ,(SELECT net_cost FROM shopping_cart WHERE id=?),?,?,(SELECT id FROM payment_method WHERE name=?),?,?)";

            row = jdbcTemplate.update(sql, new Object[]{cartId, "pending", "online", paymentMethodName, deliveryId, mp.get(0).get("guest_id").toString()});
            log.info("{} payment inserted", row);
        }
        List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM payment_transaction");
        int id = Integer.parseInt(mp4.get(0).get("MAX(id)").toString());
        List<Map<String, Object>> mp5 = jdbcTemplate.queryForList("SELECT * FROM payment_transaction WHERE id=?", id);
        log.info("pay{}", mp5);
        return row;
    }

    /*Add shopping cart to delivery process*/
    @Override
    public int add(Delivery del) {
        int deliveryId = 0;
        int row;
        if (del.getDeliveryMethod() == "pickup") {
            String sql = "INSERT INTO delivery " +
                    "(delivery_date,delivery_time,delivery_status,delivery_method_id) VALUES (?,?,?,(SELECT id FROM delivery_method WHERE name=?))";

            row = jdbcTemplate.update(sql, new Object[]{del.getDeliveryDate(), del.getDeliveryTime(), "pending", del.getDeliveryMethod()});
            log.info("{} new pickup inserted", row);
            List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM delivery");
            deliveryId = Integer.parseInt(mp4.get(0).get("MAX(id)").toString());

        } else {

            String recName = del.getRecepientName();
            String recAddress = del.getRecepientAddress();
            if (recName == null || recAddress == null) {
                log.info("All required fields are not filled");
            } else {
                String sql = "INSERT INTO delivery " +
                        "(recepient_name,recepient_address,delivery_date,delivery_time,delivery_status,description,delivery_method_id) VALUES (?,?,?,?,?,?,(SELECT id FROM delivery_method WHERE name=?))";
                row = jdbcTemplate.update(sql, new Object[]{recName, recAddress,
                        del.getDeliveryDate(), del.getDeliveryTime(), "pending", del.getDescription(), del.getDeliveryMethod()});
                log.info("{} new delivery inserted", row);
                List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM delivery");
                deliveryId = Integer.parseInt(mp4.get(0).get("MAX(id)").toString());
            }

        }
        log.info("{}", deliveryId);
        return deliveryId;
    }

    /*complete process for shopping cart with payment and delivery*/
    @Transactional(propagation = Propagation.SUPPORTS)
    public void generateCartProcess(double netCost, String username, List<Map<String, Object>> items, Delivery del, String paymentMethodName) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            int cartId = addCart(netCost, username);
            List<Integer> productIdList = addProductsToCart(items);
            updateCartProductTable(cartId, productIdList);
            int deliveryId = add(del);
            add(paymentMethodName, cartId, deliveryId);
            transactionManager.commit(stat);

            log.info("updated");

        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
            log.info("rollbacked");
        }
    }


    /*delete a cart*/
    @Override
    public int delete(int id) {
        int row;
        String sql = "DELETE * FROM shopping_cart " +
                "WHERE id=?";
        row = jdbcTemplate.update(sql, new Object[]{id});
        log.info(row + " shopping cart removed");
        return row;
    }

    /*cart count for a specific customer*/
    @Override
    public int countPerCustomer(String customerUsername) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE customer_id=(SELECT id FROM customer WHERE username=?)", customerUsername);
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

    /*cart count for a specific guest*/
    @Override
    public int countPerGuest(String mobile) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE guest_id=(SELECT id FROM guest WHERE mobile=?)", mobile);
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

    /*retrieve no.of carts for the customers(registered)*/
    @Override
    public int countTotalCustomerCarts() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE customer_id>0 ");
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

    /*retrieve no.of carts for the guests*/
    @Override
    public int countTotalGuestCarts() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE guest_id>0 ");
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}", count);
        return count;
    }

  /*  @Override
    public void addItemToCart(Item item) {
        String sql="INSERT INTO shopping_cart VALUES (?,?,?)";
        jdbcTemplate.update(sql,new Object[]{Item.class.getDeclaredConstructors()});

    }*/

    @Override
    public void modifyItemInCart(Item item) {
     /*   String sql = "UPDATE product SET size=?,quantity=?,topping_id1=?,topping_id2=? WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{});
*/
    }

    @Override
    public int removeItemFromCart(int productId) {
        String sql = "DELETE FROM product WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{productId});
        log.info("{} deleted", row);
        return row;
    }

    @Override
    public int updateDelivery(String agentName, String staffUsername, int deliveryId) {
        String sql = "UPDATE delivery SET agent_name=?,delivery_status=?,staff_id=(SELECT id FROM staff WHERE username=?) WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{agentName, "processing", staffUsername, deliveryId});
        return row;
    }

    @Override
    public int updatePayment(int paymentId, String staffUsername) {
        String sql = "UPDATE payment_transaction staff_id=(SELECT id FROM staff WHERE username=?) WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{staffUsername, paymentId});
        return row;
    }


  /*  @Override
    public void addPackageToCart(int packageId) {}*/

    /*@Override
    public void modifyPackageInCart() {}*/

   /* @Override
    public void removePackageFromCart(int packageId) {}*/

}
