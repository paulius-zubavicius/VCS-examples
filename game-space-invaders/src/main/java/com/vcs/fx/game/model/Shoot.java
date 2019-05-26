package com.vcs.fx.game.model;

import javafx.scene.paint.Color;

public class Shoot {

	private Team team;
	private double energy;
	private Point position;
	private double speedDeltaX;
    private double speedDeltaY;
//	private EnergyType type;


	public Shoot(Team team, double energy, double startX, double startY, double speedDeltaX, double speedDeltaY) {
		this.team = team;
		this.position = new Point(startX, startY);
		this.speedDeltaX = speedDeltaX;
        this.speedDeltaY = speedDeltaY;
		this.energy = energy;
	}

	public void doPhisics() {

		position.increaseX(speedDeltaX);
		position.increaseY(speedDeltaY);
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

	public double getSpeedDeltaY() {
		return speedDeltaY;
	}

	public void setSpeedDeltaY(double speedDeltaY) {
		this.speedDeltaY = speedDeltaY;
	}

    public double getSpeedDeltaX() {
        return speedDeltaX;
    }

    public void setSpeedDeltaX(double speedDeltaX) {
        this.speedDeltaX = speedDeltaX;
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
