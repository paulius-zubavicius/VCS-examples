package com.vcs.fx.canvas.snake.model;

public class Point {

	protected int x;
	protected int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Point)) {
			return false;
		}

		Point p = (Point) obj;

		return x == p.x && y == p.y;

	}

	@Override
	public int hashCode() {
		return x * 13 + y * 7;
	}

}
