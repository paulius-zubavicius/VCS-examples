package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

public class LyginisKiekis extends ColorPatternController {

    public LyginisKiekis() {
        addValid(GREEN, RED, GREEN, RED);
        addValid(RED, BLUE);
        addValid(YELLOW, YELLOW, RED, YELLOW);
        addValid(GREEN, RED, RED, BLUE, YELLOW, BLUE);
        addValid(RED, RED, RED, BLUE);

        addNotValid(BLUE);
        addNotValid(GREEN, YELLOW, GREEN, BLUE, YELLOW);
        addNotValid(BLUE, RED, RED);
        addNotValid(GREEN, GREEN, GREEN);
        addNotValid(YELLOW, YELLOW, GREEN, BLUE, YELLOW);

    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {
        return eilute.getEilute().size() % 2 == 0;

    }
}
