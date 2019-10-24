package com.vcs.bb.game.gui.swing;

import static com.vcs.bb.game.Physics.RES_H;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.model.Ball;
import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.State;
import com.vcs.bb.utils.SpriteLoader;

public class SwingGUI extends JPanel {

	private static final String FONT_NAME = "Courier";

	private static final long serialVersionUID = 1L;

	public static final int MSG_FONT_SIZE_OFFSET = Physics.RES_W / 140;
	public static final int MSG_FONT_SIZE_1 = 16;
	public static final int MSG_FONT_SIZE_2 = 20;
	public static final int MSG_FONT_SIZE_3 = 24;

	public static final int MSG_COL1 = 20;
	public static final int MSG_COL2 = 230;
	public static final int MSG_GAP_Y = 20;

	public static final double MILI_SECS_IN_SEC = 1000.0;

	private State state;

	private Image[] ballAnimation = null;
	private int currentAnimationFrame = 0;
	private double framesCounter = 0;
	private long time = 0;
	private int fps = 0;

	public SwingGUI(State state) {
		this.state = state;

		ballAnimation = SpriteLoader.loadSpriteCR("fireball.png", 6, 4);

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		time = System.currentTimeMillis();
	}

	public void animateNextFrame() {
		currentAnimationFrame++;
		if (currentAnimationFrame >= ballAnimation.length) {
			currentAnimationFrame = 0;
		}
	}

	@Override
	public void paint(Graphics g) {

		counterFPS();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Physics.RES_W, Physics.RES_H);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, Physics.RES_H, Physics.RES_W, Physics.RES_H_FOOTER - Physics.RES_H);

		drawBricks((Graphics2D) g);
		drawStatusBar(g);

		g.setColor(Color.green);
		g.fillRect(state.getPadX(), Physics.PAD_START_POS_Y, Physics.PAD_W, Physics.PAD_H);

		drawBall(g);

		if (state.isItOver())
			drawMessage(g, "Upset? <Press ENTER to reset>");
		if (state.isItWin())
			drawMessage(g, "Win the game <press ENTER to reset>");
		if (state.isItPaused())
			drawMessage(g, "Paused <press SPACE>");

		g.dispose();

	}

	private void counterFPS() {
		long timeDiff = System.currentTimeMillis() - time;
		if (timeDiff > MILI_SECS_IN_SEC) {
			fps = (int) (framesCounter * (MILI_SECS_IN_SEC / timeDiff));
			framesCounter = 0;
			time = System.currentTimeMillis();
		}

		framesCounter++;

	}

	private void drawBall(Graphics g) {
		g.setColor(Color.red);
		// g.fillOval((int) state.getBall().getBallPosX(), (int)
		// state.getBall().getBallPosY(), Ball.BALL_R, Ball.BALL_R);
		g.drawImage(ballAnimation[currentAnimationFrame], (int) state.getBall().getBallPosX() - Ball.BALL_R / 2,
				(int) state.getBall().getBallPosY() - Ball.BALL_R / 2, Ball.BALL_R * 2, Ball.BALL_R * 2, null);

	}

	private void drawStatusBar(Graphics g) {

		g.setFont(new Font(FONT_NAME, Font.BOLD, MSG_FONT_SIZE_1));
		g.setColor(Color.YELLOW);
		g.drawString("[" + state.getLevel().getName() + "]", MSG_COL1, RES_H + MSG_GAP_Y);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Level : " + (state.getCurrentLevel() + 1) + " / " + state.getLevelsCount(), MSG_COL1,
				RES_H + MSG_GAP_Y * 2);
		g.drawString("Score : " + state.getScore(), MSG_COL1, RES_H + MSG_GAP_Y * 3);

		g.drawString("FPS    : " + fps, MSG_COL2, RES_H + MSG_GAP_Y * 2);
		g.drawString("Speed  : " + state.getSpeedMs(), MSG_COL2, RES_H + MSG_GAP_Y * 3);

	}

	private void drawMessage(Graphics g, String title) {
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font(FONT_NAME, Font.BOLD, SwingGUI.MSG_FONT_SIZE_3));
		g.drawString(title, state.getPadX(), Physics.PAD_START_POS_Y - 30);

	}

	public void drawBricks(Graphics2D g) {
		for (Brick b : state.getBrics()) {

			g.setColor(new Color(b.getType().getRGB()));
			g.fillRect(b.getX(), b.getY(), Physics.BRICK_W, Physics.BRICK_H);

			g.setStroke(new BasicStroke(3));
			g.setColor(Color.ORANGE);
			g.drawRect(b.getX(), b.getY(), Physics.BRICK_W, Physics.BRICK_H);
		}
	}

}
