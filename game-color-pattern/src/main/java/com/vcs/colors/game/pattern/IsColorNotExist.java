package com.vcs.colors.game.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;

public abstract class IsColorNotExist implements ColorPattern {

	private ColoredItem color;

	IsColorNotExist(ColoredItem color) {
		this.color = color;
	}

	@Override
	public boolean isPatternMathed(ColoredLine line) {
		return line.isNotUsing(color);
	}

	@Override
	public List<ColoredLine> getValidExample() {
		List<ColoredLine> l = new ArrayList<>();
		l.add(new ColoredLine(rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor()));
		return l;
	}

	@Override
	public List<ColoredLine> getNotValidExample() {

		List<ColoredLine> l = new ArrayList<>();
		l.add(new ColoredLine(color, color, rndColor()));
		l.add(new ColoredLine(color, color, rndColor(), rndColor(), rndColor()));
		l.add(new ColoredLine(rndColor(), rndColor(), rndColor(), color, rndColor()));
		l.add(new ColoredLine(color));
		l.add(new ColoredLine(rndColor(), rndColor(), color, rndColor()));
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