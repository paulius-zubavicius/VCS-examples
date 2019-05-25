package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Team;
import com.vcs.fx.game.model.Point;

public interface ISpaceShip {

//	public void shoot();

	// public void move(MoveDirection direction);

	public Team getTeam();

	void doPhisics(long now);
        
        Point getCanonPosition();

}
