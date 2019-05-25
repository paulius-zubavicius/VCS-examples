package com.vcs.fx.game.layers;

import com.vcs.fx.game.model.Resolutions;
import com.vcs.fx.game.model.Shoot;
import com.vcs.fx.game.model.Team;
import com.vcs.fx.game.simulation.ISimulation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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


//        gc.setFill(Color.YELLOW);
        gc.setLineWidth(1);

        shoots.forEach(shoot -> {
                    if (Team.ALIAS.equals(shoot.getTeam())) {
                        gc.setFill(Color.YELLOW);
                        gc.setStroke(Color.RED);
                    } else {
                        gc.setFill(Color.WHITE);
                        gc.setStroke(Color.BLUE);
                    }
                    gc.fillRect(shoot.getPosition().getX(), shoot.getPosition().getY(), 3, shoot.getSpeed() + 15);
                    gc.strokeRect(shoot.getPosition().getX(), shoot.getPosition().getY(),  3, shoot.getSpeed() + 15);

//                    gc.beginPath();
//                    gc.moveTo(shoot.getPosition().getX(), shoot.getPosition().getY());
//                    gc.lineTo(shoot.getPosition().getX(), shoot.getPosition().getY() + shoot.getEnergy() * 10);
//                    gc.stroke();


                }
        );
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

}
