package hsenid.web.Controllers;

import hsenid.web.models.User;
import hsenid.web.supportclasses.SendStringBuilds;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@PropertySource("classpath:config.properties")
/*
* This controller is basically define to return every jsp file in the home page*/
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Value("${api.url.customer.update}")
    private String customerUpdateUrl;

//    Mapped to home page
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



    @RequestMapping("/logout")
    public String logout(HttpSession session){

        if(session!=null){
            session.invalidate();
            logger.info("User Logged out.");
        }
        return "redirect:/";

    }

    @RequestMapping("/about-us")
    public String aboutus() {
        return "/home/about-us";
    }

    @RequestMapping("/payment")
    public String payment() {
        return "payment";
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

    @RequestMapping("/search-results")
    public String searchResults(){
        return "search-results";
    }

    @RequestMapping("/search-menu")
    public String searchMenu(){
        return "search-menu";
    }

}
