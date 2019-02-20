package com.owr.games.ships.game;

import com.owr.games.ships.game.validators.BattleFieldValidationException;
import com.owr.games.ships.game.validators.ShipsValidator;
import com.owr.games.ships.game.validators.ValidationFailItem;
import com.owr.games.ships.model.BattleFieldSerialized;
import com.owr.games.ships.model.BattleFieldValue;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;

import java.util.ArrayList;
import java.util.List;

public class BattleField extends ArrayList<BattleFieldValue> {

    public static final int MAP_SIZE = 10;

    public BattleField(String map) {
        super(MAP_SIZE * MAP_SIZE);

        if (map != null && MAP_SIZE * MAP_SIZE == map.length()) {
            for (int i = 0; i < BattleField.MAP_SIZE * BattleField.MAP_SIZE; i++) {
                add(BattleFieldValue.valueOf(map.charAt(i)));
            }
        } else {
            throw new RuntimeException(map);
        }
    }

    public BattleField(List<Ship> ships) {
        super(MAP_SIZE * MAP_SIZE);

        ShipsValidator validator = new ShipsValidator();
        List<ValidationFailItem> valResult = validator.validate(ships);
        if (!valResult.isEmpty()) {
            throw new BattleFieldValidationException(valResult);
        }

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            add(BattleFieldValue.Empty);
        }

        ships.forEach(ship -> ship.convertToPoints().forEach(p -> replace(p, BattleFieldValue.Ship)));
    }


    public void hitTo(Point point) {

        List<ValidationFailItem> valResult = new ShipsValidator().validatePoint(point);
        if (!valResult.isEmpty()) {
            throw new BattleFieldValidationException(valResult);
        }

        if (BattleFieldValue.Ship.equals(get(point))) {
            replace(point, BattleFieldValue.AccurateShot);
        } else {
            replace(point, BattleFieldValue.EmptyShot);
        }
    }

    public boolean isLost() {
        return this.stream().anyMatch(mapEl -> BattleFieldValue.Ship.equals(mapEl));
    }

    public BattleFieldValue get(Point point) {
        return get(index(point));
    }

    public void replace(Point point, BattleFieldValue newValue) {
        set(index(point), newValue);
    }

    public BattleFieldSerialized serializeBattleField() {
        StringBuilder sb = new StringBuilder(BattleField.MAP_SIZE * BattleField.MAP_SIZE);
        this.forEach(p -> sb.append(p.getSymbol()));
        return new BattleFieldSerialized(sb.toString());
    }

    private int index(int x, int y) {
        return MAP_SIZE * y + x;
    }

    private int index(Point p) {
        return index(p.getX(), p.getY());
    }

}
