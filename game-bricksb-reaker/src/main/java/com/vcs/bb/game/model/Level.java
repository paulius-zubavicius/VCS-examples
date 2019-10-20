package com.vcs.bb.game.model;

import java.util.ArrayList;
import java.util.List;

public class Level {

	private List<Brick> bricks = new ArrayList<>();
	private double speed;
	private String name;

	public List<Brick> getBricks() {
		return bricks;
	}



	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
