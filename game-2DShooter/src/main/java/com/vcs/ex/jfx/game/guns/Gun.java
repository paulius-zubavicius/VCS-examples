package com.vcs.ex.jfx.game.guns;

import com.vcs.ex.jfx.game.layers.GraphicalInit;
import com.vcs.ex.jfx.game.layers.ShootingToTarget;

public interface Gun extends GraphicalInit{

	public void use(boolean useGun);

	public void shoot(boolean start, double x, double y, ShootingToTarget target);

	public void update(long currentNanoTime);

}
