package com.owr.games.ships.rest.endpoints;


import com.owr.games.ships.rest.entities.PlayerRestEntity;
import com.owr.games.ships.rest.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final static Logger LOG = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService service;

    @PostMapping("/register/{playerName}")
    public PlayerRestEntity createNewPlayer(@PathVariable("playerName") String name, @RequestHeader(name = "player-type", defaultValue = "player") String type) {
        LOG.info("Creating new player {}", name);
        return service.createPlayer(name, type);
    }

    @GetMapping("/list")
    public List<String> listPlayersNames() {
        LOG.info("Listing players names");
        return service.listNames();
    }

}
