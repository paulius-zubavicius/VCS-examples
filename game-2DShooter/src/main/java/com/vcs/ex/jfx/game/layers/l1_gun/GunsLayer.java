package com.vcs.ex.jfx.game.layers.l1_gun;

import com.vcs.ex.jfx.game.guns.MachineGun;
import com.vcs.ex.jfx.game.guns.ShotGun;
import com.vcs.ex.jfx.game.guns.bag.GunsBackPackHandler;
import com.vcs.ex.jfx.game.layers.GraphicalLayer;
import com.vcs.ex.jfx.game.shotimpact.ShotImpact;

import javafx.scene.canvas.GraphicsContext;

public class GunsLayer implements GraphicalLayer {

	private GunsBackPackHandler backPackHandler;

	public GunsLayer(GunsBackPackHandler backPackHandler) {
		this.backPackHandler = backPackHandler;
	}

	@Override
	public void init(long startNanoTime, GraphicsContext gc) {

		backPackHandler.addGun(new MachineGun());
		backPackHandler.addGun(new ShotGun());

		backPackHandler.init(startNanoTime, gc);
		backPackHandler.changeGun(true);
	}

	@Override
	public void reset() {

	}

	@Override
	public void updateTimer(long currentNanoTime) {
		backPackHandler.getGun().update(currentNanoTime);

	}

	@Override
	public ShotImpact shotImpact(double x, double y, ShotImpact impact) {

		// Fire?
		// Smoke?

		return impact;
	}

}
