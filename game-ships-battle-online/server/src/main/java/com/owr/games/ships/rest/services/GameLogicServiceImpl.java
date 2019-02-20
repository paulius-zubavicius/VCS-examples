package com.owr.games.ships.rest.services;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.game.validators.BattleFieldValidationException;
import com.owr.games.ships.game.validators.ValidationFailItem;
import com.owr.games.ships.model.BattleFieldSerialized;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import com.owr.games.ships.rest.entities.ErrorMsgRestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class GameLogicServiceImpl implements GameLogicService {

    @Autowired
    private ResourceService resService;

    @Override
    public BattleFieldSerialized createMapStr(List<ErrorMsgRestEntity> errors, Locale locale, List<Ship> ships) {
        try {
            return new BattleField(ships).serializeBattleField();
        } catch (BattleFieldValidationException e) {
            convertErrorMsg(e, errors, locale);
            return null;
        }
    }

    @Override
    public BattleFieldSerialized createMapStr(List<ErrorMsgRestEntity> errors, Locale locale, String map, Point hitPoint) {

        try {
            BattleField bf = new BattleField(map);
            bf.hitTo(hitPoint);
            return bf.serializeBattleField();
        } catch (BattleFieldValidationException e) {
            convertErrorMsg(e, errors, locale);
            return null;
        }
    }

    @Override
    public boolean isEnemyLost(String enemyField) {
        return new BattleField(enemyField).isLost();
    }

    private void convertErrorMsg(BattleFieldValidationException e, List<ErrorMsgRestEntity> errors, Locale locale) {
        for (ValidationFailItem item : e.getErrors()) {
            errors.add(resService.createErrMsgEntity(locale, item.getType().toString(), item.getParam()));
        }
    }


}
