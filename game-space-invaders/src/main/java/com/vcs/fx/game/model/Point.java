package com.vcs.fx.game.model;

public class Point {

    private double x;
    private double y;

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void increaseX(double x) {
        this.x += x;
    }

    public void increaseY(double y) {
        this.y += y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point move(double yOffset) {
        y += yOffset;
        return this;
    }

}
