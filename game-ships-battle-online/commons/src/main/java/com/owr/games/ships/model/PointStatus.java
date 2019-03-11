/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.owr.games.ships.model;

import java.util.Optional;

/**
 *
 * @author owr
 */
public enum PointStatus {

	// Empty('~'),
	EmptyShot('0'), AccurateShot('X'), Ship('#');

	private final char symbol;

	PointStatus(char symbol) {
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}

	public static Optional<PointStatus> valueOf(char ch) {

		for (PointStatus val : PointStatus.values()) {
			if (val.getSymbol() == ch) {
				return Optional.of(val);
			}
		}
		return Optional.empty();
	}

}
