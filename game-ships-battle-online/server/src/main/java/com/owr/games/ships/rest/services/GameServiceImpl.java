package com.owr.games.ships.rest.services;

import com.owr.games.ships.db.entities.GameEntity;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.entities.*;
import com.owr.games.ships.rest.resource.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;


    @Override
    public ServiceResponseEntity<ShipGameRestEntity> createGame(String token, PlayerShipsRestEntity shipsPositions) {

        //TODO
        return null;
    }

    @Override
    public ServiceResponseEntity<ShipGameRestEntity> joinGame(long gameId, String token, PlayerShipsRestEntity shipsPositions) {
        ServiceResponseEntity<ShipGameRestEntity> result = new ServiceResponseEntity<>();

        Optional<GameEntity> gameEntity = repository.findById(gameId);


        if (gameEntity.isPresent()) {
            gameEntity.get().setMap2("");
        }
        //TODO
        //new ErrorMsgRestEntity("timeout", "it's taken by another player")

        return result;
    }

    @Override
    public List<ShipGameRestEntity> waitingTurnByToken(String token) {
        //TODO
        return null;
    }

    @Override
    public ServiceResponseEntity<ShipGameRestEntity> playerTurn(long gameId, String token, Point hitTarget) {
        //TODO
        return null;
    }

    @Override
    public ServiceResponseEntity<ShipGameRestEntity> findById(long gameId) {
        ServiceResponseEntity<ShipGameRestEntity> result = new ServiceResponseEntity<>();
        Optional<GameEntity> geOpt = repository.findById(gameId);
        if (geOpt.isPresent()) {
            result.setResponse(convertFrom(geOpt.get()));
        } else {
            result.getErrors().add(new ErrorMsgRestEntity("game.not.found", "game not found by id"));
        }
        return result;
    }

    @Override
    public List<ShipGameRestEntity> findAll() {
        List<ShipGameRestEntity> result = new ArrayList<>();
        repository.findAll().forEach(ge -> result.add(convertFrom(ge)));
        return result;
    }

    private ShipGameRestEntity convertFrom(GameEntity ge) {
        return new ShipGameRestEntity(ge.getId(), ge.getState(), new ShipGamePlayerRestEntity(ge.getMap1(), ge.getPlayer1().getName(), ge.getPlayerTurn().getId().equals(ge.getPlayer1().getId())), new ShipGamePlayerRestEntity(ge.getMap2(), ge.getPlayer2().getName(), ge.getPlayerTurn().getId().equals(ge.getPlayer2().getId())));
    }
}
