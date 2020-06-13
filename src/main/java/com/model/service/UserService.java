package com.model.service;

import java.util.*;

import com.model.Player;
import com.model.User;
import com.model.Villain;

public class UserService {

	static int count = 0;
	// static HashMap<String, Player> players = new HashMap<>();
	public static HashMap<String, User> users = new HashMap<>();

	public static boolean regUser(User userModel) {

		if ((DBService.addUser(userModel.getUsername(), userModel.getEmail(), userModel.getPassword()))) {
			users.put(userModel.getEmail().toLowerCase(), new User(userModel.getEmail().toLowerCase(),
					userModel.getUsername(), userModel.getPassword(), new Player(userModel.getEmail())));
			count++;
			if (DBService.addPlayer(userModel.getEmail())) {
				return true;
			} else
				return false;

		}
		return false;
	}

	public static Player findPlayer(String email) {
		for (Map.Entry<String, User> looper : users.entrySet()) {
			if (looper.getKey().equalsIgnoreCase(email)) {
				return looper.getValue().getPlayer();
			}
		}
		return null;
	}

	public static boolean newVillain(String email, String name, int code) {

		Player player = findPlayer(email);
		Villain villain = player.addNewVillain(name, code);
		if (villain == null) {
			return false;
		} else {
			if (DBService.addVillain(player, villain, code))
				return true;
			else
				return false;
		}
	}

	public static int villainSize(String email) {

		Player player = findPlayer(email);
		return player.villains.size();
	}

	public static User checkLogin(String email, String password) {

		for (Map.Entry<String, User> looper : users.entrySet()) {
			if (looper.getKey().equalsIgnoreCase(email)) {
				if (looper.getValue().getPassword().equals(password)) {
					return looper.getValue();

				}
			}

		}

		return DBService.loginCheck(email, password);
	}

	public static String showList(String email) {
		String list = "";
		Player p = findPlayer(email);

		if (p.villains.size() > 0) {
			list += "The list of Villains are:<br/>";

			for (Map.Entry<Integer, Villain> entry : p.villains.entrySet()) {

				list += " <br/>Code: &ensp;" + entry.getKey() + ". Name: &ensp; " + entry.getValue().getName()
						+ "<br/>";
			}

			return list;
		} else {
			return "You have 0 villains. Add more to use this option.";
		}
	}

	public static String villainAction(String email, int method, String oName, int oKey, String compliment) {

		String status = "";
		Player p = findPlayer(email);
		if (p.villains.size() == 1) {

			for (Map.Entry<Integer, Villain> entry : p.villains.entrySet()) {
				if (method == 1) {
					status += "<br/>Villain Found - " + entry.getValue().getName() + "<br/>";

					status += p.strike(entry.getValue());

					status += "<br/>Health reduced to " + Math.round(entry.getValue().getHealth()) + ".<br/>";
					DBService.updatePlayer(p, entry.getValue(), entry.getKey());
					return status;
				}

				else if (method == 8) {

					if (addCompliment(entry.getValue(), compliment)) {
						status += "<br/>You are my enemy and you're " + compliment + ", Mr."
								+ entry.getValue().getName() + ".<br/><br/>";
						status += p.powerUp(1);
						DBService.updatePlayer(p, entry.getValue(), entry.getKey());

						return status;
					} else {

						return "Compliment already exists! for" + entry.getValue().getName();
					}
				} else if (method == 10)
					return showCompliment(entry.getValue());

			}

		}

		else if (p.villains.size() > 1) {
			String name = oName;
			int key = oKey;
			boolean striker = false;
			for (Map.Entry<Integer, Villain> entry : p.villains.entrySet()) {
				if (key == 0) {
					Villain v = entry.getValue();
					if (v.getName().equals(name)) {
						striker = true;
						if (method == 1) {
							status += "<br/>Villain Found - " + entry.getValue().getName() + "<br/>";

							status += p.strike(entry.getValue());

							status += "<br/>Health reduced to " + Math.round(v.getHealth()) + ".<br/><br/>";
							DBService.updatePlayer(p, entry.getValue(), entry.getKey());

						} else if (method == 8) {
							if (addCompliment(entry.getValue(), compliment)) {
								status += "<br/>You are my enemy and you're " + compliment + ", Mr."
										+ entry.getValue().getName() + ".<br/><br/>";
								status += p.powerUp(1) + ".<br/><br/>";
								DBService.updatePlayer(p, entry.getValue(), entry.getKey());

							} else {
								status += "<br/>Compliment already exists! for" + entry.getValue().getName();
							}
						} else if (method == 10)
							status = showCompliment(entry.getValue());

					}

				} else if (key > 0) {

					if (entry.getKey() == (key)) {
						Villain v = p.villains.get(entry.getKey());
						if (method == 1) {
							status += "<br/>Villain Found - " + entry.getValue().getName() + "<br/>";

							status += p.strike(v);

							status += "<br/>Health reduced to " + Math.round(v.getHealth()) + ".<br/><br/>";

							DBService.updatePlayer(p, entry.getValue(), entry.getKey());
							// System.out.println(status);
							return status;

						} else if (method == 8) {
							if (addCompliment(entry.getValue(), compliment)) {
								status = "<br/>You are my enemy and you're " + compliment + ", Mr."
										+ entry.getValue().getName() + ".<br/><br/>";
								status += p.powerUp(1);

								DBService.updatePlayer(p, entry.getValue(), entry.getKey());

								return status;
							} else {
								return "Compliment already exists! for" + entry.getValue().getName();
							}
						} else if (method == 10)
							return showCompliment(entry.getValue());

					}
				}

			}
			if (striker) {
				return status;
			}

		}
		return "Opponent does not exist";

	}

	public static String villainActionAll(String email, int method) {
		Player p = findPlayer(email);
		String compliment = "";
		String str = "";
		if (p.villains.size() > 0) {
			for (Map.Entry<Integer, Villain> entry : p.villains.entrySet()) {

				if (method == 1) {

					str += "<br/>Villain Found - " + entry.getValue().getName() + "<br/>";
					str += p.strike(entry.getValue());

					str += "<br/>Health reduced to " + entry.getValue().getHealth() + "<br/><br/>";

					DBService.updatePlayer(p, entry.getValue(), entry.getKey());

				}

				else if (method == 8)

				{
					if (addCompliment(entry.getValue(), compliment)) {
						str = p.powerUp(1);
						str += "<br/>You are my enemy and you're " + compliment + ", Mr." + entry.getValue().getName()
								+ ".<br/><br/>";

					}
					str += "Compliment already exists! for" + entry.getValue().getName();

				} else if (method == 11)
					showCompliment(entry.getValue());
				else
					entry.getValue().getSummary();

			}
			return str;
		} else
			return "Opponent does not exist";
	}

	public static String boostEnergy(String email, int method) {

		Player p = findPlayer(email);
		String str = "";
		if (method > 0)
			str = p.powerUp(1);
		else
			str = p.powerUp();

		DBService.updatePlayer(p, null, 0);
		return str;
	}

	public static boolean addCompliment(Villain v, String compliment) {

		return v.setCompliment(compliment.toLowerCase());

	}

	public static String showCompliment(Villain v) {

		return v.getCompliments();
	}

	public static String allSummary(String email) {
		Player p = findPlayer(email);
		String str = "";
		str += "<br/> " + p.getSummary() + "<br/><br/> ";
		str += "<br/><br> Villains: <br/> ";
		for (Map.Entry<Integer, Villain> entry : p.villains.entrySet()) {

			str += "<br/> " + entry.getValue().getSummary() + "<br/><br/> ";
		}
		return str;

	}

}
