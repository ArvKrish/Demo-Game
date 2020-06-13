package com.control;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.User;
import com.model.service.DBService;
import com.model.service.UserService;

@Controller
public class RegisterController {

	private final Logger logger = Logger.getLogger(RegisterController.class.getName());

	@RequestMapping("/register")
	public String RegisterView(HttpServletRequest request, Model model) {

		User user = new User();
		model.addAttribute("RegMessage", "Register User");
		model.addAttribute("user", user);
		return "RegisterView";
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.GET)
	public String GetRegisterUser(HttpServletRequest request, Model model) {

		model.addAttribute("RegMessage", "Register User");
		return "RegisterView";
	}

	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public String RegisterUser(HttpServletRequest request, @Valid @ModelAttribute("user") User userModel,
			BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "RegisterView";
		} else {

			if (DBService.emailValid(userModel.getEmail()))
				if (UserService.regUser(userModel)) {
					logger.info("User Created and added");
					return "redirect:login";

				} else {
					logger.info("INVALID");

					return "redirect:register";
				}
			else {
				model.addAttribute("RegMessage", "Email Id already exists");
				logger.info("email not valid");
				return "RegisterView";
			}
		}
	}
}
