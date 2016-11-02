package hsenid.web.Controllers;

import hsenid.web.models.ContactUs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/contactus")
public class ContactUsController {

    @RequestMapping(method = RequestMethod.GET)
    public String newContactUs(ModelMap model){
        model.addAttribute("contactus", new ContactUs());
        return "/home/contactus";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendConctUs(@Valid ContactUs contactus, BindingResult result, ModelMap model){
        if (result.hasErrors()){
            return "/home/contactus";
        }
        return "/home/self-care-home";
    }
}
