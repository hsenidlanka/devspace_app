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
package hsl.devspace.app.selfcare.login;

import hsl.devspace.app.selfcare.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
@SessionAttributes("loginForm")
public class LoginController {

//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String view = "login";
    @Autowired
    private LoginValidator loginValidator;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView initLogin(Model model) throws Exception {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return new ModelAndView(view, "model", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult result) throws Exception {
        loginValidator.validate(loginForm, result);
        if (!result.hasErrors()) {
            if (authenticationService.authenticate(loginForm)) {
                return new ModelAndView("redirect:/welcome.html");
            }
            return new ModelAndView(view);
        }
        return new ModelAndView(view);
    }
}