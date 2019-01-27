package com.vcs.lects.l18.fx.game.model;

public enum Resolutions {

	RES_400_600(400, 600);

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
