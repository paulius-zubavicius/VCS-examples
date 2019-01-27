package com.vcs.colors.game.levels;

public enum Level {
	LEVEL0, LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5, LEVEL6, LEVEL7, LEVEL8, LEVEL9;

	public static Level nextLevel(Level currentLevel) {

		for (int i = 0; i < Level.values().length - 1; i++) {
			if (Level.values()[i].equals(currentLevel)) {
				return Level.values()[i + 1];
			}
		}

		throw new RuntimeException("No next level available after: " + currentLevel);
	}

	public static boolean isItLast(Level currentLevel) {
		return Level.values()[Level.values().length - 1].equals(currentLevel);
	}

	public static double levelAsProgress(Level currentLevel) {

		for (int i = 0; i < Level.values().length; i++) {
			if (Level.values()[i].equals(currentLevel)) {
				return (i * 1.0 / (Level.values().length - 1));
			}
		}

		throw new RuntimeException("Progress unknown: " + currentLevel);
	}
}
