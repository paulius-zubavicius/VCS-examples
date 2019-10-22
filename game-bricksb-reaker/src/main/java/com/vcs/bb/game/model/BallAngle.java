package com.vcs.bb.game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public enum BallAngle {

	_0(1.0, 0.0), _15(0.965925826, -0.258819045), _30(0.866025404, -0.5), _45(0.707106781, -0.707106781),
	_60(0.5, -0.866025404), _75(0.258819045, -0.965925826), _90(0.0, -0.0), _105(-0.258819045, -0.965925826),
	_120(-0.5, -0.866025404), _135(-0.707106781, -0.707106781), _150(-0.866025404, -0.5),
	_165(-0.965925826, -0.258819045), _180(-1.0, -0.0), _195(-0.965925826, 0.258819045), _210(-0.866025404, 0.5),
	_225(-0.707106781, 0.707106781), _240(-0.5, 0.866025404), _255(-0.258819045, 0.965925826), _270(0.0, 1.0),
	_285(0.258819045, 0.965925826), _300(0.5, 0.866025404), _315(0.707106781, 0.707106781), _330(0.866025404, 0.5),
	_345(0.965925826, 0.258819045);

	private double x;
	private double y;

	private static Map<BallAngle, BallAngle> refFromYAxis = new HashMap<>();
	private static Map<BallAngle, BallAngle> refFromXAxis = new HashMap<>();
//	private static Map<BallAngle, Integer> arrIndexes = new HashMap<>();
	private static List<BallAngle> outcomeAngle = new ArrayList<>(Arrays.asList( _15, _30, _45, _60, _75, _90, _105, _120, _135, _150, _165));
	private static List<BallAngle> incomeAngle = new ArrayList<>(Arrays.asList(_195, _210, _225, _240, _255, _270, _285, _300, _315, _330, _345));
	
	static {
	
//		for (int i = 0; i < BallAngle.values().length; i++) {
//			arrIndexes.put(BallAngle.values()[i], i);
//		}
		

		refFromYAxis.put(BallAngle._0, BallAngle._0);
		refFromYAxis.put(BallAngle._15, BallAngle._345);
		refFromYAxis.put(BallAngle._30, BallAngle._330);
		refFromYAxis.put(BallAngle._45, BallAngle._315);
		refFromYAxis.put(BallAngle._60, BallAngle._300);
		refFromYAxis.put(BallAngle._75, BallAngle._285);
		refFromYAxis.put(BallAngle._90, BallAngle._270); // !!!
		refFromYAxis.put(BallAngle._105, BallAngle._255);
		refFromYAxis.put(BallAngle._120, BallAngle._240);
		refFromYAxis.put(BallAngle._135, BallAngle._225);
		refFromYAxis.put(BallAngle._150, BallAngle._210);
		refFromYAxis.put(BallAngle._165, BallAngle._195);
		refFromYAxis.put(BallAngle._180, BallAngle._180);
		refFromYAxis.put(BallAngle._195, BallAngle._165);
		refFromYAxis.put(BallAngle._210, BallAngle._150);
		refFromYAxis.put(BallAngle._225, BallAngle._135);
		refFromYAxis.put(BallAngle._240, BallAngle._120);
		refFromYAxis.put(BallAngle._255, BallAngle._105);
		refFromYAxis.put(BallAngle._270, BallAngle._90); // !!!
		refFromYAxis.put(BallAngle._285, BallAngle._75);
		refFromYAxis.put(BallAngle._300, BallAngle._60);
		refFromYAxis.put(BallAngle._315, BallAngle._45);
		refFromYAxis.put(BallAngle._330, BallAngle._30);
		refFromYAxis.put(BallAngle._345, BallAngle._15);

		refFromXAxis.put(BallAngle._0, BallAngle._180); // !!!
		refFromXAxis.put(BallAngle._15, BallAngle._165);
		refFromXAxis.put(BallAngle._30, BallAngle._150);
		refFromXAxis.put(BallAngle._45, BallAngle._135);
		refFromXAxis.put(BallAngle._60, BallAngle._120);
		refFromXAxis.put(BallAngle._75, BallAngle._105);
		refFromXAxis.put(BallAngle._90, BallAngle._90);
		refFromXAxis.put(BallAngle._105, BallAngle._75);
		refFromXAxis.put(BallAngle._120, BallAngle._60);
		refFromXAxis.put(BallAngle._135, BallAngle._45);
		refFromXAxis.put(BallAngle._150, BallAngle._30);
		refFromXAxis.put(BallAngle._165, BallAngle._15);
		refFromXAxis.put(BallAngle._180, BallAngle._0); // !!!
		refFromXAxis.put(BallAngle._195, BallAngle._345);
		refFromXAxis.put(BallAngle._210, BallAngle._330);
		refFromXAxis.put(BallAngle._225, BallAngle._315);
		refFromXAxis.put(BallAngle._240, BallAngle._300);
		refFromXAxis.put(BallAngle._255, BallAngle._285);
		refFromXAxis.put(BallAngle._270, BallAngle._270);
		refFromXAxis.put(BallAngle._285, BallAngle._255);
		refFromXAxis.put(BallAngle._300, BallAngle._240);
		refFromXAxis.put(BallAngle._315, BallAngle._225);
		refFromXAxis.put(BallAngle._330, BallAngle._210);
		refFromXAxis.put(BallAngle._345, BallAngle._195);
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

	public static BallAngle reflectFromYAxis(BallAngle currAngle) {
		return refFromYAxis.get(currAngle);
	}

	public static BallAngle reflectFromXAxis(BallAngle currAngle) {
		return refFromXAxis.get(currAngle);
	}

	// -5..0..5
	public static BallAngle bumptFromPad(BallAngle bAngle, int sector) {
		
		if (sector > -4 && sector < 4 && incomeAngle.contains(bAngle)) {
			
			//bAngle = reflectFromXAxis(bAngle);
			
			int index = incomeAngle.indexOf(bAngle);
			index += sector;
			if (index < 0) {
				index = 0;
			}
			if (index > (incomeAngle.size() -1)) {
				index = incomeAngle.size() -1;
			}
	
			return reflectFromXAxis(outcomeAngle.get(index));
		}
		
		
		throw new RuntimeException(bAngle + " : " + sector);
	}

}
