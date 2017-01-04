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
        String orderId;
        List<Map<String, Object>> max = jdbcTemplate.queryForList("SELECT MAX(id) FROM shopping_cart");
        if (max.get(0).get("MAX(id)") == null) {
            orderId = "PS00001";
        } else {
            int maxId = Integer.parseInt(max.get(0).get("MAX(id)").toString());
            List<Map<String, Object>> order = jdbcTemplate.queryForList("SELECT order_id FROM shopping_cart WHERE id=?", maxId);
            if (order.get(0).toString().equals("{order_id=}")) {
                orderId = "PS00001";
            } else {
                String idO = (order.get(0).get("order_id").toString());
                OrderIdGenerator ord = new OrderIdGenerator();
                orderId = ord.generateOrderId(idO);
            }
        }

        List<Map<String, Object>> usernameCustomer = jdbcTemplate.queryForList("SELECT id FROM customer WHERE username=?", username);
        if (usernameCustomer.size() != 0) {
            String sql = "INSERT INTO shopping_cart " +
                    "(order_id,order_date,order_time,net_cost,customer_id,guest_id) VALUES (?,CURRENT_DATE ,CURRENT_TIME,?,?,NULL)";
            jdbcTemplate.update(sql, new Object[]{orderId, netCost, usernameCustomer.get(0).get("id")});
            log.info("{} new shopping cart added");


        } else {
            List<Map<String, Object>> usernameGuest = jdbcTemplate.queryForList("SELECT id FROM guest WHERE mobile=?", username);
            if (usernameGuest.size() != 0) {
                String sql = "INSERT INTO shopping_cart " +
                        "(order_id,order_date,order_time,net_cost,customer_id,guest_id) VALUES (?,CURRENT_DATE ,CURRENT_TIME ,?,NULL ,?)";
                jdbcTemplate.update(sql, new Object[]{orderId, netCost, usernameGuest.get(0).get("id")});
                log.info("{} new shopping cart added");

            } else {
                String sql = "INSERT INTO guest " +
                        "(mobile) VALUES (?)";
                jdbcTemplate.update(sql, new Object[]{username});
                List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM guest");
                String sql2 = "INSERT INTO shopping_cart (order_id,order_date,order_time,net_cost,customer_id,guest_id) VALUES (?,CURRENT_DATE ,CURRENT_TIME ,?,NULL,?)";
                jdbcTemplate.update(sql2, new Object[]{orderId, netCost, mp4.get(0).get("MAX(id)")});
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
            String instructions = items.get(i).get("instructions").toString();

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
                        "(type,type_id,size,quantity,topping_id1,topping_id2,instructions) VALUES (?,?,?,?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity, toppingId1, toppingId2, instructions});
            } else if (top1.size() != 0) {
                toppingId1 = Integer.parseInt(top1.get(0).get("id").toString());
                String sql = "INSERT INTO product " +
                        "(type,type_id,size,quantity,topping_id1,instructions) VALUES (?,?,?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity, toppingId1, instructions});
            } else if (top2.size() != 0) {
                toppingId2 = Integer.parseInt(top2.get(0).get("id").toString());
                String sql = "INSERT INTO product " +
                        "(type,type_id,size,quantity,topping_id2,instructions) VALUES (?,?,?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity, toppingId2, instructions});
            } else {
                String sql = "INSERT INTO product " +
                        "(type,type_id,size,quantity,instructions) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(sql, new Object[]{type, id, size, quantity, instructions});
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
                    "(cart_id,date,time,amount,payment_status,order_type,customer_id,payment_method_id,delivery_id)" +
                    " VALUES (?,CURRENT_DATE,CURRENT_TIME ,(SELECT net_cost FROM shopping_cart WHERE id=?),?,?,?,(SELECT id FROM payment_method WHERE name=?),?)";

            row = jdbcTemplate.update(sql, new Object[]{cartId, cartId, "pending", "online", mp.get(0).get("customer_id").toString(), paymentMethodName, deliveryId});
            log.info("{} payment inserted", row);
        } else {
            String sql = "INSERT INTO payment_transaction " +
                    "(cart_id,date,time,amount,payment_status,order_type,payment_method_id,delivery_id,guest_id)" +
                    " VALUES (?,CURRENT_DATE,CURRENT_TIME ,(SELECT net_cost FROM shopping_cart WHERE id=?),?,?,(SELECT id FROM payment_method WHERE name=?),?,?)";

            row = jdbcTemplate.update(sql, new Object[]{cartId, cartId, "pending", "online", paymentMethodName, deliveryId, mp.get(0).get("guest_id").toString()});
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
    public int add(Delivery del, int cartId) {
        int deliveryId = 0;
        int row;
        if (del.getDeliveryMethod() == "pickup") {
            String sql = "INSERT INTO delivery " +
                    "(cart_id,delivery_date,delivery_time,delivery_status,delivery_method_id) VALUES (?,?,?,?,(SELECT id FROM delivery_method WHERE name=?))";

            row = jdbcTemplate.update(sql, new Object[]{cartId, del.getDeliveryDate(), del.getDeliveryTime(), "pending", del.getDeliveryMethod()});
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
                        "(cart_id,recepient_name,recepient_address,delivery_date,delivery_time,delivery_status,description,delivery_method_id) VALUES (?,?,?,?,?,?,?,(SELECT id FROM delivery_method WHERE name=?))";
                row = jdbcTemplate.update(sql, new Object[]{cartId, recName, recAddress,
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
    public String generateCartProcess(double netCost, String username, List<Map<String, Object>> items, Delivery del, String paymentMethodName) {
        String orderId = "0";
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            int cartId = addCart(netCost, username);
            List<Integer> productIdList = addProductsToCart(items);
            updateCartProductTable(cartId, productIdList);
            int deliveryId = add(del, cartId);
            add(paymentMethodName, cartId, deliveryId);
            orderId = selectOrderId(cartId);
            transactionManager.commit(stat);
            log.info("updated");


        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
            log.info("rollbacked");
        }
        return orderId;
    }

    @Override
    public String selectOrderId(int cartId) {
        List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT order_id FROM shopping_cart WHERE id=?", cartId);
        log.info(mp4.get(0).get("order_id").toString());
        return mp4.get(0).get("order_id").toString();
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

    /*update with */
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

    @Override
    public List<Map<String, Object>> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT order_id,order_date,order_time,net_cost,customer_id,guest_id FROM shopping_cart  ");
        log.info("{}", mp);
        return mp;
    }

    @Override
    public List<Map<String, Object>> getPurchaseHistory(String username, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT sc.id AS cart_id,sc.order_id,sc.order_date,sc.order_time,sc.net_cost,sc.customer_id," +
                "i.name,p.size,p.quantity,d.agent_name,d.staff_id,t.date AS transactin_date,dm.name AS delivery_method," +
                "pm.name AS payment_method,d.recepient_name,d.recepient_address,d.delivery_date,d.delivery_time,d.delivery_status,t.time AS transaction_time,t.payment_status" +
                " FROM shopping_cart sc INNER JOIN shopping_cart_product sp ON sc.id=sp.shopping_cart_id " +
                "INNER JOIN product p ON p.id=sp.product_id INNER JOIN delivery d ON d.cart_id=sc.id " +
                "INNER JOIN payment_transaction t ON t.cart_id=sc.id INNER JOIN item i ON p.type_id=i.id " +
                "INNER JOIN delivery_method dm ON dm.id=d.delivery_method_id INNER JOIN payment_method pm ON pm.id=t.payment_method_id" +
                " WHERE sc.customer_id=(SELECT id FROM customer WHERE username=?) LIMIT ? OFFSET ? ", username, limit, page);

        return mp;
    }


    @Override
    public List<Map<String, Object>> selectOrderDetails(String username, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM shopping_cart WHERE customer_id=(SELECT id FROM customer WHERE username=?) ORDER BY order_date DESC LIMIT ? OFFSET ?", username, limit, page);
        return mp;
    }

    @Override
    public int countOrderDetails(String username) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE customer_id=(SELECT id FROM customer WHERE username=?)", username);
        return Integer.parseInt(mp.get(0).get("total").toString());
    }

    @Override
    public List<List<Map<String, Object>>> selectItemDetailsOfOrder(String orderId) {
        List<List<Map<String, Object>>> items = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT product_id FROM shopping_cart_product WHERE shopping_cart_id=(SELECT id FROM shopping_cart WHERE order_id=?)", orderId);
        for (int i = 0; i < mp.size(); i++) {
            List<Map<String, Object>> mp2;
            List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT type FROM product WHERE id=?", mp.get(i).get("product_id"));
            if (mp1.get(0).get("type").toString().equals("item")) {
                mp2 = jdbcTemplate.queryForList("SELECT p.id ,p.type AS type,p.size,p.quantity ,p.instructions,i.name,(SELECT name FROM item WHERE id=p.topping_id1) AS topping1," +
                        "(SELECT name FROM item WHERE id=p.topping_id2) AS topping2 FROM product p " +
                        "INNER JOIN shopping_cart_product sp ON sp.product_id=p.id " +
                        "INNER JOIN item i ON i.id=p.type_id INNER JOIN shopping_cart sc ON sc.id=sp.shopping_cart_id " +
                        "WHERE sc.order_id=? AND p.id=?", orderId, mp.get(i).get("product_id"));
            } else {
                mp2 = jdbcTemplate.queryForList("SELECT p.* ,pck.name FROM product p INNER JOIN shopping_cart_product sp ON sp.product_id=p.id " +
                        "INNER JOIN package pck ON pck.id=p.type_id INNER JOIN shopping_cart sc ON sc.id=sp.shopping_cart_id " +
                        "WHERE sc.order_id=? AND p.id=?", orderId, mp.get(i).get("product_id"));
            }
            items.add(mp2);
        }
        log.info("{}", items);
        return items;
    }

    @Override
    public List<Map<String, Object>> selectPaymentAndDeliveryDetails(String orderId) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT d.id AS delivery_id,d.cart_id,d.agent_name,d.recepient_name,d.recepient_address,d.delivery_date," +
                "d.delivery_time,d.delivery_status,d.description,st.username AS delivery_handler,dm.name,t.id AS transaction_id," +
                "t.date AS transaction_date,t.time AS transaction_time,t.amount,t.payment_status,t.order_type,c.username AS customer_username,st.username" +
                " AS transaction_handler,pm.name AS payment_method FROM delivery d " +
                "INNER JOIN payment_transaction t ON t.delivery_id=d.id " +
                "INNER JOIN delivery_method dm ON dm.id=d.delivery_method_id" +
                " INNER JOIN customer c ON c.id=t.customer_id " +
                "INNER JOIN staff st ON st.id=t.staff_id INNER JOIN payment_method pm ON pm.id=t.payment_method_id " +
                "WHERE d.cart_id=(SELECT id FROM shopping_cart WHERE order_id=?)", orderId);
        return mp;
    }

  /*  public String checkUniqueOrderId(String orderID){
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM shopping_cart WHERE order_id=? ",orderID);
        if(mp.size()!=0){
            log.info("already available..checking");
            OrderIdGenerator orderId=new OrderIdGenerator();
            orderID=orderId.generateOrderId();
            checkUniqueOrderId(orderID);
        }
        log.info("{}inserted",orderID);
        return orderID;

    }*/



  /*  @Override
    public void addPackageToCart(int packageId) {}*/

    /*@Override
    public void modifyPackageInCart() {}*/

   /* @Override
    public void removePackageFromCart(int packageId) {}*/


}
