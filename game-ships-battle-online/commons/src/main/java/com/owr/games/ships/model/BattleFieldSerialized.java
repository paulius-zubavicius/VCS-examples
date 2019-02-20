package com.owr.games.ships.model;

public class BattleFieldSerialized {

    private String maskedView;
    private String originalView;

    public BattleFieldSerialized(String originalView) {
        this.originalView = originalView;
        if (originalView != null) {
            this.maskedView = originalView.replace(BattleFieldValue.Ship.getSymbol(), BattleFieldValue.Empty.getSymbol());
        }
    }

    public String getMaskedView() {
        return maskedView;
    }

    public String getOriginalView() {
        return originalView;
    }

}
