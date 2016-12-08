package hsl.devspace.app.corelogic.repository.shopping_cart;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.domain.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/23/16.
 */
public interface ShoppingCartRepository {
    int addCart(ShoppingCart shoppingCart);
    int delete(int id);

    List<Integer> addProductsToCart(List<Map<String, Object>> items);

    void updateCartProductTable(int cartId, List<Integer> productIdList);

    void generateCartProcess(ShoppingCart shoppingCart, List<Map<String, Object>> items);
    int countPerCustomer(String customerUsername);
    int countPerGuest(String mobile);
    int countTotalCustomerCarts();
    int countTotalGuestCarts();
    void addItemToCart(Item item);
    void modifyItemInCart(Item item);
    void removeItemFromCart(int itemId);
    void addPackageToCart(int packageId);
    void modifyPackageInCart();
    void removePackageFromCart(int packageId);
}
