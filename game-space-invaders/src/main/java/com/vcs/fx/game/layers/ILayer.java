package com.vcs.fx.game.layers;

import com.vcs.fx.game.model.Resolutions;

import javafx.scene.canvas.Canvas;

public interface ILayer {

	void init(Resolutions res);
	
	void updateTime(long now);

	Canvas getCanvas();
	

}
