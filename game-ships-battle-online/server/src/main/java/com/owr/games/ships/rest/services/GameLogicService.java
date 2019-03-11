package com.owr.games.ships.rest.services;

import java.util.List;
import java.util.Locale;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;

public interface GameLogicService {

	BattleField createMapStr(Locale locale, List<Ship> ships);

	BattleField createMapStr(Locale locale, String map, Point hitPoint);

    boolean isEnemyLost(BattleField enemyField);

}
