package com.vcs.lects.l18.fx.game.spaceship;

import com.vcs.lects.l18.fx.game.model.Allies;
import com.vcs.lects.l18.fx.game.model.Point;

public interface ISpaceShip {

//	public void shoot();

	// public void move(MoveDirection direction);

	public Allies getAlies();

	void doPhisics(long now);
        
        Point getCanonPosition();

}
