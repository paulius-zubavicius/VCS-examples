package com.vcs.bb.game;

import java.util.Set;

import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.GameStatus;
import com.vcs.bb.game.model.State;
import com.vcs.bb.game.model.UserKey;

public class Physics {

	public static final int RES_H = 640;
	public static final int RES_W = 800;

	public static final int BALL_START_POS_X = RES_W / 5;
	public static final int BALL_START_POS_Y = RES_H / 2;
	public static final int BALL_R = RES_H / 30;

	public static final double BALL_X_SPEED = RES_H / 600.0;
	public static final double BALL_Y_SPEED = RES_H / 300.0;
	public static final double BALL_X_START_DIR = -BALL_X_SPEED;
	public static final double BALL_Y_START_DIR = -BALL_Y_SPEED;

	public static final int PAD_MOVE_OFFSET = RES_H / 50;
	public static final int PAD_W = RES_W / 7;
	public static final int PAD_H = (RES_H / 100 < 1 ? 1 : RES_H / 100);
	public static final int PAD_START_POS_X = RES_W / 2 - PAD_W / 2;
	public static final int PAD_START_POS_Y = RES_H - RES_H / 6;

	public static final int BRICK_W = RES_W / 10;
	public static final int BRICK_H = RES_H / 15;
	public static final int BRICK_MARGIN = BRICK_W / 15;

	public static final double GAME_CYCLE_DELAY = 4.0; // ball speed

	private State state;

	public Physics(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void onUserKey(Set<UserKey> pressedKeys) {

		for (UserKey key : pressedKeys) {
			takeAction(key);
		}
	}

	private void takeAction(UserKey key) {

		if (state.isItPlay()) {
			if (UserKey.RIGHT.equals(key)) {
				int predicX = state.getPapX() + PAD_MOVE_OFFSET;
				int mostLeft = RES_W - PAD_W;

				state.setPadX(predicX >= mostLeft ? mostLeft : predicX);

				if (state.isItPaused()) {
					state.setGameStatus(GameStatus.PLAY);
				}
			}

			if (UserKey.LEFT.equals(key)) {
				int predicX = state.getPapX() - PAD_MOVE_OFFSET;

				state.setPadX(predicX < 0 ? 0 : predicX);

				if (state.isItPaused()) {
					state.setGameStatus(GameStatus.PLAY);
				}
			}
		}

		if (UserKey.ENTER.equals(key)) {
			if (!state.isItPaused()) {
				state.reset();
			}
		}

		if (UserKey.SPACE.equals(key)) {
			if (state.isItPlay()) {
				state.setGameStatus(GameStatus.PAUSE);
			} else if (state.isItPaused()) {
				state.setGameStatus(GameStatus.PLAY);
			}
		}

		if (UserKey.F1.equals(key)) {
			throw new RuntimeException();
		}

		if (UserKey.ESC.equals(key)) {
			System.exit(0);
		}

	}

	public void simulation() {
		if (state.isItPlay()) {
			colisions();

			if (isGameOver()) {
				state.setGameStatus(GameStatus.OVER);
			}

			if (isGameWin()) {
				state.setGameStatus(GameStatus.WIN);
			}
		}
	}

	private boolean isGameWin() {
		return state.getBrics().size() <= 0;
	}

	private boolean isGameOver() {
		return state.getBallPosY() > RES_H - BALL_R;
	}

	private void colisions() {

		for (Brick b : state.getBrics()) {
			if (b.isTouching(state.getBallPosX(), state.getBallPosY(), BALL_R)) {

				state.scoreInc(b.getType().getPoints());

				if (state.getBallPosX() + (BALL_R - 1) <= b.getX() || state.getBallPosX() + 1 >= b.getX() + BRICK_W) {
					state.ballPosXDirChange();
				} else {
					state.ballPosYDirChange();
				}

				state.getBrics().remove(b);
				break;
			}
		}

		if (state.getBallPosX() < 0) {
			state.ballPosXDirChange();
		}
		if (state.getBallPosY() < 0) {
			state.ballPosYDirChange();
		}
		if (state.getBallPosX() > RES_W - BALL_R) {
			state.ballPosXDirChange();
		}

		if (state.getBallPosY() + BALL_R > PAD_START_POS_Y) {
			if (state.getPapX() <= state.getBallPosX() + BALL_R) {
				if ((state.getPapX() + PAD_W) >= state.getBallPosX()) {
					state.setBallPosY(PAD_START_POS_Y - BALL_R);
					state.ballPosYDirChange();
				}
			}
		}

		state.ballPosXInc(state.getBallXdir());
		state.ballPosYInc(state.getBallYdir());

	}

}
