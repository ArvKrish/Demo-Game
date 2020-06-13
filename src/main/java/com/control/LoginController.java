package com.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.User;
import com.model.service.UserService;

@Controller
public class LoginController {
	private final Logger logger = Logger.getLogger(LoginController.class.getName());

	@RequestMapping(value = "/login-user", method = RequestMethod.POST)
	public String loginProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if(("").equals(email.trim()) || ("").equals(password.trim()) ) {
				logger.info("blank");
				return "LoginView";
			}
			User user = UserService.checkLogin(email, password);
			if (user == null) {
				logger.info("Login fail");
				return "LoginView";
			} else {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}

				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(300);
				logger.info(session.getId());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("User", user.getPlayer().getName());
				session.setAttribute("Health", user.getPlayer().getHealth());
				session.setAttribute("Level", user.getPlayer().getLevel());
				session.setAttribute("villainSize", UserService.villainSize(email));
				session.setAttribute("message", "");
				logger.info("Login Success");
				return "NewOpponent";
			}
		} catch (Exception e) {
			logger.severe("Exception - " + e);
			return "redirect:/";
		}
		
	}

	@RequestMapping(value = "/login-User", method = RequestMethod.GET)
	public String GetLogin() {
		return "LoginView";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);
		return "LoginView";
	}

}