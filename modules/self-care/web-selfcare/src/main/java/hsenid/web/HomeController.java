package hsenid.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PropertySource("classpath:config.properties")
public class HomeController {
    @Value("${mongodb.url}")
    private String mongodbUrl;

    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"/", "/home"})
    public String home() {
        logger.info("Web Application Started");
        return "/home/self-care-home";
    }

    @RequestMapping("/menu")
    public String menu() {
        return "/home/menu";
    }

    @RequestMapping("/locations")
    public String locations() {
        return "/home/locations";
    }

    @RequestMapping("/createfeedback")
    public String createfeedback() {
        return "/home/createfeedback";
    }

    @RequestMapping("/contactus")
    public String contactus() {
        return "/home/contactus";
    }

    @RequestMapping("/aboutus")
    public String aboutus() {
        return "/home/aboutus";
    }

    @RequestMapping("/payment")
    public String payment() {
        return "payment";
    }

    @RequestMapping("/shopping-cart")
    public String shoppingCart() {
        return "shopping-cart";
    }

    @RequestMapping("/forgotpassword")
    public String forgotpassword() {
        return "forgotpassword";
    }


    @RequestMapping("/delivery")
    public String delivery() {
        return "delivery";
    }

    @RequestMapping("/pickup-summary")
    public String pickupSummary() {
        return "pickup-summary";
    }

    @RequestMapping("/view-customer-details")
    public String viewCustomerDetails() {
        return "view-customer-details";
    }

    @RequestMapping("/pay")
    public String pay() {
        return "pay";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/delivery-summary")
    public String deliverySummary(){
        return "delivery-summary";
    }

    @RequestMapping("search-results")
    public String searchResults(){
        return "search-results";
    }

}
