package com.owr.games.ships.rest.entities;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponseEntity<T> {

    private T response;

    private List<ErrorMsgRestEntity> errors = new ArrayList<>();

    public ServiceResponseEntity() {
    }

    public ServiceResponseEntity(ErrorMsgRestEntity err) {
        this.errors.add(err);
    }

    public ServiceResponseEntity(T response) {
        this.response = response;
    }

    public ServiceResponseEntity(T response, List<ErrorMsgRestEntity> errors) {
        this.response = response;
        this.errors.addAll(errors);
    }


    public boolean isAnyErrors() {
        return !errors.isEmpty();
    }

    public T getResponse() {
        return response;
    }

    public List<ErrorMsgRestEntity> getErrors() {
        return errors;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
