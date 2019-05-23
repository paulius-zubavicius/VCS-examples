package com.vcs.fx.game.layers;

import java.util.ArrayList;
import java.util.List;

import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;
import com.vcs.fx.game.model.Allies;
import com.vcs.fx.game.model.MoveDirection;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Rectangle;
import com.vcs.fx.game.model.Resolutions;
import com.vcs.fx.game.utils.ResourceUtil;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ShipsLayer implements ILayer {

    private static final String PLAYER_SHIP = "img/player.png";
    private static final int SHIP_SIZE = 50;
    private static final String ENEMY_SHIP = "img/enemies.png";
    private Canvas canvas;
    private GraphicsContext gc;
    private Resolutions res;

    private PlayerSpaceShip player;

    private Image playerShipImg;
    private Image[] enemyShipImg;

    private List<EnemySpaceShip> ships;

    @Override
    public void init(Resolutions res) {

        this.res = res;

        playerShipImg = ResourceUtil.loadImg(PLAYER_SHIP);
        enemyShipImg = ResourceUtil.loadSpriteArr(ENEMY_SHIP, 8, 3);

        Point pos = new Point(res.getW() / 2 - SHIP_SIZE / 2, res.getH() - SHIP_SIZE);

        Rectangle playerMoveBounds = new Rectangle(new Point(0, res.getH() / 2), res.getH() / 2, res.getW());
        player = new PlayerSpaceShip(new Rectangle(pos, SHIP_SIZE, SHIP_SIZE), Allies.ALIAS, playerMoveBounds);

        ships = new ArrayList<>();

        EnemiesLoader enemies = new EnemiesLoader();
        ships.addAll(enemies.load(res));

        canvas = new Canvas(res.getW(), res.getH());
        gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
    }

    @Override
    public void updateTime(long now) {

        Point p = null;
        int w = 0;
        int h = 0;

        gc.clearRect(0, 0, res.getW(), res.getH());

        for (EnemySpaceShip ship : ships) {

            p = ship.getPosition().getPos1();
            h = ship.getPosition().getHeight();
            w = ship.getPosition().getWeight();

            ship.doPhisics(now);

            drawShip(p, h, w, enemyShipImg[ship.getShipType()]);

        }

        drawShip(player.getPosition().getPos1(), player.getPosition().getHeight(), player.getPosition().getWeight(),
                playerShipImg);

    }

    private void drawShip(Point p, int h, int w, Image img) {
        // gc.clearRect(p.getX(), p.getY(), w, h);
        gc.drawImage(img, p.getX(), p.getY(), w, h);
    }

    public void pressedTheButton(MoveDirection direction, long now) {

        player.move(direction);
        player.doPhisics(now);

    }

    public PlayerSpaceShip getPlayerShip() {
        return player;
    }

    @Override
    public Canvas getCanvas() {

        return canvas;
    }

}
