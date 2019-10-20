package com.vcs.games.exampl.pingpong.run;
import javax.swing.JFrame;

import com.vcs.games.exampl.pingpong.Game;
import com.vcs.games.exampl.pingpong.gui.swing.SwingGUI;
import com.vcs.games.exampl.pingpong.model.State;

public class Main {
	
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		
		
		Game sim = new Game(new State(3, 7));
		
		SwingGUI gamePlay = new SwingGUI(sim);
		obj.setBounds(10, 10, Game.RES_W, Game.RES_H);
		obj.setTitle("Brick Breaker");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
	}


}
