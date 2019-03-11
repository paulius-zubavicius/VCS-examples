package com.owr.games.ships.game.validators;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;

public class ValidatorDiagonallyTest {

    private ShipsValidator v;


    @Before
    public void init() {
        v = new ShipsValidator();
    }


    @Test
    public void hook1Test() {
        Ship[] ships = {
                new Ship(new Point(0, 0), new Point(0, 0)),
                new Ship(new Point(1, 1), new Point(1, 2)),
                new Ship(new Point(2, 2), new Point(2, 4)),
                new Ship(new Point(3, 3), new Point(3, 7)),
                new Ship(new Point(4, 4), new Point(5, 9)) // Diagonally
        };

        List<ValidationFailItem> rez = v.validateSafe(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.DiagonallyPosition, rez.get(0).getType());
    }

    @Test
    public void hook2Test() {
        Ship[] ships = {
                new Ship(new Point(0, 0), new Point(1, 1)),// Diagonally
                new Ship(new Point(1, 1), new Point(2, 2)),// Diagonally
                new Ship(new Point(2, 2), new Point(3, 4)),// Diagonally
                new Ship(new Point(3, 3), new Point(4, 7)),// Diagonally
                new Ship(new Point(4, 4), new Point(5, 9)) // Diagonally
        };

        List<ValidationFailItem> rez = v.validateSafe(ships);
        Assert.assertEquals(5, rez.size());

        for (ValidationFailItem item : rez) {
            Assert.assertEquals(ShipValidationErrCode.DiagonallyPosition, item.getType());
        }
    }

}
