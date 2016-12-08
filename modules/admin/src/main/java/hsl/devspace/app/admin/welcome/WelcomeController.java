package hsl.devspace.app.admin.welcome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value ="/PizzaShefu")
@SessionAttributes("thought")
public class WelcomeController {

    private static final Logger LOG = LogManager.getLogger(WelcomeController.class);
    private static final String view = "home";
    HttpServletRequest request;

   @RequestMapping(value ="/")
    public String initWelcome()  {
        LOG.trace("inside the welcome initiate service method");

        return view;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
       return "redirect:https://localhost:8443/cas/logout";
//        return "1";
    }

}
