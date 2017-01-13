package hsenid.web.Controllers.user;

import hsenid.web.models.RegisterModal;
import hsenid.web.models.ServerResponseMessage;
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

import javax.validation.Valid;

@Controller
@PropertySource("classpath:config.properties")
@RequestMapping("/register")
public class RegisterController {

    @Value("${api.url.register}")
    private String registerUrl;

    @Value("${api.url.customer.search}")
    private String customerSearchUrl;

    final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sendRegister(){
        return new ModelAndView("home/register", "register", new RegisterModal());
    }


    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("register") @Valid RegisterModal registerModal, BindingResult result, Model model){
        if (result.hasErrors()){
            return "/home/register";
        }

//        To avoid null values breaking our code we replace null value with white space.
        String addr3 = SendStringBuilds.getValueOrDefault(registerModal.getAddressLine03(), " ");
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("title", registerModal.getTitle());
        jsonObject.put("firstName", registerModal.getFirstName());
        jsonObject.put("lastName", registerModal.getLastName());
        jsonObject.put("username", registerModal.getUsername());
        jsonObject.put("password", registerModal.getPassword());
        jsonObject.put("email", registerModal.getEmail());
        jsonObject.put("addressL1", registerModal.getAddressLine01());
        jsonObject.put("addressL2", registerModal.getAddressLine02());
        jsonObject.put("addressL3", addr3);
        jsonObject.put("mobile", registerModal.getMobile());
        logger.info(String.valueOf(jsonObject));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        try {
            ServerResponseMessage responseMessage = restTemplate.postForObject(registerUrl, httpEntity, ServerResponseMessage.class);
            String regSuccessUrl = SendStringBuilds.sendString("redirect:", "/email-verification?username=", registerModal.getUsername());
            return regSuccessUrl;
        } catch (RestClientException e) {
            logger.error("User registration failed reason -> {}.", e.getMessage());
        }

        return "redirect:/register";
    }

}
