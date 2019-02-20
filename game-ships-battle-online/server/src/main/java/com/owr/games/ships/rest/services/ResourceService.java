package com.owr.games.ships.rest.services;

import com.owr.games.ships.rest.entities.ErrorMsgRestEntity;

import java.util.Locale;


public interface ResourceService {

    ErrorMsgRestEntity createErrMsgEntity(Locale locale, String code, String ... params);

    String translate(Locale locale, String code, String ... params);
}
