package com.vcs.fx.game.layers;

import java.util.List;

import com.vcs.fx.game.simulation.ISimulation;
import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Resolutions;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ShipsLayer implements IGCLayer {

    private Canvas canvas;
    private GraphicsContext gc;
    private Resolutions res;
    private PlayerSpaceShip player;
    private List<EnemySpaceShip> ships;

    public ShipsLayer(ISimulation simulation) {
        this.ships = simulation.getEnemies();
        this.player = simulation.getPlayer();
    }

    @Override
    public void init(Resolutions res) {

        this.res = res;

        canvas = new Canvas(res.getW(), res.getH());
        gc = canvas.getGraphicsContext2D();

    }

    @Override
    public void updateTime(long now) {

        gc.clearRect(0, 0, res.getW(), res.getH());

        ships.forEach(ship -> drawShip(ship.getPosition().getPos1(), ship.getPosition().getHeight(), ship.getPosition().getWidth(), ship.getImg() )); //enemyShipImg[ship.getShipType()]

        drawShip(player.getPosition().getPos1(), player.getPosition().getHeight(), player.getPosition().getWidth(), player.getImg());

    }

    private void drawShip(Point p, double h, double w, Image img) {
        gc.drawImage(img, p.getX(), p.getY(), w, h);
    }

    @Override
    public Canvas getCanvas() {

        return canvas;
    }

}
