package hsl.devspace.app.corelogic.repository.shopping_cart;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.domain.ShoppingCart;
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
    public int addCart(ShoppingCart shoppingCart) {
        int row;
        String sql = "INSERT INTO shopping_cart " +
                "(net_cost,customer_id,guest_id) VALUES (?,(SELECT id FROM customer WHERE username=?),(SELECT id FROM guest WHERE mobile=?))";
        row = jdbcTemplate.update(sql, new Object[]{shoppingCart.getNetCost(),shoppingCart.getCustomerUsername(),shoppingCart.getGuestMobile()});
        log.info("{} new shopping cart added",row);
        List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM shopping_cart");
        int id = Integer.parseInt(mp4.get(0).get("id").toString());
        return id;
    }

    /*Add products in the cart to table*/
    @Override
    public List<Integer> addProductsToCart(List<Map<String, Object>> items) {
        List<Integer> productList = new ArrayList<Integer>();
        for (int i = 0; i < items.size(); i++) {
            String productName = items.get(i).get("itemName").toString();
            int quantity = Integer.parseInt(items.get(i).get("quantity").toString());
            List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM item WHERE name=?", productName);
            int id = Integer.parseInt(mp.get(0).get("id").toString());
            String type = "item";
            if (mp.size() == 0) {
                List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT id FROM package WHERE name=?", productName);
                id = Integer.parseInt(mp1.get(0).get("id").toString());
                type = "package";
            }

            String sql = "INSERT INTO product " +
                    "(type,type_id,quantity) VALUES (?,?,?)";
            jdbcTemplate.update(sql, new Object[]{type, id, quantity});
            List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT MAX(id) FROM product");
            id = Integer.parseInt(mp4.get(0).get("id").toString());
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void generateCartProcess(ShoppingCart shoppingCart, List<Map<String, Object>> items) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            int cartId = addCart(shoppingCart);
            List<Integer> productIdList = addProductsToCart(items);
            updateCartProductTable(cartId, productIdList);
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
        log.info("{}",count);
        return count;
    }

    /*cart count for a specific guest*/
    @Override
    public int countPerGuest(String mobile) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE guest_id=(SELECT id FROM guest WHERE mobile=?)", mobile);
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}",count);
        return count;
    }

    /*retrieve no.of carts for the customers(registered)*/
    @Override
    public int countTotalCustomerCarts() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE customer_id>0 ");
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}",count);
        return count;
    }

    /*retrieve no.of carts for the guests*/
    @Override
    public int countTotalGuestCarts() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT COUNT(*) AS total FROM shopping_cart WHERE guest_id>0 ");
        int count = Integer.parseInt(mp.get(0).get("total").toString());
        log.info("{}",count);
        return count;
    }

    @Override
    public void addItemToCart(Item item) {
        String sql="INSERT INTO shopping_cart VALUES (?,?,?)";
        jdbcTemplate.update(sql,new Object[]{Item.class.getDeclaredConstructors()});

    }

    @Override
    public void modifyItemInCart(Item item) {


    }

    @Override
    public void removeItemFromCart(int itemId) {

    }

    @Override
    public void addPackageToCart(int packageId) {

    }

    @Override
    public void modifyPackageInCart() {

    }

    @Override
    public void removePackageFromCart(int packageId) {

    }


}
