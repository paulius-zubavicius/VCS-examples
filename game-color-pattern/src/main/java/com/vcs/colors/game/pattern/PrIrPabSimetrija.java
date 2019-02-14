package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

/**
 * pirma spalva lygi paskutinei
 */

public class PrIrPabSimetrija extends ColorPatternController {

    public PrIrPabSimetrija() {

        addValid(BLUE, YELLOW, RED, GREEN, BLUE);
        addValid(BLUE, GREEN, BLUE);
        addValid(GREEN);
        addValid(YELLOW, BLUE, GREEN, GREEN, YELLOW);
        addValid(BLUE, BLUE, BLUE, BLUE);

        addNotValid(BLUE, GREEN);
        addNotValid(BLUE, GREEN, BLUE, RED);
        addNotValid(YELLOW);
        addNotValid(YELLOW, BLUE, BLUE, BLUE);
        addNotValid(YELLOW, BLUE, RED, BLUE);
    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {
        return eilute.getEilute().get(0) == (eilute.getEilute().get(eilute.getEilute().size() - 1));
    }


}
