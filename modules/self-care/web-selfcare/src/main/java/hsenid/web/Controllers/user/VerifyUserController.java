package hsenid.web.Controllers.user;

import hsenid.web.models.*;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@PropertySource("classpath:config.properties")
@RequestMapping("/email-verification")
public class VerifyUserController {
    final static Logger logger = LoggerFactory.getLogger(VerifyUserController.class);
    @Value("${api.url.base.url}")
    private String baseUrl;

    @Value("${api.url.customer.search}")
    private String customerSearchUrl;

    final static String username = "username";
    final static String password = "password";
    RestTemplate restTemplate = new RestTemplate();
    User user = new User();
    Verification verification = new Verification();


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView emailVerification(){
        return new ModelAndView("includes/email-verification", "emailverification", new EmailVerificationModel());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String confirmVerification(@ModelAttribute("emailverification") @Valid EmailVerificationModel verificationModel, BindingResult result, RedirectAttributes redirectAttributes, Model model, HttpSession session){
        if (result.hasErrors()){
            String retStr = SendStringBuilds.sendString("includes/email-verification/", "?username=", verificationModel.getUsername());
            return retStr;
        }

        String uname = verificationModel.getUsername();
        String verificationCode = verificationModel.getVerificationCode();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(username, uname);
        jsonObject.put("verificationCode", verificationCode);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<JSONObject>(jsonObject, headers);
        String verifyEmailUrl = SendStringBuilds.sendString(baseUrl, "/customers/verify");
        logger.info(String.valueOf(jsonObject));
        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(verifyEmailUrl, jsonObjectHttpEntity, ServerResponseMessage.class);
            if (responseMessage.getStatus().equals("success")){

                String fullCustomerSearchUrl = SendStringBuilds.sendString(customerSearchUrl,uname);
                logger.error(fullCustomerSearchUrl);
                try {
                    User confirmedUser = new User();

                    ReplyFromServer replyFromServer = restTemplate.getForObject(fullCustomerSearchUrl, ReplyFromServer.class);

                    confirmedUser.setTitle(replyFromServer.getData().get(0).getTitle());
                    confirmedUser.setFirstName(replyFromServer.getData().get(0).getFirstName());
                    confirmedUser.setLastName(replyFromServer.getData().get(0).getLastName());
                    confirmedUser.setEmail(replyFromServer.getData().get(0).getEmail());
                    confirmedUser.setMobile(replyFromServer.getData().get(0).getMobile());
                    confirmedUser.setUsername(replyFromServer.getData().get(0).getUsername());
                    confirmedUser.setAddressLine01(replyFromServer.getData().get(0).getAddressLine01());
                    confirmedUser.setAddressLine02(replyFromServer.getData().get(0).getAddressLine02());
                    confirmedUser.setAddressLine03(replyFromServer.getData().get(0).getAddressLine03());

                    session.setAttribute("name", SendStringBuilds.sendString(confirmedUser.getFirstName(), " ", confirmedUser.getLastName()));
                    session.setAttribute("email", confirmedUser.getEmail());
                    session.setAttribute(username, uname);
                    redirectAttributes.addFlashAttribute("verifyPass", "pass");
                    
                    logger.info("User Data set work");
                    return "redirect:/email-verification";
                } catch (RestClientException e) {
                    logger.error("Inner User Data retrive Failed {}", e.getMessage());
                }
            }else{
                if(session!=null){
                    session.invalidate();
                }
                redirectAttributes.addFlashAttribute("notValidCode", "notValid");
                logger.info("Not Valide code work");
                return "redirect:/email-verification";
//                return "redirect:/includes/email-verification";
            }

        } catch (RestClientException e) {
            if(session!=null){
                session.invalidate();
            }
            logger.error("Verification failed Reason -> {}", e.getMessage());

        }
//        logger.info( "testphrase {} {}", verificationModel.getUsername(), verificationModel.getVerificationCode());
        redirectAttributes.addFlashAttribute("verifyFailed", "failed");
        return "redirect:home/menu";
    }
}
