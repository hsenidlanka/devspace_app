package hsenid.web.Controllers.includes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IncludeFileController {

    @RequestMapping("/profile-info")
    public String sendProfileData(){
        return "/includes/profile-info";
    }

    @RequestMapping("/testcont")
    public Exception testt(){
        return new Exception("Test Exception");
    }

}
