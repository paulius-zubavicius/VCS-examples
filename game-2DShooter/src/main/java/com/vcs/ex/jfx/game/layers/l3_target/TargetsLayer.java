package com.vcs.ex.jfx.game.layers.l3_target;

import java.io.File;
import java.util.Random;

import com.vcs.ex.jfx.game.layers.GraphicalLayer;
import com.vcs.ex.jfx.game.resources.ImgRs;
import com.vcs.ex.jfx.game.shotimpact.ImpactType;
import com.vcs.ex.jfx.game.shotimpact.ShotImpact;
import com.vcs.ex.jfx.game.utils.ImgLoader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class TargetsLayer implements GraphicalLayer {
	private static final long MILI_SEC = 1000_000;
	private static final long TIME_PER_FRAME_MILI = 100 * MILI_SEC;

	private Image[] sprite = null;
	private Image targetImage = null;
	private GraphicsContext gc;
	private long startNanoTime;
	private boolean hurting = false;
	private int currentFrame = 0;
	private int spFrameIndex;
	private double spriteIndexOffset = 0;

	private AudioClip sFxVictim = new AudioClip(new File("media/snd/targets/scream1.wav").toURI().toString());

	private double shotX;
	private double shotY;
	private double w;
	private double h;

	private double targetX;
	private double targetY;

	private double targetW;
	private double targetH;

	private Random rnd = new Random();

	@Override
	public void init(long startNanoTime, GraphicsContext gc) {
		this.startNanoTime = startNanoTime;
		this.gc = gc;
		sprite = ImgLoader.sprite(ImgRs.BLOOD3);

		targetImage = ImgLoader.img(ImgRs.TARGET2);

		targetW = targetImage.getWidth();
		targetH = targetImage.getHeight();

		targetX = 100;
		targetY = 100;

		w = sprite[0].getWidth() / 2;
		h = sprite[0].getHeight() / 2;

		spriteIndexOffset = w / 2;

		sFxVictim.setCycleCount(1);

		gc.drawImage(targetImage, targetX, targetY, targetW, targetH);
	}

	@Override
	public void reset() {
		// currentFrame = 0;
	}

	@Override
	public void updateTimer(long currentNanoTime) {
		// if (!hurting) {
		// gc.clearRect(targetX, targetY, targetW, targetH);
		// gc.drawImage(targetImage, targetX, targetY, targetW, targetH);
		// return;
		// }
		// gc.clearRect(targetX, targetY, targetW, targetH);

		if (hurting) {

			gc.drawImage(sprite[spFrameIndex], shotX - spriteIndexOffset, shotY - spriteIndexOffset, w, h);

			hurting = false;
		}

		// if (currentFrame < sprite.length) {
		// spFrameIndex = ((int) ((currentNanoTime - startNanoTime) /
		// TIME_PER_FRAME_MILI)) % sprite.length;
		//
		// if (currentFrame != spFrameIndex) {
		// //gc.clearRect(targetX, targetY, targetW, targetH);
		// // gc.clearRect(shotX, shotY, w, h);
		//
		// //gc.drawImage(targetImage, targetX, targetY, targetW, targetH);
		// gc.drawImage(sprite[spFrameIndex], shotX - spriteIndexOffset, shotY -
		// spriteIndexOffset, w, h);
		// }
		//
		// currentFrame = spFrameIndex;
		// }

	}

	@Override
	public ShotImpact shotImpact(double x, double y, ShotImpact impact) {

		this.shotX = x;
		this.shotY = y;

		if (collision(x, y)) {
			hurting = true;

			if (!sFxVictim.isPlaying()) {
				sFxVictim.play(0.5);
			}
			//spFrameIndex = rnd.nextInt(14) + 8;
			spFrameIndex = rnd.nextInt(sprite.length);
			return impact.setPower(10).setType(ImpactType.BLOOD);
		}

		return impact;
	}

	private boolean collision(double x, double y) {
		return x >= targetX && y >= targetY && x <= (targetX + targetW) && y <= (targetY + targetH);
	}

	// public void keepHurting(boolean hurting, double shotX, double shotY) {
	// this.shotX = shotX - spriteIndexOffset;
	// this.shotY = shotY - spriteIndexOffset;
	// this.hurting = hurting;
	//
	// }
}
