package com.owr.games.tiny.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {

    public static final int RECT_SIZE = 20;
    public static final int DELAY = 1000;
    private Snake snake;


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

//        JButton b = new JButton("click");
//        b.setBounds(130, 100, 100, 40);
//        f.add(b);
        JPanel pan = new GameBoard(snake);


        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (KeyEvent.VK_W == keyEvent.getKeyCode()) snake.direction('U');
                if (KeyEvent.VK_S == keyEvent.getKeyCode()) snake.direction('D');
                if (KeyEvent.VK_A == keyEvent.getKeyCode()) snake.direction('L');
                if (KeyEvent.VK_D == keyEvent.getKeyCode()) snake.direction('R');
                if (KeyEvent.VK_ESCAPE == keyEvent.getKeyCode()) System.exit(0);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) { }
        });

        f.add(pan);

        f.setSize(RECT_SIZE * Snake.SIZE, RECT_SIZE * Snake.SIZE);
        f.setLayout(null);
        f.setType(Window.Type.UTILITY);
        //f.pack();
        f.setVisible(true);


        new Thread() {
            @Override
            public void run() {
                long moment = System.currentTimeMillis();
                while (true) {
                    if (System.currentTimeMillis() - moment > DELAY) {
                        snake.moveOneStep();
                        pan.repaint();
                        moment = System.currentTimeMillis();
                    }
                }
            }

        }.start();


//cycle

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

        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, 3, 3);
        g.setColor(Color.BLACK);
        g.drawRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, 3, 3);

        g.drawString("H", h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE);

        h = h.getNextPill();
        int c = 1;
        while (h != null) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, 3, 3);
            g.setColor(Color.BLACK);
            g.drawRoundRect(h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, 3, 3);

            g.drawString("" + c++, h.getX() * Game.RECT_SIZE, h.getY() * Game.RECT_SIZE + Game.RECT_SIZE);
            h = h.getNextPill();
        }

        g.setColor(Color.RED);
        g.fillRoundRect(snake.getAppX() * Game.RECT_SIZE, snake.getAppY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, 3, 3);
        g.setColor(Color.BLACK);
        g.drawRoundRect(snake.getAppX() * Game.RECT_SIZE, snake.getAppY() * Game.RECT_SIZE, Game.RECT_SIZE, Game.RECT_SIZE, 3, 3);


    }
}
