package com.owr.games.ships.rest.db.entities;

import com.owr.games.ships.model.GameStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 100, max = 100)
    private String map1;

    @Size(min = 100, max = 100)
    private String map2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player1")
    private PlayerEntity player1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player2")
    private PlayerEntity player2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerTurn")
    private PlayerEntity playerTurn;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMap1() {
        return map1;
    }

    public void setMap1(String map1) {
        this.map1 = map1;
    }

    public String getMap2() {
        return map2;
    }

    public void setMap2(String map2) {
        this.map2 = map2;
    }

    public PlayerEntity getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerEntity player1) {
        this.player1 = player1;
    }

    public PlayerEntity getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerEntity player2) {
        this.player2 = player2;
    }

    public PlayerEntity getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(PlayerEntity playerTurn) {
        this.playerTurn = playerTurn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
