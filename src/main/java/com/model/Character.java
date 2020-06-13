package com.model;

public abstract class Character {

	private String name;
	private double health;
	private double power;
	public void setHealth(double health) {
		this.health = health;
	}

	public void setPower(double power) {
		this.power = power;
	}

	

	public Character(String name) {
		this.name = name;
		this.health = 100;
		this.power = 99;
	}

	public double getHealth() {
		return Math.round(this.health);
	}

	public double getPower() {
		return this.power;
	}

	public String getName() {
		return this.name;
	}

	abstract String strikeUpdate();

	abstract String getSummary();
	
	public void levelChange(int level) {
		this.power=this.getPower()-(5*level);
		if(this.power<1)
			this.power=1; 
	}

	protected void boostHealth(double energy) {
		if (this.getHealth() < 1)
			this.health = 1;
		this.health += this.health * energy;
		if (this.health > 100) {
			this.health = 100;
		}
	}

	protected boolean decreaseHealth(Character c) {
		if (this == c) {
			c.health -= c.getHealth() * (this.getPower() / 200);
		} else {

			c.health -= c.getHealth() * (this.getPower() / 100);
		}

		if (c.health < 1) {
			c.health = 0;
			// System.out.println("\n\t" + c.getName() + "'s Health : " +
			// String.format("%.0f", c.getHealth()));
			return true;
		}
		// System.out.println("\n\t" + c.getName() + "'s Health : " +
		// String.format("%.0f", c.getHealth()));
		return false;
	}

	
}
