package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Allies;
import com.vcs.fx.game.model.Rectangle;

public class EnemySpaceShip extends SpaceShip {

	private static final int UPDATE = 10000000;
	private static final double SWAG_SPEED = 1000000000.0;

	private int shipType;
	private int moveMultiplier;
	private long was;
	private double originalX;

	public EnemySpaceShip(Rectangle pos, Allies alies, int shipType, int moveMultiplier) {
		super(pos, alies);

		this.shipType = shipType;
		this.moveMultiplier = moveMultiplier;

		originalX = pos.getPos1().getX();
	}

	@Override
	public void doPhisics(long now) {

		if (now - was > UPDATE) {
			pos.getPos1().setX(originalX +  (moveMultiplier * Math.sin(now / SWAG_SPEED)));
			pos.getPos2().setX(pos.getPos1().getX() + pos.getWeight());
			was = now;
		}

	}

	public int getShipType() {
		return shipType;
	}

}
