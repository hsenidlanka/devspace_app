package hsl.devspace.app.selfcare.source.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {
    @RequestMapping(value = "web-selfcare", method = GET)
    public String home(){
        return "self-care-home";
    }
}
