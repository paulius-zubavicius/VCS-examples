package com.vcs.fx.game.model;

public class Shoot {

	private Allies alies;
	// private int energy;
	private Point position;
	private int speed;

	public Shoot(Allies alies, Point position, int speed) {
		this.alies = alies;
		this.position = position;
		this.speed = speed;
	}

	public Allies getAlies() {
		return alies;
	}

	public void setAlies(Allies alies) {
		this.alies = alies;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
