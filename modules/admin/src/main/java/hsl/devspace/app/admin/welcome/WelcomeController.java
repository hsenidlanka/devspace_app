package hsl.devspace.app.admin.welcome;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
@RequestMapping(value ="/PizzaShefu")
@SessionAttributes("thought")
public class WelcomeController {

    private static final Logger LOG = LogManager.getLogger(WelcomeController.class);
    private static final String view = "home";
    HttpServletRequest request;



  /*  @RequestMapping(value="")
    public String showTestPage() {
        return "forward:/welcome?locale=en&param2=bar";
    }*/

   @RequestMapping(value ="")
    public String initWelcome(Locale locale)  {

       LOG.trace("inside the welcome initiate service method");

       LOG.trace("The locale is {}",locale);
//       LOG.trace("The ur variable is {}",ur);

       // create a new array and get all installed locales
       Locale[] locales = Locale.getAvailableLocales();
       // print locales
       LOG.trace("Installed locales are:");
       for (int i = 0; i < locales.length; i++) {
           System.out.println(i + ":" + locales[i]);
           LOG.trace("Installed locales are {}",locales[i]);
       }

        return view;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:https://localhost:8443/cas/logout";
//        return "1";
    }
}
