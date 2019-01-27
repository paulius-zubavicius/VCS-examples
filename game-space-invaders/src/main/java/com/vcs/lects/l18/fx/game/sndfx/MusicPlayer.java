package com.vcs.lects.l18.fx.game.sndfx;

import java.io.File;

import javafx.scene.media.AudioClip;

public class MusicPlayer {

	private AudioClip bgSound;
	private double volume = 0.5;

	public void playNext() {
		bgSound = new AudioClip(new File("snd/bg.wav").toURI().toString());
		bgSound.setCycleCount(AudioClip.INDEFINITE);
		bgSound.play(volume);
	}

	public void volume(double volume) {
		this.volume = volume;
		bgSound.setVolume(volume);
	}

	public double getVolume() {
		return this.volume;
	}

}
