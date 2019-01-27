package com.vcs.ex.jfx.game.layers;

import javafx.scene.canvas.GraphicsContext;

public interface GraphicalInit {
	/**
	 * Initialization
	 */
	void init(long startNanoTime, GraphicsContext gc);
}
