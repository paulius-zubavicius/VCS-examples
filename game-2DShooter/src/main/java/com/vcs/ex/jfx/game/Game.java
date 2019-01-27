package com.vcs.ex.jfx.game;

import com.vcs.ex.jfx.game.config.ResolutionMode;
import com.vcs.ex.jfx.game.guns.bag.GunsBackPackHandler;
import com.vcs.ex.jfx.game.layers.LayersHandler;
import com.vcs.ex.jfx.game.layers.l1_gun.GunsLayer;
import com.vcs.ex.jfx.game.layers.l2_obs.ObstaclesLayer;
import com.vcs.ex.jfx.game.layers.l3_target.TargetsLayer;
import com.vcs.ex.jfx.game.layers.l4_bg.BgLayer;
import com.vcs.ex.jfx.game.music.MusicPlayer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {

	private ResolutionMode resMode = ResolutionMode.RES_800x600;

	@Override
	public void start(Stage primaryStage) {

		final long startNanoTime = System.nanoTime();

		/**
		 * Back ground music
		 */
		MusicPlayer mp = new MusicPlayer();
		mp.playNext();

		/**
		 * Layers and game objects
		 */
		GunsBackPackHandler backPackHandler = new GunsBackPackHandler();
		LayersHandler layers = new LayersHandler();

		layers.registerLayer(new BgLayer());
		layers.registerLayer(new TargetsLayer());
		layers.registerLayer(new ObstaclesLayer());
		layers.registerLayer(new GunsLayer(backPackHandler));

		Pane root = layers.createLayeredCanvasPane(resMode, startNanoTime);

		/**
		 * Main window
		 */
		Scene scene = new Scene(root);
		// primaryStage.setAlwaysOnTop(true);
		// primaryStage.setOpacity(0.5);
		// primaryStage.resizableProperty().setValue(Boolean.FALSE);
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setMinWidth(resMode.getW());
		primaryStage.setMinHeight(resMode.getH());
		// primaryStage.setFullScreen(true);

		primaryStage.setScene(scene);
		primaryStage.show();

		/**
		 * Event handlers / inputs
		 */

		scene.setOnMousePressed((evt) -> {
			if (MouseButton.PRIMARY.equals(evt.getButton())) {
				backPackHandler.getGun().shoot(true, evt.getX(), evt.getY(), layers);
			}
		});

		scene.setOnMouseReleased((evt) -> {
			if (MouseButton.PRIMARY.equals(evt.getButton())) {
				backPackHandler.getGun().shoot(false, evt.getX(), evt.getY(), layers);
			}
		});

		scene.setOnScroll((evt) -> backPackHandler.changeGun(evt.getTextDeltaY() > 0));
		scene.setOnZoom((evt) -> backPackHandler.changeGun(evt.getZoomFactor() > 0));
		scene.addEventFilter(MouseEvent.DRAG_DETECTED, (evt) -> scene.startFullDrag());
		scene.setOnMouseDragOver((evt) -> backPackHandler.getGun().shoot(true, evt.getX(), evt.getY(), layers));

		scene.setOnKeyPressed((evt) -> {
			if (KeyCode.ESCAPE.equals(evt.getCode())) {
				System.exit(0);
			}

		});

		/**
		 * Game cycle
		 */

		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				layers.updateTimer(currentNanoTime);
			}
		}.start();
	}

}
