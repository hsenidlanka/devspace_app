package hsenid.web.Test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("thought")
public class TestController {
    @RequestMapping("session")
    public ModelAndView testSession(){
        return new ModelAndView("testsession");
    }

    @RequestMapping("remember")
    public ModelAndView testr(@RequestParam String thoughtParam){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("thought", thoughtParam);
        modelAndView.setViewName("testsession");
                return modelAndView;
    }
}
