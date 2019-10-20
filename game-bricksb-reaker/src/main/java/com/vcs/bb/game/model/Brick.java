package com.vcs.bb.game.model;

public class Brick {

	private int x;
	private int y;
	private int w;
	private int h;

	public Brick(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean isTouching(int bX, int bY, int bW) {
		return (isInside(x, x + w, bX, bX + bW) && isInside(y, y + h, bY, bY + bW))
				|| (isInside(bX, bX + bW, x, x + w) && isInside(bY, bY + bW, y, y + h));
	}

	private boolean isInside(int tBegin, int tEnd, int rangeBegin, int rangeEnd) {
		return isPointInside(tBegin, rangeBegin, rangeEnd) || isPointInside(tEnd, rangeBegin, rangeEnd);
	}

	private boolean isPointInside(int point, int rangeBegin, int rangeEnd) {
		return point >= rangeBegin && point <= rangeEnd;
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

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

}
