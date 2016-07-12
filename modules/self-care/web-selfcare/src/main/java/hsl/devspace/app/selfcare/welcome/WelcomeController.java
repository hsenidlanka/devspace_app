/*
 *   (C) Copyright 2009-2010 hSenid Software International (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Software International (Pvt) Limited.
 *
 *   hSenid Software International (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 */
package hsl.devspace.app.selfcare.welcome;

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

//    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    private static final String view = "welcome";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView initLogin(Model model) throws Exception {
        WelcomeForm welcomeForm = new WelcomeForm();
        model.addAttribute("welcomeForm", welcomeForm);
        return new ModelAndView(view, "model", model);
    }


}