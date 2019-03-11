package com.owr.games.ships.game.validators;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ValidatorCoordBoundsTest {
    private static final int OUT_OF_B_NEGATIVE = -1;
    private static final int OUT_OF_B = BattleField.MAP_SIZE + 1;

    private ShipsValidator v;

    @Before
    public void init() {
        v = new ShipsValidator();
    }

    @Test
    public void hook1Test() {
        Ship[] ships = {new Ship(new Point(1, 1), new Point(OUT_OF_B_NEGATIVE, 1)), new Ship(new Point(1, 1), new Point(1, 1)), new Ship(new Point(1, 1), new Point(1, 1)), new Ship(new Point(1, 1), new Point(1, 1)), new Ship(new Point(1, 1), new Point(1, 1))};

        List<ValidationFailItem> rez = v.validateSafe(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.CoordinateOutOfBounds, rez.get(0).getType());
    }

    @Test
    public void hook2Test() {
        Ship[] ships = {new Ship(new Point(1, 1), new Point(OUT_OF_B, 1)), new Ship(new Point(1, 1), new Point(1, 1)), new Ship(new Point(1, 1), new Point(1, 1)), new Ship(new Point(1, 1), new Point(1, 1)), new Ship(new Point(1, 1), new Point(1, 1))};

        List<ValidationFailItem> rez = v.validateSafe(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.CoordinateOutOfBounds, rez.get(0).getType());
    }

    @Test
    public void hook3Test() {
        Ship[] ships = {new Ship(new Point(OUT_OF_B, OUT_OF_B), new Point(OUT_OF_B, OUT_OF_B)), new Ship(new Point(OUT_OF_B, OUT_OF_B), new Point(OUT_OF_B, OUT_OF_B)), new Ship(new Point(OUT_OF_B, OUT_OF_B), new Point(OUT_OF_B, OUT_OF_B)), new Ship(new Point(OUT_OF_B, OUT_OF_B), new Point(OUT_OF_B, OUT_OF_B)), new Ship(new Point(OUT_OF_B, OUT_OF_B), new Point(OUT_OF_B, OUT_OF_B))};

        List<ValidationFailItem> rez = v.validateSafe(ships);
        Assert.assertEquals(10, rez.size());

        for (ValidationFailItem item : rez) {
            Assert.assertEquals(ShipValidationErrCode.CoordinateOutOfBounds, item.getType());
        }
    }


}
