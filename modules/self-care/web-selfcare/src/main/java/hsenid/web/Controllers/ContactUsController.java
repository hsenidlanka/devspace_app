package hsenid.web.Controllers;

import hsenid.web.models.ContactUs;
import hsenid.web.models.ReplyFromServer;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/contact-us")
public class ContactUsController {
    final Logger logger = LoggerFactory.getLogger(ContactUsController.class);

    @Value("${api.url.contactUs.add}")
    private String contactUsUrl;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView newContactUs(){
        return new ModelAndView("/home/contact-us","contactus", new ContactUs());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendConctUs(@ModelAttribute("contactus") @Valid ContactUs contactus, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "/home/contact-us";
        }

        redirectAttributes.addFlashAttribute("validForm", "dd");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("inquiryType", contactus.getInquiryType());
        jsonObject.put("title", contactus.getTitle());
        jsonObject.put("name", contactus.getName());
        jsonObject.put("email", contactus.getContactEmail());
        jsonObject.put("mobile", contactus.getContactNo());
        jsonObject.put("message", contactus.getMsg());

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        try{
            ReplyFromServer message = restTemplate.postForObject(contactUsUrl, httpEntity, ReplyFromServer.class);
        }catch (Exception e){
            logger.error(e.getMessage());

        }
        redirectAttributes.addFlashAttribute("validForm", "True");
        return "redirect:/contact-us";
//        return "/home/self-care-home";
    }
}
