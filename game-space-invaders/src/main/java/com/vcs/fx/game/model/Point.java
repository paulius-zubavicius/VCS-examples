package com.vcs.fx.game.model;

public class Point {

    private int x;
    private int y;

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void increaseX(int x) {
        this.x += x;
    }

    public void increaseY(int y) {
        this.y += y;
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

    public Point move(int yOffset) {
        y += yOffset;
        return this;
    }

}
