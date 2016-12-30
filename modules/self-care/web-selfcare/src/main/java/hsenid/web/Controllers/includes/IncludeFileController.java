package hsenid.web.Controllers.includes;

import hsenid.web.models.ChangePassword;
import hsenid.web.models.ReplyFromServer;
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
public class IncludeFileController {
    final Logger logger = LoggerFactory.getLogger(IncludeFileController.class);

    @Value("${api.url.customer.search}")
    private String userDataUrl;

    @Value("${api.url.base.url}")
    private String baseUrl;

    @RequestMapping("/profile-info")
    public String sendProfileData(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        String fullUserDataUrl = SendStringBuilds.sendString(userDataUrl, username);

        RestTemplate restTemplate = new RestTemplate();

        User user = new User();

        try {
            ReplyFromServer replyFromServer = restTemplate.getForObject(fullUserDataUrl, ReplyFromServer.class);

            user.setFirstName(replyFromServer.getData().get(0).getFirstName());
            user.setLastName(replyFromServer.getData().get(0).getLastName());
            user.setEmail(replyFromServer.getData().get(0).getEmail());
            user.setMobile(replyFromServer.getData().get(0).getMobile());
            user.setUsername(username);
            user.setAddressLine01(replyFromServer.getData().get(0).getAddressLine01());
            user.setAddressLine02(replyFromServer.getData().get(0).getAddressLine02());
            user.setAddressLine03(replyFromServer.getData().get(0).getAddressLine03());

        } catch (RestClientException e) {
            logger.error("Profile sending failed. Reason - {}", e.getMessage());
            throw e;
        }

        model.addAttribute("userDataProfile", user);
        logger.info(user.getFirstName());
        return "/includes/profile-info";
    }

    @GetMapping("/change-password")
    public String changePasswordRequest(ChangePassword changePassword, Model model){
        model.addAttribute("changepassword", changePassword);
        return "includes/change-password";
    }

    @PostMapping("/change-password-post")
    public String changePasswordSubmit(HttpSession session, @ModelAttribute("changepassword") @Valid ChangePassword changePassword, BindingResult result, RedirectAttributes redirectAttributes){

        if (result.hasErrors()){
            return "includes/change-password";
        }

        String changePasswordUrl = "/customers/password";

        JSONObject jsonObject = new JSONObject();

        String username = (String) session.getAttribute("username");
        String currentPassword = changePassword.getCurrentPassword();
        String newPassword = changePassword.getNewPassword();

        jsonObject.put("username", username);
        jsonObject.put("password", currentPassword);
        jsonObject.put("newPassword", newPassword);

        RestTemplate restTemplate = new RestTemplate();
        String changePasswordFullUrl = SendStringBuilds.sendString(baseUrl, changePasswordUrl);

        try {
            restTemplate.put(changePasswordFullUrl, jsonObject);
            redirectAttributes.addFlashAttribute("updateSuccessful", "success");
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            redirectAttributes.addAttribute("formFailed", "Form Failed!");
        }

        return "redirect:/change-password";
    }
}
