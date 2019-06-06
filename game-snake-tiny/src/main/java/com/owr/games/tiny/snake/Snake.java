package com.owr.games.tiny.snake;


import java.util.Random;

public class Snake {

    public static final int SIZE = 15;

    private static final char DIR = 'R';
    private Pill head;
    private char dir = DIR;

    private int appX;
    private int appY;

    public Snake() {
        reset();
    }

    public void reset() {
        head = new Pill(3, 0, null);
        dir = DIR;
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

    public int moveOneStep() {

        int fHeadPosX = head.getX();
        int fHeadPosY = head.getY();

        if (dir == 'R') fHeadPosX++;
        if (dir == 'L') fHeadPosX--;
        if (dir == 'U') fHeadPosY--;
        if (dir == 'D') fHeadPosY++;

        if (fHeadPosX== appX && fHeadPosY== appY) {
            head = new Pill(appX, appY, head);
            addNewApple();
        } else {
            head.move(fHeadPosX, fHeadPosY);
        }

        return head.getSize(0);//level
    }

    public boolean isStillAlive() {
        if (head.getX() >= SIZE || head.getX() < 0) return false;
        if (head.getY() >= SIZE || head.getY() < 0) return false;
        if (head.isEatingItSelf()) return false;

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
