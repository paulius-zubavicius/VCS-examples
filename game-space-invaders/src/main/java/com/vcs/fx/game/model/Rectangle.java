package com.vcs.fx.game.model;

public class Rectangle {

	private Point pos1;
	private Point pos2;

	private double height;
	private double width;

	public Rectangle(Point pos1, double height, double width) {

		this.height = height;
		this.width = width;

		this.pos1 = pos1;
		this.pos2 = new Point(pos1);
		pos2.increaseX(width);
		pos2.increaseY(height);

	}
	
	public boolean isInside(Rectangle p) {

		return p.pos1.getX() >= pos1.getX() && p.pos2.getX() <= pos2.getX() &&
				p.pos1.getY() >= pos1.getY() && p.pos2.getY() <= pos2.getY();
	}

	public boolean isTouching(Rectangle p) {

		boolean xCross1 = pos1.getX() >= p.pos1.getX() && pos1.getX() <= p.pos2.getX();
		boolean xCross2 = pos2.getX() >= p.pos1.getX() && pos2.getX() <= p.pos2.getX();

		boolean yCross1 = pos1.getY() >= p.pos1.getY() && pos1.getY() <= p.pos2.getY();
		boolean yCross2 = pos2.getY() >= p.pos1.getY() && pos2.getY() <= p.pos2.getY();

		return (xCross1 || xCross2) && (yCross1 || yCross2);
	}

	public boolean isInside(Point p) {
		return p.getX() >= pos1.getX() && p.getX() <= pos2.getX() && p.getY() >= pos1.getY() && p.getY() <= pos2.getY();
	}

	public void increaseX(double offset) {
		pos1.increaseX(offset);
		pos2.increaseX(offset);
	}

	public void increaseY(double offset) {
		pos1.increaseY(offset);
		pos2.increaseY(offset);
	}

	public Point getPos1() {
		return pos1;
	}

	public void setPos1(Point pos1) {
		this.pos1 = pos1;
	}

	public Point getPos2() {
		return pos2;
	}

	public void setPos2(Point pos2) {
		this.pos2 = pos2;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

}
