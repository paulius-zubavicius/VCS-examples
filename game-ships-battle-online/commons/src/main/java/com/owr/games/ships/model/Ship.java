package com.owr.games.ships.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private Point p1;
    private Point p2;

    public Ship() {

    }

    public Ship(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "[" + "p1=" + p1 + ", p2=" + p2 + ']';
    }

    public List<Point> convertToPoints() {
        List<Point> points = new ArrayList<>();

        if (p1.getX() == p2.getX()) {

            int minY = Math.min(p1.getY(), p2.getY());
            int maxY = Math.max(p1.getY(), p2.getY());

            for (int y = minY; y <= maxY; y++) {
                points.add(new Point(p1.getX(), y));
            }
            return points;
        }

        if (p1.getY() == p2.getY()) {

            int minX = Math.min(p1.getX(), p2.getX());
            int maxX = Math.max(p1.getX(), p2.getX());

            for (int x = minX; x <= maxX; x++) {
                points.add(new Point(x, p1.getY()));
            }
            return points;
        }

        throw new RuntimeException();
    }

}
