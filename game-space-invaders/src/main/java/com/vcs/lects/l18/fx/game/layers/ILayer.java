package com.vcs.lects.l18.fx.game.layers;

import com.vcs.lects.l18.fx.game.model.Resolutions;

import javafx.scene.canvas.Canvas;

public interface ILayer {

	void init(Resolutions res);
	
	void updateTime(long now);

	Canvas getCanvas();
	

}
