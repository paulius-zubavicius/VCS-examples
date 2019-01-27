package com.owr.sim.model.map;

import java.util.ArrayList;
import java.util.List;

public class CrossRoad implements IWayElement {

	private int id;

	private MapPoint point;

	private List<Road> roadsStarts = new ArrayList<Road>();
	private List<Road> roadsEnds = new ArrayList<Road>();

	private TraficLight traficLight;

	public CrossRoad(int uniqId, int x, int y) {
		this.id = uniqId;
		this.point = new MapPoint(x, y);
		
		traficLight = new TraficLight();
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return "" + id;
	}

	public MapPoint getPoint() {
		return point;
	}

	public void setPoint(MapPoint point) {
		this.point = point;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Road> getRoadsStarts() {
		return roadsStarts;
	}

	public void setRoadsStarts(List<Road> roadsStarts) {
		this.roadsStarts = roadsStarts;
	}

	public List<Road> getRoadsEnds() {
		return roadsEnds;
	}

	public void setRoadsEnds(List<Road> roadsEnds) {
		this.roadsEnds = roadsEnds;
	}

	public TraficLight getTraficLight() {
		return traficLight;
	}

	public void setTraficLight(TraficLight traficLight) {
		this.traficLight = traficLight;
	}

}
