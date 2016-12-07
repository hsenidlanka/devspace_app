package hsl.devspace.app.corelogic.repository.shopping_cart;

import hsl.devspace.app.corelogic.domain.Item;
import hsl.devspace.app.corelogic.domain.ShoppingCart;

/**
 * Created by hsenid on 9/23/16.
 */
public interface ShoppingCartRepository {
    int addCart(ShoppingCart shoppingCart);
    int delete(int id);
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
