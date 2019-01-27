package com.vcs.lects.l18.fx.game.app;

import com.vcs.lects.l18.fx.game.layers.BGLayer;
import com.vcs.lects.l18.fx.game.layers.ShipsLayer;
import com.vcs.lects.l18.fx.game.layers.ShootsLayer;
import com.vcs.lects.l18.fx.game.model.MoveDirection;
import com.vcs.lects.l18.fx.game.model.Resolutions;
import com.vcs.lects.l18.fx.game.sndfx.MusicPlayer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    private Resolutions res = Resolutions.RES_400_600;

    private Pane root = null;

    private MoveDirection playerDirection = MoveDirection.NONE;
    private boolean playerShooting = false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Space invaders v0.8 alfa ... preorder NOW!!!");
        primaryStage.setMinWidth(res.getW());
        primaryStage.setMinHeight(res.getH());
        // primaryStage.setMaxWidth(RES_W);
        // primaryStage.setMaxHeight(RES_H);

        // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setAlwaysOnTop(true);

        /**
         *
         */
        MusicPlayer mPlayer = new MusicPlayer();
        mPlayer.playNext();

        BGLayer gb = new BGLayer();
        ShipsLayer shipsLayer = new ShipsLayer();

        gb.init(res);
        shipsLayer.init(res);

        ShootsLayer shootsLayer = new ShootsLayer();
        shootsLayer.init(res);

        root = new Pane(gb.getCanvas(), shipsLayer.getCanvas(), shootsLayer.getCanvas());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyReleased((evt) -> {

            if (KeyCode.LEFT.equals(evt.getCode())
                    || KeyCode.RIGHT.equals(evt.getCode())
                    || KeyCode.UP.equals(evt.getCode())
                    || KeyCode.DOWN.equals(evt.getCode())) {

                playerDirection = MoveDirection.NONE;
            }

            if (KeyCode.SPACE.equals(evt.getCode())) {
                playerShooting = false;
            }

        });

        scene.setOnKeyPressed((evt) -> {
            if (KeyCode.LEFT.equals(evt.getCode())) {
                playerDirection = MoveDirection.LEFT;
            }

            if (KeyCode.RIGHT.equals(evt.getCode())) {
                playerDirection = MoveDirection.RIGHT;
            }

            if (KeyCode.UP.equals(evt.getCode())) {
                playerDirection = MoveDirection.UP;
            }

            if (KeyCode.DOWN.equals(evt.getCode())) {
                playerDirection = MoveDirection.DOWN;
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

}
