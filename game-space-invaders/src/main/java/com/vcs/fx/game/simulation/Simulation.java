package com.vcs.fx.game.simulation;

import com.vcs.fx.game.loaders.EnemiesLoader;
import com.vcs.fx.game.model.*;
import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;
import com.vcs.fx.game.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation implements ISimulation {


    private static final long EN_SHOOT_FREQUENCY = 500L;
    private static final String PLAYER_SHIP = "img/player1.png";

    private Resolutions res;
    private Random rnd = new Random();
    private double wasLastShot;

    private List<Shoot> shoots = new ArrayList<>();
    private PlayerSpaceShip player;
    private List<EnemySpaceShip> enemies;

    @Override
    public void init(Resolutions res) {
        this.res = res;


        enemies = new EnemiesLoader().load(res);

//        Rectangle playerMoveBounds = new Rectangle(new Point(0, res.getH() / 2), res.getH() / 2, res.getW());
        Rectangle playerMoveBounds = new Rectangle(new Point(0, 0), res.getH(), res.getW());

        player = new PlayerSpaceShip(new Point(res.getW() / 2, res.getH() - 50), ResourceUtil.loadImg(PLAYER_SHIP), playerMoveBounds);
    }

    public void playerShooting(long now) {
        shoots.addAll(player.createShoot(now));
    }

    public void pressedTheButton(MoveDirection direction, long now) {
        player.move(direction);
        player.doPhisics(now);
    }

    public void changeGun(int number) {
        System.out.println(number);
        player.selectGun(Gun.values()[number - 1]);
    }

    @Override
    public void updateTime(long now) {

        shoots.removeIf(shoot -> !isInside(shoot.getPosition(), res.getW(), res.getH()) || !shoot.isItEnergy());
        enemies.removeIf(ship -> ship.isDead());

        enemies.forEach(ship -> {

            if (now - wasLastShot > (rnd.nextInt(1100000) + 500000) * EN_SHOOT_FREQUENCY) {
                shoots.addAll(ship.createShoot(now));
                wasLastShot = now;
            }

            ship.doPhisics(now);
        });

        shoots.forEach(shoot -> shoot.getPosition().move((Team.ENEMY.equals(shoot.getTeam()) ? shoot.getSpeed() : -shoot.getSpeed())));

        collisions();

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

    private void collisions() {
        //shoots.stream().filter(shoot -> Team.ALIAS.equals(shoot.getTeam()) ).forEach(shoot -> enemies.);

        for (Shoot shoot : shoots) {
            if (Team.ALIAS.equals(shoot.getTeam())) {
                for (EnemySpaceShip ship : enemies) {
                    if (ship.getPosition().isInside(shoot.getPosition())) {

                        // bullet calcs
                        // damaging ship and calc bullet energy
                        shoot.setEnergy(ship.energyAfterShot(shoot.getEnergy()));

                        // if it still have a energy - just slowdown
                        shoot.setSpeed(shoot.getSpeed() * 0.5);

                    }
                }
            } else {
                if (player.getPosition().isInside(shoot.getPosition())) {
                    System.out.println("you lost");
                    //FIXME callback to main cycle
                }
            }
        }
    }

    private boolean isInside(Point sp, double w, double h) {
        return (sp.getY() > 0 && sp.getY() < h) && (sp.getX() > 0 && sp.getX() < w);
    }


}
