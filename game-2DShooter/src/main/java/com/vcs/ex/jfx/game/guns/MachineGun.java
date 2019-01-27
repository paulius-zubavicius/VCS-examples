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

public class MachineGun implements Gun {

	private static final long MILI_SEC = 1000_000;
	private static final long TIME_PER_FRAME_MILI = 100 * MILI_SEC;

	private static final double SHOT_POWER = 25.0;

	private Image[] sprite = null;
	// private Image[] spriteRun = null;
	private GraphicsContext gc;
	private long startNanoTime;
	private boolean shooting = false;
	private int currentFrame = 0;
	private int spFrameIndex;
	private int spriteIndexOffset = 0;

	private AudioClip sFxShooting = new AudioClip(new File("media/snd/guns/machinegun/mashine.wav").toURI().toString());

	private AudioClip sFxDrilling = new AudioClip(new File("media/snd/guns/machinegun/drill.wav").toURI().toString());

	private double x;
	private double y;
	private double w;
	private double h;

	private double xShot;
	private double yShot;
	private ShootingToTarget target;

	@Override
	public void init(long startNanoTime, GraphicsContext gc) {
		this.startNanoTime = startNanoTime;
		this.gc = gc;
		sprite = ImgLoader.sprite(ImgRs.GUN_MACHINE1);

		w = sprite[spFrameIndex].getWidth() * 2;
		h = sprite[spFrameIndex].getHeight() * 2;

		x = gc.getCanvas().getWidth() / 2;
		y = gc.getCanvas().getHeight() - h;

		// Shooting
		sFxShooting.setCycleCount(AudioClip.INDEFINITE);

		// Drilling
		sFxDrilling.setCycleCount(AudioClip.INDEFINITE);
	}

	@Override
	public void shoot(boolean start, double x, double y, ShootingToTarget target) {
		this.shooting = start;

		if (shooting) {
			this.xShot = x;
			this.yShot = y;
			this.target = target;
			if (!sFxShooting.isPlaying()) {
				sFxShooting.play();
			}

		} else {
			sFxShooting.stop();
		}

		// currentFrame = 0;
	}

	@Override
	public void update(long currentNanoTime) {

		spriteIndexOffset = (shooting ? 2 : 0);

		spFrameIndex = ((int) ((currentNanoTime - startNanoTime) / TIME_PER_FRAME_MILI)) % 2;

		if (currentFrame != spFrameIndex) {

			if (shooting) {
				target.shotImpact(xShot, yShot, new ShotImpact(SHOT_POWER, ImpactType.BULLET));
			}

			gc.clearRect(x, y, w, h);
			gc.drawImage(sprite[spFrameIndex + spriteIndexOffset], x, y, w, h);
		}

		currentFrame = spFrameIndex;
	}

	@Override
	public void use(boolean use) {
		if (use) {
			sFxDrilling.play(0.1);
		} else {
			sFxShooting.stop();
			sFxDrilling.stop();
		}

	}

}
