package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;

/**
 * Pradzia ir pabaiga nesutampa tokiu budu: 3...0; 2...1; 1...2; 0...3;
 */

public class PrIrPabNesutampaPglTaisyklePoDvi extends ColorPatternController {

    public PrIrPabNesutampaPglTaisyklePoDvi() {

        addValid(RED, RED, BLUE, YELLOW);
        addValid(GREEN, YELLOW, YELLOW, BLUE);
        addValid(RED, GREEN, RED, YELLOW);
        addValid(BLUE, YELLOW, GREEN, RED, GREEN);
        addValid(YELLOW, RED);

        addNotValid(BLUE, GREEN, GREEN, RED);
        addNotValid(RED, BLUE, GREEN, BLUE, BLUE);
        addNotValid(GREEN, BLUE, BLUE, YELLOW);
        addNotValid(BLUE);
        addNotValid(YELLOW, RED, GREEN);
    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        return redYellow(eilute) || greenBlue(eilute) || blueGreen(eilute) || yellowRed(eilute);

    }

    public boolean redYellow(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.RED))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.YELLOW);
    }

    public boolean greenBlue(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.GREEN))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.BLUE);
    }

    public boolean blueGreen(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.BLUE))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.GREEN);
    }

    public boolean yellowRed(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.YELLOW))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.RED);
    }


}
