package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

/*
eilute > 2 ir pirma ir priespaskutine spalva nesutampa
*/

public class PirmairPriespaskutineNesutampa extends ColorPatternController {

    public PirmairPriespaskutineNesutampa() {
        addValid(GREEN, RED, RED);
        addValid(YELLOW, YELLOW, BLUE, GREEN);
        addValid(GREEN, GREEN, BLUE, BLUE);
        addValid(RED, BLUE, GREEN, YELLOW, GREEN);
        addValid(BLUE, BLUE, GREEN, GREEN, RED, BLUE);

        addNotValid(GREEN);
        addNotValid(YELLOW, BLUE, GREEN, YELLOW, RED);
        addNotValid(BLUE, BLUE, BLUE);
        addNotValid(YELLOW, GREEN, YELLOW, YELLOW);
        addNotValid(BLUE, GREEN, RED, BLUE, GREEN);

    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        return !(eilute.getEilute().get(0).equals(eilute.getEilute().get(eilute.getEilute().size() - 1)));
    }
}
