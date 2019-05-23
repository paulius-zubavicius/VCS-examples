package com.vcs.fx.game;

import com.vcs.fx.game.layers.BGLayer;
import com.vcs.fx.game.layers.ShipsLayer;
import com.vcs.fx.game.layers.ShootsLayer;
import com.vcs.fx.game.model.MoveDirection;
import com.vcs.fx.game.model.Resolutions;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;

public class Game extends Application {

    private Resolutions res = Resolutions.RES_400_600;
    private MoveDirection playerDirection = MoveDirection.NONE;
    private boolean playerShooting = false;
    private boolean keyDown = false;
    private boolean keyUp = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;


    /**
     * To run: mvn clean javafx:run
     */

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Space invaders v0.8 alfa");
        primaryStage.setMinWidth(res.getW());
        primaryStage.setMinHeight(res.getH());
        // primaryStage.setMaxWidth(RES_W);
        // primaryStage.setMaxHeight(RES_H);

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setAlwaysOnTop(true);

        Media sound = new Media(new File("snd/bg.wav").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();

        BGLayer gb = new BGLayer();
        ShipsLayer shipsLayer = new ShipsLayer();

        gb.init(res);
        shipsLayer.init(res);

        ShootsLayer shootsLayer = new ShootsLayer();
        shootsLayer.init(res);

        Pane root = new Pane(gb.getCanvas(), shipsLayer.getCanvas(), shootsLayer.getCanvas());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyReleased((evt) -> {

            if (evt.getCode().isArrowKey()) {
                playerDirection = detectDirection(evt, false);
            }

            if (KeyCode.SPACE.equals(evt.getCode())) {
                playerShooting = false;
            }

        });

        scene.setOnKeyPressed((evt) -> {

            if (evt.getCode().isArrowKey()) {
                playerDirection = detectDirection(evt, true);
            }

            if (KeyCode.SPACE.equals(evt.getCode())) {
                playerShooting = true;
            }

            if (KeyCode.R.equals(evt.getCode())) {
                // TODO reset the game
            }

            if (KeyCode.ESCAPE.equals(evt.getCode())) {
                System.exit(0);
            }

        });

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                gb.updateTime(now);
                if (playerShooting) {
                    shootsLayer.shooting(shipsLayer.getPlayerShip());
                }
                shipsLayer.pressedTheButton(playerDirection, now);
                shipsLayer.updateTime(now);
            }
        }.start();

    }

    private MoveDirection detectDirection(KeyEvent evt, boolean pressed) {

        if (KeyCode.LEFT.equals(evt.getCode())) keyLeft = pressed;
        if (KeyCode.RIGHT.equals(evt.getCode())) keyRight = pressed;
        if (KeyCode.UP.equals(evt.getCode())) keyUp = pressed;
        if (KeyCode.DOWN.equals(evt.getCode())) keyDown = pressed;


        if (!keyLeft && !keyRight && !keyUp && !keyDown) {
            return MoveDirection.NONE;
        }

        if (keyLeft == keyRight) {
            if (keyUp && !keyDown) return MoveDirection.UP;
            if (!keyUp && keyDown) return MoveDirection.DOWN;
            return MoveDirection.NONE;
        }

        if (keyUp == keyDown) {
            if (keyLeft && !keyRight) return MoveDirection.LEFT;
            if (!keyLeft && keyRight) return MoveDirection.RIGHT;
            return MoveDirection.NONE;
        }

        if (!keyLeft && keyRight) {
            if (keyUp && !keyDown) return MoveDirection.UP_RIGHT;
            if (!keyUp && keyDown) return MoveDirection.DOWN_RIGHT;
            return MoveDirection.RIGHT;
        }

        if (keyLeft && !keyRight) {
            if (keyUp && !keyDown) return MoveDirection.UP_LEFT;
            if (!keyUp && keyDown) return MoveDirection.DOWN_LEFT;
            return MoveDirection.LEFT;
        }


        throw new RuntimeException();
    }

}
