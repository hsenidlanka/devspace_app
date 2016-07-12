package hsl.devspace.app.selfcare.controllers;

import hsl.devspace.app.selfcare.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Map model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "loginform";
	}

	@RequestMapping("/loginform")
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Map model) {
		String userName = "UserName";
		String password = "password";
		if (result.hasErrors()) {
			return "loginform";
		}
		loginForm = (LoginForm) model.get("loginForm");
		if (!loginForm.getUserName().equals(userName) || !loginForm.getPassword().equals(password)) {
			return "loginform";
		}
		model.put("loginForm", loginForm);
		return "loginsuccess";
	}

}
