package com.vcs.ex.jfx.game.layers;

import java.util.ArrayList;
import java.util.List;

import com.vcs.ex.jfx.game.config.ResolutionMode;
import com.vcs.ex.jfx.game.shotimpact.ShotImpact;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class LayersHandler implements ShootingToTarget {

	private List<GraphicalLayer> layers = new ArrayList<>();

	public void registerLayer(GraphicalLayer layer) {
		layers.add(layer);
	}

	public Pane createLayeredCanvasPane(ResolutionMode resolutionMode, long startNanoTime) {
		Pane layeredCanvasPane = new Pane();
		Canvas canvas;
		for (GraphicalLayer l : layers) {
			canvas = new Canvas(resolutionMode.getW(), resolutionMode.getH());
			l.init(startNanoTime, canvas.getGraphicsContext2D());
			layeredCanvasPane.getChildren().add(canvas);

			l.reset();
		}

		return layeredCanvasPane;
	}

	public void updateTimer(long currentNanoTime) {
		for (GraphicalLayer graphicalLayer : layers) {
			graphicalLayer.updateTimer(currentNanoTime);
		}
	}

	@Override
	public ShotImpact shotImpact(double x, double y, ShotImpact impact) {

		for (int i = layers.size() - 1; i >= 0; i--) {
			impact = layers.get(i).shotImpact(x, y, impact);
		}

		return null;
	}

}
