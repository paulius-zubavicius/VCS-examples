package com.vcs.colors.game.app;

import com.vcs.colors.game.Game;
import com.vcs.colors.game.gui.GameWindowActions;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameApplication extends Application {


	/**
	 * To run: mvn clean javafx:run
	 * */

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Game controller
		Game game =  new Game();

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
