package com.vcs.colors.game.gui;

import com.vcs.colors.game.Game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameApplication extends Application {

	private Game createGame() {
		return new Game();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Game controller
		Game game = createGame();

		// Creates main window pane
		Pane mainWinPane = new Pane();

		// Main win controller
		new GameWindowActions(mainWinPane, game);

		Scene scene = new Scene(mainWinPane);

		primaryStage.setMinWidth(600);
		primaryStage.setMinHeight(300);
		primaryStage.setTitle("Spalvos");
		primaryStage.setScene(scene);
		primaryStage.setAlwaysOnTop(false);
		primaryStage.setResizable(false);
		// primaryStage.getIcons().add(new Image("icon.png"));
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
	}

}
