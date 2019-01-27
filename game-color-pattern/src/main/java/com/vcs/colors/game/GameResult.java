package com.vcs.colors.game;

import com.vcs.colors.game.levels.LevelFlow;

public class GameResult {

	private LevelFlow status;
	private double progress;
	private int missed;

	GameResult(LevelFlow status, double progress, int missed) {
		this.status = status;
		this.progress = progress;
		this.missed = missed;
	}

	public LevelFlow getStatus() {
		return status;
	}

	public void setStatus(LevelFlow status) {
		this.status = status;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public int getMissed() {
		return missed;
	}

	public void setMissed(int missed) {
		this.missed = missed;
	}

}
