package com.vcs.bb.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.vcs.bb.game.gui.swing.SwingGUI;
import com.vcs.bb.game.model.Ball;
import com.vcs.bb.game.model.State;
import com.vcs.bb.game.model.enums.GameStatus;
import com.vcs.bb.game.model.enums.UserKey;
import com.vcs.bb.utils.LevelsLoader;

/**
 * 
 * <li>5) Rezoliucija ir setingus iskelti</li>
 * <li>9) Leveliai turi kestis</li>
 */

public class Game {

	private static final int FPS = 60;
	private static final int REPAINT_SPEED_MILIS = 1000 / FPS;
	private static final int WAIT_CYCLES_FOR_NEXT_ANIM_FRAME = 5;
	private static final int GAME_SPEED_MILIS = 4;

	private Physics phs;
	private SwingGUI gui;
	private Timer phsTimer;
	private Timer guiTimer;

	private int waitForNextAniFrameCounter = WAIT_CYCLES_FOR_NEXT_ANIM_FRAME;

	private Set<UserKey> pressedKeys = new HashSet<>();

	private Map<Integer, UserKey> validPressReleaseKeys = new HashMap<>();
	{
		validPressReleaseKeys.put(KeyEvent.VK_RIGHT, UserKey.RIGHT);
		validPressReleaseKeys.put(KeyEvent.VK_LEFT, UserKey.LEFT);
		validPressReleaseKeys.put(KeyEvent.VK_ENTER, UserKey.ENTER);
		validPressReleaseKeys.put(KeyEvent.VK_ESCAPE, UserKey.ESC);
		validPressReleaseKeys.put(KeyEvent.VK_SPACE, UserKey.SPACE);
	}

	public void startGame() {

		phs = new Physics(new State(LevelsLoader.loadLevels()));
		gui = new SwingGUI(phs.getState());

		gui.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressedKeys.add(validPressReleaseKeys.getOrDefault(e.getKeyCode(), UserKey.ANY));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				UserKey uk = validPressReleaseKeys.getOrDefault(e.getKeyCode(), UserKey.ANY);
				pressedKeys.remove(uk);
				onUserKey(uk);
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		phsTimer = new Timer(GAME_SPEED_MILIS, (e) -> {
			// Sends keyboard events to simulate
			phs.userInput(pressedKeys.contains(UserKey.LEFT), pressedKeys.contains(UserKey.RIGHT));
			simulation();
			countForNextAnimationFrame();
			phsTimer.restart();
		});

		guiTimer = new Timer(REPAINT_SPEED_MILIS, (e) -> {
			gui.repaint();
			guiTimer.restart();
		});

		phsTimer.start();
		guiTimer.start();

		JFrame obj = new JFrame();
		obj.setBounds(10, 10, Physics.RES_W, Physics.RES_H_FOOTER);
		obj.setTitle("Brick Breaker");
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gui);
		obj.setVisible(true);
	}

	public void simulation() {
		if (phs.getState().isItPlay()) {
			phs.colisions();

			if (isGameOver()) {
				phs.getState().setGameStatus(GameStatus.GAME_OVER);
			}

			if (isLevelWin()) {
				if (loadNextLevel()) {
					phs.getState().setGameStatus(GameStatus.PAUSE);
				} else {
					phs.getState().setGameStatus(GameStatus.GAME_WIN);
				}
			}
		}
	}

	private boolean isLevelWin() {
		return phs.getState().getBrics().size() <= 0;
	}

	private boolean isGameOver() {
		return phs.getState().getBall().getBallPosY() > Physics.RES_H - Ball.BALL_R;
	}

	private boolean loadNextLevel() {

		if (phs.getState().getLevelsCount() == phs.getState().getCurrentLevel() + 1) {
			return false;
		}

		phs.getState().setCurrentLevel(phs.getState().getCurrentLevel() + 1);
		phs.getState().resetLevel();
		return true;
	}

	private void countForNextAnimationFrame() {
		if (--waitForNextAniFrameCounter < 1) {
			gui.animateNextFrame();
			waitForNextAniFrameCounter = WAIT_CYCLES_FOR_NEXT_ANIM_FRAME;
		}
	}

	private void onUserKey(UserKey key) {

		if (UserKey.ENTER.equals(key)) {
			if (!phs.getState().isItPaused()) {
				phs.getState().resetGame();
				phs.getState().setGameStatus(GameStatus.PAUSE);
			}
		}

		if (UserKey.F1.equals(key)) {
			throw new RuntimeException();
		}

		if (UserKey.ESC.equals(key)) {
			System.exit(0);
		}

		if (UserKey.SPACE.equals(key)) {
			if (phs.getState().isItPlay()) {
				phs.getState().setGameStatus(GameStatus.PAUSE);
			} else if (phs.getState().isItPaused()) {
				phs.getState().setGameStatus(GameStatus.PLAY);
			}
		}

	}

}
