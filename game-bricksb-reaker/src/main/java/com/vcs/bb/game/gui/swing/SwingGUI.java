package com.vcs.bb.game.gui.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.model.Ball;
import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.State;

public class SwingGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int MSG_FONT_SIZE_OFFSET = Physics.RES_W / 140;
	public static final int MSG_FONT_SIZE_1 = Physics.RES_W / 35;
	public static final int MSG_FONT_SIZE_2 = MSG_FONT_SIZE_1 + MSG_FONT_SIZE_OFFSET;
	public static final int MSG_FONT_SIZE_3 = MSG_FONT_SIZE_2 + MSG_FONT_SIZE_OFFSET;

	public static final int MSG_SCORE_X = MSG_FONT_SIZE_OFFSET;// Physics.RES_W - Physics.RES_W / 4;
	public static final int MSG_SCORE_Y = Physics.RES_H - Physics.RES_H / 10;
	public static final int MSG_LEVEL_X = Physics.RES_H / 3;

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
		// FIXME Draw ir changes
		g.setColor(Color.black);
		g.fillRect(0, 0, Physics.RES_W, Physics.RES_H);

		// draw map
		// FIXME Draw ir changes
		draw((Graphics2D) g);

		// borders
//		g.setColor(Color.yellow);
//		g.fillRect(0, 0, 3, 592);
//		g.fillRect(0, 0, 692, 3);
//		g.fillRect(691, 0, 3, 592);

		// score\
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, MSG_FONT_SIZE_1));
		g.drawString("Score: " + state.getScore(), MSG_SCORE_X, MSG_SCORE_Y);
		g.drawString(state.getLevel().getName(), MSG_LEVEL_X, MSG_SCORE_Y);

//		int sectorW = Physics.PAD_W / 7;
		g.setColor(Color.green);
		
//		for (int i = 0; i < 7; i++) {
//			if(i % 2 == 0) {
//				g.setColor(Color.green);
//			} else {
//				g.setColor(Color.magenta);
//			}
//			
//			g.fillRect(state.getPadX() + sectorW * i, Physics.PAD_START_POS_Y , sectorW, Physics.PAD_H);
//			
//		}
		
		g.fillRect(state.getPadX(), Physics.PAD_START_POS_Y , Physics.PAD_W, Physics.PAD_H);
		

		// ball
		g.setColor(Color.red);
		g.fillOval((int) state.getBall().getBallPosX(), (int) state.getBall().getBallPosY(), Ball.BALL_R, Ball.BALL_R);

		if (state.isItOver())
			endMessage(g, "Game Over");
		if (state.isItWin())
			endMessage(g, "Victory!");
		if (state.isItPaused())
			endMessage(g, "[||] Paused");
		
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
		for (Brick b : state.getBrics()) {

			g.setColor(new Color(b.getType().getRGB()));
			g.fillRect(b.getX(), b.getY(), Physics.BRICK_W, Physics.BRICK_H);

			g.setStroke(new BasicStroke(3));
			g.setColor(Color.ORANGE);
			g.drawRect(b.getX(), b.getY(), Physics.BRICK_W, Physics.BRICK_H);
		}
	}

}
