package com.vcs.fx.game.simulation;

import com.vcs.fx.game.layers.ILayer;
import com.vcs.fx.game.model.Shoot;
import com.vcs.fx.game.spaceship.EnemySpaceShip;
import com.vcs.fx.game.spaceship.PlayerSpaceShip;

import java.util.List;

public interface ISimulation extends ILayer {


     List<Shoot> getShoots();

     PlayerSpaceShip getPlayer() ;

     List<EnemySpaceShip> getEnemies();

}
