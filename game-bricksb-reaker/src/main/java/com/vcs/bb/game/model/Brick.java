package com.vcs.bb.game.model;

public class Brick {

	private BrickType type;
	private int x;
	private int y;
	private int w;
	private int h;

	public Brick(BrickType type, int x, int y, int w, int h) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean isTouching(double bX, double bY, double bW) {
		return (isInside(x, x + w, bX, bX + bW) && isInside(y, y + h, bY, bY + bW))
				|| (isInside(bX, bX + bW, x, x + w) && isInside(bY, bY + bW, y, y + h));
	}

	private boolean isInside(double tBegin, double tEnd, double rangeBegin, double rangeEnd) {
		return isPointInside(tBegin, rangeBegin, rangeEnd) || isPointInside(tEnd, rangeBegin, rangeEnd);
	}

	private boolean isPointInside(double point, double rangeBegin, double rangeEnd) {
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

	public BrickType getType() {
		return type;
	}

	public int getR() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
