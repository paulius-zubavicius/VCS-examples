package com.owr.games.ships.game.validators;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;

import java.util.*;

public class ShipsValidator {

    public static final int SHIPS_COUNT = 5;

    public List<ValidationFailItem> validate(List<Ship> ships) {
        return validate( ships.toArray(new Ship[0]));
    }

    public List<ValidationFailItem> validate(Ship... ships) {

        List<ValidationFailItem> fails = new ArrayList<>();

        nullHook(ships, fails);

        if (!fails.isEmpty()) {
            return fails;
        }

        countHook(ships, fails);

        if (!fails.isEmpty()) {
            return fails;
        }

        for (Ship ship : ships) {
            coordBoundsHook(ship, fails);
            diagonallyHook(ship, fails);
        }

        if (!fails.isEmpty()) {
            return fails;
        }

        crossingHook(ships, fails);

        sizesHook(ships, fails);

        return fails;

    }

    public List<ValidationFailItem> validatePoint(Point p) {

        List<ValidationFailItem> result = new ArrayList<>();

        if (p.getX() < 0 ||p.getY() < 0 || p.getX() >= BattleField.MAP_SIZE || p.getY() >= BattleField.MAP_SIZE) {
            result.add(new ValidationFailItem(ShipValidationErrCode.CoordinateOutOfBounds, p.toString()));
        }

        return result;

    }

    private void nullHook(Ship[] ships, List<ValidationFailItem> fails) {
        if (ships == null || ships.length < 1) {
//            throw new BattleFieldValidationException("no ships");
            fails.add(new ValidationFailItem(ShipValidationErrCode.NullValue));
            return;
        }

        for (Ship ship : ships) {
            if (ship == null) {
//                throw new BattleFieldValidationException("empty ship");
                fails.add(new ValidationFailItem(ShipValidationErrCode.NullValue));
                return;
            }

            if (ship.getP1() == null) {
                fails.add(new ValidationFailItem(ShipValidationErrCode.NullValue, "1"));
                //throw new BattleFieldValidationException("empty point 1 of ship");
            }

            if (ship.getP2() == null) {
                fails.add(new ValidationFailItem(ShipValidationErrCode.NullValue, "2"));
//                throw new BattleFieldValidationException("empty point 2 of ship");
            }
        }
    }

    private void countHook(Ship[] ships, List<ValidationFailItem> fails) {
        if (ships.length != SHIPS_COUNT) {
//            throw new BattleFieldValidationException("ships count must be " + SHIPS_COUNT);
            fails.add(new ValidationFailItem(ShipValidationErrCode.ShipsCount, "" + ships.length + " != " + SHIPS_COUNT));
        }
    }



    private void coordBoundsHook(Ship ship, List<ValidationFailItem> fails) {
        fails.addAll( validatePoint(ship.getP1()));
        fails.addAll( validatePoint(ship.getP2()));
    }

    private void diagonallyHook(Ship ship, List<ValidationFailItem> fails) {

        if (ship.getP1().getX() != ship.getP2().getX() && ship.getP1().getY() != ship.getP2().getY()) {
            fails.add(new ValidationFailItem(ShipValidationErrCode.DiagonallyPosition, ship.toString()));
        }

    }

    private void sizesHook(Ship[] ships, List<ValidationFailItem> fails) {


        Map<Integer, Integer> shipSizes = new HashMap<>();

        for (Ship ship : ships) {
            int size = calcShipSize(ship);
            if (!shipSizes.containsKey(size)) {
                shipSizes.put(size, 0);
            }

            shipSizes.put(size, shipSizes.get(size) + 1);
        }


        for (int size = 1; size <= SHIPS_COUNT; size++) {

            Integer count = shipSizes.remove(size);
            if (count == null) {
                fails.add(new ValidationFailItem(ShipValidationErrCode.ShipSizeMissing, "Missing ship size: " + size));
            } else if (count > 1) {
                fails.add(new ValidationFailItem(ShipValidationErrCode.ShipSizeDuplicate, "More than one ship size: " + size));
            }

        }

        for (Integer size : shipSizes.keySet()) {
            fails.add(new ValidationFailItem(ShipValidationErrCode.ShipSizeToBig, "Ship size to big " + size));
        }

    }

    private int calcShipSize(Ship ship) {
        return Math.max(Math.abs(ship.getP1().getX() - ship.getP2().getX()), Math.abs(ship.getP1().getY() - ship.getP2().getY())) + 1;
    }

    private void crossingHook(Ship[] ships, List<ValidationFailItem> fails) {


        List<String> duplicates = new ArrayList<>();

        for (int i = 0; i < ships.length; i++) {
            for (int j = i; j < ships.length; j++) {

                if (!duplicates.contains(generateKey(ships[i], ships[j]))) {
                    duplicates.add(generateKey(ships[i], ships[j]));
                    if (ships[i] != ships[j] && crossingLeastOnePoint(ships[i], ships[j])) {
                        fails.add(new ValidationFailItem(ShipValidationErrCode.Crossing, "" + ships[i] + " X " + ships[j]));
                    }
                }
            }
        }
    }

    private String generateKey(Ship ship1, Ship ship2) {

        if (ship1.hashCode() > ship2.hashCode()) {
            return "" + ship1.hashCode() + "_" + ship2.hashCode();
        }

        return "" + ship2.hashCode() + "_" + ship1.hashCode();

    }

    private boolean crossingLeastOnePoint(Ship ship1, Ship ship2) {

        List<Point> points1 = ship1.convertToPoints();
        List<Point> points2 = ship2.convertToPoints();

        for (Point p : points1) {
            if (points2.contains(p)) {
                return true;
            }
        }

        return false;
    }


    private boolean checkOnePoint(Ship ship, Point p) {
        int x1 = Math.min(ship.getP1().getX(), ship.getP2().getX());
        int x2 = Math.max(ship.getP1().getX(), ship.getP2().getX());

        int y1 = Math.min(ship.getP1().getY(), ship.getP2().getY());
        int y2 = Math.max(ship.getP1().getY(), ship.getP2().getY());

        if (x1 >= p.getX() && p.getX() <= x2) {

            if (y1 >= p.getY() && p.getY() <= y2) {
                return false;
            }
        }

        return true;
    }

}
