package hsenid.web.Controllers.user;


import hsenid.web.models.ReplyFromServer;
import hsenid.web.models.User;
import hsenid.web.supportclasses.SendStringBuilds;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@PropertySource("classpath:config.properties")
public class UpdateUserController {

    final static Logger logger = LoggerFactory.getLogger(UpdateUserController.class);

    @Value("${api.url.customer.update}")
    private String customerUpdateUrl;

    @Value("${api.url.customer.search}")
    private String customerDataSendUrl;

    @GetMapping("/update-user")
    public String updateuser(HttpSession session, Model model) {
        model.addAttribute("updateuser", new User());
        return "/home/update-user";

    }


    @PostMapping("/update-user")
    public String submitUpdate(@ModelAttribute("updateuser") @Valid User updateuser, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {

        JSONObject jsonObject = new JSONObject();
        logger.info(String.valueOf(bindingResult.hasErrors()));

        if (bindingResult.hasErrors()) {
            return "/home/update-user";
        }

        jsonObject.put("title", updateuser.getTitle());
        jsonObject.put("firstName", updateuser.getFirstName());
        jsonObject.put("lastName", updateuser.getLastName());
        jsonObject.put("username", updateuser.getUsername());
        jsonObject.put("email", updateuser.getEmail());
        jsonObject.put("addressL1", updateuser.getAddressLine01());
        jsonObject.put("addressL2", updateuser.getAddressLine02());
        jsonObject.put("addressL3", updateuser.getAddressLine03());
        jsonObject.put("mobile", updateuser.getMobile());

        RestTemplate restTemplate = new RestTemplate();


        try {
            restTemplate.put(customerUpdateUrl, jsonObject);
            redirectAttributes.addFlashAttribute("updatedMsg", "Profile updation Successful!!!");

        } catch (RestClientException e) {
            logger.error("update error {}", e.getMessage());
            redirectAttributes.addFlashAttribute("updatedMsg", "Profile updation failed!!!");
        }
        return "redirect:/update-user";
    }

//    This method send json object which includes user's details
    @GetMapping("sendUserData")
    @ResponseBody
    public User sendUserData(HttpServletRequest request){
        User user = new User();
        String username = request.getParameter("username");
        String userDetails = SendStringBuilds.sendString(customerDataSendUrl, username);


        RestTemplate restTemplate1 = new RestTemplate();
        ReplyFromServer replyFromServer1 = restTemplate1.getForObject(userDetails, ReplyFromServer.class);

        user.setTitle(replyFromServer1.getData().get(0).getTitle());
        user.setFirstName(replyFromServer1.getData().get(0).getFirstName());
        user.setLastName(replyFromServer1.getData().get(0).getLastName());
        user.setEmail(replyFromServer1.getData().get(0).getEmail());
        user.setMobile(replyFromServer1.getData().get(0).getMobile());
        user.setUsername(replyFromServer1.getData().get(0).getUsername());
        user.setAddressLine01(replyFromServer1.getData().get(0).getAddressLine01());
        user.setAddressLine02(replyFromServer1.getData().get(0).getAddressLine02());
        user.setAddressLine03(replyFromServer1.getData().get(0).getAddressLine03());

        return user;
    }
}
