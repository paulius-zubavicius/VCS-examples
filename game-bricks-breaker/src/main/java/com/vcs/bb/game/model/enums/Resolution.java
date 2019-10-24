package com.vcs.bb.game.model.enums;

public enum Resolution {

	RES_800x600(800, 600), RES_600x400(600, 400);

	private int w;
	private int h;

	private Resolution(int w, int h) {
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
