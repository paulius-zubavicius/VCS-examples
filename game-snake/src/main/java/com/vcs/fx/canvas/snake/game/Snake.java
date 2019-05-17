package com.vcs.fx.canvas.snake.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vcs.fx.canvas.snake.model.Dir;
import com.vcs.fx.canvas.snake.model.Orient;
import com.vcs.fx.canvas.snake.model.Point;
import com.vcs.fx.canvas.snake.model.SnPoint;

public class Snake {

	private static final int START_LEVEL = 1;
	private static final int MAX_LEVEL = 30;

	private List<SnPoint> snPoints = new ArrayList<>();
	private int width = 0;
	private int height = 0;
	private Point apple;
	private Random rnd = new Random();
	private int level = START_LEVEL;

	public Snake(int width, int height) {

		this.width = width;
		this.height = height;
		reset();
	}

	public void reset() {
		snPoints.clear();
		snPoints.add(new SnPoint(0, 0, Dir.RIGHT, Orient.LEFT_RIGHT));
		snPoints.add(new SnPoint(1, 0, Dir.RIGHT, Orient.LEFT_RIGHT));
		snPoints.add(new SnPoint(2, 0, Dir.RIGHT, Orient.LEFT_RIGHT));
		level = START_LEVEL;
		dropApple();
	}

	public List<SnPoint> getSnPoints() {
		return snPoints;
	}

	public Point dropApple() {

		Point r = null;
		boolean collisionFail = true;

		do {
			collisionFail = false;
			r = new Point(rnd.nextInt(width), rnd.nextInt(height));

			for (SnPoint snPoint : snPoints) {
				if (snPoint.equals(r)) {
					collisionFail = true;
					break;
				}
			}

		} while (collisionFail);

		apple = r;

		return r;
	}

	public Point getApple() {
		return apple;
	}

	public boolean move() {
		return move(null);
	}

	public int getLevel() {
		return level;
	}

	public int getLevelSpeed() {
		return level / MAX_LEVEL;
	}

	public boolean move(Dir playerDirection) {

		// If valid user direction
		if (playerDirection != null && checkDirection(playerDirection)) {
			snPoints.add(new SnPoint(getHead(), playerDirection));
		} else {
			snPoints.add(new SnPoint(getHead()));
		}

		if (collisionFail(getHead())) {
			return false;
		}

		if (getHead().equals(apple)) {
			nextLevel();
			dropApple();
		} else {
			snPoints.remove(0);
		}

		return true;
	}

	private void nextLevel() {
		level = (level >= MAX_LEVEL ? MAX_LEVEL : level + 1);
	}

	private SnPoint getHead() {
		return snPoints.get(snPoints.size() - 1);
	}

	private boolean checkDirection(Dir changeDirection) {

		Dir headDirection = getHead().getDirection();

		if (Dir.UP.equals(changeDirection) || Dir.DOWN.equals(changeDirection)) {
			return Dir.LEFT.equals(headDirection) || Dir.RIGHT.equals(headDirection);
		}
		if (Dir.LEFT.equals(changeDirection) || Dir.RIGHT.equals(changeDirection)) {
			return Dir.DOWN.equals(headDirection) || Dir.UP.equals(headDirection);
		}
		throw new RuntimeException("" + changeDirection);
	}

	private boolean collisionFail(SnPoint head) {
		if (head.getX() < 0 || head.getY() < 0 || head.getX() >= width || head.getY() >= height) {
			return true;
		}

		for (int i = 0; i < snPoints.size() - 1; i++) {
			if (head.equals(snPoints.get(i))) {
				return true;
			}
		}

		return false;
	}
}
