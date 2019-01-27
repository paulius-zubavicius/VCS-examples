package com.vcs.fx.canvas.snake.game;

import java.io.File;
import java.util.Random;

import com.vcs.fx.canvas.snake.Snake;
import com.vcs.fx.canvas.snake.model.Dir;
import com.vcs.fx.canvas.snake.model.SnPoint;
import com.vcs.fx.canvas.snake.utils.SpriteLoader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SnakeGame extends Application {

	private static final int SIZE_W = 15;
	private static final int SIZE_H = 15;

	private static final double PIXELS = 50;

	private static final double W_PIXELS = SIZE_W * PIXELS;
	private static final double H_PIXELS = SIZE_H * PIXELS;
	private static final long NANOS_DIVEDER = 1000000000L;

	private long lastTick = 0;
	private Dir playerInput = null;
	private boolean gameRunning = true;
	private Image[] snakeSprite;
	private double obsticleX = W_PIXELS;
	private double obsticleY = 0;
	private int obsticleImgIndex = 0;
	private Random rnd = new Random();
	private boolean obsticlesEnable = true;

	private Snake snake = new Snake(SIZE_W, SIZE_H);

	/***
	 * 
	 * 
	 * - Obuoliai - Pailgejimas ir pagreitejimas
	 * 
	 * - Snd fx - fonine muzika
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {

		/**
		 * Load snake sprite
		 */
		snakeSprite = SpriteLoader.loadSprite("data/snake-graphics.png", 5, 4);

		/**
		 * Layers
		 */
		Canvas canvasSnake = new Canvas(W_PIXELS, H_PIXELS);
		Canvas canvasBg = new Canvas(W_PIXELS, H_PIXELS);
		Canvas canvasObs = new Canvas(W_PIXELS, H_PIXELS);
		canvasObs.setOpacity(0.9);

		GraphicsContext gcSnake = canvasSnake.getGraphicsContext2D();
		GraphicsContext gcBg = canvasBg.getGraphicsContext2D();
		GraphicsContext gcObs = canvasObs.getGraphicsContext2D();

		Image[] obsticles = new Image[] { new Image(new File("data/leaf.png").toURI().toString()),
				new Image(new File("data/leaf2.png").toURI().toString()),
				new Image(new File("data/cloud.png").toURI().toString()),
				new Image(new File("data/cloud2.png").toURI().toString()),
				new Image(new File("data/face.png").toURI().toString()),
				new Image(new File("data/poop.png").toURI().toString()),
				new Image(new File("data/skull.png").toURI().toString()),
				new Image(new File("data/spongebob.png").toURI().toString()),
				new Image(new File("data/spongebob.png").toURI().toString()) };

		gcBg.drawImage(new Image(new File("data/grass.jpeg").toURI().toString()), 0, 0, W_PIXELS, H_PIXELS);
		//
		// gcBg.drawImage(new
		// Image("https://g2.dcdn.lt/images/pix/iskander-sparnuotosios-raketos-paleidimas-72087510.jpg"),
		// 0, 0, W_PIXELS, H_PIXELS);
		Pane root = new Pane(canvasBg, canvasSnake, canvasObs);

		Scene scene = new Scene(root);
		primaryStage.resizableProperty().setValue(Boolean.FALSE);
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Esc - quit; R - reset; O - on/off obsticles;");
		primaryStage.setMinWidth(W_PIXELS);
		primaryStage.setMinHeight(H_PIXELS);
		// primaryStage.setFullScreen(true);

		primaryStage.setScene(scene);
		primaryStage.show();

		long startTime = System.currentTimeMillis();

		gcSnake.setLineWidth(1.0);
		gcSnake.setStroke(Color.YELLOW);
		gcSnake.setFont(Font.font(16));

		gcObs.setLineWidth(1.0);
		gcObs.setStroke(Color.YELLOW);
		gcObs.setFont(Font.font(16));

		(new AnimationTimer() {

			@Override
			public void handle(long currentTimeNanos) {

				if (gameRunning) {
					long tick = (currentTimeNanos - startTime) / (NANOS_DIVEDER - snake.getLevelSpeed() * 100L);

					if (lastTick != tick) {

						if (!snake.move(playerInput)) {
							gameRunning = false;
							return;
						}

						playerInput = null;
						lastTick = tick;
					}

					/**
					 * Clear
					 */
					gcSnake.clearRect(0, 0, W_PIXELS, H_PIXELS);

					/**
					 * Snake
					 */
					gcSnake.drawImage(snakeSprite[15], PIXELS * snake.getApple().getX(),
							PIXELS * snake.getApple().getY(), PIXELS, PIXELS);

					drawSnakePoint(snake.getSnPoints().get(0), gcSnake, false, true);
					for (int i = 1; i < snake.getSnPoints().size() - 1; i++) {
						drawSnakePoint(snake.getSnPoints().get(i), gcSnake, false, false);
					}
					drawSnakePoint(snake.getSnPoints().get(snake.getSnPoints().size() - 1), gcSnake, true, false);

					/**
					 * Info
					 */
					gcSnake.strokeText("Level: " + snake.getLevel(), 10, 20);

					/**
					 * Obst
					 */
					if (obsticlesEnable) {
						obsticleX += 3;
						if (obsticleX > W_PIXELS) {

							obsticleImgIndex = rnd.nextInt(obsticles.length);

							obsticleX = -obsticles[obsticleImgIndex].getWidth();
							obsticleY = rnd.nextInt((int) H_PIXELS);
						}

						gcObs.clearRect(obsticleX, obsticleY, obsticles[obsticleImgIndex].getWidth(),
								obsticles[obsticleImgIndex].getHeight());
						gcObs.drawImage(obsticles[obsticleImgIndex], obsticleX, obsticleY);
					} else {
						gcObs.clearRect(0, 0, W_PIXELS, H_PIXELS);
					}

				} else {
					gcObs.setFont(Font.font(24));
					gcObs.strokeText("This is it... press R to reset", W_PIXELS / 2 - 150, H_PIXELS / 2);
				}

			}
		}).start();

		scene.setOnKeyPressed((evt) -> {

			if (KeyCode.LEFT.equals(evt.getCode())) {
				playerInput = Dir.LEFT;
			}

			if (KeyCode.RIGHT.equals(evt.getCode())) {
				playerInput = Dir.RIGHT;
			}

			if (KeyCode.UP.equals(evt.getCode())) {
				playerInput = Dir.UP;
			}

			if (KeyCode.DOWN.equals(evt.getCode())) {
				playerInput = Dir.DOWN;
			}

			if (KeyCode.R.equals(evt.getCode())) {
				snake.reset();
				lastTick = 0;
				playerInput = null;
				gameRunning = true;
			}

			if (KeyCode.O.equals(evt.getCode())) {
				obsticlesEnable = !obsticlesEnable;
			}

			if (KeyCode.ESCAPE.equals(evt.getCode())) {
				System.exit(0);
			}

		});

	}

	private void drawSnakePoint(SnPoint snPoint, GraphicsContext gc, boolean head, boolean tail) {
		int spIndex = 15;

		if (tail) {
			switch (snPoint.getDirection()) {
			case DOWN:
				spIndex = 19;
				break;
			case UP:
				spIndex = 13;
				break;
			case LEFT:
				spIndex = 18;
				break;
			case RIGHT:
				spIndex = 14;
				break;
			}
		} else if (head) {
			switch (snPoint.getDirection()) {
			case DOWN:
				spIndex = 9;
				break;
			case UP:
				spIndex = 3;
				break;
			case LEFT:
				spIndex = 8;
				break;
			case RIGHT:
				spIndex = 4;
				break;
			}
		} else {
			switch (snPoint.getOrientation()) {
			case LEFT_RIGHT:
				spIndex = 1;
				break;
			case UP_DOWN:
				spIndex = 7;
				break;
			case DOWN_LEFT:
				spIndex = 2;
				break;
			case DOWN_RIGHT:
				spIndex = 0;
				break;
			case UP_LEFT:
				spIndex = 12;
				break;
			case UP_RIGHT:
				spIndex = 5;
				break;
			}
		}

		gc.drawImage(snakeSprite[spIndex], PIXELS * snPoint.getX(), PIXELS * snPoint.getY(), PIXELS, PIXELS);
	}

}
