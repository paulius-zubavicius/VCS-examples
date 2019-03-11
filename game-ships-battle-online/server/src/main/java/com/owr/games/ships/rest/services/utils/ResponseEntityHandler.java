package com.owr.games.ships.rest.services.utils;

import java.util.Arrays;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.owr.games.ships.rest.entities.ErrorCode;
import com.owr.games.ships.rest.entities.ErrorMsgRestEntity;
import com.owr.games.ships.rest.entities.ShipGameRestEntity;
import com.owr.games.ships.rest.exceptions.GameServiceException;

public class ResponseEntityHandler {

	private final static Logger LOG = LoggerFactory.getLogger(ResponseEntityHandler.class);

	public static ResponseEntity<?> handle(Supplier<ShipGameRestEntity> supplier, Long gameId, String logMsg) {
		ShipGameRestEntity result = null;

		try {
			result = supplier.get();
		} catch (GameServiceException e) {
			LOG.info("FAILED: {}. GameId: {}", logMsg, gameId);
			return ResponseEntity.badRequest().body(e.getErrors());
		} catch (Exception e) {
			LOG.error("ERROR: " + logMsg + ". GameId: " + gameId, e);
			return ResponseEntity.badRequest()
					.body(Arrays.asList(new ErrorMsgRestEntity(ErrorCode.ERROR.toString(), e.getMessage())));
		}

		return ResponseEntity.ok(result);
	}
}
