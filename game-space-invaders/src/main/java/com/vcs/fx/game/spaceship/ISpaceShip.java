package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Gun;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Shoot;
import com.vcs.fx.game.model.Team;
import javafx.scene.image.Image;

import java.util.Collection;

public interface ISpaceShip {

    Team getTeam();

    void doPhisics(long now);

//    Point getCanonPosition();
    /**
     * canonNb - 0: central canon
     * -1;1 : left right
     * -2;2 : more left right
     * ... and so on
     */
    double getCannonX(int canonNb);
    double getCannonY();

    Collection<? extends Shoot> createShoot(long now);
    Gun getGun();
    boolean selectGun(Gun gun);

    Image getImg();
}
