package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Allies;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Rectangle;

public abstract class SpaceShip implements ISpaceShip {

    private Allies alias = Allies.ENEMY;
    protected Rectangle pos;
    private Point canonPos;

    public SpaceShip(Rectangle pos, Allies alias) {
        this.pos = pos;
        this.alias = alias;
    }

    public Rectangle getPosition() {
        return pos;
    }

    @Override
    public Allies getAlies() {
        return alias;
    }

    @Override
    public Point getCanonPosition() {

        if (Allies.ALIAS.equals(alias)) {
            canonPos.setY(pos.getPos1().getY());
        } else {
            canonPos.setY(pos.getPos2().getY());
        }

        canonPos.setX(pos.getPos1().getX() + pos.getWeight() / 2);

        return canonPos;
    }

}
