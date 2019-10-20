package com.vcs.games.exampl.pingpong;

import com.vcs.games.exampl.pingpong.model.Brick;
import com.vcs.games.exampl.pingpong.model.GameStatus;
import com.vcs.games.exampl.pingpong.model.State;
import com.vcs.games.exampl.pingpong.model.UserKey;

public class Game {

	public static final int RES_H = 900;
	public static final int RES_W = 1920;

	public static final int BALL_START_POS_X = RES_W / 5;
	public static final int BALL_START_POS_Y = RES_H / 2;
	public static final int BALL_R = RES_H / 30;

	public static final double BALL_X_SPEED = RES_H / 600.0;
	public static final double BALL_Y_SPEED = RES_H / 300.0;
	public static final double BALL_X_START_DIR = -BALL_X_SPEED;
	public static final double BALL_Y_START_DIR = -BALL_Y_SPEED;

	public static final int PLAYER_MOVE_OFFSET = RES_H / 30;
	public static final int PLAYER_PAD_W = RES_W / 7;
	public static final int PLAYER_PAD_H = 15;// ( RES_H / 100 < 1 ? 1 : RES_H / 100);
	public static final int PLAYER_START_POS_X = RES_W / 2 - PLAYER_PAD_W / 2;
	public static final int PLAYER_START_POS_Y = RES_H - RES_H / 12;

	public static final int BRICK_W = RES_W / 10;
	public static final int BRICK_H = RES_H / 15;
	public static final int BRICK_SPACE = BRICK_W / 15;

	public static final int GAME_SCORE_INC = 5;
	public static final int GAME_CYCLE_DELAY = 40; // ball speed

	private State state;

	public Game(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void onUserKey(UserKey key) {
		if (UserKey.RIGHT.equals(key)) {
			int predicX = state.getPlayerX() + PLAYER_MOVE_OFFSET;
			int mostLeft = RES_W - PLAYER_PAD_W;

			state.setPlayerX(predicX >= mostLeft ? mostLeft : predicX);

			if (state.isItPaused()) {
				state.setGameStatus(GameStatus.PLAY);
			}
		}

		if (UserKey.LEFT.equals(key)) {
			int predicX = state.getPlayerX() - PLAYER_MOVE_OFFSET;

			state.setPlayerX(predicX < 0 ? 0 : predicX);

			if (state.isItPaused()) {
				state.setGameStatus(GameStatus.PLAY);
			}
		}

		if (UserKey.ENTER.equals(key)) {
			if (state.isItPlay()) {
				state.reset();
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
		return state.getTotalBricks() <= 0;
	}

	private boolean isGameOver() {
		return state.getBallposY() > RES_H - BALL_R;
	}

	private void colisions() {

		for (Brick b : state.getMap()) {
			if (b.isTouching(state.getBallposX(), state.getBallposY(), BALL_R)) {

				state.scoreInc(GAME_SCORE_INC);

				if (state.getBallposX() + (BALL_R - 1) <= b.getX() || state.getBallposX() + 1 >= b.getX() + BRICK_W) {
					state.ballPosXDirChange();
				} else {
					state.ballPosYDirChange();
				}

				state.getMap().remove(b);
				break;
			}
		}

		state.ballPosXInc(state.getBallXdir());
		state.ballPosYInc(state.getBallYdir());

		if (state.getBallposX() < 0) {
			state.ballPosXDirChange();
		}
		if (state.getBallposY() < 0) {
			state.ballPosYDirChange();
		}
		if (state.getBallposX() > RES_W - BALL_R) {
			state.ballPosXDirChange();
		}

		if (state.getBallposY() + BALL_R >= PLAYER_START_POS_Y) {
			if (state.getBallposX() - BALL_R >= state.getPlayerX()) {
				if (state.getBallposX() <= (state.getPlayerX() + PLAYER_PAD_W)) {
					state.ballPosYDirChange();
				}
			}
		}
	}

}
