package com.owr.games.ships.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.game.validators.BattleFieldValidationException;
import com.owr.games.ships.game.validators.ValidationFailItem;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.Ship;
import com.owr.games.ships.rest.entities.ErrorMsgRestEntity;
import com.owr.games.ships.rest.exceptions.GameServiceException;

@Service
public class GameLogicServiceImpl implements GameLogicService {

    @Autowired
    private ResourceService resService;

    @Override
    public BattleField createMapStr( Locale locale, List<Ship> ships) {
        try {
            return new BattleField(ships);
        } catch (BattleFieldValidationException e) {
        	List<ErrorMsgRestEntity> errors = new ArrayList<>();
            convertErrorMsg(e, errors, locale);
            throw new GameServiceException(errors);
        }
    }

    @Override
    public  BattleField createMapStr( Locale locale, String map, Point hitPoint) {
        try {
            BattleField bf = new BattleField(map);
            bf.hitTo(hitPoint);
            return bf;
        } catch (BattleFieldValidationException e) {
        	List<ErrorMsgRestEntity> errors = new ArrayList<>();
            convertErrorMsg(e, errors, locale);
            throw new GameServiceException(errors);
        }
    }

    @Override
    public boolean isEnemyLost(BattleField enemyField) {
        return enemyField.isLost();
    }

    private void convertErrorMsg(BattleFieldValidationException e, List<ErrorMsgRestEntity> errors, Locale locale) {
        for (ValidationFailItem item : e.getErrors()) {
            errors.add(resService.createErrMsgEntity(locale, item.getType().toString(), item.getParam()));
        }
    }


}
