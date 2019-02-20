/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.owr.games.ships.game.validators;

import java.util.List;

/**
 * @author owr
 */
public class BattleFieldValidationException extends RuntimeException {

    private List<ValidationFailItem> errors;

    public BattleFieldValidationException(List<ValidationFailItem> errors) {
        this.errors = errors;
    }

    public List<ValidationFailItem> getErrors() {
        return errors;
    }
}
