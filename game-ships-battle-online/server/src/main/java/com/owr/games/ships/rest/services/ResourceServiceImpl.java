package com.owr.games.ships.rest.services;

import com.owr.games.ships.rest.entities.ErrorMsgRestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ResourceServiceImpl implements ResourceService {


    @Autowired
    private MessageSource messageSource;

    @Override
    public ErrorMsgRestEntity createErrMsgEntity(Locale locale, String code, String... params) {
        return new ErrorMsgRestEntity(code, translate(locale, code, params));
    }

    @Override
    public String translate(Locale locale, String code, String... params) {
        return messageSource.getMessage(code, params, "??? " + code + " ??? ", locale);
    }
}
