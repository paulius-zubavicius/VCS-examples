package com.vcs.fx.game.layers;

import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.ISpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;
import com.vcs.fx.game.model.Resolutions;
import com.vcs.fx.game.model.ShootsHandler;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class ShootsLayer implements ILayer {

    private ShootsHandler sHandler;

//    private List<EnemySpaceShip> ships;
//    private PlayerSpaceShip playerShip;
    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void updateTime(long now) {
        sHandler.doPhisics(now);
        
        
        
        
        
    }

    @Override
    public void init(Resolutions res) {
        sHandler = new ShootsHandler(res);

        canvas = new Canvas(res.getW(), res.getH());
        gc = canvas.getGraphicsContext2D();
    }

    public void shooting(ISpaceShip shooter) {
        sHandler.shoot(shooter.getAlies(), shooter.getCanonPosition());
    }

    public void checkShootCollitions(PlayerSpaceShip playerShip, List<EnemySpaceShip> ships) {

    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

}
