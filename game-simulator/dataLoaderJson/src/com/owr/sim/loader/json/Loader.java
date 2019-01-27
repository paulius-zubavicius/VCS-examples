package com.owr.sim.loader.json;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;
import com.owr.sim.loader.IDataLoader;
import com.owr.sim.model.json.CrossRoadJson;
import com.owr.sim.model.json.MapJson;
import com.owr.sim.model.json.PointJson;
import com.owr.sim.model.json.RoadJson;
import com.owr.sim.model.json.RoadPointJson;
import com.owr.sim.model.map.CrossRoad;
import com.owr.sim.model.map.Road;
import com.owr.sim.model.map.RoadSection;
import com.owr.sim.model.map.RoadsMap;

public class Loader implements IDataLoader {

	@Override
	public RoadsMap load(String mapName) {

		MapJson loadedData = readMap(mapName);

		RoadsMap result = linkToRoadMap(loadedData);

		return result;
	}

	private MapJson readMap(String mapName) {

		File file = new File(mapName);
		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		Gson gson = new Gson();

		return gson.fromJson(reader, MapJson.class);
	}

	private RoadsMap linkToRoadMap(MapJson src) {
		RoadsMap target = new RoadsMap();

		if (src.getCrossRoads().size() < 3) {
			throw new JsonLoaderException();
		}

		createCrossRoads(src.getCrossRoads(), target.getCrossRoads());

		createRoads(src.getRoads(), target.getRoads(), target.getCrossRoads());

		target.setName(src.getMapName());
		target.setBackGround(loadImage(src.getBackground()));

		return target;
	}

	private void createRoads(List<RoadJson> rSrc, List<Road> rTarget, List<CrossRoad> crTarget) {
		Road r = null;
		for (RoadJson road : rSrc) {
			r = new Road(road.getId(), road.getName());
			rTarget.add(r);

			creteRoadSections(road.getPoints(), r.getSections(), crTarget);

		}

	}

	private void creteRoadSections(List<RoadPointJson> points, List<RoadSection> sections, List<CrossRoad> crTarget) {

		PointJson point1 = null;
		PointJson point2 = null;
		RoadSection section = null;

		point1 = findPointByCrId(points.get(0).getCrossRoadId(), crTarget);
		int spLimit = points.get(0).getSpeedLimit();
		boolean tunnel = points.get(0).isTunnel();

		// TODO sekciju pildymas
		for (int i = 1; i < points.size() - 1; i++) {

		}

		section = new RoadSection();
		section.setSpeedLimit(spLimit);
		section.setTunnel(tunnel);

		point2 = findPointByCrId(points.get(points.size() - 1).getCrossRoadId(), crTarget);
		section.setLenght(calcSectionLenght(point1.getX(), point1.getY(), point2.getX(), point2.getY()));

		sections.add(section);

	}

	private int calcSectionLenght(int x, int y, int x2, int y2) {

		// TODO Auto-generated method stub
		return 0;
	}

	private PointJson findPointByCrId(int id, List<CrossRoad> crTarget) {

		// find by id
		for (CrossRoad cr : crTarget) {
			if (id == cr.getId()) {
				return new PointJson(cr.getPoint().getX(), cr.getPoint().getY());
			}
		}

		throw new JsonLoaderException();
	}

	private void createCrossRoads(List<CrossRoadJson> crSrc, List<CrossRoad> crTarget) {
		for (CrossRoadJson cross : crSrc) {
			crTarget.add(new CrossRoad(cross.getId(), cross.getPoint().getX(), cross.getPoint().getY()));
		}
	}

	private Image loadImage(String background) {
		// TODO Auto-generated method stub
		return null;
	}

}
