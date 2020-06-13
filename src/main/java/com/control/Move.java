package com.control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.service.UserService;

@Controller
public class Move {
	private final Logger logger = Logger.getLogger(Move.class.getName());

	@RequestMapping("/move")
	public String Mover(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {

			Enumeration<String> en = request.getParameterNames();

			if (en.hasMoreElements()) {
				HttpSession session = request.getSession(false);
				logger.info(session.getId());

				String email = (String) session.getAttribute("email");
				session.setAttribute("Level", UserService.findPlayer(email).getLevel());
				String name = request.getParameter("Action");

				if ("strike".equalsIgnoreCase(name)) {

					if (UserService.villainSize(email) < 2) {
						String message = UserService.villainAction(email, 1, "", 0, "");

						double playerHealth = UserService.findPlayer(email).getHealth();
						session.setAttribute("Health", playerHealth);
						session.setAttribute("Message", message);
						return "Actions";
					} else {
						String message = "strike";
						session.setAttribute("Message", message);

						return "Opponent";
					}

				}

				else if ("strikeAll".equalsIgnoreCase(name)) {

					String message = UserService.villainActionAll(email, 1);
					session.setAttribute("Message", message);

					double playerHealth = UserService.findPlayer(email).getHealth();
					session.setAttribute("Health", playerHealth);
					return "Actions";
				}

				else if ("addVillain".equalsIgnoreCase(name)) {
					String message = "";
					session.setAttribute("Message", message);
					return "NewOpponent";
				} else if ("boost".equalsIgnoreCase(name)) {
					String message = UserService.boostEnergy(email, 0);

					double playerHealth = UserService.findPlayer(email).getHealth();
					session.setAttribute("Health", playerHealth);
					logger.info(email + " - " + name + " - " + message);
					session.setAttribute("Message", message);
					return "Actions";
				} else if ("showvillain".equalsIgnoreCase(name)) {
					String message = UserService.showList(email);
					session.setAttribute("Message", message);
					logger.info(email + " - " + name + " - " + message);
					return "Actions";
				} else if ("compliment".equalsIgnoreCase(name)) {
					String message = "Compliment";
					session.setAttribute("Message", message);
					logger.info(email + " - " + name + " - " + message);
					return "Compliment";
				} else if ("showcompliment".equalsIgnoreCase(name)) {
					String message = "Show Compliment";
					session.setAttribute("Message", message);
					logger.info(email + " - " + name + " - " + message);
					if (UserService.villainSize(email) < 2) {
						message = UserService.villainAction(email, 10, "", 0, "");
						session.setAttribute("Message", message);
						return "Actions";
					} else {
						return "Opponent";
					}
				} else if ("summary".equalsIgnoreCase(name)) {
					String message = UserService.allSummary(email);
					session.setAttribute("Message", message);
					logger.info(email + " - " + name + " - " + message);
					return "Actions";
				} else if ("exit".equalsIgnoreCase(name)) {
					String message = "";
					session.setAttribute("Message", message);
					session.invalidate();

					logger.info(email + " - " + name);
					return "redirect:/";
				}

			}

			else {
				logger.warning("No parameters- Redirect");
				return "redirect:actions";
			}
		} catch (Exception e) {
			logger.severe("Exception - " + e);
			return "redirect:/";
		}
		return "redirect:actions";
	}
}
