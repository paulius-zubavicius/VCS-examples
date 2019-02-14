package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

/*
pradžia ir pabaiga nesutampa ir salia esancios spalvos nesutampa
*/

public class PrIrPabAsimetrijaIrKaimynesNesutampa extends ColorPatternController {

    public PrIrPabAsimetrijaIrKaimynesNesutampa() {

        addValid(BLUE, GREEN, YELLOW, GREEN);
        addValid(YELLOW, RED);
        addValid(GREEN, RED, BLUE, GREEN, YELLOW);
        addValid(BLUE, RED, GREEN);
        addValid(YELLOW, BLUE, GREEN);

        addNotValid(GREEN, RED, BLUE, BLUE);
        addNotValid(GREEN);
        addNotValid(RED, BLUE, GREEN, RED);
        addNotValid(YELLOW, BLUE, GREEN, GREEN, RED);
        addNotValid(RED, RED, RED, RED);

    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        int count = 0;

        for (int i = 0; i < eilute.getEilute().size() - 1; i++) {
            if (eilute.getEilute().get(i) != (eilute.getEilute().get(i + 1))) {
                count++;
            }
        }


        return (count == eilute.getEilute().size() - 1)
                && (eilute.getEilute().get(0) != (eilute.getEilute().get(eilute.getEilute().size() - 1)));
    }

}
