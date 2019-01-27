package com.vcs.ex.jfx.game.layers;

public interface GraphicalLayer extends GraphicalInit, ShootingToTarget {

	/**
	 * Updates the physics and graphics
	 */
	void updateTimer(long currentNanoTime);

	/**
	 * Clear the layer
	 */
	void reset();

}
