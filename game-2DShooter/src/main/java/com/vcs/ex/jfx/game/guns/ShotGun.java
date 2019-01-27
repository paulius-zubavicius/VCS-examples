package com.vcs.ex.jfx.game.guns;

import java.io.File;

import com.vcs.ex.jfx.game.layers.ShootingToTarget;
import com.vcs.ex.jfx.game.resources.ImgRs;
import com.vcs.ex.jfx.game.shotimpact.ImpactType;
import com.vcs.ex.jfx.game.shotimpact.ShotImpact;
import com.vcs.ex.jfx.game.utils.ImgLoader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class ShotGun implements Gun {

	private static final long MILI_SEC = 1000_000;
	private static final long TIME_PER_FRAME_MILI = 100 * MILI_SEC;

	private static final double SHOT_POWER = 100.0;

	private Image[] sprite = null;
	private GraphicsContext gc;
	private long startNanoTime;
	private boolean shooting = false;
	private int currentFrame = 0;
	private int spFrameIndex;
	private int timeMoment = 0;

	private AudioClip sFxShot = new AudioClip(new File("media/snd/guns/shotgun/gunshot.wav").toURI().toString());

	private double x;
	private double y;
	private double w;
	private double h;

	@Override
	public void init(long startNanoTime, GraphicsContext gc) {
		this.startNanoTime = startNanoTime;
		this.gc = gc;
		sprite = ImgLoader.sprite(ImgRs.GUN_SHOT1);

		w = sprite[spFrameIndex].getWidth() * 2;
		h = sprite[spFrameIndex].getHeight() * 2;

		x = gc.getCanvas().getWidth() / 2;
		y = gc.getCanvas().getHeight() - h;

		sFxShot.setCycleCount(1);

	}

	@Override
	public void shoot(boolean start, double x, double y, ShootingToTarget target) {

		if (start) {

			// if it still in shooting progress... do nothing
			if (!shooting) {
				currentFrame = 0;
				shooting = true;

				sFxShot.play();

				target.shotImpact(x, y,new ShotImpact(SHOT_POWER, ImpactType.SHOTS));
			}
		}
	}

	@Override
	public void update(long currentNanoTime) {
		if (!shooting) {
			gc.clearRect(x, y, w, h);
			gc.drawImage(sprite[0], x, y, w, h);
			timeMoment = ((int) ((currentNanoTime - startNanoTime) / TIME_PER_FRAME_MILI));
			sFxShot.stop();
			return;
		}

		spFrameIndex = ((int) ((currentNanoTime - startNanoTime) / TIME_PER_FRAME_MILI)) - timeMoment;

		if (spFrameIndex < sprite.length) {
			if (currentFrame != spFrameIndex) {
				gc.clearRect(x, y, w, h);
				gc.drawImage(sprite[spFrameIndex], x, y, w, h);
			}
		} else {
			shooting = false;
		}

		currentFrame = spFrameIndex;
	}

	@Override
	public void use(boolean use) {
		if (!use) {
			sFxShot.stop();
		}

	}

}
