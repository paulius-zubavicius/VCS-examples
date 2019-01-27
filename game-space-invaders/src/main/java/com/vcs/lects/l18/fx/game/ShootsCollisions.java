package com.vcs.lects.l18.fx.game;

import com.vcs.lects.l18.fx.game.model.Allies;
import com.vcs.lects.l18.fx.game.model.Point;
import com.vcs.lects.l18.fx.game.model.Resolutions;
import com.vcs.lects.l18.fx.game.model.Shoot;
import java.util.ArrayList;
import java.util.List;

public class ShootsCollisions {

    private Resolutions res;

    public ShootsCollisions(Resolutions res) {
        this.res = res;
    }

    void doPhisics(long now, List<Shoot> shootsSpace, Allies alies) {

        Shoot shoot = null;

        for (int i = 0; i < shootsSpace.size(); i++) {
            shoot = shootsSpace.get(i);

            // Ar vis dar ekrane
            if (isInside(shoot.getPosition(), res.getW(), res.getH())) {
                // Pajudina suvi
                if (Allies.ENEMY.equals(alies)) {
                    shoot.setPosition(shoot.getPosition().move(shoot.getSpeed()));
                } else {
                    shoot.setPosition(shoot.getPosition().move(-shoot.getSpeed()));
                }

                // Check colision
//                if () {
//
//                }
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
