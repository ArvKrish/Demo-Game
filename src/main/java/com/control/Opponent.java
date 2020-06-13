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
public class Opponent {

	private static Logger logger = Logger.getLogger(Opponent.class.getName());

	@RequestMapping("/actions")
	public String actionsGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
		
				return "Actions";
			

		} catch (Exception e) {
			logger.severe("Exception  ll " + e);
			return "redirect:/";
		}

	}

	@RequestMapping("/action")
	public String newOpponent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Enumeration<String> en = request.getParameterNames();

			if (en.hasMoreElements()) {
				HttpSession session = request.getSession(false);

				logger.info(session.getId());

				String email = (String) (session.getAttribute("email"));
				String name = (String) (request.getParameter("oname"));
				
				if(("").equals(name.trim()) ) {
					logger.info("blank");
					return "NewOpponent";
				}
				int key = Integer.parseInt(request.getParameter("key"));
				boolean check = UserService.newVillain(email, name, key);
				session.setAttribute("Level", UserService.findPlayer(email).getLevel());
				if (check) {
					session.setAttribute("Message", "New Villain added");
					logger.info(email + " - " + "New Villain added");
				} else {
					session.setAttribute("Message", "Duplicate code. Try again!");
					logger.info(email + " - " + "Duplicate code. Try again!");
				}
				session.setAttribute("villainSize", UserService.villainSize(email));

				return "Actions";
			} else {
				logger.info("No parameters- Redirect");

				return "redirect:actions";
			}
		} catch (Exception e) {
			logger.severe("Exception " + e);
			return "return:/";
		}
	
	}

}
