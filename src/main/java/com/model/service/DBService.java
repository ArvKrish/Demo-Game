package com.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;


import com.model.Player;
import com.model.User;
import com.model.Villain;

public class DBService {
	static final Logger logger = Logger.getLogger(DBService.class.getName());

	public static boolean addPlayer(String email) {
		try {
			Player player = UserService.findPlayer(email);
			Connection connection = DBConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement("insert into players values(?,?,?,?,?)");
			stmt.setString(1, player.getName());
			stmt.setDouble(2, player.getPower());
			stmt.setDouble(3, player.getHealth());
			stmt.setInt(4, player.getLevel());
			stmt.setInt(5, player.getKillCount());
			System.out.println(stmt.executeUpdate() + " records changed");
			return true;
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Exception occured - DB FAIL");
			return false;
		}

	}

	public static boolean addVillain(Player player, Villain villain, int code) {
		try {

			Connection connection = DBConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement("insert into villains values(?,?,?,?,?)");
			stmt.setString(1, player.getName());
			stmt.setString(2, villain.getName());
			stmt.setInt(3, code);
			stmt.setDouble(4, villain.getHealth());

			stmt.setString(5, player.getName() + code);
			System.out.println(stmt.executeUpdate() + " records changed");

			return true;
		} catch (Exception e) {

			logger.log(Level.SEVERE, "Exception occured - DB FAIL");
			return false;
	
		}
	}

	public static boolean addUser(String username, String email, String password) {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement("insert into users values(?,?,?)");
			stmt.setString(1, username);

			stmt.setString(2, email);
			stmt.setString(3, password);
			System.out.println(stmt.executeUpdate() + " records changed");
			return true;
		} catch (SQLException e) {

			logger.log(Level.SEVERE, "Exception occured - DB FAIL");
			return false;
		}
	}

	public static boolean emailValid(String email) {

		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("Select * from users where email=?");
			stmt.setString(1, email);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				return false;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception occured - DB FAIL");
			return false;
		}

		return true;

	}

	public static User loginCheck(String email, String password) {
		Connection connection = DBConnection.getConnection();
		try {

			PreparedStatement stmt = connection.prepareStatement("Select * from users where email=? and password=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				PreparedStatement st = connection.prepareStatement("Select * from players where username=?");
				st.setString(1, email);
				ResultSet set1 = st.executeQuery();
				while (set1.next()) {
					Player p = new Player(set1.getString(1), set1.getInt(2), set1.getInt(3), set1.getInt(4),
							set1.getInt(5));
					User user = new User(set.getString(1), set.getString(2), set.getString(3), p);
					UserService.users.put(email, user);
					getVillains(email);

					return user;
				}
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception occured - DB FAIL");
			}
		return null;
	}

	public static void getVillains(String email) {
		Player player = UserService.findPlayer(email);
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("Select * from villains where username=?");
			stmt.setString(1, email);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				player.villains.put(set.getInt(3), new Villain(set.getString(2).toString(), set.getInt(4)));
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception occured - DB FAIL");
			}

	}

	public static void updatePlayer(Player player, Villain villain, int code) {

		Connection connection = DBConnection.getConnection();
		try {

			PreparedStatement stmt = connection
					.prepareStatement("Update players set power=?, health=?,level=?,killcount=? where username=?");
			stmt.setString(5, player.getName());
			stmt.setDouble(1, player.getPower());
			stmt.setDouble(2, player.getHealth());
			stmt.setInt(3, player.getLevel());
			stmt.setInt(4, player.getKillCount());
			stmt.executeUpdate();

			if (villain != null) {

				PreparedStatement stmt2 = connection.prepareStatement("Update villains set health=? where code=?");

				stmt2.setDouble(1, villain.getHealth());
				stmt2.setInt(2, code);
				stmt2.executeUpdate();

			}

		} catch (Exception e) {

			logger.log(Level.SEVERE, "Exception occured - DB FAIL");
			
		}
	}

}
