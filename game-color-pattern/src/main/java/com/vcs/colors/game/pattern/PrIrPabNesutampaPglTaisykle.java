package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;

public class PrIrPabNesutampaPglTaisykle extends ColorPatternController {

    public PrIrPabNesutampaPglTaisykle() {
        addValid(RED, RED, BLUE, GREEN);
        addValid(GREEN, GREEN, YELLOW, BLUE);
        addValid(RED, GREEN, RED, GREEN);
        addValid(BLUE, YELLOW, GREEN, RED, YELLOW);
        addValid(YELLOW, RED);

        addNotValid(BLUE, GREEN, GREEN, GREEN);
        addNotValid(RED, BLUE, GREEN, BLUE, YELLOW);
        addNotValid(GREEN, BLUE, BLUE, RED);
        addNotValid(BLUE);
        addNotValid(YELLOW, RED, GREEN);
    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        return redGreen(eilute) || greenBlue(eilute) || blueYellow(eilute) || yellowRed(eilute);

    }

    public boolean redGreen(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.RED))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.GREEN);
    }

    public boolean greenBlue(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.GREEN))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.BLUE);
    }

    public boolean blueYellow(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.BLUE))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.YELLOW);
    }

    public boolean yellowRed(ColoredLine eilute) {
        return (eilute.getEilute().get(0).equals(ColoredItem.YELLOW))
                && (eilute.getEilute().get(eilute.getEilute().size() - 1)).equals(ColoredItem.RED);
    }
}
