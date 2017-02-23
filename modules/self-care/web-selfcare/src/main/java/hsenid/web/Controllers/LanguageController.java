package hsenid.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LanguageController {

    @RequestMapping(value = "/language", method = RequestMethod.POST)
    @ResponseBody
    public void setLanguage(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("language") == null || session.getAttribute("language") == "") {
            session.setAttribute("language", "en");
        } else {
            session.setAttribute("language", request.getParameter("lang"));
        }
    }

    @RequestMapping(value = "/language", method = RequestMethod.GET)
    @ResponseBody
    public String getLanguage(HttpSession session) {
        String language;
        if (session.getAttribute("language") == null || session.getAttribute("language") == "") {
            language = "en";
            session.setAttribute("language", "en");
        } else {
            language = session.getAttribute("language").toString();
        }
        return language;
    }
}
