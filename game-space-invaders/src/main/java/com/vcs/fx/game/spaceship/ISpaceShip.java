package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Allies;
import com.vcs.fx.game.model.Point;

public interface ISpaceShip {

//	public void shoot();

	// public void move(MoveDirection direction);

	public Allies getAlies();

	void doPhisics(long now);
        
        Point getCanonPosition();

}
