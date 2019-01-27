package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

/*
salia esancios spalvos nesutampa
*/

public class KaimynesRaidesNesutampa extends ColorPatternController {

    public KaimynesRaidesNesutampa() {

        addValid(BLUE, GREEN, YELLOW);
        addValid(BLUE);
        addValid(YELLOW, RED, YELLOW);
        addValid(GREEN, BLUE);
        addValid(YELLOW, RED, BLUE, GREEN, BLUE);

        addNotValid(BLUE, BLUE, YELLOW, GREEN);
        addNotValid(RED, GREEN, GREEN, GREEN);
        addNotValid(BLUE, BLUE, GREEN, YELLOW, YELLOW);
        addNotValid(GREEN, GREEN);
        addNotValid(BLUE, YELLOW, YELLOW, RED, RED);
    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        int count = 0;

        for (int i = 0; i < eilute.getEilute().size() - 1; i++) {
            if (eilute.getEilute().get(i) != (eilute.getEilute().get(i + 1))) {
                count++;
            }
        }

        return count == eilute.getEilute().size() - 1 && rightLength(eilute);
    }

    public boolean rightLength(ColoredLine eilute) {
        return eilute.getEilute().size() >= 1 && eilute.getEilute().size() <= 6;
    }

}
