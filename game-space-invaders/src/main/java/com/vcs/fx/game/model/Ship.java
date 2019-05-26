package com.vcs.fx.game.model;

public enum Ship {

    Model00(0, Gun.PEW_PEW, 0.1, 5.0),
    Model01(1, Gun.PEW_PEW, 0.1, 5.0),
    Model02(2, Gun.PEW_PEW, 0.1, 5.0),
    Model03(3, Gun.PEW_PEW, 0.1, 5.0),
    Model04(4, Gun.PEW_PEW, 0.2, 5.0),
    Model05(5, Gun.PEW_PEW, 0.2, 5.0),
    Model06(6, Gun.PEW_PEW, 0.2, 5.0),
    Model07(7, Gun.PEW_PEW, 0.2, 5.0),
    Model08(8, Gun.PEW_PEW, 0.2, 5.0),
    Model09(9, Gun.PEW_PEW, 0.2, 5.0),
    Model10(10, Gun.PEW_PEW, 0.2, 5.0),
    Model11(11, Gun.PEW_PEW, 0.2, 5.0),
    Model12(12, Gun.PEW_PEW, 0.2, 5.0),
    Model13(13, Gun.PEW_PEW, 0.2, 5.0),
    Model14(14, Gun.PEW_PEW, 0.1, 5.0),
    Model15(15, Gun.PEW_PEW, 0.1, 5.0),
    Model16(16, Gun.PEW_PEW, 0.1, 5.0),
    Model17(17, Gun.PEW_PEW, 0.1, 5.0),
    Model18(18, Gun.JUSTICE, 0.1, 5.0),
    Model19(19, Gun.JUSTICE, 0.1, 5.0),
    Model20(20, Gun.JUSTICE, 0.1, 5.0),
    Model21(21, Gun.JUSTICE, 0.1, 5.0),
    Model22(22, Gun.JUSTICE, 0.1, 5.0),
    Model23(23, Gun.JUSTICE, 0.1, 5.0);



    private int model;
    private Gun gun;
    private double speed;
    private double health;


    Ship(int model, Gun gun, double speed, double health) {
        this.model = model;
        this.gun = gun;
        this.speed = speed;
        this.health = health;
    }

    public int getModel() {
        return model;
    }

    public Gun getGun() {
        return gun;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHealth() {
        return health;
    }
}
