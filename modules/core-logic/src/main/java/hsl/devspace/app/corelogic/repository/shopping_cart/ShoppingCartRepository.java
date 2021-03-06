package hsl.devspace.app.corelogic.repository.shopping_cart;

import hsl.devspace.app.corelogic.domain.Delivery;
import hsl.devspace.app.corelogic.domain.Item;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/23/16.
 */
public interface ShoppingCartRepository {
    int addCart(double netCost, String username);
    int delete(int id);

    List<Integer> addProductsToCart(List<Map<String, Object>> items);

    void updateCartProductTable(int cartId, List<Integer> productIdList);

    String generateCartProcess(double netCost, String username, List<Map<String, Object>> items, Delivery del, String paymentMethodName);

    int countPerCustomer(String customerUsername);

    int countPerGuest(String mobile);

    int countTotalCustomerCarts();

    int countTotalGuestCarts();

    //    void addItemToCart(Item item);
    void modifyItemInCart(Item item);

    int removeItemFromCart(int productId);

    int add(Delivery del, int cartId);

    int add(String paymentMethodName, int cartId, int deliveryId);

    int updateDelivery(String agentName,String staffUsername,int deliveryId);

    int updatePayment(int paymentId,String staffUsername);

    List<Map<String, Object>> selectAll();

    String selectOrderId(int cartId);

    List<Map<String, Object>> getPurchaseHistory(String username, int limit, int page);

    List<List<Map<String, Object>>> selectItemDetailsOfOrder(String orderId);

    List<Map<String, Object>> selectDeliveryDetails(String orderId);

    List<Map<String, Object>> selectOrderDetails(String username, int limit, int page);

    public int countOrderDetails(String username);

    List<Map<String, Object>> selectPaymentDetails(String orderId);

    List<Map<String, Object>> getOrderDetailsByDate(String username, LocalDate date, int limit, int page);

    int countOrdersForCustomerByDate(String customerUsername, LocalDate date);

    List<Map<String, Object>> selectItemHistoryForCustomer(String username, int limit, int offset);

    List<Map<String, Object>> selectUnAssignedDelivery();

    List<Map<String, Object>> selectPendingDelivery();

    int countItemHistoryForCustomer(String username);

    List<Map<String, Object>> selectAllOrders();

    List<Map<String, Object>> selectAllOrdersPaginate(int limit, int offset);

    List<Map<String, Object>> selectAllOrdersByDateRange(Date date1, Date date2, int limit, int offset);

    List<Map<String, Object>> selectAllOrdersByDateFrom(Date dateFrom, int limit, int offset);

    List<Map<String, Object>> selectAllOrdersByDateTo(Date dateTo, int limit, int offset);

    public int countTotalCarts();
}
