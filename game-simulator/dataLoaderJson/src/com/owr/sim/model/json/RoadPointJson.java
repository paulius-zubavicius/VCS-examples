package com.owr.sim.model.json;

public class RoadPointJson {

	private PointJson point;
	private Integer crossRoadId;
	private Integer speedLimit;
	private boolean tunnel;

	public PointJson getPoint() {
		return point;
	}

	public void setPoint(PointJson point) {
		this.point = point;
	}

	public Integer getCrossRoadId() {
		return crossRoadId;
	}

	public void setCrossRoadId(Integer crossRoadId) {
		this.crossRoadId = crossRoadId;
	}

	public Integer getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(Integer speedLimit) {
		this.speedLimit = speedLimit;
	}

	public boolean isTunnel() {
		return tunnel;
	}

	public void setTunnel(boolean tunnel) {
		this.tunnel = tunnel;
	}

}
