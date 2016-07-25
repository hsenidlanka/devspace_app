package hsl.devspace.app.admin.welcome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/welcome")
@SessionAttributes("welcomeForm")
public class WelcomeController {

    private static final Logger LOG = LogManager.getLogger(WelcomeController.class);
    private static final String view = "welcome";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView initWelcome(Model model) throws Exception {

        LOG.trace("inside the welcome initiate service method");

        WelcomeForm welcomeForm = new WelcomeForm();
        model.addAttribute("welcomeForm", welcomeForm);
        return new ModelAndView(view, "model", model);
    }

}
