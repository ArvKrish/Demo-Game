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
public class GetOpponent {
	private static final Logger logger = Logger.getLogger(GetOpponent.class.getName());

	@RequestMapping("/get-opponent")
	public String Opponent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Enumeration<String> en = request.getParameterNames();
			HttpSession session = request.getSession(false);
			logger.info(session.getId());
			if (en.hasMoreElements()) {

				String action = request.getParameter("submit");

				String email = (String) session.getAttribute("email");
				String method = (String) session.getAttribute("Message");
				// System.out.println(method);
				session.setAttribute("Level", UserService.findPlayer(email).getLevel());
				String str = "";
				if ("value".equalsIgnoreCase(action)) {

					String name = request.getParameter("oname");

					if (("").equals(name.trim())) {
						logger.info("blank");
						return "Opponent";
					}
					if ("strike".equalsIgnoreCase(method)) {
						str = UserService.villainAction(email, 1, name, 0, "");
					} else if ("compliment".equalsIgnoreCase(method)) {

						String compliment = (String) session.getAttribute("compliment");
						str = UserService.villainAction(email, 8, name, 0, compliment);
						double playerHealth = UserService.findPlayer(email).getHealth();
						session.setAttribute("Health", playerHealth);

					} else if ("show Compliment".equalsIgnoreCase(method)) {
						str = UserService.villainAction(email, 10, name, 0, "");
					}
					double playerHealth = UserService.findPlayer(email).getHealth();
					session.setAttribute("Health", playerHealth);

					session.setAttribute("Message", str);

					return "Actions";
				}
				if ("key".equalsIgnoreCase(action)) {

					int key = Integer.parseInt(request.getParameter("key"));

					if (key < 0 || key > 100) {
						logger.info("blank");
						return "Opponent";
					}

					logger.info("key " + key + " - " + method);
					if ("strike".equalsIgnoreCase(method))
						str = UserService.villainAction(email, 1, "", key, "");
					else if ("compliment".equalsIgnoreCase(method)) {

						String compliment = (String) session.getAttribute("compliment");
						str = UserService.villainAction(email, 8, "", key, compliment);
						double playerHealth = UserService.findPlayer(email).getHealth();
						session.setAttribute("Health", playerHealth);
					} else if ("show Compliment".equalsIgnoreCase(method)) {
						str = UserService.villainAction(email, 10, "", key, "");

					}

					double playerHealth = UserService.findPlayer(email).getHealth();
					session.setAttribute("Health", playerHealth);

					session.setAttribute("Message", str);

					return "Actions";
				}
			} else {
				logger.log(Level.WARNING, "No parameter - redirect ");
				return "redirect:actions";
			}
		} catch (Exception e) {
			logger.severe("Exception - " + e);
			return "redirect:/";
		}
		return "redirect:/";	}

}
