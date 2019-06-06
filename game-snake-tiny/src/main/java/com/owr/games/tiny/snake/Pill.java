package com.owr.games.tiny.snake;

public class Pill {

    private Pill nextPill;

    private int x;
    private int y;


    public Pill( int x, int y, Pill nextPill) {
        this.nextPill = nextPill;
        this.x = x;
        this.y = y;
    }

    public void move(int coordX, int coordY) {
        if (nextPill != null) {
            nextPill.move(x, y);
        }
        x = coordX;
        y = coordY;
    }


    public boolean isEatingItSelf() {
        return (nextPill != null ? nextPill.isItOnSnake(x, y) : false);
    }

    public int getSize(int counter) {
        return (nextPill != null ? nextPill.getSize(counter + 1) : counter);
    }

    public boolean isItOnSnake(int pointX, int pointY) {
        return (x == pointX && y == pointY) || (nextPill != null ? nextPill.isItOnSnake(pointX, pointY) : false);
    }

    public Pill getNextPill() {
        return nextPill;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
