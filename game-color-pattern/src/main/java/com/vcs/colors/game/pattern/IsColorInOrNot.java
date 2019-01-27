package com.vcs.colors.game.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;

public abstract class IsColorInOrNot extends ColorPatternController {

	private ColoredItem color;
	private boolean isColorIn;

	IsColorInOrNot(ColoredItem color, boolean isColorIn) {
		this.color = color;
		this.isColorIn = isColorIn;

		if (isColorIn) {
			addValid(getExistsExample());
			addNotValid(getNotExistsExample());
		} else {
			addValid(getNotExistsExample());
			addNotValid(getExistsExample());
		}

	}

	@Override
	public boolean isPatternMathed(ColoredLine line) {

		if (line.getEilute().size() >= 1 && line.getEilute().size() <= 6) {
			if (isColorIn) {
				return line.isUsing(color);
			} else {
				return line.isNotUsing(color);
			}
		}
		
		return false;

	}

	public List<ColoredLine> getExistsExample() {
		List<ColoredLine> l = new ArrayList<>();
		l.add(new ColoredLine(color, color, rndColor()));
		l.add(new ColoredLine(color, color, rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor(), color, rndColor()));
		l.add(new ColoredLine(color));
		l.add(new ColoredLine(rndColor(), rndColor(), color, rndColor()));
		return l;
	}

	public List<ColoredLine> getNotExistsExample() {
		List<ColoredLine> l = new ArrayList<>();
		l.add(new ColoredLine(rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor()));
		return l;
	}

	/**
	 * @return random color except declared as
	 * 
	 *         <pre>
	 *         Spalva color;
	 *         </pre>
	 */
	private ColoredItem rndColor() {
		ColoredItem[] colors = ColoredItem.values();
		Random rnd = new Random();
		int colorIndex = rnd.nextInt(colors.length);
		while (colors[colorIndex] == color) {
			colorIndex = rnd.nextInt(colors.length);
		}

		return colors[colorIndex];
	}

}