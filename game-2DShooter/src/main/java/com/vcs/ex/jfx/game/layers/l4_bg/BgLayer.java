package com.vcs.ex.jfx.game.layers.l4_bg;

import java.util.Random;

import com.vcs.ex.jfx.game.layers.GraphicalLayer;
import com.vcs.ex.jfx.game.resources.ImgRs;
import com.vcs.ex.jfx.game.shotimpact.ShotImpact;
import com.vcs.ex.jfx.game.utils.ImgLoader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BgLayer implements GraphicalLayer {

	private static final double DAMAGE_IMG_SCALE = 0.5;

	private GraphicsContext gc;

	private Image[] bulletDmgSprite = null;
	private Image[] bulletBlood = null;
	private Random rnd;

	private double w;
	private double h;
	private double centerOffsetX;
	private double centerOffsetY;

	@Override
	public void init(long startNanoTime, GraphicsContext gc) {

		this.gc = gc;

		bulletDmgSprite = ImgLoader.sprite(ImgRs.BULLET3);
		w = bulletDmgSprite[0].getWidth() * DAMAGE_IMG_SCALE;
		h = bulletDmgSprite[0].getHeight() * DAMAGE_IMG_SCALE;

		centerOffsetX = w / 2;
		centerOffsetY = h / 2;
		rnd = new Random(startNanoTime);

		bulletBlood = ImgLoader.sprite(ImgRs.BLOOD2);

	}

	@Override
	public void updateTimer(long currentNanoTime) {

	}

	@Override
	public void reset() {
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.drawImage(ImgLoader.img(ImgRs.BG_WALL), 0, 0);
	}

	@Override
	public ShotImpact shotImpact(double x, double y, ShotImpact impact) {

		// List of shots instead direct draw?
		// Fiksuotas zymiu skaicius, kuris isnyksta savaime? ar laikosi su visam?

		switch (impact.getType()) {

		case BLOOD:
			gc.drawImage(bulletBlood[rnd.nextInt(bulletBlood.length)], x - bulletBlood[0].getWidth() / 2,
					y - bulletBlood[0].getHeight() / 2, bulletBlood[0].getWidth(), bulletBlood[0].getHeight());
			break;
		default:
			gc.drawImage(bulletDmgSprite[rnd.nextInt(bulletDmgSprite.length)], x - centerOffsetX, y - centerOffsetY, w,
					h);
		}

		// TODO iskelti i updateTimer

		// Absorbs everything
		return impact.setPower(0);
	}

}
