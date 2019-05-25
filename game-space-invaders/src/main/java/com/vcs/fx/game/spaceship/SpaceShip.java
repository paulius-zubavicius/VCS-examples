package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class SpaceShip implements ISpaceShip {
    private static final int SHIP_SIZE = 50;
    private static final int SCALE_FACTOR = 2;

    private static final int CANNON_OFFSET = 5;

    private static final long EN_SHOOT_FREQUENCY = 10000L;
    private static final long SHOOT_FREQUENCY_MAX = 100000000L * 20;
    private static final long SHOOT_FREQUENCY = 100000000L;

    private Team team;
    protected Rectangle pos;
    protected Image img;
    private Gun gun = Gun.PEW_PEW;
    private long wasLastShot;


    private List<Shoot> shoots = new ArrayList<>();

    private double health = 10;


    public SpaceShip( Team team, Image img, double x, double y) {
        this.pos = new Rectangle(new Point(x , y), img.getHeight() / SCALE_FACTOR, img.getWidth() / SCALE_FACTOR);
        this.team = team;
        this.img = img;
    }

    public Rectangle getPosition() {
        return pos;
    }

    public boolean isDead() {
        return health < 0;
    }

    public double energyAfterShot(double energy) {
        health -= energy;
        if (isDead()) {
            return -health;
        }
        return 0;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public Image getImg() {
        return img;
    }

    @Override
    public double getCannonX(int cannonNb) {
        return (pos.getPos1().getX() + pos.getWidth() / 2.0) + (cannonNb * CANNON_OFFSET);
    }

    @Override
    public double getCannonY() {

        if (Team.ALIAS.equals(team)) {
            return pos.getPos1().getY();
        } else {
            return pos.getPos2().getY();
        }
    }

    @Override
    public Collection<? extends Shoot> createShoot(long now) {

        if (now - wasLastShot > gun.getFrequency()) {
            shoots.clear();

            for (int canPos : gun.getCanPos()) {
                shoots.add(new Shoot(team, getCannonX(canPos), getCannonY(), gun.getSpeed(), gun.getDamage()));
            }

            wasLastShot = now;
            return shoots;
        }

        shoots.clear();
        return shoots;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    @Override
    public boolean selectGun(Gun gun) {
        this.gun = gun;
        return false;
    }


}
