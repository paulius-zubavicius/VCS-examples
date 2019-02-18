package com.owr.games.ships.game.validators;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ValidatorNullTest {

    private ShipsValidator v;

    @Before
    public void init() {
        v = new ShipsValidator();
    }

    @Test
    public void hook1Test() {
        List<ValidationFailItem> rez = v.validate();
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.NullValue, rez.get(0).getType());
    }

    @Test
    public void hook2Test() {
        Ship[] ships = null;

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.NullValue, rez.get(0).getType());
    }

    @Test
    public void hook3Test() {
        Ship[] ships = {};

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.NullValue, rez.get(0).getType());
    }

    @Test
    public void hook4Test() {
        Ship[] ships = {new Ship(new Point(1, 1), new Point(1, 1)), null};

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.NullValue, rez.get(0).getType());
    }

    @Test
    public void hook5Test() {
        Ship[] ships = {new Ship(new Point(1, 1), null)};

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.NullValue, rez.get(0).getType());
    }

}
