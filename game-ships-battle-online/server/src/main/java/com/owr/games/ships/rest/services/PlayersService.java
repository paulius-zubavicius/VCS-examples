package com.owr.games.ships.rest.services;

import com.owr.games.ships.db.entities.PlayerEntity;
import com.owr.games.ships.rest.entities.PlayerRestEntity;


public interface PlayersService {

    PlayerRestEntity createPlayer(String name, String type);

    Iterable<PlayerRestEntity> listAll();
}
