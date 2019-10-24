package com.vcs.bb.game.ball;

public class BallFactory {

	private static Ball flat;
	private static FireBall fire;
	private static EarthBall earth;

	public static Ball createInstance(String type) {

		if ("earth".equalsIgnoreCase(type)) {
			if (earth == null) {
				earth = new EarthBall();
			}
			return earth;
		}

		if ("fire".equalsIgnoreCase(type)) {
			if (fire == null) {
				fire = new FireBall();
			}
			return fire;
		}

		if (flat == null) {
			flat = new Ball();
		}

		return flat;

	}
}
