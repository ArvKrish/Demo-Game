package com.control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.service.UserService;

@Controller
public class GetCompliment {
	private static final Logger logger = Logger.getLogger(GetCompliment.class.getName());

	@RequestMapping("/get-compliment")
	public String ComplimentHandler(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Enumeration<String> en = request.getParameterNames();

			if (en.hasMoreElements()) {

				String compliment = request.getParameter("compliment");

				if (("").equals(compliment.trim())) {
					logger.info("blank");
					return "redirect:actions";
				}
				HttpSession session = request.getSession(false);
				logger.info(session.getId());

				String email = (String) session.getAttribute("email");
				session.setAttribute("Level", UserService.findPlayer(email).getLevel());
				session.setAttribute("compliment", compliment);
				session.setAttribute("Message", "Compliment");
				if (UserService.villainSize(email) == 1) {
					String message = UserService.villainAction(email, 8, "", 0, compliment);
					session.setAttribute("Message", message);
					double playerHealth = UserService.findPlayer(email).getHealth();
					session.setAttribute("Health", playerHealth);
					return "Actions";

				} else {
					return "Opponent";
				}
			} else {
				logger.warning("No parameters- Redirect");
				return "Compliment";
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception " + e);
			return "redirect:/";
		}
	}
}
