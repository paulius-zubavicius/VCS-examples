package com.owr.games.ships.rest.resource;

import com.owr.games.ships.rest.db.entities.GameEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface GameRepository extends CrudRepository<GameEntity, Long> {

    @Query("SELECT (SELECT count(1) FROM Game g WHERE g.status = 'WAITING_2ND_PLAYER') AS created, (SELECT count(1) FROM Game g WHERE g.status = 'BATTLE') AS live, (SELECT count(1) FROM Game g WHERE g.status = 'WIN') AS done FROM Dual d ")
    Map<String, Long> collectStatistics();


    @Query("SELECT g FROM Game g WHERE g.id = :gameId AND g.player1.token <> :token AND g.status = 'WAITING_2ND_PLAYER' ")
    GameEntity findWaitingForJoiner(@Param("gameId") Long gameId, @Param("token") String token2ndPlayer);

    @Query("SELECT g FROM Game g WHERE g.player1.token <> :token AND g.status = 'WAITING_2ND_PLAYER' ")
    List<GameEntity> findWaitingForJoiners(@Param("token") String token2ndPlayer);


    @Query("SELECT g FROM Game g WHERE g.id = :gameId AND g.playerTurn.token = :token AND g.status='BATTLE' ")
    GameEntity findWaitingForMyTurn(@Param("gameId") Long gameId, @Param("token") String token);

    @Query("SELECT g FROM Game g WHERE g.playerTurn.token = :token AND g.status='BATTLE' ")
    List<GameEntity> findWaitingForMyTurns(@Param("token") String token);

    @Query("SELECT g FROM Game g WHERE (g.player1.token = :token OR g.player2.token = :token) AND g.playerTurn.token <> :token AND g.status='BATTLE' ")
    List<GameEntity> findWaitingForOthersTurn(@Param("token") String token);
}
