package com.owr.games.ships.client.runner.app;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SplashScreen extends Preloader {

	private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.initStyle(StageStyle.UNDECORATED);
		this.stage.setScene(createScene());
		this.stage.setAlwaysOnTop(true);
//		this.stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.png")));
		this.stage.getIcons().add(new Image("torp.png"));
		this.stage.show();

	}

	public Scene createScene() {
		Pane root = new Pane();
		// root.setStyle("-fx-background-color: transparent;");
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("logo.jpg"));
		ImageView imgView = new ImageView(image);

		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		Text title = new Text("Ships battle");
		title.setFont(new Font(40));
		title.setFill(Color.WHITE);

		title.setLayoutX(50);
		title.setLayoutY(100);

		Text version = new Text("v0.8");
		version.setFont(new Font(10));
		version.setFill(Color.WHITE);

		version.setLayoutX(50);
		version.setLayoutY(120);

		root.getChildren().addAll(imgView, title, version);

		Scene scene = new Scene(root, image.getWidth(), image.getHeight());
		//scene.setFill(Color.TRANSPARENT);

		return scene;
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification evt) {
		if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {

			// Run in thread cause need to release main form creation process
			new Thread(() -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Platform.runLater(() -> stage.hide());

			}).start();

		}
	}

}
