package com.vcs.bb.game.ball;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.gui.Animated;
import com.vcs.bb.game.model.enums.BallAngle;

public class Ball implements Animated {

	private static final int BALL_START_POS_X = Physics.RES_W / 2;
	private static final int BALL_START_POS_Y = Physics.RES_H - Physics.RES_H / 5;
	private static final int BALL_R = Physics.RES_H / 40;

	protected double speed;
	protected BallAngle angle;
	protected double x;
	protected double y;

	private BufferedImage ballImg;

	public Ball() {
		angle = BallAngle._270;
		x = BALL_START_POS_X;
		y = BALL_START_POS_Y;

		ballImg = new BufferedImage(BALL_R, BALL_R, BufferedImage.TYPE_INT_ARGB);
		ballImg.getGraphics().setColor(Color.RED);
		ballImg.getGraphics().fillOval(0, 0, BALL_R, BALL_R);

	}
	
	public int getSize() {
		return BALL_R;
	}

	public void setBallPosY(double posY) {
		this.y = posY;

	}

	public void simulateMove() {
		this.x += angle.getX() * speed;
		this.y += angle.getY() * speed;
	}

	public void bouncedOffHorizontal() {
		angle = BallAngle.bouncedOffHorizontal(angle);
	}

	public void bouncedOffVertical() {
		angle = BallAngle.bouncedOffVertical(angle);
	}

	public void bouncedOffPad(int dirChangeIndex) {
		angle = BallAngle.bouncedOffPad(angle, dirChangeIndex);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public BallAngle getAngle() {
		return angle;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setAngle(BallAngle angle) {
		this.angle = angle;
	}

	@Override
	public double getImgPosX() {
		return x;
	}

	@Override
	public double getImgPosY() {
		return y;
	}

	@Override
	public void animate() {
	}

	@Override
	public Image getImage() {
		return ballImg;
	}

	@Override
	public int getImgSize() {
		return BALL_R;
	}

}
