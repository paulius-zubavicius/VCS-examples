package com.owr.games.ships.rest.resource;

import com.owr.games.ships.rest.db.entities.PlayerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {

    PlayerEntity findByToken(String token);

    @Query("SELECT p.name FROM Player p")
    List<String> listNames();



    // Most Win
    //Most Lost
    // Most done

    //Most created
    // Most joined

    @Query("SELECT p.name, count(1) AS win FROM Game g, Player p WHERE g.player1 = p AND g.status = 'WAITING_2ND_PLAYER' GROUP BY p.name ")
    Map<String, Long> collectStatistics();


    // win / lost; (played at all)

}
