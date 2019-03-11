package com.owr.games.ships.rest.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.owr.games.ships.rest.entities.ErrorMsgRestEntity;

public class GameServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<ErrorMsgRestEntity> errors = new ArrayList<>();

	public GameServiceException(ErrorMsgRestEntity error) {
		errors.add(error);
	}

	public GameServiceException(ErrorMsgRestEntity... errorsArr) {
		errors.addAll(Arrays.asList(errorsArr));
	}

	public GameServiceException(List<ErrorMsgRestEntity> errorsList) {
		errors.addAll(errorsList);
	}

	public List<ErrorMsgRestEntity> getErrors() {
		return errors;
	}

}
