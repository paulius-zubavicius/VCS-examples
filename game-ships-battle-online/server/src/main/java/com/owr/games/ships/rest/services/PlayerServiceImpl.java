package com.owr.games.ships.rest.services;

import com.owr.games.ships.db.entities.PlayerEntity;
import com.owr.games.ships.rest.entities.PlayerRestEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;
import com.owr.games.ships.rest.resource.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PlayerServiceImpl implements PlayersService {

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
    public Iterable<PlayerRestEntity> listAll() {

        Iterable<PlayerEntity> allEntities = repo.findAll();

        List<PlayerRestEntity> result = new ArrayList<>();
        for (PlayerEntity entity : allEntities) {
            result.add(new PlayerRestEntity(entity.getName()));
        }

        return result;
    }
}
