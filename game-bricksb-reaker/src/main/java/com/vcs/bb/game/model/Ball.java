package com.vcs.bb.game.model;

import com.vcs.bb.game.Physics;

public class Ball {

	public static final int BALL_START_POS_X = Physics.RES_W / 5;
	public static final int BALL_START_POS_Y = Physics.RES_H / 2;
	public static final int BALL_R = Physics.RES_H / 20;
	public static final double BALL_SPEED = Physics.RES_H / 600.0;

	private double speed;
	private BallAngle angle;
	private double ballposX;
	private double ballposY;

	public Ball(double speed, BallAngle angle) {
		this.speed = speed;
		this.angle = angle;
	}

	public Ball() {
		this.speed = BALL_SPEED;
		this.angle = BallAngle._255;
		this.ballposX = BALL_START_POS_X;
		this.ballposY = BALL_START_POS_Y;
	}

	public void setBallPosY(double posY) {
		this.ballposY = posY;

	}

	public void simulateMove() {
		this.ballposX += angle.getX() * speed;
		this.ballposY +=  angle.getY() * speed;
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

	public double getBallPosX() {
		return ballposX;
	}

	public double getBallPosY() {
		return ballposY;
	}

	public void setAngle(BallAngle angle) {
		this.angle = angle;
	}

	
}
