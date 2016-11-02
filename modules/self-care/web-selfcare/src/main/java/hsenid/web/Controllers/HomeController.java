package hsenid.web.Controllers;

import hsenid.web.models.ContactUs;
import hsenid.web.models.ReplyFromServer;
import hsenid.web.models.UpdateUser;
import hsenid.web.supportclasses.SendStringBuilds;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@PropertySource("classpath:config.properties")
/*
* This controller is basically define to return every jsp file in the home page*/
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Value("${api.url.contactUs.add}")
    private String contactUsUrl;

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


    @GetMapping("/updateuser")
    public String updateuser(HttpSession session, Model model){
        model.addAttribute("updateuser", new UpdateUser());
        return "/home/updateuser";
    }

    @PostMapping("/updateuser")
    public String submitUpdate(@ModelAttribute @Valid UpdateUser updateUser, BindingResult bindingResult,HttpSession session, final RedirectAttributes redirectAttributes){

        JSONObject jsonObject = new JSONObject();


        if (bindingResult.hasErrors()){
            logger.info("binding errors");
        }

        logger.info(String.valueOf(bindingResult.hasErrors()));

        jsonObject.put("title", updateUser.getTitle());
        jsonObject.put("firstName", updateUser.getFirstName());
        jsonObject.put("lastName", updateUser.getLastName());
        jsonObject.put("username", updateUser.getUsername());
        jsonObject.put("email", updateUser.getEmail());
        jsonObject.put("addressL1", updateUser.getAddressLine01());
        jsonObject.put("addressL2", updateUser.getAddressLine02());
        jsonObject.put("addressL3", updateUser.getAddressLine03());
        jsonObject.put("mobile", updateUser.getMobile());

//        logger.info(jsonObject.toString());

        RestTemplate restTemplate = new RestTemplate();


        try {
            restTemplate.put(customerUpdateUrl, jsonObject);

/*
* Here we update the session for front end change*/
            session.removeAttribute("title");
            session.setAttribute("title", updateUser.getTitle());

            session.removeAttribute("firstName");
            session.setAttribute("firstName", updateUser.getFirstName());

            session.removeAttribute("lastName");
            session.setAttribute("lastName", updateUser.getLastName());

            session.removeAttribute("email");
            session.setAttribute("email", updateUser.getEmail());

            String namebuild = SendStringBuilds.sendString(updateUser.getFirstName(), " ", updateUser.getLastName());
            session.removeAttribute("name");
            session.setAttribute("name", namebuild);

            session.removeAttribute("mobile");
            session.setAttribute("mobile", updateUser.getMobile());

            session.removeAttribute("addr1");
            session.setAttribute("addr1", updateUser.getAddressLine01());

            session.removeAttribute("addr2");
            session.setAttribute("addr2", updateUser.getAddressLine02());

            session.removeAttribute("addr3");
            session.setAttribute("addr3", updateUser.getAddressLine03());

            redirectAttributes.addFlashAttribute("updatedMsg", "Profile updation Successful!!!");


        } catch (RestClientException e) {
            logger.info("update error {}", e.getMessage());
            redirectAttributes.addFlashAttribute("updatedMsg", "Profile updation failed!!!");
        }


        logger.info("uu name {}", updateUser.getEmail());
        return "redirect:/updateuser";

    }

//    @GetMapping(value = "/contactus")
    public ModelAndView contactus() {
        return new ModelAndView("/home/contactus", "contactus", new ContactUs());
    }




//    @PostMapping(value = "/contactus" )
    public String sendContactus(@Valid ContactUs contactUs, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            logger.info("binding errors");
            return "/home/contactus";
        }
        redirectAttributes.addFlashAttribute("validForm", "dd");
        logger.info(String.valueOf(bindingResult.hasErrors()));


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
        redirectAttributes.addFlashAttribute("validForm", "True");
        return "contactus";
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
