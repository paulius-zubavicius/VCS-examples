package com.owr.games.ships.rest.services;

import com.owr.games.ships.model.BattleFieldSerialized;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import com.owr.games.ships.rest.entities.ErrorMsgRestEntity;

import java.util.List;
import java.util.Locale;

public interface GameLogicService {

    BattleFieldSerialized createMapStr(List<ErrorMsgRestEntity> errors, Locale locale, List<Ship> ships);

    BattleFieldSerialized createMapStr(List<ErrorMsgRestEntity> errors, Locale locale, String map, Point hitPoint);

    boolean isEnemyLost(String enemyField);

}
