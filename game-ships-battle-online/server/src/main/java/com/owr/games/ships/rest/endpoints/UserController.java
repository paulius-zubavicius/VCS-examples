package com.owr.games.ships.rest.endpoints;


import com.owr.games.ships.rest.entities.PlayerRestEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;
import com.owr.games.ships.rest.services.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class UserController {

    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PlayersService service;

    @PostMapping("/player/create/{playerName}")
    public PlayerRestEntity createNewPlayer(@PathVariable("playerName") String name, @RequestHeader(name = "player-type", defaultValue = "player") String type) {
        LOG.info("Creating new player {}", name);
        return service.createPlayer(name, type);
    }

    @GetMapping("/list")
    public Iterable<PlayerRestEntity> listPlayers() {
        LOG.info("Fetching all players");
        return service.listAll();
    }

    @GetMapping("/list/by-top")
    public Iterable<PlayerRestEntity> listPlayersTop() {
        LOG.info("Fetching top players");
        return service.listAll();
    }


}
