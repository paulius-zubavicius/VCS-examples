package com.owr.games.ships.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.model.GameStatus;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.db.entities.GameEntity;
import com.owr.games.ships.rest.db.entities.PlayerEntity;
import com.owr.games.ships.rest.entities.ErrorCode;
import com.owr.games.ships.rest.entities.JoinIntoGameRestEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;
import com.owr.games.ships.rest.exceptions.GameServiceException;
import com.owr.games.ships.rest.resource.GameRepository;
import com.owr.games.ships.rest.resource.PlayerRepository;
import com.owr.games.ships.rest.services.utils.RestEntityUtil;

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
	public ShipGameRestEntity createGame(Locale locale, String token,
			JoinIntoGameRestEntity data) {

		token = tokenToLowerCase(locale, token);
		BattleField bf = logicService.createMapStr(locale, data.getShips());
		
		PlayerEntity player1 = getUser(locale, token);
		
		GameEntity ge = new GameEntity();
		ge.setStatus(GameStatus.WAITING_2ND_PLAYER);
		ge.setMap1(bf.getOriginalStr());
		ge.setPlayer1(player1);
		ge = gmRepo.save(ge);

		return RestEntityUtil.createRestEntity(ge, token);
	}

	@Override
	public ShipGameRestEntity joinGame(Locale locale, String token, long gameId, JoinIntoGameRestEntity data) {

		token = tokenToLowerCase(locale, token);
		BattleField bf = logicService.createMapStr(locale, data.getShips());

		PlayerEntity player2 = getUser(locale, token);
		GameEntity ge = findWaitingForJoiner(locale, gameId, token);

		ge.setMap2(bf.getOriginalStr());
		ge.setPlayer2(player2);
		ge.setPlayerTurn(player2); // Joiner starts the battle
		ge.setStatus(GameStatus.BATTLE);

		return RestEntityUtil.createRestEntity(ge, token);
	}

	@Override
	public ShipGameRestEntity playerTurn(Locale locale, String token, long gameId, Point hitTarget) {

		token = tokenToLowerCase(locale, token);

		GameEntity ge = findWaitingForMyTurn(locale, gameId, token);

		boolean player1 = detectPlayer1(ge);
		BattleField bf = logicService.createMapStr(locale, (player1 ? ge.getMap2() : ge.getMap1()), hitTarget);

		if (player1) {
			ge.setMap2(bf.getOriginalStr());
		} else {
			ge.setMap1(bf.getOriginalStr());
		}

		if (logicService.isEnemyLost(bf)) {
			ge.setStatus(GameStatus.WIN);
		} else {

			// next turn
			if (player1) {
				ge.setPlayerTurn(ge.getPlayer2());
			} else {
				ge.setPlayerTurn(ge.getPlayer1());
			}
		}

		return RestEntityUtil.createRestEntity(ge, token);
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
	public Map<String, Long> calcGameStats() {
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

	private String tokenToLowerCase(Locale locale, String token) {
		if (token == null || token.isEmpty()) {
			throw new GameServiceException(
					resService.createErrMsgEntity(locale, ErrorCode.TOKEN_INVALID.toString(), token));
		}
		return token.toLowerCase();
	}

	private PlayerEntity getUser(Locale locale, String token) {
		PlayerEntity player = plRepo.findByToken(token);
		if (player == null) {
			throw new GameServiceException(
					resService.createErrMsgEntity(locale, ErrorCode.TOKEN_INVALID.toString(), token));
		}
		return player;
	}

	private GameEntity findWaitingForMyTurn(Locale locale, long gameId, String token) {
		GameEntity ge = gmRepo.findWaitingForMyTurn(gameId, token);
		if (ge == null) {
			throw new GameServiceException(
					resService.createErrMsgEntity(locale, ErrorCode.GAME_IT_IS_NOT_MY_TURN.toString()));
		}
		return ge;
	}

	private GameEntity findWaitingForJoiner(Locale locale, long gameId, String token) {
		GameEntity ge = gmRepo.findWaitingForJoiner(gameId, token);
		if (ge == null) {
			throw new GameServiceException(
					resService.createErrMsgEntity(locale, ErrorCode.GAME_JOINING_FAIL.toString(), "" + gameId));
		}
		return ge;
	}
}
