package hsl.devspace.app.corelogic.repository.shopping_cart;

import hsl.devspace.app.corelogic.domain.Delivery;
import hsl.devspace.app.corelogic.domain.Item;

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

    void generateCartProcess(double netCost, String username, List<Map<String, Object>> items, Delivery del, String paymentMethodName);

    int countPerCustomer(String customerUsername);

    int countPerGuest(String mobile);

    int countTotalCustomerCarts();

    int countTotalGuestCarts();

    //    void addItemToCart(Item item);
    void modifyItemInCart(Item item);

    void removeItemFromCart(int itemId);

    int add(Delivery del);

    int add(String paymentMethodName, int cartId, int deliveryId);

    int updateDelivery(String agentName,String staffUsername,int deliveryId);

    int updatePayment(int paymentId,String staffUsername);
//    void addPackageToCart(int packageId);
//    void modifyPackageInCart();
//    void removePackageFromCart(int packageId);
}
