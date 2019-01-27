package com.owr.sim.model.map;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class RoadsMap {

	private String name;
	private Image backGround;

	private List<CrossRoad> crossRoads = new ArrayList<>();
	private List<Road> roads = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getBackGround() {
		return backGround;
	}

	public void setBackGround(Image backGround) {
		this.backGround = backGround;
	}

	public List<CrossRoad> getCrossRoads() {
		return crossRoads;
	}

	public List<Road> getRoads() {
		return roads;
	}

}
