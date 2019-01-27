package com.owr.sim.model.json;

import java.util.List;

public class MapJson {

	private String mapName;
	private String background;
	private List<RoadJson> roads;
	private List<CrossRoadJson> crossRoads;

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public List<RoadJson> getRoads() {
		return roads;
	}

	public void setRoads(List<RoadJson> roads) {
		this.roads = roads;
	}

	public List<CrossRoadJson> getCrossRoads() {
		return crossRoads;
	}

	public void setCrossRoads(List<CrossRoadJson> crossRoads) {
		this.crossRoads = crossRoads;
	}

}
