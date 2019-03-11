package com.owr.games.ships.rest.entities;

import java.util.List;

import com.owr.games.ships.model.BattleFieldPoint;

public class ShipGamePlayerRestEntity {

    private List<BattleFieldPoint> map;

    private String name;

    private boolean playerTurn;

    public ShipGamePlayerRestEntity() {
    }

    public ShipGamePlayerRestEntity(List<BattleFieldPoint> map, String name, boolean playerTurn) {
        this.map = map;
        this.name = name;
        this.playerTurn = playerTurn;
    }

    public List<BattleFieldPoint> getMap() {
        return map;
    }

    public void setMap(List<BattleFieldPoint> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
}
