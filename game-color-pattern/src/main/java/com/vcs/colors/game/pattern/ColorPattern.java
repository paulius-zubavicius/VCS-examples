package com.vcs.colors.game.pattern;

import java.util.List;

import com.vcs.colors.game.pattern.line.ColoredLine;

public interface ColorPattern {

	public boolean isPatternMathed(ColoredLine line);
	
	public boolean isValid(ColoredLine line);

	public List<ColoredLine> getValidExample();

	public List<ColoredLine> getNotValidExample();
}
