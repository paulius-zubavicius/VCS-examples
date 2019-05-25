package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Team;
import com.vcs.fx.game.model.Rectangle;
import javafx.scene.image.Image;

public class EnemySpaceShip extends SpaceShip {

	private static final int UPDATE = 10000000;
	private static final double SWAG_SPEED = 1000000000.0;

	private int shipType;
	private int moveMultiplier;
	private long was;
	private double originalX;

	public EnemySpaceShip(Point pos, Image img, int shipType, int moveMultiplier) {
		super(Team.ENEMY, img, pos.getX(), pos.getY());

		this.shipType = shipType;
		this.moveMultiplier = moveMultiplier;

		originalX = pos.getX();

		//was = Instant.now().toEpochMilli();

	}

	@Override
	public void doPhisics(long now) {

		if (now - was > UPDATE) {
			pos.getPos1().setX(originalX +  (moveMultiplier * Math.sin(now / SWAG_SPEED)));
			pos.getPos2().setX(pos.getPos1().getX() + pos.getWidth());
			was = now;
		}
	}

	public int getShipType() {
		return shipType;
	}

}
