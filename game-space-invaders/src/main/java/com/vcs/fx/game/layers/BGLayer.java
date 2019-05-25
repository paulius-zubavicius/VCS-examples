package com.vcs.fx.game.layers;

import com.vcs.fx.game.model.Resolutions;
import com.vcs.fx.game.utils.ResourceUtil;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BGLayer implements IGCLayer {

	private static final String BG_RESOURCE = "img/bg.jpeg";

	private static final int SKIP_FRAMES = 10;

	private Canvas canvas;
	private GraphicsContext gc;
	private Resolutions res;

	private Image bgImg;

	private int bgY;

	private int skipFrameCounter;

	@Override
	public void init(Resolutions res) {

		this.res = res;
		bgImg = ResourceUtil.loadImg(BG_RESOURCE);
		canvas = new Canvas(res.getW(), res.getH());
		gc = canvas.getGraphicsContext2D();

		bgY = 0;
		skipFrameCounter = 0;
	}

	@Override
	public void updateTime(long now) {

		if (skipFrameCounter > SKIP_FRAMES) {
			bgY += 1;

			gc.drawImage(bgImg, 0, bgY, res.getW(), res.getH());

			gc.drawImage(bgImg, 0, bgY - res.getH(), res.getW(), res.getH());

			if (bgY > res.getH()) {
				bgY = 0;
			}

			skipFrameCounter = 0;

		}

		skipFrameCounter++;

	}

	@Override
	public Canvas getCanvas() {

		return canvas;
	}

}
