package com.vcs.games.exampl.pingpong.model;

import java.util.ArrayList;
import java.util.List;

import com.vcs.games.exampl.pingpong.Physics;

public class State {

	private int score;
	private List<Brick> bricks = new ArrayList<>();

	private int playerX;
	private int ballposX;
	private int ballposY;
	private double ballXdir;
	private double ballYdir;
	private GameStatus gameStatus;

	private int col;
	private int rows;

	public State(int col, int rows) {

		this.col = col;
		this.rows = rows;
		reset();
	}

	public void reset() {
		resetMap();
		score = 0;
		playerX = Physics.PLAYER_START_POS_X;
		ballposX = Physics.BALL_START_POS_X;
		ballposY = Physics.BALL_START_POS_Y;
		ballXdir = Physics.BALL_X_START_DIR;
		ballYdir = Physics.BALL_Y_START_DIR;
		gameStatus = GameStatus.PAUSE;
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

	private void resetMap() {

		bricks.clear();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < col; j++) {
				bricks.add(new Brick(10 + i * (Physics.BRICK_W + Physics.BRICK_SPACE),
						10 + j * (Physics.BRICK_H + Physics.BRICK_SPACE), Physics.BRICK_W, Physics.BRICK_H));
			}
		}

	}

	public int getScore() {
		return score;
	}

	public int getTotalBricks() {
		return bricks.size();
	}

	public List<Brick> getMap() {
		return bricks;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getBallposX() {
		return ballposX;
	}

	public int getBallposY() {
		return ballposY;
	}

	public double getBallXdir() {
		return ballXdir;
	}

	public double getBallYdir() {
		return ballYdir;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public void ballPosXInc(double incVal) {
		this.ballposX += incVal;

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

}
