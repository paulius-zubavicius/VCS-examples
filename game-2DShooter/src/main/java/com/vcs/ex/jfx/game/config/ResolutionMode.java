package com.vcs.ex.jfx.game.config;

public enum ResolutionMode {

	RES_800x600(800, 600);

	private int w;
	private int h;

	ResolutionMode(int w, int h) {
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
