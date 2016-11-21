package hsenid.web.Controllers.user;


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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@PropertySource("classpath:config.properties")
public class UpdateUserController {

    final static Logger logger = LoggerFactory.getLogger(UpdateUserController.class);

    @Value("${api.url.customer.update}")
    private String customerUpdateUrl;

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

        RestTemplate restTemplate = new RestTemplate();


        try {
            restTemplate.put(customerUpdateUrl, jsonObject);

            /* Here we update the session for front end change*/

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
            logger.error("update error {}", e.getMessage());
            redirectAttributes.addFlashAttribute("updatedMsg", "Profile updation failed!!!");
        }
        return "redirect:/update-user";

    }
}
