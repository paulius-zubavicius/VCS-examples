package com.owr.games.ships.rest.entities;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;

import java.util.List;

public class JoinIntoGameRestEntity {

    private List<Ship> ships;

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
