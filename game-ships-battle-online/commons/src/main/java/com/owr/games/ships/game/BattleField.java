package com.owr.games.ships.game;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.BattleFieldValue;
import com.owr.games.ships.utils.MapUtil;
import java.util.ArrayList;

public class BattleField extends ArrayList<BattleFieldValue> {

    public static final int MAP_SIZE = 10;

    public BattleField(String map) {
        super(MAP_SIZE * MAP_SIZE);

        if (map != null && MAP_SIZE * MAP_SIZE == map.length()) {
            stringToList(map);
        }
        throw new RuntimeException(map);
    }

    public void hitTo(Point point) {

        if (BattleFieldValue.Ship.equals(get(point))) {
            replace(point, BattleFieldValue.AccurateShot);
        } else {
            replace(point, BattleFieldValue.EmptyShot);
        }
    }

    public boolean isLost() {
        return MapUtil.count(this, BattleFieldValue.Ship) > 0;
    }

    private void stringToList(String map) {
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            add(BattleFieldValue.valueOf(map.charAt(i)));
        }
    }

    public BattleFieldValue get(Point point) {
        return get(index(point));
    }

    public void replace(Point point, BattleFieldValue newValue) {
        set(index(point), newValue);
    }

    private int index(int x, int y) {
        return MAP_SIZE * y + x;
    }

    private int index(Point p) {
        return index(p.getX(), p.getY());
    }

}
