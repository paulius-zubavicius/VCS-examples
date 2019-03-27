package com.owr.games.ships.client.runner.app;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxApp extends Application{

	private AnnotationConfigApplicationContext ctx = null;

	@Override
	public void init() {
		ctx = new AnnotationConfigApplicationContext();
		//ctx.getEnvironment().setActiveProfiles(Profiles.LOCAL);
		ctx.register(AppCtxConfig.class);
		ctx.refresh();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		/**
		 * Check the version
		 */
		//GuiSchService schService = ctx.getBean(GuiSchService.class);
		//schService.verifyVersion();

		MainWindow mainWin = ctx.getBean(MainWindow.class);

		// Creates main window pane
		BorderPane mainWinPane = new BorderPane();

		// ... pass it to scene
		mainWin.init(mainWinPane);
		Scene scene = new Scene(mainWinPane);
		// scene.

		// HD 720
		primaryStage.setMinWidth(1280);
		primaryStage.setMinHeight(720);
		primaryStage.setTitle("Ships battle");
		primaryStage.setScene(scene);
		primaryStage.setAlwaysOnTop(false);
		// primaryStage.getIcons().add(new
		// Image(getClass().getClassLoader().getResourceAsStream("icon.png")));
		primaryStage.getIcons().add(new Image("logo.jpg"));
		primaryStage.show();

		// Default first form
		//Nav nav = ctx.getBean(Nav.class);
		//nav.navClear(LoginForm.class);

		primaryStage.setOnCloseRequest((a) -> System.exit(0));

	}

	@Override
	public void stop() {
		ctx.close();
	}


}
