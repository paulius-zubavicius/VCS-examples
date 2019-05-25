package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Team;
import com.vcs.fx.game.model.Rectangle;

import java.time.Instant;

public class EnemySpaceShip extends SpaceShip {

	private static final int UPDATE = 10000000;
	private static final double SWAG_SPEED = 1000000000.0;



	private int shipType;
	private int moveMultiplier;
	private long was;
	private long wasLastShot;

	private double originalX;

	public EnemySpaceShip(Rectangle pos, Team team, int shipType, int moveMultiplier) {
		super(pos, team);

		this.shipType = shipType;
		this.moveMultiplier = moveMultiplier;

		originalX = pos.getPos1().getX();

		//was = Instant.now().toEpochMilli();

	}

	@Override
	public void doPhisics(long now) {

		if (now - was > UPDATE) {
			pos.getPos1().setX(originalX +  (moveMultiplier * Math.sin(now / SWAG_SPEED)));
			pos.getPos2().setX(pos.getPos1().getX() + pos.getWeight());
			was = now;
		}


	}

	public long getWasLastShot() {
		return wasLastShot;
	}

	public void setWasLastShot(long wasLastShot) {
		this.wasLastShot = wasLastShot;
	}

	public int getShipType() {
		return shipType;
	}

}
