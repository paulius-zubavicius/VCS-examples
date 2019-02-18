package com.owr.games.ships.game.validators;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class ValidatorCrossingTest {

    private ShipsValidator v;


    @Before
    public void init() {
        v = new ShipsValidator();
    }


    @Test
    public void hook1Test() {
        Ship[] ships = {
                new Ship(new Point(0, 0), new Point(0, 0)),
                new Ship(new Point(1, 0), new Point(1, 1)),
                new Ship(new Point(2, 0), new Point(2, 2)),
                new Ship(new Point(3, 0), new Point(3, 3)), // Crossing X
                new Ship(new Point(3, 0), new Point(3, 4)) // Crossing X
        };

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.Crossing, rez.get(0).getType());
    }

    @Test
    public void hook2Test() {
        Ship[] ships = {
                new Ship(new Point(0, 0), new Point(0, 0)),// Crossing
                new Ship(new Point(0, 0), new Point(0, 1)),// Crossing
                new Ship(new Point(0, 0), new Point(0, 2)),// Crossing
                new Ship(new Point(0, 0), new Point(0, 3)), // Crossing
                new Ship(new Point(0, 0), new Point(0, 4)) // Crossing
        };

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(10, rez.size());
        Assert.assertEquals(ShipValidationErrCode.Crossing, rez.get(0).getType());
    }

}
