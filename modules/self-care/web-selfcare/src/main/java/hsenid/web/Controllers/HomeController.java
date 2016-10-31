package hsenid.web.Controllers;

import hsenid.web.models.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@PropertySource("classpath:config.properties")
/*
* This controller is basically define to return every jsp file in the home page*/
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Value("${api.url.contactUs.add}")
    private String contactUsUrl;

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


    @GetMapping("/updateuser")
    public String updateuser(HttpSession session, Model model){
        model.addAttribute("updateuser", new UpdateUser());
        return "/home/updateuser";
    }

    @PostMapping("/updateuser")
    public String submitUpdate(@ModelAttribute UpdateUser updateUser){
        logger.info("uu name {}", updateUser.getEmail());
        return "redirect:/updateuser";
    }


    @RequestMapping(value = "/contactus", method = RequestMethod.GET)
    public ModelAndView contactus() {
        return new ModelAndView("/home/contactus", "contactus", new ContactUs());
    }




    @RequestMapping(value = "/sendContactus", method = RequestMethod.POST)
    public String sendContactus(@ModelAttribute ContactUs contactUs){

//        logger.info("Send Contact {}",contactUs.getInquiryType());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("inquiryType", contactUs.getInquiryType());
        jsonObject.put("title", contactUs.getTitle());
        jsonObject.put("name", contactUs.getName());
        jsonObject.put("email", contactUs.getContactEmail());
        jsonObject.put("mobile", contactUs.getContactNo());
        jsonObject.put("message", contactUs.getMsg());

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        try{
            ReplyFromServer message = restTemplate.postForObject(contactUsUrl, httpEntity, ReplyFromServer.class);
        }catch (Exception e){
            logger.info(e.getMessage());

        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){

        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";

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
