package com.vcs.fx.canvas.snake.model;

import static com.vcs.fx.canvas.snake.model.Dir.DOWN;
import static com.vcs.fx.canvas.snake.model.Dir.LEFT;
import static com.vcs.fx.canvas.snake.model.Dir.RIGHT;
import static com.vcs.fx.canvas.snake.model.Dir.UP;

public class SnPoint extends Point {

	private Dir direction;
	private Orient orientation;

	public SnPoint(int x, int y, Dir dir, Orient or) {
		super(x, y);
		this.direction = dir;
		this.orientation = or;
	}

	/**
	 * Creating new head for next move
	 */
	public SnPoint(SnPoint oldHhead) {
		this(oldHhead, null);
	}

	/**
	 * Creating new head for next move
	 */
	public SnPoint(SnPoint oldHhead, Dir playerDir) {
		super(oldHhead.x, oldHhead.y);

		playerDir = (playerDir == null ? oldHhead.direction : playerDir);

		x = oldHhead.x;
		y = oldHhead.y;
		direction = playerDir;
		orientation = null;

		switch (playerDir) {
		case UP:
			y--;
			orientation = Orient.UP_DOWN;
			break;
		case DOWN:
			y++;
			orientation = Orient.UP_DOWN;
			break;
		case LEFT:
			x--;
			orientation = Orient.LEFT_RIGHT;
			break;
		case RIGHT:
			x++;
			orientation = Orient.LEFT_RIGHT;
			break;
		}

		// After turn (if it is at all) calculate orientation
		oldHhead.setOrientation(calcOrientationOfOldHead(oldHhead, playerDir));
		oldHhead.setDirection(playerDir);

	}

	private Orient calcOrientationOfOldHead(SnPoint oldHhead, Dir playerDir) {
		Dir old = oldHhead.getDirection();

		if (!playerDir.equals(oldHhead.getDirection())) {

			if (RIGHT.equals(playerDir) && UP.equals(old)) {
				return Orient.DOWN_RIGHT;
			}

			if (RIGHT.equals(playerDir) && DOWN.equals(old)) {
				return Orient.UP_RIGHT;
			}

			if (LEFT.equals(playerDir) && UP.equals(old)) {
				return Orient.DOWN_LEFT;
			}

			if (LEFT.equals(playerDir) && DOWN.equals(old) ) {
				return Orient.UP_LEFT;
			}
			
			
			if (RIGHT.equals(old) && UP.equals(playerDir)) {
				return Orient.UP_LEFT;
			}

			if (RIGHT.equals(old) && DOWN.equals(playerDir)) {
				return Orient.DOWN_LEFT;
			}

			if (LEFT.equals(old) && UP.equals(playerDir)) {
				return Orient.UP_RIGHT;
			}

			if (LEFT.equals(old) && DOWN.equals(playerDir)) {
				return Orient.DOWN_RIGHT;
			}

		} else {
			return oldHhead.orientation;
		}
		throw new RuntimeException();
	}

	public Dir getDirection() {
		return direction;
	}

	public void setDirection(Dir direction) {
		this.direction = direction;
	}

	public Orient getOrientation() {
		return orientation;
	}

	public void setOrientation(Orient orientation) {
		this.orientation = orientation;
	}

}
