package com.owr.games.ships.model;

import java.util.Objects;

public class BattleFieldPoint implements Cloneable{

	private PointStatus status;
	private Point point;

	public BattleFieldPoint() {
	}

	public BattleFieldPoint(Point point, PointStatus status) {
		this.point = point;
		this.status = status;
	}

	public PointStatus getStatus() {
		return status;
	}

	public void setStatus(PointStatus status) {
		this.status = status;
	}

	public Point getPoint() {
		return point;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		BattleFieldPoint bfPoint = (BattleFieldPoint) o;
		return bfPoint.getPoint().x == point.x && bfPoint.getPoint().y == point.y && bfPoint.status == status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(point.x, point.y, status);
	}

	@Override
	public String toString() {
		return "[" + "x=" + point.x + ", y=" + point.y + ", status=" + status + "]";
	}
	
	@Override
	public Object clone() {
		return new BattleFieldPoint(new Point(point.x, point.y), status);
	}

}
