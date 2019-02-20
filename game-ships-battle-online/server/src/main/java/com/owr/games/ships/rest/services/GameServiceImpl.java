package com.owr.games.ships.rest.services;

import com.owr.games.ships.model.BattleFieldSerialized;
import com.owr.games.ships.model.GameStatus;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.db.entities.GameEntity;
import com.owr.games.ships.rest.db.entities.PlayerEntity;
import com.owr.games.ships.rest.entities.*;
import com.owr.games.ships.rest.resource.GameRepository;
import com.owr.games.ships.rest.resource.PlayerRepository;
import com.owr.games.ships.rest.services.utils.RestEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gmRepo;

    @Autowired
    private PlayerRepository plRepo;

    @Autowired
    private ResourceService resService;

    @Autowired
    private GameLogicService logicService;

    @Override
    public ServiceResponseEntity<ShipGameRestEntity> createGame(Locale locale, String token, JoinIntoGameRestEntity data) {

        ServiceResponseEntity<ShipGameRestEntity> result = new ServiceResponseEntity<>();

        token = tokenToLowerCase(result.getErrors(), locale, token);
        BattleFieldSerialized bfSer = logicService.createMapStr(result.getErrors(), locale, data.getShips());
        if (result.isAnyErrors()) return result;

        PlayerEntity player1 = getUser(result.getErrors(), locale, token);
        if (result.isAnyErrors()) return result;

        GameEntity ge = new GameEntity();
        ge.setStatus(GameStatus.WAITING_2ND_PLAYER);
        ge.setMap1(bfSer.getOriginalView());
        ge.setPlayer1(player1);
        ge = gmRepo.save(ge);

        result.setResponse(RestEntityUtil.createRestEntity(ge, token));

        return result;
    }

    @Override
    public ServiceResponseEntity<ShipGameRestEntity> joinGame(Locale locale, String token, long gameId, JoinIntoGameRestEntity data) {

        ServiceResponseEntity<ShipGameRestEntity> result = new ServiceResponseEntity<>();
        token = tokenToLowerCase( result.getErrors(), locale, token);
        BattleFieldSerialized bfSer = logicService.createMapStr(result.getErrors(), locale, data.getShips());
        if (result.isAnyErrors()) return result;

        PlayerEntity player2 = getUser(result.getErrors(), locale, token);
        GameEntity ge = findWaitingForJoiner(result.getErrors(), locale, gameId, token);
        if (result.isAnyErrors()) return result;

        ge.setMap2(bfSer.getOriginalView());
        ge.setPlayer2(player2);
        ge.setPlayerTurn(player2); // Joiner starts the battle
        ge.setStatus(GameStatus.BATTLE);

        result.setResponse(RestEntityUtil.createRestEntity(ge, token));

        return result;
    }

    @Override
    public ServiceResponseEntity<ShipGameRestEntity> playerTurn(Locale locale, String token, long gameId, Point hitTarget) {
//FIXME split method
        ServiceResponseEntity<ShipGameRestEntity> result = new ServiceResponseEntity<>();
        token = tokenToLowerCase(result.getErrors(), locale, token);
        if (result.isAnyErrors()) return result;

        GameEntity ge = findWaitingForMyTurn(result.getErrors(), locale, gameId, token);
        if (result.isAnyErrors()) return result;

        boolean player1 = detectPlayer1(ge);
        BattleFieldSerialized bfSer = logicService.createMapStr(result.getErrors(), locale, (player1 ? ge.getMap2() : ge.getMap1()), hitTarget);
        if (result.isAnyErrors()) return result;

        if (player1) {
            ge.setMap2(bfSer.getOriginalView());
        } else {
            ge.setMap1(bfSer.getOriginalView());
        }

        if (logicService.isEnemyLost(bfSer.getOriginalView())) {
            ge.setStatus(GameStatus.WIN);
        } else {

            // next turn
            if (player1) {
                ge.setPlayerTurn(ge.getPlayer2());
            } else {
                ge.setPlayerTurn(ge.getPlayer1());
            }
        }

        result.setResponse(RestEntityUtil.createRestEntity(ge, token));

        return result;
    }

    @Override
    public List<ShipGameRestEntity> waitingForJoiner(Locale locale, String token) {
        List<ShipGameRestEntity> result = new ArrayList<>();
        gmRepo.findWaitingForJoiners(token).forEach(ge -> result.add(RestEntityUtil.createRestEntity(ge, token)));
        return result;
    }

    @Override
    public List<ShipGameRestEntity> waitingForOthersTurn(Locale locale, String token) {
        List<ShipGameRestEntity> result = new ArrayList<>();
        gmRepo.findWaitingForOthersTurn(token).forEach(ge -> result.add(RestEntityUtil.createRestEntity(ge, token)));
        return result;
    }

    @Override
    public  Map<String, Long>  calcGameStats() {
        return gmRepo.collectStatistics();
    }

    @Override
    public List<ShipGameRestEntity> waitingForMyTurn(Locale locale, String token) {
        List<ShipGameRestEntity> result = new ArrayList<>();
        gmRepo.findWaitingForMyTurns(token).forEach(ge -> result.add(RestEntityUtil.createRestEntity(ge, token)));
        return result;
    }

    private boolean detectPlayer1(GameEntity ge) {

        if (ge.getPlayerTurn().getToken().equals(ge.getPlayer1().getToken())) {
            return true;
        } else if (ge.getPlayerTurn().getToken().equals(ge.getPlayer2().getToken())) {
            return false;
        }

        throw new RuntimeException();
    }

    private String tokenToLowerCase(List<ErrorMsgRestEntity> errors, Locale locale, String token) {

        if (token == null || token.isEmpty()) {
            errors.add(resService.createErrMsgEntity(locale, ErrorCode.TOKEN_INVALID.toString(), token));
            return null;
        }

        return token.toLowerCase();
    }

    private PlayerEntity getUser(List<ErrorMsgRestEntity> errors, Locale locale, String token) {
        PlayerEntity player = plRepo.findByToken(token);
        if (player == null) {
            errors.add(resService.createErrMsgEntity(locale, ErrorCode.TOKEN_INVALID.toString(), token));
        }
        return player;
    }


    private GameEntity findWaitingForMyTurn(List<ErrorMsgRestEntity> errors, Locale locale, long gameId, String token) {
        GameEntity ge = gmRepo.findWaitingForMyTurn(gameId, token);
        if (ge == null) {
            errors.add(resService.createErrMsgEntity(locale, ErrorCode.GAME_IT_IS_NOT_MY_TURN.toString()));
        }
        return ge;
    }

    private GameEntity findWaitingForJoiner(List<ErrorMsgRestEntity> errors, Locale locale, long gameId, String token) {
        GameEntity ge = gmRepo.findWaitingForJoiner(gameId, token);
        if (ge == null) {
            errors.add(resService.createErrMsgEntity(locale, ErrorCode.GAME_JOINING_FAIL.toString(), "" + gameId));
        }
        return ge;
    }
}
