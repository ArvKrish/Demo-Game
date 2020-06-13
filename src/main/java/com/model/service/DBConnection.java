
package com.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

	private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

	static Connection getConnection() {
		try {
			String dbDriver = "com.mysql.cj.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost:3306/users?" + "autoReconnect=true&useSSL=false";

			String dbPassword = "password";
			String dbUser = "root";
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			logger.log(Level.SEVERE, "yoyo");
		}
		return null;

	}
}