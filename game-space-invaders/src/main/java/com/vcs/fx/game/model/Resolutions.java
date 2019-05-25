package com.vcs.fx.game.model;

public enum Resolutions {

	RES_400_600(400, 600),

	RES_600_900(600, 900);

	private int w;
	private int h;

	private Resolutions(int w, int h) {
		this.w = w;
		this.h = h;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

}
