package com.vcs.ex.jfx.game.shotimpact;

public class ShotImpact {

	private double power;
	private ImpactType type;

	public ShotImpact(double power, ImpactType type) {
		super();
		this.power = power;
		this.type = type;
	}

	public double getPower() {
		return power;
	}

	public ShotImpact setPower(double power) {
		this.power = power;
		return this;
	}

	public ImpactType getType() {
		return type;
	}

	public ShotImpact setType(ImpactType type) {
		this.type = type;
		return this;
	}

}
