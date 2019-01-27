package com.vcs.colors.game.pattern.line;

public enum ColoredItem {
	BLUE, GREEN, RED, YELLOW;

	public static ColoredItem getNextCycled(ColoredItem current) {
		ColoredItem next = getNext(current);
		if (next != null) {
			return next;
		} else {
			return ColoredItem.values()[0];
		}
	}

	private static ColoredItem getNext(ColoredItem current) {

		for (int i = 0; i < ColoredItem.values().length - 1; i++) {
			if (ColoredItem.values()[i].equals(current)) {
				return ColoredItem.values()[i + 1];
			}
		}

		return null;
	}
}
