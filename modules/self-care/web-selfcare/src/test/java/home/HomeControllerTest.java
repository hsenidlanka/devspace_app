package home;

import hsenid.web.Controllers.HomeController;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class HomeControllerTest extends AbstractTestNGSpringContextTests {

    HomeController homeController = new HomeController();

    @Test()
    void testHomeController(){
        String reply = homeController.home();
        Assert.assertNotNull(reply);
        Assert.assertEquals(reply, "/home/self-care-home");
    }

    @Test()
    void testMenuController(){
        String reply = homeController.menu();
        Assert.assertNotNull(reply);
        Assert.assertEquals(reply, "/home/menu");
    }
    @Test()
    void testAboutusController(){
        String reply = homeController.aboutus();
        Assert.assertNotNull(reply);
        Assert.assertEquals(reply, "/home/about-us");
    }
    @Test()
    void testLocationsController(){
        String reply = homeController.locations();
        Assert.assertNotNull(reply);
        Assert.assertEquals(reply, "/home/locations");
    }
    @Test()
    void testPaymentController(){
        String reply = homeController.payment();
        Assert.assertNotNull(reply);
        Assert.assertEquals(reply, "payment");
    }
    @Test()
    void testForgotpasswordController(){
        String reply = homeController.forgotpassword();
        Assert.assertNotNull(reply);
        Assert.assertEquals(reply, "forgotpassword");
    }
    @Test()
    void testSearchMenuController(){
        String reply = homeController.searchMenu();
        Assert.assertNotNull(reply);
        Assert.assertEquals(reply, "search-menu");
    }


}