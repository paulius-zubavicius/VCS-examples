package com.owr.games.ships.rest.services.utils;

import com.owr.games.ships.game.BattleField;
import com.owr.games.ships.rest.db.entities.GameEntity;
import com.owr.games.ships.rest.db.entities.PlayerEntity;
import com.owr.games.ships.rest.entities.ShipGamePlayerRestEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;

public class RestEntityUtil {

	public static ShipGameRestEntity createRestEntity(GameEntity ge, String token) {

		ShipGameRestEntity entity = new ShipGameRestEntity();

		entity.setGameId(ge.getId());
		entity.setStatus(ge.getStatus());

		entity.setPlayer1(new ShipGamePlayerRestEntity());
		entity.setPlayer2(new ShipGamePlayerRestEntity());

		setsPlayerEntity(entity.getPlayer1(), ge.getPlayer1(), ge.getPlayerTurn(), ge.getMap1(), token);
		setsPlayerEntity(entity.getPlayer2(), ge.getPlayer2(), ge.getPlayerTurn(), ge.getMap2(), token);

		return entity;
	}

	private static void setsPlayerEntity(ShipGamePlayerRestEntity playerEntity, PlayerEntity player,
			PlayerEntity playerTurn, String playerMap, String token) {
		boolean isItForEnemy = !getNotNullToken(player).equalsIgnoreCase(token);
		BattleField bf = new BattleField(playerMap);

		playerEntity.setMap( bf.cloneBattleFieldData(isItForEnemy));
		playerEntity.setName(getName(player));
		playerEntity.setPlayerTurn(equalingById(playerTurn, player));
	}

	private static String getName(PlayerEntity player) {
		if (player != null && player.getName() != null) {
			return player.getName();
		}
		return null;
	}

	private static String getNotNullToken(PlayerEntity player) {
		if (player != null && player.getToken() != null) {
			return player.getToken();
		}
		return "";
	}

	private static boolean equalingById(PlayerEntity playerTurn, PlayerEntity player) {
		if (playerTurn == null || player == null) {
			return false;
		}

		if (playerTurn.getId() == null || player.getId() == null) {
			return false;
		}

		return playerTurn.getId().equals(player.getId());
	}
}
