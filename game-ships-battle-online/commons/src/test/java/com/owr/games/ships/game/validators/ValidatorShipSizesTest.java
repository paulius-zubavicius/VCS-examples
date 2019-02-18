package com.owr.games.ships.game.validators;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ValidatorShipSizesTest {

    private ShipsValidator v;


    @Before
    public void init() {
        v = new ShipsValidator();
    }

    @Test
    public void hook1Test() {
        Ship[] ships = {
                new Ship(new Point(0, 0), new Point(0, 1)),//2
                new Ship(new Point(1, 0), new Point(1, 1)),//2
                new Ship(new Point(2, 0), new Point(2, 2)),//3
                new Ship(new Point(3, 0), new Point(3, 3)),//4
                new Ship(new Point(4, 0), new Point(4, 4)) //5
        };

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(2, rez.size());

        int shipSizeMissing = 0;
        int shipSizeDuplicate = 0;
        for (ValidationFailItem item : rez) {
            if (ShipValidationErrCode.ShipSizeMissing.equals(item.getType())) {
                shipSizeMissing++;
            }

            if (ShipValidationErrCode.ShipSizeDuplicate.equals(item.getType())) {
                shipSizeDuplicate++;
            }
        }

        Assert.assertEquals(shipSizeMissing, 1);
        Assert.assertEquals(shipSizeDuplicate, 1);
    }

    @Test
    public void hook2Test() {
        Ship[] ships = {
                new Ship(new Point(0, 0), new Point(0, 5)),//6
                new Ship(new Point(1, 0), new Point(1, 1)),//2
                new Ship(new Point(2, 0), new Point(2, 2)),//3
                new Ship(new Point(3, 0), new Point(3, 3)),//4
                new Ship(new Point(4, 0), new Point(4, 4)) //5
        };

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(2, rez.size());


        int shipSizeMissing = 0;
        int shipSizeToBig = 0;
        for (ValidationFailItem item : rez) {
            if (ShipValidationErrCode.ShipSizeMissing.equals(item.getType())) {
                shipSizeMissing++;
            }

            if (ShipValidationErrCode.ShipSizeToBig.equals(item.getType())) {
                shipSizeToBig++;
            }
        }

        Assert.assertEquals(shipSizeMissing, 1);
        Assert.assertEquals(shipSizeToBig, 1);

    }

}
