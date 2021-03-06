package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.*;
import javafx.scene.image.Image;

import java.util.Collection;

public class PlayerSpaceShip extends SpaceShip {


	/*
	* Ratio of (Pythagorean theorem):  1.0 / sqrt( 1.0^2 + 1.0^2 )
	* */
	private static final double DIAGONAL_SPEED = 1.0 / 1.414213562;
	private static final double SHIP_SPEED = 1.0;

	private final Rectangle moveBounds;
	private MoveDirection direction;


	public PlayerSpaceShip(Point point, Image img , Rectangle moveBounds) {
		super(Team.ALIAS, img, point.getX(), point.getY());
		this.moveBounds = moveBounds;
		this.img = img;
	}

	public void move(MoveDirection direction) {
		this.direction = direction;
	}


	@Override
	public void doPhisics(long now) {



		switch (direction) {
		case UP:
			boundsCheckAndMove(0, -SHIP_SPEED);
			return;
		case UP_LEFT:
			boundsCheckAndMove(-DIAGONAL_SPEED, -DIAGONAL_SPEED);
			return;
		case UP_RIGHT:
			boundsCheckAndMove(DIAGONAL_SPEED, -DIAGONAL_SPEED);
			return;
		case DOWN:
			boundsCheckAndMove(0, SHIP_SPEED);
			return;
		case DOWN_LEFT:
			boundsCheckAndMove(-DIAGONAL_SPEED, DIAGONAL_SPEED);
			return;
		case DOWN_RIGHT:
			boundsCheckAndMove(DIAGONAL_SPEED, DIAGONAL_SPEED);
			return;
		case LEFT:
			boundsCheckAndMove(-SHIP_SPEED, 0);
			return;
		case RIGHT:
			boundsCheckAndMove(SHIP_SPEED, 0);
			return;
		default:
			return;
		}
	}




	private void boundsCheckAndMove(double xOffset, double yOffset) {

		pos.increaseX(xOffset);
		pos.increaseY(yOffset);

		if (moveBounds != null && !moveBounds.isInside(pos)) {
			pos.increaseX(-xOffset);
			pos.increaseY(-yOffset);
		}

	}

}
