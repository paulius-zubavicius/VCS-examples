package com.vcs.ex.jfx.game.layers.l2_obs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vcs.ex.jfx.game.layers.GraphicalLayer;
import com.vcs.ex.jfx.game.resources.ImgRs;
import com.vcs.ex.jfx.game.shotimpact.ShotImpact;
import com.vcs.ex.jfx.game.utils.ImgLoader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObstaclesLayer implements GraphicalLayer {

	private static final double WIND_SPEED = 0.1;
	private static final double SEC = 1000000000.0;
	private static final long FX_SPEED = 6000000L;

	private Image cloud;
	private GraphicsContext gc;
	private long startNanoTime;
	private Random rnd;
	private Image[][] bulletSmoke = null;

	private double w;
	private double h;
	private double centerOffsetX;
	private double centerOffsetY;

	private ShootSmokePos fxPos;

	private List<ShootSmokePos> shootSmokePosPool = new ArrayList<>();

	private double x, y;

	@Override
	public void init(long startNanoTime, GraphicsContext gc) {
		this.gc = gc;
		this.startNanoTime = startNanoTime;
		this.cloud = ImgLoader.img(ImgRs.CLOUD2);

		bulletSmoke = new Image[4][];

		bulletSmoke[0] = ImgLoader.sprite(ImgRs.SMOKE1_1);
		bulletSmoke[1] = ImgLoader.sprite(ImgRs.SMOKE2_1);
		bulletSmoke[2] = ImgLoader.sprite(ImgRs.SMOKE2_2);
		bulletSmoke[3] = ImgLoader.sprite(ImgRs.SMOKE3_1);

		w = bulletSmoke[0][0].getWidth();
		h = bulletSmoke[0][0].getHeight();

		centerOffsetX = w / 2;
		centerOffsetY = h / 2;

		rnd = new Random(startNanoTime);
	}

	@Override
	public void reset() {
		x = 0;
		y = 0;
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.drawImage(cloud, 0, 0);
	}

	@Override
	public void updateTimer(long currentNanoTime) {
		double t = (currentNanoTime - startNanoTime) / SEC;

		// gc.clearRect(x, y, cloud.getWidth(), cloud.getHeight());

		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

		// boolean fxFlag = false;

		// Clear the smoke effects before repaint & remove old ones
		for (int i = 0; i < shootSmokePosPool.size(); i++) {
			fxPos = shootSmokePosPool.get(i);

			// gc.clearRect(fxPos.x - centerOffsetX - fxPos.cloudOffsetX + x,
			// fxPos.y - centerOffsetY - fxPos.cloudOffsetY + y, w, h);

			if (fxPos.valid) {

				if (currentNanoTime - fxPos.lastTime > FX_SPEED) {
					if (fxPos.spriteIndex < bulletSmoke[fxPos.spriteFxIndex].length - 1) {
						fxPos.spriteIndex = fxPos.spriteIndex + 1;
						// fxFlag = true;
					} else {
						fxPos.valid = false;
					}
				}

				fxPos.lastTime = currentNanoTime;
			} else {
				shootSmokePosPool.remove(i);
				i--;
			}

		}

		// for (ShootSmokePos pos : shootSmokePosPool) {
		// gc.clearRect(pos.x - centerOffsetX - pos.cloudOffsetX + x, pos.y -
		// centerOffsetY - pos.cloudOffsetY + y, w,
		// h);
		// }

		x += WIND_SPEED;
		y = 128 * Math.sin(t * WIND_SPEED);

		gc.drawImage(cloud, x, y);

		// if (fxFlag) {
		for (ShootSmokePos pos : shootSmokePosPool) {
			gc.drawImage(bulletSmoke[pos.spriteFxIndex][pos.spriteIndex], pos.x - centerOffsetX - pos.cloudOffsetX + x,
					pos.y - centerOffsetY - pos.cloudOffsetY + y, w, h);
		}
		// }

		if (x > gc.getCanvas().getWidth()) {
			x = -cloud.getWidth();
		}

	}

	@Override
	public ShotImpact shotImpact(double x, double y, ShotImpact impact) {

		shootSmokePosPool.add(new ShootSmokePos(x, y, this.x, this.y, rnd.nextInt(bulletSmoke.length)));

		return impact;
	}

	private class ShootSmokePos {

		private boolean valid;

		private double x;
		private double y;

		private double cloudOffsetX;
		private double cloudOffsetY;

		private int spriteIndex;
		private int spriteFxIndex;
		private long lastTime;

		public ShootSmokePos(double x, double y, double cloudOffsetX, double cloudOffsetY, int spriteFxIndex) {
			this.valid = true;
			this.x = x;
			this.y = y;
			this.cloudOffsetX = cloudOffsetX;
			this.cloudOffsetY = cloudOffsetY;
			this.spriteIndex = -1;
			this.spriteFxIndex = spriteFxIndex;
			this.lastTime = System.currentTimeMillis();

		}

	}

}
