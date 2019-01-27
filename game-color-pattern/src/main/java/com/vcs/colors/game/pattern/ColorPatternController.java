package com.vcs.colors.game.pattern;

import java.util.ArrayList;
import java.util.List;

import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;

public abstract class ColorPatternController implements ColorPattern {

	private List<ColoredLine> valid = new ArrayList<>();
	private List<ColoredLine> notValid = new ArrayList<>();

	@Override
	public boolean isValid(ColoredLine line) {

		// It is not legal to enter valid from examples :)
		if (isItInList(line, valid)) {
			return false;
		}

		// If it's ok...
		if (isPatternMathed(line)) {
			return true;
		}

		// If it's not... append "missed" list
		if (!isItInList(line, notValid)) {
			appendList(line);
		}

		return false;
	}

	@Override
	public List<ColoredLine> getValidExample() {
		return valid;
	}

	@Override
	public List<ColoredLine> getNotValidExample() {
		return notValid;
	}

	protected void addValid(ColoredItem... coloredLines) {
		valid.add(new ColoredLine(coloredLines));
	}

	protected void addNotValid(ColoredItem... coloredLines) {
		notValid.add(new ColoredLine(coloredLines));
	}

	protected void addValid(List<ColoredLine> allLines) {
		valid.addAll(allLines);
	}

	protected void addNotValid(List<ColoredLine> allLines) {
		notValid.addAll(allLines);
	}

	private void appendList(ColoredLine line) {
		notValid.remove(notValid.size() - 1);
		notValid.add(0, line);
	}

	private boolean isItInList(ColoredLine line, List<ColoredLine> list) {
		for (ColoredLine exLine : list) {
			if (exLine.isItTheSame(line)) {
				return true;
			}
		}

		return false;
	}

}
