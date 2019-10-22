package com.vcs.bb.game.run;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.gui.swing.SwingGUI;
import com.vcs.bb.game.levels.LevelsLoader;
import com.vcs.bb.game.model.GameStatus;
import com.vcs.bb.game.model.Level;
import com.vcs.bb.game.model.Resolution;
import com.vcs.bb.game.model.State;
import com.vcs.bb.game.model.UserKey;

/**
 * 
 * <li>5) Rezoliucija ir setingus iskelti</li>
 * <li>9) Leveliai turi kestis</li>
 * <li>10) Perpaisyti tik tuos komponentus kurie trigerinti perpaisyti, o ne
 * viska kiekviena karta</li>
 */

public class Game {

	private static final int FPS = 60;
	private static final int FPS_DELAY = 1000 / FPS;

	private Physics phs;
	private SwingGUI gui;
	private Timer phsTimer;
	private Timer guiTimer;

	private Set<UserKey> pressedKeys = new HashSet<>();

	private Map<Integer, UserKey> validPressReleaseKeys = new HashMap<>();
	{
		validPressReleaseKeys.put(KeyEvent.VK_RIGHT, UserKey.RIGHT);
		validPressReleaseKeys.put(KeyEvent.VK_LEFT, UserKey.LEFT);

		validPressReleaseKeys.put(KeyEvent.VK_ENTER, UserKey.ENTER);
		validPressReleaseKeys.put(KeyEvent.VK_ESCAPE, UserKey.ESC);
		validPressReleaseKeys.put(KeyEvent.VK_SPACE, UserKey.SPACE);

	}

	void startGameApp() {

		LevelsLoader ll = new LevelsLoader();
		List<Level> levels = ll.loadLevels(Resolution.RES_600x400);

		phs = new Physics(new State(levels.get(0)));
		gui = new SwingGUI(phs.getState());
//		gui.addMouseMotionListener(new MouseMotionListener() {
//
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				if (phs.getState().isItPlay()) {
//					phs.getState().setPadX(e.getX() - Physics.PAD_W / 2);
//				}
//			}
//
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			}
//		});
//		gui.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				phs.getState().setGameStatus(GameStatus.PLAY);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});
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

		phsTimer = new Timer((int) phs.getState().getSpeedMs(), (e) -> {
			// Sends keyboard events to simulate
			phs.userInput(pressedKeys.contains(UserKey.LEFT), pressedKeys.contains(UserKey.RIGHT));
			
			phs.simulation();
			gui.repaint();
			guiTimer.restart();
		});

		guiTimer = new Timer(FPS_DELAY, (e) -> {
			gui.repaint();
			guiTimer.restart();
		});

		JFrame obj = new JFrame();
		obj.setBounds(10, 10, Physics.RES_W, Physics.RES_H);
		obj.setTitle("Brick Breaker");
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gui);

		phsTimer.start();
		guiTimer.start();

		obj.setVisible(true);
	}

	public void onUserKey(UserKey key) {

		if (UserKey.ENTER.equals(key)) {
			if (!phs.getState().isItPaused()) {
				phs.getState().reset();
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
