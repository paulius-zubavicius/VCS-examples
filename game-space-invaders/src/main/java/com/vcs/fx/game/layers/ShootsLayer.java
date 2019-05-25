package com.vcs.fx.game.layers;

import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Resolutions;
import com.vcs.fx.game.model.Shoot;
import com.vcs.fx.game.model.Team;
import com.vcs.fx.game.simulation.ISimulation;
import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.ISpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ShootsLayer implements IGCLayer {

    private Canvas canvas;
    private GraphicsContext gc;
    private Resolutions res;

    private List<Shoot> shoots;

    public ShootsLayer(ISimulation simulation) {
        this.shoots = simulation.getShoots();
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
        gc.setFill(Color.YELLOW);

        shoots.forEach(shoot -> gc.fillRect(shoot.getPosition().getX(), shoot.getPosition().getY(), 2, 15));
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

}
