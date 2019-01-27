package com.vcs.ex.jfx.game.guns.bag;

import java.util.ArrayList;
import java.util.List;

import com.vcs.ex.jfx.game.guns.Gun;
import com.vcs.ex.jfx.game.layers.GraphicalInit;

import javafx.scene.canvas.GraphicsContext;

public class GunsBackPackHandler implements GraphicalInit {

	private List<Gun> guns = new ArrayList<>();
	private int index = 0;

	@Override
	public void init(long startNanoTime, GraphicsContext gc) {
		for (Gun gun : guns) {
			gun.init(startNanoTime, gc);
		}
		setCurrentUse();
	}

	public void addGun(Gun gun) {
		guns.add(gun);
	}

	public Gun getGun() {
		return guns.get(index);
	}

	public void changeGun(boolean next) {

		if (next) {
			if (index + 1 == guns.size()) {
				index = 0;
			} else {
				index++;
			}
		} else {
			if (index - 1 < 0) {
				index = guns.size() - 1;
			} else {
				index--;
			}
		}

		setCurrentUse();

	}

	private void setCurrentUse() {
		for (Gun gun : guns) {
			gun.use(false);
		}

		getGun().use(true);
	}

}
