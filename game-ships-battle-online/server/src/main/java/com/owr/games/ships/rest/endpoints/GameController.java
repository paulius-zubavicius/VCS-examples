package com.owr.games.ships.rest.endpoints;


import com.owr.games.ships.model.Point;
import com.owr.games.ships.rest.entities.JoinIntoGameRestEntity;
import com.owr.games.ships.rest.entities.ServiceResponseEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;
import com.owr.games.ships.rest.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/game")
public class GameController {

    private final static Logger LOG = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService service;

    @PostMapping("/create")
    public ResponseEntity createGame(Locale locale, @NonNull @RequestHeader("token") String token, @NonNull @RequestBody JoinIntoGameRestEntity data) {
        LOG.info("createGame. GameId: ?");
        return handleResponse( service.createGame(locale, token, data), null, "createGame");
    }

    @PostMapping("/join/{gameId}")
    public ResponseEntity joinGame(Locale locale, @NonNull @RequestHeader("token") String token, @NonNull @PathVariable("gameId") long gameId, @NonNull @RequestBody JoinIntoGameRestEntity data) {
        LOG.info("joinToGame. GameId: {}", gameId);
        return handleResponse(service.joinGame(locale, token, gameId, data), gameId, "joinToGame");
    }

    @PostMapping("/turn/{gameId}")
    public ResponseEntity playerTurn(Locale locale, @NonNull @RequestHeader("token") String token, @NonNull @PathVariable("gameId") long gameId, @NonNull @RequestBody Point hitTarget) {
        LOG.info("playerTurn. GameId:{}", gameId);
       return handleResponse(service.playerTurn(locale, token, gameId, hitTarget), gameId, "playerTurn");
    }

    private ResponseEntity handleResponse(ServiceResponseEntity<ShipGameRestEntity> result, Long gameId, String logMsg) {
        if (!result.getErrors().isEmpty()) {
            LOG.info("FAILED: " + logMsg + ". GameId: {}", gameId);
            return ResponseEntity.badRequest().body(result.getErrors());
        }
        return ResponseEntity.ok(result.getResponse());
    }

    @GetMapping("/join/list")
    public List<ShipGameRestEntity> listJoiners(Locale locale,  @NonNull @RequestHeader("token") String token) {
        LOG.info("List: waiting for me to join");
        return service.waitingForJoiner(locale, token);
    }

    @PostMapping("/my-turn/list")
    public List<ShipGameRestEntity> waitingForMyTurn(Locale locale,  @NonNull @RequestHeader("token") String token) {
        LOG.info("List: waiting for my turn");
        return service.waitingForMyTurn(locale, token);
    }

    @PostMapping("/my-wait/list")
    public List<ShipGameRestEntity> waitingForOthersTurn(Locale locale,  @NonNull @RequestHeader("token") String token) {
        LOG.info("List: waiting for others turn");
        return service.waitingForOthersTurn(locale, token);
    }

}
