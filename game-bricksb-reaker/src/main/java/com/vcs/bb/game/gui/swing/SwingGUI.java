package com.vcs.bb.game.gui.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.State;

public class SwingGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int MSG_FONT_SIZE_OFFSET = Physics.RES_W / 140;
	public static final int MSG_FONT_SIZE_1 = Physics.RES_W / 35;
	public static final int MSG_FONT_SIZE_2 = MSG_FONT_SIZE_1 + MSG_FONT_SIZE_OFFSET;
	public static final int MSG_FONT_SIZE_3 = MSG_FONT_SIZE_2 + MSG_FONT_SIZE_OFFSET;

	public static final int MSG_SCORE_X = Physics.RES_W - Physics.RES_W / 4;
	public static final int MSG_SCORE_Y = Physics.RES_H / 20;

	public static final int MSG_GAME_X = Physics.RES_W / 2;
	public static final int MSG_GAME_Y = Physics.RES_H / 2;

	private State state;

	public SwingGUI(State state) {
		this.state = state;

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, Physics.RES_W, Physics.RES_H);

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
		g.fillRect(state.getPlayerX(), Physics.PLAYER_START_POS_Y, Physics.PLAYER_PAD_W, Physics.PLAYER_PAD_H);

		// ball
		g.setColor(Color.red);
		g.fillOval((int)state.getBallPosX(), (int)state.getBallPosY(), Physics.BALL_R, Physics.BALL_R);

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
		g.drawString("Press SPACE to Restart", SwingGUI.MSG_GAME_X,
				SwingGUI.MSG_GAME_Y + SwingGUI.MSG_FONT_SIZE_3 * 2 + SwingGUI.MSG_FONT_SIZE_OFFSET);
	}

	public void draw(Graphics2D g) {
		for (Brick b : state.getMap()) {
			g.setColor(Color.red);
			g.fillRect(b.getX(), b.getY(), Physics.BRICK_W, Physics.BRICK_H);

			g.setStroke(new BasicStroke(3));
			g.setColor(Color.black);
			g.drawRect(b.getX(), b.getY(), Physics.BRICK_W, Physics.BRICK_H);
		}
	}

}
