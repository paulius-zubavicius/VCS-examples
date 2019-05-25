package com.vcs.fx.game.model;

public enum Gun {

    //FIXME add sound
    //FIXME add graphical info
    //AMO?

    PEW_PEW("PewPew", 0.5, 0.7, 1000000000L, 0 ),
    JUSTICE("Justice", 1.0, 1.0, 100000000L, -4, -2, 0, 2, 4 ),
    LASER("Laser", 3.0, 10.0, 100000000L, -2,  2  ),
    BFG("BFG", 15.0, 0.4,     1000000000L, 0  ),
    X_RAY("X", 50.0, 15.0,     100000000L, -2,-1,0,1,2  );



    private String name;
    private int[] canPos;
    private long frequency;
    private double damage;
    private double speed;

    Gun(String name, double damage, double speed, long frequency, int... canPos) {
        this.name = name;
        this.canPos = canPos;
        this.frequency = frequency;
        this.damage = damage;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int[] getCanPos() {
        return canPos;
    }

    public long getFrequency() {
        return frequency;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }
}
