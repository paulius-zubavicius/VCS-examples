package com.owr.games.tiny.snake;


import java.util.Random;

public class Snake {

    public static final int SIZE = 20;

    private Pill head;
    private char dir = 'R';

    private int appX;
    private int appY;

    public Snake() {
        head = new Pill(3, 0, new Pill(2, 0, new Pill(1, 0, null)));
        addNewApple();
    }

    private void addNewApple() {
        do {
            appX = new Random().nextInt(SIZE);
            appY = new Random().nextInt(SIZE);
        } while (head.isItOnSnake(appX, appY));
    }

    public void direction(char userDir) {
        if (userDir == 'R' && (dir == 'U' || dir == 'D')) dir = 'R';
        if (userDir == 'L' && (dir == 'U' || dir == 'D')) dir = 'L';
        if (userDir == 'U' && (dir == 'R' || dir == 'L')) dir = 'U';
        if (userDir == 'D' && (dir == 'R' || dir == 'L')) dir = 'D';
    }

    public boolean moveOneStep() {

        if (dir == 'R') head.move(1, 0);
        if (dir == 'L') head.move(-1, 0);
        if (dir == 'U') head.move(0, -1);
        if (dir == 'D') head.move(0, 1);

        if (head.getX() >= SIZE - 1 || head.getX() < 0) return false;
        if (head.getY() >= SIZE - 1 || head.getY() < 0) return false;
        if (head.isEatingItSelf()) return false;

        if (head.isItOnSnake(appX, appY)) {
            head = new Pill(appX, appY, head );
            addNewApple();
        }

        return true;
    }

    public Pill getHead() {
        return head;
    }

    public int getAppX() {
        return appX;
    }

    public int getAppY() {
        return appY;
    }
}
