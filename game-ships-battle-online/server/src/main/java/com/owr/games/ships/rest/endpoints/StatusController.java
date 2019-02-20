package com.owr.games.ships.rest.endpoints;

import com.owr.games.ships.rest.services.GameService;
import com.owr.games.ships.rest.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/")
public class StatusController {

	private final static Logger LOG = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerService playerService;

	@GetMapping
	public String sayHi() {
		//request.getLocale();
		return "<html><body><a href='http://" + request.getLocalName() + ":" + request.getLocalPort()
				+ "/swagger-ui.html'>Swagger UI</a></body></html>";
	}

	@GetMapping("/stat/games")
	public Map<String, Long> gameStat() {
		LOG.info("Fetching top players");
		return gameService.calcGameStats();
	}

	// Most maches
	// Most Win
	//Most Lost
	//Most created
	// Most joined
	@GetMapping("/stat/players")
	public Map<String, Long> playersStat() {
		LOG.info("Fetching top players");
		return playerService.calcGameStats();
	}

	// win / lost; (played at all)
	@GetMapping("/stat/players/top-10-winners")
	public Map<String, Long> playersTopStat() {
		LOG.info("Fetching top players");
		return gameService.calcGameStats();
	}






}
