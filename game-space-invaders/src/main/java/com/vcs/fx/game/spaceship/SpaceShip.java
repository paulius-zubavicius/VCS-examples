package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Team;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Rectangle;

public abstract class SpaceShip implements ISpaceShip {

    private Team team = Team.ENEMY;
    protected Rectangle pos;
    private Point canonPos;
    private double health = 10;

    public SpaceShip(Rectangle pos, Team team) {
        this.pos = pos;
        this.team = team;
        canonPos = new Point(0,0);
        updateCanonPos();
    }

    public Rectangle getPosition() {
        return pos;
    }

    public boolean isDead() {
        return  health < 0;
    }

    public double energyAfterShot(double energy) {
        health -= energy;
        if (isDead()) {
            return - health;
        }
        return 0;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public Point getCanonPosition() {

        updateCanonPos();

        return canonPos;
    }

    private void updateCanonPos() {
        if (Team.ALIAS.equals(team)) {
            canonPos.setY(pos.getPos1().getY());
        } else {
            canonPos.setY(pos.getPos2().getY());
        }

        canonPos.setX(pos.getPos1().getX() + pos.getWeight() / 2);
    }

}
