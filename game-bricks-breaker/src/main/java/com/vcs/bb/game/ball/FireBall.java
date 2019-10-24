package com.vcs.bb.game.ball;

import java.awt.Image;

import com.vcs.bb.utils.SpriteLoader;

public class FireBall extends Ball {

	private static final double BALL_SIZE_MLTP = 0.5;

	private static final int TRANSPARENT_IMG_GAP = (int) (35 * BALL_SIZE_MLTP);
	private static final int BALL_IMG_SIZE = (int) (134 * BALL_SIZE_MLTP);
	private static final int BALL_SIZE = BALL_IMG_SIZE - TRANSPARENT_IMG_GAP - TRANSPARENT_IMG_GAP;

	private int currentAnimationFrame = 0;
	private Image[] sprite = null;

	public FireBall() {
		super();
		sprite = SpriteLoader.loadSprite("fireball.png", 6, 4);
	}

	@Override
	public void animate() {
		currentAnimationFrame++;
		if (currentAnimationFrame >= sprite.length) {
			currentAnimationFrame = 0;
		}
	}

	public Image getImage() {
		return sprite[currentAnimationFrame];
	}

	@Override
	public double getImgPosX() {
		return x - TRANSPARENT_IMG_GAP;
	}

	@Override
	public double getImgPosY() {
		return y - TRANSPARENT_IMG_GAP;
	}

	@Override
	public int getSize() {
		return BALL_SIZE;
	}

	@Override
	public int getImgSize() {
		return BALL_IMG_SIZE;
	}

}
