package com.vcs.fx.game.layers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.vcs.fx.game.loaders.EnemiesLoader;
import com.vcs.fx.game.simulation.ISimulation;
import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;
import com.vcs.fx.game.model.Team;
import com.vcs.fx.game.model.MoveDirection;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Rectangle;
import com.vcs.fx.game.model.Resolutions;
import com.vcs.fx.game.utils.ResourceUtil;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ShipsLayer implements IGCLayer {


    private static final String PLAYER_SHIP = "img/player.png";

    private static final String ENEMY_SHIP = "img/enemies.png";
    private Canvas canvas;
    private GraphicsContext gc;
    private Resolutions res;

    private PlayerSpaceShip player;

    private Image playerShipImg;
    private Image[] enemyShipImg;

    private List<EnemySpaceShip> ships;

    public ShipsLayer(ISimulation simulation) {
        this.ships = simulation.getEnemies();
        this.player = simulation.getPlayer();
    }

    @Override
    public void init(Resolutions res) {

        this.res = res;

        playerShipImg = ResourceUtil.loadImg(PLAYER_SHIP);
        enemyShipImg = ResourceUtil.loadSpriteArr(ENEMY_SHIP, 8, 3);

        canvas = new Canvas(res.getW(), res.getH());
        gc = canvas.getGraphicsContext2D();

//        gc.setFill(Color.GREEN);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(5);

//        wasLastShot = Instant.now().toEpochMilli();
    }

    @Override
    public void updateTime(long now) {

        gc.clearRect(0, 0, res.getW(), res.getH());

        ships.forEach(ship -> drawShip(ship.getPosition().getPos1(), ship.getPosition().getHeight(), ship.getPosition().getWeight(), enemyShipImg[ship.getShipType()]));

        drawShip(player.getPosition().getPos1(), player.getPosition().getHeight(), player.getPosition().getWeight(), playerShipImg);

    }

    private void drawShip(Point p, int h, int w, Image img) {
        gc.drawImage(img, p.getX(), p.getY(), w, h);
    }

    @Override
    public Canvas getCanvas() {

        return canvas;
    }

}
