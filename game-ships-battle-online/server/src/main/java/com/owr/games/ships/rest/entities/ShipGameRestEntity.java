package com.owr.games.ships.rest.entities;

import com.owr.games.ships.model.GameStatus;

public class ShipGameRestEntity {

    private Long gameId;

    private ShipGamePlayerRestEntity player1;
    private ShipGamePlayerRestEntity player2;

    private GameStatus status;

    public ShipGameRestEntity() {
    }

    public ShipGameRestEntity(Long gameId) {
        this.gameId = gameId;
        status = GameStatus.WAITING_2ND_PLAYER;
        player1 = new ShipGamePlayerRestEntity(null, null, false);
        player1 = new ShipGamePlayerRestEntity(null, null, false);
    }

    public ShipGameRestEntity(Long gameId, GameStatus status, ShipGamePlayerRestEntity player1, ShipGamePlayerRestEntity player2) {
        this.gameId = gameId;
        this.player1 = player1;
        this.player2 = player2;
        this.status = status;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public ShipGamePlayerRestEntity getPlayer1() {
        return player1;
    }

    public void setPlayer1(ShipGamePlayerRestEntity player1) {
        this.player1 = player1;
    }

    public ShipGamePlayerRestEntity getPlayer2() {
        return player2;
    }

    public void setPlayer2(ShipGamePlayerRestEntity player2) {
        this.player2 = player2;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
