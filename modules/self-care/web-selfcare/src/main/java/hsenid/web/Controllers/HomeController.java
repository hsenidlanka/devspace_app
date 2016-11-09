package hsenid.web.Controllers;

import hsenid.web.models.UpdateUser;
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


    @GetMapping("/updateuser")
    public String updateuser(HttpSession session, Model model){
        model.addAttribute("updateuser", new UpdateUser());
        return "/home/updateuser";
    }

//    public String sendConctUs(@ModelAttribute("contactus") @Valid ContactUs contactus, BindingResult result, Model model, RedirectAttributes redirectAttributes){
    @PostMapping("/updateuser")
    public String submitUpdate(@ModelAttribute("updateuser") @Valid UpdateUser updateuser, BindingResult bindingResult,HttpSession session, RedirectAttributes redirectAttributes){

        JSONObject jsonObject = new JSONObject();
        logger.info(String.valueOf(bindingResult.hasErrors()));

        if (bindingResult.hasErrors()){
//            logger.info("binding errors");
            return "/home/updateuser";
        }

//        logger.info(String.valueOf(bindingResult.hasErrors()));

        logger.info("title value {}", updateuser.getTitle());
        jsonObject.put("title", updateuser.getTitle());
        jsonObject.put("firstName", updateuser.getFirstName());
        jsonObject.put("lastName", updateuser.getLastName());
        jsonObject.put("username", updateuser.getUsername());
        jsonObject.put("email", updateuser.getEmail());
        jsonObject.put("addressL1", updateuser.getAddressLine01());
        jsonObject.put("addressL2", updateuser.getAddressLine02());
        jsonObject.put("addressL3", updateuser.getAddressLine03());
        jsonObject.put("mobile", updateuser.getMobile());

//        logger.info(jsonObject.toString());
//logger.info(jsonObject.toString());
        RestTemplate restTemplate = new RestTemplate();


        try {
            restTemplate.put(customerUpdateUrl, jsonObject);

/*
* Here we update the session for front end change
* */
            session.removeAttribute("title");
            session.setAttribute("title", updateuser.getTitle());

            session.removeAttribute("firstName");
            session.setAttribute("firstName", updateuser.getFirstName());

            session.removeAttribute("lastName");
            session.setAttribute("lastName", updateuser.getLastName());

            session.removeAttribute("email");
            session.setAttribute("email", updateuser.getEmail());

            String namebuild = SendStringBuilds.sendString(updateuser.getFirstName(), " ", updateuser.getLastName());
            session.removeAttribute("name");
            session.setAttribute("name", namebuild);

            session.removeAttribute("mobile");
            session.setAttribute("mobile", updateuser.getMobile());

            session.removeAttribute("addr1");
            session.setAttribute("addr1", updateuser.getAddressLine01());

            session.removeAttribute("addr2");
            session.setAttribute("addr2", updateuser.getAddressLine02());

            session.removeAttribute("addr3");
            session.setAttribute("addr3", updateuser.getAddressLine03());

            redirectAttributes.addFlashAttribute("updatedMsg", "Profile updation Successful!!!");


        } catch (RestClientException e) {
            logger.info("update error {}", e.getMessage());
            redirectAttributes.addFlashAttribute("updatedMsg", "Profile updation failed!!!");
        }


//        logger.info("uu name {}", updateUser.getEmail());
        return "redirect:/updateuser";

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
