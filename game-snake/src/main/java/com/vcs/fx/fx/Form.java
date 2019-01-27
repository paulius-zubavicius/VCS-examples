package com.vcs.fx.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Form extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Label label = new Label("Hello world as GUI :)");

		Button btn = new Button("Dont push me");
		btn.setLayoutX(100);
		btn.setLayoutY(100);

		Button btnExit = new Button("Kill my self");
		btnExit.setLayoutX(200);
		btnExit.setLayoutY(100);

		btn.setOnAction((evt) -> label.setText(label.getText() + " :)"));
		btnExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		Pane root = new Pane();

		root.getChildren().add(label);
		root.getChildren().add(btn);
		root.getChildren().add(btnExit);

		/**
		 * Main window
		 */
		Scene scene = new Scene(root);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setOpacity(0.8);
		// primaryStage.resizableProperty().setValue(Boolean.FALSE);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Test appsa'as");
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(400);
		// primaryStage.setFullScreen(true);

		primaryStage.setScene(scene);
		primaryStage.show();

		scene.setOnKeyPressed((evt) -> {
			if (KeyCode.ESCAPE.equals(evt.getCode())) {
				System.exit(0);
			}

		});

	}
}
