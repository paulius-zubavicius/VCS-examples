package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

/*
eilute ilgesne nei 2
ir pirma arba antra spalba sutampa su paskutine
*/

public class PirmaArbaAntraSpalvaSutampaSuPaskutine extends ColorPatternController {

    public PirmaArbaAntraSpalvaSutampaSuPaskutine() {

        addValid(GREEN, BLUE, GREEN);
        addValid(RED, RED, BLUE, GREEN, RED);
        addValid(YELLOW, BLUE, BLUE);
        addValid(RED, RED, BLUE, BLUE, RED);
        addValid(GREEN, RED, YELLOW, YELLOW, GREEN);

        addNotValid(GREEN, BLUE, GREEN, GREEN, RED);
        addNotValid(YELLOW);
        addNotValid(GREEN, GREEN, RED, BLUE);
        addNotValid(RED, GREEN, BLUE);
        addNotValid(YELLOW, YELLOW, YELLOW, GREEN);
    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        return rightLength(eilute) && firstOrSecondMatchLast(eilute);
    }

    public boolean rightLength(ColoredLine eilute) {
        return eilute.getEilute().size() >= 1 && eilute.getEilute().size() <= 6;
    }


    public boolean firstOrSecondMatchLast(ColoredLine eilute) {

        return eilute.getEilute().size() <= 2 ||
                ((eilute.getEilute().get(0) == (eilute.getEilute().get(eilute.getEilute().size() - 1)))
                || (eilute.getEilute().get(1).equals(eilute.getEilute().get(eilute.getEilute().size() - 1))));
    }

}
