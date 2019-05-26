package com.vcs.fx.game.layers;

import com.vcs.fx.game.model.Resolutions;
import javafx.scene.canvas.Canvas;

public class Menu implements IGCLayer {

    private boolean menuMode;

    @Override
    public Canvas getCanvas() {
        return null;
    }

    @Override
    public void init(Resolutions res) {

    }

    @Override
    public void updateTime(long now) {

    }

    public boolean isMenuMode() {
        return menuMode;
    }
}
