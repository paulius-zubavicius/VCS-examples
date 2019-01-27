package com.owr.sim.loader;

import com.owr.sim.model.map.RoadsMap;

public interface IDataLoader {

	public RoadsMap load(String mapName);
}
