package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller

public class HomeController {
    @RequestMapping({"/", "/home"})
    public String home(){
        return "self-care-home";
    }

    @RequestMapping("/menu")
    public String menu(){
        return "menu";
    }

    @RequestMapping("/locations")
    public String locations(){
        return "locations";
    }

    @RequestMapping("/createfeedback")
    public String createfeedback(){
        return "createfeedback";
    }

    @RequestMapping("/contactus")
    public String contactus(){
        return "contactus";
    }

    @RequestMapping("/aboutus")
    public String aboutus(){
        return "aboutus";
    }

    @RequestMapping("/payment")
    public String payment(){
        return "payment";
    }

    @RequestMapping("/shopping-cart")
    public String shoppingCart(){
        return "shopping-cart";
    }

    @RequestMapping("forgotpassword")
    public String forgotpassword(){
        return "forgotpassword";
    }
//    Test request
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}