package com.owr.games.ships.rest.services;


import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.entities.PlayerShipsRestEntity;
import com.owr.games.ships.rest.entities.ServiceResponseEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;

import java.util.List;

public interface GameService {

    ServiceResponseEntity<ShipGameRestEntity> createGame(String token, PlayerShipsRestEntity shipsPositions);

    ServiceResponseEntity<ShipGameRestEntity> joinGame(long gameId, String token, PlayerShipsRestEntity shipsPositions);

    List<ShipGameRestEntity> waitingTurnByToken(String token);

    ServiceResponseEntity<ShipGameRestEntity> playerTurn(long gameId, String token, Point hitTarget);

    ServiceResponseEntity<ShipGameRestEntity> findById(long gameId);

    List<ShipGameRestEntity> findAll();
}
