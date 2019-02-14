package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

/*
pradžia ir pabaiga nesutampa
*/

public class PrIrPabAsimetrija extends ColorPatternController {

    public PrIrPabAsimetrija() {

        addValid(BLUE, RED, GREEN, BLUE, YELLOW);
        addValid(GREEN);
        addValid(YELLOW, RED, BLUE, GREEN, GREEN);
        addValid(BLUE, GREEN, BLUE, BLUE, GREEN);
        addValid(YELLOW, YELLOW, RED);

        addNotValid(BLUE, BLUE, BLUE);
        addNotValid(RED, YELLOW, GREEN, BLUE, RED);
        addNotValid(YELLOW, YELLOW, YELLOW, GREEN, YELLOW);
        addNotValid(RED, GREEN, BLUE, RED, GREEN);
        addNotValid(YELLOW, GREEN, GREEN, BLUE, YELLOW, RED);

    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        return eilute.getEilute().get(0) != (eilute.getEilute().get(eilute.getEilute().size() - 1));
    }
}