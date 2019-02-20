package com.owr.games.ships.game.validators;

public class ValidationFailItem {

    private ShipValidationErrCode type;
    private String[] param;

    public ValidationFailItem(ShipValidationErrCode type, String... param) {
        this.type = type;
        this.param = param;
    }

    public ValidationFailItem(ShipValidationErrCode type) {
        this.type = type;
    }

    public ShipValidationErrCode getType() {
        return type;
    }

    public void setType(ShipValidationErrCode type) {
        this.type = type;
    }

    public String[] getParam() {
        return param;
    }


}
