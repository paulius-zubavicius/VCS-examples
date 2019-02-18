package com.owr.games.ships.rest.endpoints;


import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.entities.PlayerShipsRestEntity;
import com.owr.games.ships.rest.entities.ServiceResponseEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;
import com.owr.games.ships.rest.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/games")
public class GameController {

    private final static Logger LOG = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService service;


    @PostMapping("/game/create")
    public ResponseEntity createGame(@PathVariable("token") String token, @RequestBody PlayerShipsRestEntity shipsPositions) {
        LOG.info("Creating a new game");
        ServiceResponseEntity<ShipGameRestEntity> result = service.createGame(token, shipsPositions);

        if (!result.getErrors().isEmpty()) {
            LOG.info("Creating a new game failed");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result.getErrors());
        }

        return ResponseEntity.ok(result.getResponse());
    }

    @PostMapping("/game/{gameId}/join")
    public ResponseEntity joinGame(@PathVariable("token") String token, @PathVariable("gameId") @NumberFormat long gameId, @RequestBody PlayerShipsRestEntity shipsPositions) {
        LOG.info("Joining into game {}", gameId);

        ServiceResponseEntity<ShipGameRestEntity> result = service.joinGame(gameId, token, shipsPositions);

        if (!result.getErrors().isEmpty()) {
            LOG.info("Joining into game failed {}", gameId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result.getErrors());
        }

        return ResponseEntity.ok(result.getResponse());
    }

    @GetMapping("/game/{gameId}/view")
    public ResponseEntity spectateGame(@PathVariable("gameId") @NumberFormat long gameId) {
        LOG.info("Spectate game {}", gameId);
        ServiceResponseEntity<ShipGameRestEntity> result = service.findById(gameId);

        if (!result.getErrors().isEmpty()) {
            LOG.info("Spectate game failed {}", gameId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result.getErrors());
        }

        return ResponseEntity.ok(result.getResponse());
    }

    @GetMapping("/list")
    public List<ShipGameRestEntity> listGames() {
        LOG.info("Fetch all games");
        return service.findAll();
    }

    @PostMapping("/list/waiting-for-my-turn")
    public List<ShipGameRestEntity> waitingForMyTurn(@PathVariable("token") String token) {
        LOG.info("Listing games for turn");
        return service.waitingTurnByToken(token);
    }

    @PostMapping("/game/{gameId}/my-turn")
    public ResponseEntity playerTurn(@PathVariable("token") String token, @PathVariable("gameId") @NumberFormat long gameId, @RequestBody Point hitTarget) {
        LOG.info("Taking a turn in game {}", gameId);
        ServiceResponseEntity<ShipGameRestEntity> result = service.playerTurn(gameId, token, hitTarget);


        if (!result.getErrors().isEmpty()) {
            LOG.info("Taking a turn in game failed {}", gameId);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result.getErrors());
        }

        return ResponseEntity.ok(result.getResponse());
    }


}
