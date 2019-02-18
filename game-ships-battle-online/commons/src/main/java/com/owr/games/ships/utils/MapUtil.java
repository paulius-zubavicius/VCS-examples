package com.owr.games.ships.utils;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.model.BattleFieldValue;
import java.util.List;

public class MapUtil {

    public static String mapToStringForEnemy(List<BattleFieldValue> map) {

        StringBuilder sb = new StringBuilder(BattleField.MAP_SIZE);
        map.stream().filter(mapEl -> (BattleFieldValue.Ship.equals(mapEl))).forEach(p -> sb.append(p));
        return sb.toString();

    }

    public static String mapToString(List<BattleFieldValue> map) {

        StringBuilder sb = new StringBuilder(BattleField.MAP_SIZE);
        map.forEach(p -> sb.append(p));
        return sb.toString();

    }

    public static long count(List<BattleFieldValue> map, BattleFieldValue valueForCounting) {
        return map.stream().filter(mapEl -> (valueForCounting.equals(mapEl))).count();
    }

}
