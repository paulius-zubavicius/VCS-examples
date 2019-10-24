package com.vcs.bb.game;

import com.vcs.bb.game.model.Ball;
import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.State;
import com.vcs.bb.game.model.enums.GameStatus;

public class Physics {

	public static final int RES_H = 640;
	public static final int RES_W = 800;
	public static final int RES_H_FOOTER = RES_H + 100;

	public static final int PAD_MOVE_OFFSET = RES_W / 200;
	public static final int PAD_W = RES_W / 7;
	public static final int PAD_H = (RES_H / 100 < 1 ? 1 : RES_H / 100);
	public static final int PAD_START_POS_X = RES_W / 2 - PAD_W / 2;
	public static final int PAD_START_POS_Y = RES_H - RES_H / 12;
	public static final int PAD_SECTIONS = 7;

	public static final int BRICK_W = RES_W / 10;
	public static final int BRICK_H = RES_H / 15;
	public static final int BRICK_MARGIN = BRICK_W / 15;

	private State state;

	public Physics(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void userInput(boolean left, boolean right) {

		if (state.isItPlay()) {
			if (right) {
				int predicX = state.getPadX() + PAD_MOVE_OFFSET;
				int mostLeft = RES_W - PAD_W;

				state.setPadX(predicX >= mostLeft ? mostLeft : predicX);

				if (state.isItPaused()) {
					state.setGameStatus(GameStatus.PLAY);
				}
			}

			if (left) {
				int predicX = state.getPadX() - PAD_MOVE_OFFSET;

				state.setPadX(predicX < 0 ? 0 : predicX);

				if (state.isItPaused()) {
					state.setGameStatus(GameStatus.PLAY);
				}
			}
		}
	}

	public void simulation() {
		if (state.isItPlay()) {
			colisions();

			if (isGameOver()) {
				state.setGameStatus(GameStatus.GAME_OVER);
			}

			if (isLevelWin()) {
				if (state.loadNextLevel()) {
					state.setGameStatus(GameStatus.PAUSE);
				} else {
					state.setGameStatus(GameStatus.GAME_WIN);
				}
			}
		}
	}

//	private boolean isGameWin() {
//		return state.getLevelsCount() - 1 == state.getCurrentLevel();
//	}

	private boolean isLevelWin() {
		return state.getBrics().size() <= 0;
	}

	private boolean isGameOver() {
		return state.getBall().getBallPosY() > RES_H - Ball.BALL_R;
	}

	private void colisions() {

		for (Brick b : state.getBrics()) {
			if (b.isTouching(state.getBall().getBallPosX(), state.getBall().getBallPosY(), Ball.BALL_R)) {

				state.scoreInc(b.getType().getPoints());

				if (state.getBall().getBallPosX() + (Ball.BALL_R - 1) <= b.getX()
						|| state.getBall().getBallPosX() + 1 >= b.getX() + BRICK_W) {
					state.getBall().bouncedOffVertical();
				} else {
					state.getBall().bouncedOffHorizontal();
				}

				state.getBrics().remove(b);
				break;
			}
		}

		if (state.getBall().getBallPosX() < 0) {
			state.getBall().bouncedOffVertical();
		}
		if (state.getBall().getBallPosY() < 0) {
			state.getBall().bouncedOffHorizontal();
		}
		if (state.getBall().getBallPosX() > RES_W - Ball.BALL_R) {
			state.getBall().bouncedOffVertical();
		}

		if (state.getBall().getBallPosY() + Ball.BALL_R > PAD_START_POS_Y
				&& state.getBall().getBallPosY() + Ball.BALL_R < PAD_START_POS_Y + PAD_H) {

			int centerOfBall = (int) (state.getBall().getBallPosX() + Ball.BALL_R / 2.0);

			if (state.getPadX() <= centerOfBall) {
				if ((state.getPadX() + PAD_W) >= centerOfBall) {

					// Bugfix - do not allow follow below Y of pad
					state.getBall().setBallPosY(PAD_START_POS_Y - Ball.BALL_R);

					int sector = calcSector(centerOfBall, state.getPadX(), PAD_W);

					state.getBall().bouncedOffPad(sector);
				}
			}
		}
		state.getBall().simulateMove();
	}

	/**
	 * returns - -3, -2, -1, 0, 1, 2, 3
	 */
	private int calcSector(int centerOfBall, int padX1, int padW) {

		int sectorSize = padW / PAD_SECTIONS;
		int amplitude = PAD_SECTIONS / 2;
		int sec = ((centerOfBall - padX1) / sectorSize - amplitude);

		if (sec > amplitude) {
			return amplitude;
		}
		if (sec < -amplitude) {
			return -amplitude;
		}
		return sec;
	}

}
