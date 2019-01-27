package com.vcs.ex.jfx.game.layers;

import com.vcs.ex.jfx.game.shotimpact.ShotImpact;

public interface ShootingToTarget {

	/**
	 * It will call the current shooting gun. So many times as needs.
	 */
	ShotImpact shotImpact(double x, double y, ShotImpact impact);
}
