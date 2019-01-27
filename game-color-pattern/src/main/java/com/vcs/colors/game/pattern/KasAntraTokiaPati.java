package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredLine;

/*

kas antra tokia pati nuo pirmo
*/

public class KasAntraTokiaPati extends ColorPatternController {

    public KasAntraTokiaPati() {

        addValid(BLUE, BLUE, BLUE);
        addValid(BLUE, GREEN, BLUE, GREEN, BLUE);
        addValid(YELLOW, BLUE, YELLOW, GREEN);
        addValid(GREEN);
        addValid(BLUE, BLUE);

        addNotValid(BLUE, YELLOW, GREEN);
        addNotValid(BLUE, GREEN, GREEN, RED);
        addNotValid(GREEN, GREEN, YELLOW);
        addNotValid(YELLOW, YELLOW, GREEN, YELLOW);
        addNotValid(YELLOW, BLUE, RED, BLUE, RED);
    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        return rightLength(eilute) && (everySecondColor(eilute) || methodForFive(eilute) || methodForFour(eilute));

    }



    public boolean rightLength(ColoredLine eilute) {
        return eilute.getEilute().size() >= 1 && eilute.getEilute().size() <= 6;
    }

    public boolean everySecondColor(ColoredLine eilute) {

        return eilute.getEilute().size() <= 2;
    }

    private boolean methodForFive(ColoredLine eilute) {
        return eilute.getEilute().size() >=5 && (eilute.getEilute().get(0).equals(eilute.getEilute().get(2)) &&
                (eilute.getEilute().get(2).equals(eilute.getEilute().get(4))));

    }

    private boolean methodForFour(ColoredLine eilute) {
        return (eilute.getEilute().size() >=3 && eilute.getEilute().size() <=4) &&
                (eilute.getEilute().get(0).equals(eilute.getEilute().get(2)));
    }
}