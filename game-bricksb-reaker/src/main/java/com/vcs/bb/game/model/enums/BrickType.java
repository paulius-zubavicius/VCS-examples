package com.vcs.bb.game.model.enums;

public enum BrickType {

	B1('1', 1, 0x00ffbb), B2('2', 2, 0x00ff99), B3('3', 3, 0x11bb88), B4('4', 4, 0x229977), B5('5', 5, 0x336666), B6('6', 6, 0x663333), B7('7', 7, 0x990000), B8('8', 8, 0xaa0000), B9('9', 9, 0xff0000),
	B0('0', 0, 0x333333);

	private char symbol;
	private int points;
	private int rgb;

	private BrickType(char symbol, int points, int rgb) {
		this.symbol = symbol;
		this.points = points;
		this.rgb = rgb;
	}

	public char getSymbol() {
		return symbol;
	}

	public int getPoints() {
		return points;
	}

	public static BrickType valueOfByChar(char symbol) {
		for (BrickType v : BrickType.values()) {
			if (v.getSymbol() == symbol) {
				return v;
			}
		}
		return null;
	}

	public int getRGB() {
		return rgb;
	}
}
