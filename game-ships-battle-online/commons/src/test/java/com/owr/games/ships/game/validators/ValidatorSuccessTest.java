package com.owr.games.ships.game.validators;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;

public class ValidatorSuccessTest {

    private ShipsValidator v;


    @Before
    public void init() {
        v = new ShipsValidator();
    }


    @Test
    public void succTest() {
        Ship[] ships = {
                new Ship(new Point(0, 0), new Point(0, 0)),
                new Ship(new Point(1, 0), new Point(1, 1)),
                new Ship(new Point(2, 0), new Point(2, 2)),
                new Ship(new Point(3, 0), new Point(3, 3)),
                new Ship(new Point(4, 0), new Point(4, 4))
        };

        List<ValidationFailItem> rez = v.validateSafe(ships);
        Assert.assertEquals(0, rez.size());

    }
}
