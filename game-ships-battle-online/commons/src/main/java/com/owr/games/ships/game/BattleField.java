package com.owr.games.ships.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.owr.games.ships.game.validators.ShipsValidator;
import com.owr.games.ships.model.BattleFieldPoint;
import com.owr.games.ships.model.Point;
import com.owr.games.ships.model.PointStatus;
import com.owr.games.ships.model.Ship;

public class BattleField {

	private static final char EMPTY = '~';

	public static final int MAP_SIZE = 10;

	private List<BattleFieldPoint> data = new ArrayList<>();

	public BattleField(String map) {

		if (map != null && MAP_SIZE * MAP_SIZE == map.length()) {
			Optional<PointStatus> status = null;
			for (int y = 0; y < MAP_SIZE; y++) {
				for (int x = 0; x < BattleField.MAP_SIZE; x++) {
					status = PointStatus.valueOf(map.charAt(index(x, y)));
					Point point = new Point(x, y);
					status.ifPresent(val -> data.add(new BattleFieldPoint(point, val)));
				}
			}
		} else {
			throw new RuntimeException(map);
		}
	}

	public BattleField(List<Ship> ships) {
		new ShipsValidator().validate(ships);
		ships.forEach(ship -> ship.convertToPoints().forEach(p -> data.add(new BattleFieldPoint(p, PointStatus.Ship))));
	}

	public void hitTo(Point point) {
		new ShipsValidator().validatePoint(point);

		Optional<BattleFieldPoint> hitPoint = data.stream().filter(p -> p.getPoint().equals(point)).findFirst();

		if (hitPoint.isPresent()) {
			if (PointStatus.Ship.equals(hitPoint.get().getStatus())) {
				hitPoint.get().setStatus(PointStatus.AccurateShot);
			}
		} else {
			data.add(new BattleFieldPoint(point, PointStatus.EmptyShot));
		}

	}

	public boolean isLost() {
		return data.stream().anyMatch(mapEl -> PointStatus.Ship.equals(mapEl.getStatus()));
	}

	public List<BattleFieldPoint> cloneBattleFieldData(boolean masked) {
		return data.stream().filter(bfp -> !masked || !bfp.getStatus().equals(PointStatus.Ship)).collect(
				ArrayList<BattleFieldPoint>::new, (l, e) -> l.add((BattleFieldPoint) e.clone()), ArrayList::addAll);
	}

	public String getOriginalStr() {
		StringBuilder cs = new StringBuilder(MAP_SIZE * MAP_SIZE);
		for (int r = 0; r < MAP_SIZE * MAP_SIZE; r++) {
			cs.append(EMPTY);
		}
		data.forEach(e -> cs.setCharAt(index(e.getPoint()), e.getStatus().getSymbol()));
		return cs.toString();
	}

	private int index(Point p) {
		return index(p.getX(), p.getY());
	}

	private int index(int x, int y) {
		return MAP_SIZE * y + x;
	}

}
