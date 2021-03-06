package com.vcs.colors.game.pattern;

import static com.vcs.colors.game.pattern.line.ColoredItem.BLUE;
import static com.vcs.colors.game.pattern.line.ColoredItem.GREEN;
import static com.vcs.colors.game.pattern.line.ColoredItem.RED;
import static com.vcs.colors.game.pattern.line.ColoredItem.YELLOW;

import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;

/*
pirma ir trecia spalva negali buti raudona, eilute gali buti bet kokio ilgio
*/

public class PirmaIrTreciaSpalvosNeRaudonos extends ColorPatternController {

    public PirmaIrTreciaSpalvosNeRaudonos() {
        addValid(BLUE, GREEN, YELLOW, GREEN, GREEN);
        addValid(YELLOW);
        addValid(YELLOW, RED, BLUE, RED, RED);
        addValid(YELLOW, GREEN, GREEN, RED);
        addValid(BLUE, RED);

        addNotValid(BLUE, RED, GREEN, YELLOW);
        addNotValid(RED, GREEN, GREEN, GREEN);
        addNotValid(RED);
        addNotValid(YELLOW, YELLOW, RED, BLUE, BLUE, RED);
        addNotValid(GREEN, RED);
    }

    @Override
    public boolean isPatternMathed(ColoredLine eilute) {

        return !(eilute.getEilute().get(0).equals(RED));

    }

    public boolean ifShort(ColoredLine eilute) {
        return (eilute.getEilute().size() < 3 && (!(eilute.getEilute().get(0).equals(ColoredItem.RED))));
    }

    public boolean ifLong(ColoredLine eilute) {
        return  (eilute.getEilute().size() >= 3 && (!(eilute.getEilute().get(0).equals(ColoredItem.RED))) &&
                (!(eilute.getEilute().get(3).equals(ColoredItem.RED))));
    }
}
