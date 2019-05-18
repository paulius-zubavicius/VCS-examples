package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Allies;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Rectangle;

public abstract class SpaceShip implements ISpaceShip {

    private Allies alies = Allies.ENEMY;
    protected Rectangle pos;
    private Point canonPos;

    public SpaceShip(Rectangle pos, Allies alies) {
        this.pos = pos;
        this.alies = alies;
    }

    public Rectangle getPosition() {
        return pos;
    }

    @Override
    public Allies getAlies() {
        return alies;
    }

    @Override
    public Point getCanonPosition() {

        if (Allies.ALIES.equals(alies)) {
            canonPos.setY(pos.getPos1().getY());
        } else {
            canonPos.setY(pos.getPos2().getY());
        }

        canonPos.setX(pos.getPos1().getX() + pos.getWeight() / 2);

        return canonPos;
    }

}
