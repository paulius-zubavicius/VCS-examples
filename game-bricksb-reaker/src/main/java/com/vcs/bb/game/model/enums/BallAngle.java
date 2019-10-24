package com.vcs.bb.game.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BallAngle {

	_0(1.0, 0.0), 
	_15(0.965925826, -0.258819045), 
	_30(0.866025404, -0.5), 
	_45(0.707106781, -0.707106781),
	_60(0.5, -0.866025404), 
	_75(0.258819045, -0.965925826), 
	_90(0.0, -1.0),
	_105(-0.258819045, -0.965925826),
	_120(-0.5, -0.866025404), 
	_135(-0.707106781, -0.707106781), 
	_150(-0.866025404, -0.5),
	_165(-0.965925826, -0.258819045), 
	_180(-1.0, -0.0), 
	_195(-0.965925826, 0.258819045), 
	_210(-0.866025404, 0.5),
	_225(-0.707106781, 0.707106781), 
	_240(-0.5, 0.866025404), 
	_255(-0.258819045, 0.965925826), 
	_270(0.0, 1.0),
	_285(0.258819045, 0.965925826), 
	_300(0.5, 0.866025404), 
	_315(0.707106781, 0.707106781), 
	_330(0.866025404, 0.5),
	_345(0.965925826, 0.258819045);

	private double x;
	private double y;

	private static Map<BallAngle, BallAngle> refHorizontal = new HashMap<>();
	private static Map<BallAngle, BallAngle> refVertical = new HashMap<>();

	private static List<BallAngle> outcomeAngle = new ArrayList<>(Arrays.asList( _15, _30, _45, _60, _75, _90, _105, _120, _135, _150, _165));
	private static List<BallAngle> incomeAngle = new ArrayList<>(Arrays.asList(_195, _210, _225, _240, _255, _270, _285, _300, _315, _330, _345));
	
	static {

		refHorizontal.put(BallAngle._0, BallAngle._0);
		refHorizontal.put(BallAngle._15, BallAngle._345);
		refHorizontal.put(BallAngle._30, BallAngle._330);
		refHorizontal.put(BallAngle._45, BallAngle._315);
		refHorizontal.put(BallAngle._60, BallAngle._300);
		refHorizontal.put(BallAngle._75, BallAngle._285);
		refHorizontal.put(BallAngle._90, BallAngle._270); // !!!
		refHorizontal.put(BallAngle._105, BallAngle._255);
		refHorizontal.put(BallAngle._120, BallAngle._240);
		refHorizontal.put(BallAngle._135, BallAngle._225);
		refHorizontal.put(BallAngle._150, BallAngle._210);
		refHorizontal.put(BallAngle._165, BallAngle._195);
		refHorizontal.put(BallAngle._180, BallAngle._180);
		refHorizontal.put(BallAngle._195, BallAngle._165);
		refHorizontal.put(BallAngle._210, BallAngle._150);
		refHorizontal.put(BallAngle._225, BallAngle._135);
		refHorizontal.put(BallAngle._240, BallAngle._120);
		refHorizontal.put(BallAngle._255, BallAngle._105);
		refHorizontal.put(BallAngle._270, BallAngle._90); // !!!
		refHorizontal.put(BallAngle._285, BallAngle._75);
		refHorizontal.put(BallAngle._300, BallAngle._60);
		refHorizontal.put(BallAngle._315, BallAngle._45);
		refHorizontal.put(BallAngle._330, BallAngle._30);
		refHorizontal.put(BallAngle._345, BallAngle._15);

		refVertical.put(BallAngle._0, BallAngle._180); // !!!
		refVertical.put(BallAngle._15, BallAngle._165);
		refVertical.put(BallAngle._30, BallAngle._150);
		refVertical.put(BallAngle._45, BallAngle._135);
		refVertical.put(BallAngle._60, BallAngle._120);
		refVertical.put(BallAngle._75, BallAngle._105);
		refVertical.put(BallAngle._90, BallAngle._90);
		refVertical.put(BallAngle._105, BallAngle._75);
		refVertical.put(BallAngle._120, BallAngle._60);
		refVertical.put(BallAngle._135, BallAngle._45);
		refVertical.put(BallAngle._150, BallAngle._30);
		refVertical.put(BallAngle._165, BallAngle._15);
		refVertical.put(BallAngle._180, BallAngle._0); // !!!
		refVertical.put(BallAngle._195, BallAngle._345);
		refVertical.put(BallAngle._210, BallAngle._330);
		refVertical.put(BallAngle._225, BallAngle._315);
		refVertical.put(BallAngle._240, BallAngle._300);
		refVertical.put(BallAngle._255, BallAngle._285);
		refVertical.put(BallAngle._270, BallAngle._270);
		refVertical.put(BallAngle._285, BallAngle._255);
		refVertical.put(BallAngle._300, BallAngle._240);
		refVertical.put(BallAngle._315, BallAngle._225);
		refVertical.put(BallAngle._330, BallAngle._210);
		refVertical.put(BallAngle._345, BallAngle._195);
	}

	private BallAngle(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public static BallAngle bouncedOffHorizontal(BallAngle currAngle) {
		return refHorizontal.get(currAngle);
	}

	public static BallAngle bouncedOffVertical(BallAngle currAngle) {
		return refVertical.get(currAngle);
	}

	// -5..0..5
	public static BallAngle bouncedOffPad(BallAngle bAngle, int sector) {
		
		if (sector > -4 && sector < 4 && incomeAngle.contains(bAngle)) {

			int index = incomeAngle.indexOf(bAngle);
			index += sector;
			if (index < 0) {
				index = 0;
			}
			if (index > (incomeAngle.size() -1)) {
				index = incomeAngle.size() -1;
			}
	
			return bouncedOffVertical(outcomeAngle.get(index));
		}
		throw new RuntimeException(bAngle + " : " + sector);
	}

}
