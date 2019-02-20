package com.owr.games.ships.rest.services;

import com.owr.games.ships.rest.db.entities.PlayerEntity;
import com.owr.games.ships.rest.entities.PlayerRestEntity;
import com.owr.games.ships.rest.resource.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repo;

    @Override
    public PlayerRestEntity createPlayer(String name, String type) {

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(name);
        playerEntity.setType(type);
        playerEntity.setToken(UUID.randomUUID().toString());

        PlayerEntity savedEntity = repo.save(playerEntity);

        PlayerRestEntity result = new PlayerRestEntity();
        result.setName(savedEntity.getName());
        result.setType(savedEntity.getType());
        result.setToken(savedEntity.getToken());

        return result;
    }

    @Override
    public List<String> listNames() {
        return repo.listNames();
    }
    @Override
    public Map<String, Long> calcGameStats() {
        return repo.collectStatistics();
    }

}
