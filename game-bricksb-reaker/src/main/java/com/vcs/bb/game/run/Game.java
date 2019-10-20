package com.vcs.bb.game.run;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.gui.swing.SwingGUI;
import com.vcs.bb.game.model.State;
import com.vcs.bb.game.model.UserKey;

/**
 * 
 * <li>2) Kamuoliukas kartais palenda po pad'u</li>
 * <li>3) Kamuoliukas leidziasi palei siena</li>
 * <li>4) Kaladeliu isdestymas</li>
 * <li>5) Rezoliucija ir setingus iskelti</li>
 * <li>6) enteris neveikia</li>
 */

public class Game {

	private Physics phs;
	private SwingGUI gui;
	private Timer phsTimer;
	private Timer guiTimer;

	private Set<UserKey> pressedKeys = new HashSet<>();

	private Map<Integer, UserKey> mappedKeys = new HashMap<>();
	{
		mappedKeys.put(KeyEvent.VK_RIGHT, UserKey.RIGHT);
		mappedKeys.put(KeyEvent.VK_LEFT, UserKey.LEFT);
		mappedKeys.put(KeyEvent.VK_SPACE, UserKey.SPACE);
		mappedKeys.put(KeyEvent.VK_ESCAPE, UserKey.ESC);
	}

	void startGameApp() {

		phs = new Physics(new State(3, 7));
		gui = new SwingGUI(phs.getState());
		gui.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressedKeys.add(mappedKeys.getOrDefault(e.getKeyCode(), UserKey.ANY));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pressedKeys.remove(mappedKeys.getOrDefault(e.getKeyCode(), UserKey.ANY));
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		phsTimer = new Timer(Physics.GAME_CYCLE_DELAY, (e) -> {
			// Sends keyboard events to simulate
			phs.onUserKey(pressedKeys);
			phs.simulation();
			gui.repaint();
			guiTimer.restart();
		});

		guiTimer = new Timer(15, (e) -> {
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

}
