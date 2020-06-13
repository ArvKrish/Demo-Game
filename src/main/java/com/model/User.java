package com.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	@NotNull()
	@Pattern(regexp = "\\S+", message ="No space")
	@Size(min = 8, max = 18, message = "Should be between 8 and 18 characters")
	private String username;

	@NotNull()
	@Pattern(regexp = "\\S+", message = "No space")
	@Size(min = 8, max = 30, message = "Should be between 8 and 18 characters")
	private String email;

	@NotNull()
	@Pattern(regexp = "\\S+", message = "No space")
	@Size(min = 8, max = 18, message = "Should be between 8 and 18 characters")
	private String password;

	Player player;

	public User(String username, String email, String password, Player player) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.player = player;
		// Keys.players.put(this.email, this.player);
	}

	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
