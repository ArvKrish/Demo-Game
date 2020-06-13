package com.model;

import java.util.*;

public class Player extends Character {

	public HashMap<Integer, Villain> villains = new HashMap<>();
	private HashSet<String> attributes = new HashSet<>();
	private int level;
	private int killCount;

	public int getKillCount() {
		return killCount;
	}

	public void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Player(String name, double power, int health, int level, int killcount) {
		super(name);
		setHealth(health);
		setPower(power);
		setLevel(level);
		setKillCount(killcount);
	}

	public Player(String name) {
		super(name);
		setLevel(1);
		setKillCount(0);

	}

	public void setAttributes(String attr) {
		attributes.add(attr);
	}

	public Villain addNewVillain(String name, int code) {
		if (villains.containsKey(code))
			return null;
		else {
			Villain villain = new Villain(name);
			villains.put(code, villain);
			return villain;
		}
	}

	public String getAttributes() {
		String allAttribute = "The Attributes of " + this.getName() + " are:\n";
		for (String attr : attributes) {
			allAttribute += attr + "<br/>";
		}
		return allAttribute;
	}

	public String strike(Villain v) {
		boolean healthChecker = (this.getHealth() - (this.getHealth() * (this.getPower() / 200))) > 0 ? true : false;
		if (healthChecker) {

			if (v.getHealth() > 0) {
				decreaseHealth(this);

				boolean deathChecker = decreaseHealth(v);

				if (deathChecker) {
					killUpdate();
					return "<br/>K.O.<br/>";

				} else {
					return strikeUpdate();
				}
			} else
				return "<br/>Opponent is finished<br/>";
		} else {
			return "<br/>Energy too low<br/>Please use powerups<br/>";
		}

	}

	public void levelUpdate() {
		setLevel(getLevel() + 1);
		levelChange(getLevel());

	}

	public void killUpdate() {

		setKillCount(this.getKillCount() + 1);
		if (getKillCount() % 5 == 0) {
			levelUpdate();
		}
	}

	public String strikeUpdate() {

		return "<br/> Whaat a strike!<br/>";
	}

	public String powerUp() {
		this.boostHealth(.10);
		return "<br>Drinking Boost<br/>Restoring health:\t" + this.getHealth();
	}

	public String powerUp(int energy) {
		this.boostHealth(.25);
		return "<br>Drinking Boost\nRestoring health:\t" + this.getHealth();
	}

	@Override
	public String toString() {
		return "<br>Player :" + this.getName() + "<br>Health :" + this.getHealth() + "<br>Level :" + this.getLevel();
	}

	public String getSummary() {
		return this.toString();
	}
}
