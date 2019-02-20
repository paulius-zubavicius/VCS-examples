package com.owr.games.ships.game.validators;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ValidatorCountTest {

    private ShipsValidator v;

    @Before
    public void init() {
        v = new ShipsValidator();
    }


    @Test
    public void hook1Test() {
        Ship[] ships = {new Ship(new Point(1, 1), new Point(1, 1))};

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.ShipsCount, rez.get(0).getType());
        Assert.assertEquals("1 != " + ShipsValidator.SHIPS_COUNT, rez.get(0).getParam()[0]);
    }

    @Test
    public void hook2Test() {
        Ship sh = new Ship(new Point(1, 1), new Point(1, 1));
        Ship[] ships = {sh, sh, sh, sh};

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.ShipsCount, rez.get(0).getType());
        Assert.assertEquals("4 != " + ShipsValidator.SHIPS_COUNT, rez.get(0).getParam()[0]);
    }

    @Test
    public void hook3Test() {
        Ship sh = new Ship(new Point(1, 1), new Point(1, 1));
        Ship[] ships = {sh, sh, sh, sh, sh, sh};

        List<ValidationFailItem> rez = v.validate(ships);
        Assert.assertEquals(1, rez.size());
        Assert.assertEquals(ShipValidationErrCode.ShipsCount, rez.get(0).getType());
        Assert.assertEquals("6 != " + ShipsValidator.SHIPS_COUNT, rez.get(0).getParam()[0]);
    }

}
