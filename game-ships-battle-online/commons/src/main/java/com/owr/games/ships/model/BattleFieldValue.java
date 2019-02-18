/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.owr.games.ships.model;

/**
 *
 * @author owr
 */
public enum BattleFieldValue {

    Empty('~'), EmptyShot('0'), AccurateShot('X'), Ship('#');

    private final char symbol;

    BattleFieldValue(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static BattleFieldValue valueOf(char ch) {
        for (BattleFieldValue val : BattleFieldValue.values()) {
            if (val.getSymbol() == ch) {
                return val;
            }
        }

        throw new RuntimeException("No case for: " + ch);
    }

}
