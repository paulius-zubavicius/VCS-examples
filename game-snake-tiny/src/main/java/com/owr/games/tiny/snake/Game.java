package com.owr.games.tiny.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class Game {

    public static final int RECT_SIZE = 20;
    public static final int ROUND = 6;
    public static final int DELAY = 800;
    public static final int TICS = 5;
    private Snake snake;
    private Map<Integer, Integer> delays;

    private int delayByLevel(int level) {
        switch (level) {
            case 0:
            case 1:
                return 1000;
            case 2:
            case 3:
                return 800;
            case 4:
            case 5:
                return 700;
            case 6:
            case 7:
                return 600;
            case 8:
            case 9:
                return 500;
            case 10:
            case 11:
                return 400;
            case 12:
            case 13:
                return 300;
            case 14:
            case 15:
                return 250;
            case 16:
            case 17:
                return 200;
            case 18:
            case 19:
                return 180;
            default:
                return 180 - level;
        }
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Game().createAndShowGUI();
            }
        });

    }

    private void createAndShowGUI() {

        snake = new Snake();

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (KeyEvent.VK_W == keyEvent.getKeyCode()) snake.direction('U');
                if (KeyEvent.VK_S == keyEvent.getKeyCode()) snake.direction('D');
                if (KeyEvent.VK_A == keyEvent.getKeyCode()) snake.direction('L');
                if (KeyEvent.VK_D == keyEvent.getKeyCode()) snake.direction('R');
                if (KeyEvent.VK_ESCAPE == keyEvent.getKeyCode()) System.exit(0);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });

        JPanel pan = new GameBoard(snake);
        f.add(pan);

        f.setSize(RECT_SIZE * Snake.SIZE, RECT_SIZE * Snake.SIZE);
        f.setLayout(null);
        f.setType(Window.Type.UTILITY);
        f.setVisible(true);


        new Thread() {
            @Override
            public void run() {

                while (true) {
                    pan.repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();

        new Thread() {
            @Override
            public void run() {

                JOptionPane.showMessageDialog(f, "Ready?");

                boolean canContinue = true;
                int level = 0;

                while (true) {

                    try {
                        Thread.sleep(delayByLevel(level));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    level = snake.moveOneStep();
                    canContinue = snake.isStillAlive();

                    f.setTitle("Level: " + level);
                    if (!canContinue) {
                        int opt = JOptionPane.showOptionDialog(f, "Reset the game?", "Game is over", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                        if (opt == 0) {
                            canContinue = true;
                            snake.reset();
                        } else {
                            System.exit(0);
                        }

                    }

                }
            }

        }.start();
    }


}


class GameBoard extends JPanel {

    private Snake snake;

    public GameBoard(Snake snake) {
        this.snake = snake;
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBounds(0, 0, Game.RECT_SIZE * Snake.SIZE, Game.RECT_SIZE * Snake.SIZE);

    }

    public Dimension getPreferredSize() {
        return new Dimension(Game.RECT_SIZE * Snake.SIZE, Game.RECT_SIZE * Snake.SIZE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Pill h = snake.getHead();

        g.setColor(Color.BLACK);
        g.fillRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, Game.ROUND, Game.ROUND);
        g.setColor(Color.BLACK);
        g.drawRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, Game.ROUND, Game.ROUND);


        h = h.getNextPill();
        int c = 1;
        while (h != null) {

            g.setColor(Color.GREEN);
            g.fillRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, Game.ROUND, Game.ROUND);
            g.setColor(Color.BLACK);
            g.drawRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, Game.ROUND, Game.ROUND);
            h = h.getNextPill();
        }

        g.setColor(Color.RED);
        g.fillRoundRect(snake.getAppX() * Game.RECT_SIZE, snake.getAppY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, Game.ROUND, Game.ROUND);
        g.setColor(Color.BLACK);
        g.drawRoundRect(snake.getAppX() * Game.RECT_SIZE, snake.getAppY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, Game.ROUND, Game.ROUND);

    }
}
