package com.owr.games.ships.rest.entities;

public class ShipGamePlayerRestEntity {

    private String map;

    private String name;

    private boolean playerTurn;

    public ShipGamePlayerRestEntity() {
    }

    public ShipGamePlayerRestEntity(String map, String name, boolean playerTurn) {
        this.map = map;
        this.name = name;
        this.playerTurn = playerTurn;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
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
