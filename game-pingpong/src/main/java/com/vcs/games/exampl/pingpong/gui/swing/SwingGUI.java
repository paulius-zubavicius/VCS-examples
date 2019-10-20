package com.vcs.games.exampl.pingpong.gui.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.vcs.games.exampl.pingpong.Game;
import com.vcs.games.exampl.pingpong.model.Brick;
import com.vcs.games.exampl.pingpong.model.State;
import com.vcs.games.exampl.pingpong.model.UserKey;

/**
 * 
 * 1) Pasileidzia tuscias screen'as 2) Brick naudoti ne array[][] o List'a
 * 
 */

public class SwingGUI extends JPanel implements KeyListener, ActionListener {

	public static final int MSG_FONT_SIZE_OFFSET = Game.RES_W / 140;
	public static final int MSG_FONT_SIZE_1 = Game.RES_W / 35;
	public static final int MSG_FONT_SIZE_2 = MSG_FONT_SIZE_1 + MSG_FONT_SIZE_OFFSET;
	public static final int MSG_FONT_SIZE_3 = MSG_FONT_SIZE_2 + MSG_FONT_SIZE_OFFSET;

	public static final int MSG_SCORE_X = Game.RES_W - Game.RES_W / 4;
	public static final int MSG_SCORE_Y = Game.RES_H / 20;

	public static final int MSG_GAME_X = Game.RES_W / 2;
	public static final int MSG_GAME_Y = Game.RES_H / 2;

	private Map<Integer, UserKey> ui = new HashMap<>();
	{
		ui.put(KeyEvent.VK_RIGHT, UserKey.RIGHT);
		ui.put(KeyEvent.VK_LEFT, UserKey.LEFT);
		ui.put(KeyEvent.VK_ENTER, UserKey.ENTER);
		ui.put(KeyEvent.VK_ESCAPE, UserKey.ESC);
	}

	private State state;
	private Game game;
	private Timer timer;

	public SwingGUI(Game sim) {
		this.state = sim.getState();
		this.game = sim;

		state.reset();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(Game.GAME_CYCLE_DELAY, this);
		timer.start();

	}

	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.RES_W, Game.RES_H);

		// draw map
		draw((Graphics2D) g);

		// borders
//		g.setColor(Color.yellow);
//		g.fillRect(0, 0, 3, 592);
//		g.fillRect(0, 0, 692, 3);
//		g.fillRect(691, 0, 3, 592);

		// score\
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, MSG_FONT_SIZE_2));
		g.drawString("Score: " + state.getScore(), MSG_SCORE_X, MSG_SCORE_Y);

		// the paddle
		g.setColor(Color.green);
		g.fillRect(state.getPlayerX(), Game.PLAYER_START_POS_Y, Game.PLAYER_PAD_W, Game.PLAYER_PAD_H);

		// ball
		g.setColor(Color.red);
		g.fillOval(state.getBallposX(), state.getBallposY(), Game.BALL_R, Game.BALL_R);

		if (state.isItOver())
			endMessage(g, "Game Over");
		if (state.isItWin())
			endMessage(g, "Victory!");

		g.dispose();

	}

	private void endMessage(Graphics g, String title) {
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, SwingGUI.MSG_FONT_SIZE_3));
		g.drawString(title, SwingGUI.MSG_GAME_X, SwingGUI.MSG_GAME_Y);
		g.drawString("Your Score is: " + state.getScore(), SwingGUI.MSG_GAME_X,
				SwingGUI.MSG_GAME_Y + SwingGUI.MSG_FONT_SIZE_3 + SwingGUI.MSG_FONT_SIZE_OFFSET);

		g.setFont(new Font("serif", Font.BOLD, SwingGUI.MSG_FONT_SIZE_1));
		g.drawString("Press ENTER to Restart", SwingGUI.MSG_GAME_X,
				SwingGUI.MSG_GAME_Y + SwingGUI.MSG_FONT_SIZE_3 * 2 + SwingGUI.MSG_FONT_SIZE_OFFSET);
	}

	public void actionPerformed(ActionEvent e) {
		timer.restart();
		game.simulation();
		repaint();
	}

	public void draw(Graphics2D g) {
		for (Brick b : state.getMap()) {
			g.setColor(Color.red);
			g.fillRect(b.getX(), b.getY(), Game.BRICK_W, Game.BRICK_H);

			g.setStroke(new BasicStroke(3));
			g.setColor(Color.black);
			g.drawRect(b.getX(), b.getY(), Game.BRICK_W, Game.BRICK_H);
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO 2) Brick numusimas is desines kazkur praskipinta plotis ar tarpas

		game.onUserKey(ui.getOrDefault(e.getKeyCode(), UserKey.ANY));
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
