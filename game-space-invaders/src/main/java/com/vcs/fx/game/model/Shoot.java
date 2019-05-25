package com.vcs.fx.game.model;

import javafx.scene.paint.Color;

public class Shoot {

	private Team team;
	private double energy;
	private Point position;
	private double speed;
//	private EnergyType type;


//	public Shoot(Team team, Point position, double speed, double energy) {
//		this.team = team;
//		this.position = new Point(position);
//		this.speed = speed;
//		this.energy = energy;
//	}

	public Shoot(Team team, double startX, double startY, double speed, double energy) {
		this.team = team;
		this.position = new Point(startX, startY);
		this.speed = speed;
		this.energy = energy;
	}

	public Team getTeam() {
		return team;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public boolean isItEnergy() {
		return energy > 0;
	}
}
