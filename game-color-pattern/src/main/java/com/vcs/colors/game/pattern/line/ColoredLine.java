package com.vcs.colors.game.pattern.line;

import java.util.ArrayList;
import java.util.List;

public class ColoredLine {

	private List<ColoredItem> eilute = new ArrayList<>();

	public List<ColoredItem> getEilute() {
		return eilute;
	}

	public ColoredLine(ColoredItem... spalvos) {
		// eilute.addAll(Arrays.asList(spalvos));

		for (ColoredItem item : spalvos) {
			if (item != null) {
				eilute.add(item);
			}

		}
	}

	public boolean isUsing(ColoredItem sp) {

		for (ColoredItem spalva : eilute) {
			if (spalva.equals(sp)) {
				return true;
			}
		}

		return false;

	}

	public boolean isNotUsing(ColoredItem sp) {
		return !isUsing(sp);
	}

	public boolean isItTheSame(ColoredLine line) {

		if (eilute.size() == line.eilute.size()) {
			for (int i = 0; i < eilute.size(); i++) {
				if (!eilute.get(i).equals(line.eilute.get(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
