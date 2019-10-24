package com.vcs.bb.game.model;

import java.util.ArrayList;
import java.util.List;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.ball.Ball;
import com.vcs.bb.game.ball.BallFactory;
import com.vcs.bb.game.ball.EarthBall;
import com.vcs.bb.game.model.enums.GameStatus;

public class State {

	private int score;
	private List<Brick> bricks = new ArrayList<>();
	private List<Level> levels;
	private int currentLevel;
	private int padX;

	private Ball ball;
	private GameStatus gameStatus;
	private double speedMs;

	public State(List<Level> levels) {
		this.levels = levels;
		resetGame();
	}

	public void resetGame() {

		currentLevel = 0;
		score = 0;
		gameStatus = GameStatus.PAUSE;
		resetLevel();
	}

	public void resetLevel() {
		bricks.clear();
		bricks.addAll(levels.get(currentLevel).getBricks());

		speedMs = levels.get(currentLevel).getSpeed();

		ball = BallFactory.createInstance(levels.get(currentLevel).getBall());
		ball.setSpeed(speedMs);
		
		padX = Physics.PAD_START_POS_X;
	}

	public Ball getBall() {
		return ball;
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
		return GameStatus.GAME_WIN.equals(gameStatus);
	}

	public boolean isItOver() {
		return GameStatus.GAME_OVER.equals(gameStatus);
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

	public int getPadX() {
		return padX;
	}

	public void setPadX(int playerX) {
		this.padX = playerX;
	}

	public void scoreInc(int incVal) {
		this.score += incVal;

	}

	public Level getLevel() {
		return levels.get(currentLevel);
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getLevelsCount() {
		return levels.size();
	}

}
