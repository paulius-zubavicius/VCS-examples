package com.owr.games.ships.rest.services;

import com.owr.games.ships.rest.entities.PlayerRestEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface PlayerService {

    PlayerRestEntity createPlayer(String name, String type);

    List<String> listNames();

    Map<String, Long> calcGameStats();
}
