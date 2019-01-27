package com.vcs.lects.l18.fx.game.model;

public class Rectangle {

	private Point pos1;
	private Point pos2;

	private int height;
	private int weight;

	public Rectangle(Point pos1, int height, int weight) {

		this.height = height;
		this.weight = weight;

		this.pos1 = pos1;
		this.pos2 = new Point(pos1);
		pos2.increaseX(weight);
		pos2.increaseY(height);

	}
	
	public boolean isInside(Rectangle p) {
		
		return p.pos1.getX() >= pos1.getX() && p.pos2.getX() <= pos2.getX() && 
				p.pos1.getY() >= pos1.getY() && p.pos2.getY() <= pos2.getY();
	}

	public boolean isInside(Point p) {
		return p.getX() >= pos1.getX() && p.getX() <= pos2.getX() && p.getY() >= pos1.getY() && p.getY() <= pos2.getY();
	}

	public void increaseX(int offset) {
		pos1.increaseX(offset);
		pos2.increaseX(offset);
	}

	public void increaseY(int offset) {
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
