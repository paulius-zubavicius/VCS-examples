package com.vcs.fx.game.simulation;

import com.vcs.fx.game.loaders.EnemiesLoader;
import com.vcs.fx.game.model.*;
import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation implements ISimulation {

    private static final double SHOT_SPEED = 0.7;
    private static final double SHOT_ENERGY = 3.0;

    private static final double EN_SHOT_SPEED = 0.4;
    private static final double EN_SHOT_ENERGY = 1.0;

    private static final int SHIP_SIZE = 50;

    private static final long SHOOT_FREQUENCY = 1000000000L;
    private static final long SHOOT_FREQUENCY_MAX = 100000000L * 20;

    private Resolutions res;

    private Random rnd = new Random();

    private List<Shoot> shoots = new ArrayList<>();
    private PlayerSpaceShip player;
    private List<EnemySpaceShip> enemies;

    @Override
    public void init(Resolutions res) {
        this.res = res;

        EnemiesLoader loader = new EnemiesLoader();
        enemies = loader.load(res);

        Point pos = new Point(res.getW() / 2 - SHIP_SIZE / 2, res.getH() - SHIP_SIZE);
        Rectangle playerMoveBounds = new Rectangle(new Point(0, res.getH() / 2), res.getH() / 2, res.getW());
        player = new PlayerSpaceShip(new Rectangle(pos, SHIP_SIZE, SHIP_SIZE), Team.ALIAS, playerMoveBounds);
    }

    public void playerShooting() {
        shoots.add(new Shoot(player.getTeam(), player.getCanonPosition(), SHOT_SPEED, SHOT_ENERGY));
    }

    public void pressedTheButton(MoveDirection direction, long now) {
        player.move(direction);
        player.doPhisics(now);
    }

    @Override
    public List<Shoot> getShoots() {
        return shoots;
    }

    @Override
    public PlayerSpaceShip getPlayer() {
        return player;
    }

    @Override
    public List<EnemySpaceShip> getEnemies() {
        return enemies;
    }

    @Override
    public void updateTime(long now) {

        shoots.removeIf(shoot -> !isInside(shoot.getPosition(), res.getW(), res.getH()) || !shoot.isItWillHurt());
        enemies.removeIf(ship -> ship.isDead());

        enemies.forEach(ship ->  {

            if (now - ship.getWasLastShot() > (rnd.nextInt(11) + 5) * SHOOT_FREQUENCY) {
                shoots.add(new Shoot(Team.ENEMY, ship.getCanonPosition(), EN_SHOT_SPEED, EN_SHOT_ENERGY));
                ship.setWasLastShot(now);
            }

            ship.doPhisics(now);

        });

        shoots.forEach(shoot -> shoot.getPosition().move( (Team.ENEMY.equals(shoot.getTeam()) ? shoot.getSpeed() : - shoot.getSpeed())));

        collisions();

    }

    private void collisions() {
        //shoots.stream().filter(shoot -> Team.ALIAS.equals(shoot.getTeam()) ).forEach(shoot -> enemies.);

        for (Shoot shoot: shoots) {
            if (Team.ALIAS.equals(shoot.getTeam()) ) {
                for (EnemySpaceShip ship: enemies) {
                    if ( ship.getPosition().isInside(shoot.getPosition())) {

                        // bullet calcs
                        // damaging ship and calc bullet energy
                        shoot.setEnergy(ship.energyAfterShot(shoot.getEnergy()));

                        // if it still have a energy - just slowdown
                        shoot.setSpeed(shoot.getSpeed() * 0.5);

                    }
                }
            } else {
                if ( player.getPosition().isInside(shoot.getPosition())) {
                    System.out.println("you lost");
                    //FIXME callback to main cycle
                }
            }
        }
    }

    private boolean isInside(Point sp, double w, double h) {
        return (sp.getY() > 0 && sp.getY() < h) && (sp.getX() > 0 && sp.getX() <w);
    }
}
