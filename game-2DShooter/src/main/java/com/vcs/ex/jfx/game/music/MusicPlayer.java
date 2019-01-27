package com.vcs.ex.jfx.game.music;

import java.io.File;

import javafx.scene.media.AudioClip;

/**
 * Music
 */
public class MusicPlayer {

	private AudioClip bgSound;
	private double volume = 0.5;

	public void playNext() {
		// TODO scan the music folder for random tract
		//
//		bgSound = new AudioClip(new File("media/music/Ampyx-Holo.wav").toURI().toString());
		bgSound = new AudioClip(new File("media/music/doom.wav").toURI().toString());
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
