package com.owr.games.ships.rest.services;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.entities.JoinIntoGameRestEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;

public interface GameService {

    ShipGameRestEntity createGame(Locale locale, String token, JoinIntoGameRestEntity shipsPositions);

    ShipGameRestEntity joinGame(Locale locale, String token, long gameId, JoinIntoGameRestEntity shipsPositions);

    ShipGameRestEntity playerTurn(Locale locale, String token, long gameId, Point hitTarget);

    List<ShipGameRestEntity> waitingForMyTurn(Locale locale, String token);

    List<ShipGameRestEntity> waitingForJoiner(Locale locale, String token);

    List<ShipGameRestEntity> waitingForOthersTurn(Locale locale, String token);

    Map<String, Long> calcGameStats();
}
