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

    public void move(int xOffset, int yOffset) {

        if (!(xOffset != 0 ^ yOffset != 0 )) return;
        xOffset += (xOffset > 0 ? 1 : 0) + (xOffset < 0 ? -1 : 0);
        yOffset += (yOffset > 0 ? 1 : 0) + (yOffset < 0 ? -1 : 0);

        if (nextPill != null) {
//            nextPill.x = x;
//            nextPill.y = y;
            nextPill.moveToParentPos(x, y);
        }

        x += xOffset;
        y += yOffset;


    }

    private void moveToParentPos(int parentX, int parentY) {
        if (nextPill != null) {
            nextPill.move(x, y);
        }

        x = parentX;
        y = parentY;
    }

    public boolean isEatingItSelf() {
        return isItOnSnake(x, y);
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
