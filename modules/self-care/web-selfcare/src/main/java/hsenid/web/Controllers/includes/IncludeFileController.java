package hsenid.web.Controllers.includes;

import hsenid.web.models.ReplyFromServer;
import hsenid.web.models.User;
import hsenid.web.supportclasses.SendStringBuilds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
@PropertySource("classpath:config.properties")
public class IncludeFileController {
    final Logger logger = LoggerFactory.getLogger(IncludeFileController.class);

    @Value("${api.url.customer.search}")
    private String userDataUrl;

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

    /*@RequestMapping("/testcont")
    public Exception testt(){
        return new Exception("Test Exception");
    }*/

}
