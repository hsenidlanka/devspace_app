package hsl.devspace.app.corelogic.repository.category;

import hsl.devspace.app.corelogic.domain.ShoppingCart;
import hsl.devspace.app.corelogic.repository.item.ItemRepositoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Random;

/**
 * Created by hsenid on 9/15/16.
 */
public class CategoryApp {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-user.xml");
        ItemRepositoryImpl catDemo = (ItemRepositoryImpl) context.getBean("itemRepository");
        ShoppingCart cat=new ShoppingCart(700.00,"","0771123456");
        catDemo.getTopRatedItemsFromAllCategories();
        final String[] proper_noun = {"F", "t"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        System.out.println(proper_noun[index]);

    }
}