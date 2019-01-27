package com.vcs.lects.l18.fx.game.model;

import java.util.ArrayList;
import java.util.List;

public class ShootsHandler {

    private static final int SHOT_SPEED = 10;
    
    private List<Shoot> shootsSpace = new ArrayList<>();

    private Resolutions res;

    public ShootsHandler(Resolutions res) {
        this.res = res;
    }

    public void shoot(Allies alies, Point position) {
        shootsSpace.add(new Shoot(alies, position, SHOT_SPEED));
    }

    public List<Shoot> getShoots() {
        return shootsSpace;
    }

    public void doPhisics(long now) {

        Shoot shoot = null;

        for (int i = 0; i < shootsSpace.size(); i++) {
            shoot = shootsSpace.get(i);

            // Ar vis dar ekrane
            if (isInside(shoot.getPosition(), res.getW(), res.getH())) {
                // Pajudina suvi
                if (Allies.ENEMY.equals(shoot.getAlies())) {
                    shoot.setPosition(shoot.getPosition().move(shoot.getSpeed()));
                } else {
                    shoot.setPosition(shoot.getPosition().move(-shoot.getSpeed()));
                }
            } else {
                // Ismetam is ekrano ribu
                shootsSpace.remove(i);
                i--;
            }
        }
    }

    private boolean isInside(Point sp, int w, int h) {
        return (sp.getY() > 0 && sp.getY() < h) && (sp.getX() > 0 && sp.getX() < w);
    }

}
