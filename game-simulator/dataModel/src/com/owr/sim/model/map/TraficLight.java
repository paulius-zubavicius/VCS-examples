package com.owr.sim.model.map;

public class TraficLight {

	private int greenLightRoadIndex;
	private int greenLightLong;
	private long greenLightStarted;

	public int getGreenLightRoadIndex() {
		return greenLightRoadIndex;
	}

	public void setGreenLightRoadIndex(int greenLightRoadIndex) {
		this.greenLightRoadIndex = greenLightRoadIndex;
	}

	public int getGreenLightLong() {
		return greenLightLong;
	}

	public void setGreenLightLong(int greenLightLong) {
		this.greenLightLong = greenLightLong;
	}

	public long getGreenLightStarted() {
		return greenLightStarted;
	}

	public void setGreenLightStarted(long greenLightStarted) {
		this.greenLightStarted = greenLightStarted;
	}

}
