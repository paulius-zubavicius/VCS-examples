package com.vcs.fx.game.settings;

import java.util.Optional;
import java.util.Properties;

public class Settings {

    private Properties prop = new Properties();


    public Settings() {
        prop.setProperty(Prop.GC_SCALE_FACTOR.toString(),"0.5");
    }


   public void loadSettings() {

    }

    public Optional<String> getProp(String key){
        return Optional.ofNullable(prop.getProperty(key));
    }
}
