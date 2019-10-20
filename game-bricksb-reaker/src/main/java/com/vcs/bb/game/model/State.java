package com.vcs.bb.game.model;

import java.util.ArrayList;
import java.util.List;

import com.vcs.bb.game.Physics;

public class State {

	private int score;
	private List<Brick> bricks = new ArrayList<>();
	private Level level;
	private int padX;
	private double ballposX;
	private double ballposY;
	private double ballXdir;
	private double ballYdir;
	private GameStatus gameStatus;
	private double speedMs;

	public State(Level level) {
		this.level = level;
		reset();
	}

	public void reset() {

		bricks.clear();
		bricks.addAll(level.getBricks());

		score = 0;
		speedMs = Physics.GAME_CYCLE_DELAY * level.getSpeed();
		padX = Physics.PAD_START_POS_X;
		ballposX = Physics.BALL_START_POS_X;
		ballposY = Physics.BALL_START_POS_Y;
		ballXdir = Physics.BALL_X_START_DIR;
		ballYdir = Physics.BALL_Y_START_DIR;
		gameStatus = GameStatus.PAUSE;
	}

	public double getSpeedMs() {
		return speedMs;
	}

	public void speedMsInc(double incVal) {
		this.speedMs += incVal;
	}

	public boolean isItPaused() {
		return GameStatus.PAUSE.equals(gameStatus);
	}

	public boolean isItPlay() {
		return GameStatus.PLAY.equals(gameStatus);
	}

	public boolean isItWin() {
		return GameStatus.WIN.equals(gameStatus);
	}

	public boolean isItOver() {
		return GameStatus.OVER.equals(gameStatus);
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getScore() {
		return score;
	}

	public List<Brick> getBrics() {
		return bricks;
	}

	public int getPapX() {
		return padX;
	}

	public double getBallPosX() {
		return ballposX;
	}

	public double getBallPosY() {
		return ballposY;
	}

	public double getBallXdir() {
		return ballXdir;
	}

	public double getBallYdir() {
		return ballYdir;
	}

	public void setPadX(int playerX) {
		this.padX = playerX;
	}

	public void ballPosXInc(double incVal) {
		this.ballposX += incVal;

	}

	public void setBallPosY(double posY) {
		this.ballposY = posY;

	}

	public void ballPosYInc(double incVal) {
		this.ballposY += incVal;

	}

	public void ballPosXDirChange() {
		this.ballXdir = -this.ballXdir;

	}

	public void ballPosYDirChange() {
		this.ballYdir = -this.ballYdir;

	}

	public void scoreInc(int incVal) {
		this.score += incVal;

	}

	public Level getLevel() {
		return level;
	}

}
