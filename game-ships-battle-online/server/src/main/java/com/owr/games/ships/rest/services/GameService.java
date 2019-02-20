package com.owr.games.ships.rest.services;


import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.entities.JoinIntoGameRestEntity;
import com.owr.games.ships.rest.entities.ServiceResponseEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface GameService {

    ServiceResponseEntity<ShipGameRestEntity> createGame(Locale locale, String token, JoinIntoGameRestEntity shipsPositions);

    ServiceResponseEntity<ShipGameRestEntity> joinGame(Locale locale, String token, long gameId, JoinIntoGameRestEntity shipsPositions);

    ServiceResponseEntity<ShipGameRestEntity> playerTurn(Locale locale, String token, long gameId, Point hitTarget);

//    ServiceResponseEntity<ShipGameRestEntity> viewMyGame(Locale locale, String token, long gameId);


    List<ShipGameRestEntity> waitingForMyTurn(Locale locale, String token);

    List<ShipGameRestEntity> waitingForJoiner(Locale locale, String token);

    List<ShipGameRestEntity> waitingForOthersTurn(Locale locale, String token);

    Map<String, Long> calcGameStats();
}
