package com.vcs.ex.jfx.game.resources;


public enum ImgRs {
	
	BG_WALL("bg/wall.jpg"),
	BG_SKY_CLOUDY("bg/bg.jpg"),
	BG_SKY_CLEAR("bg/bg2.jpg"),
	BG_ROOM1("bg/room1.jpg"),
	BG_ROOM2("bg/room2.jpg"),
	BG_UNDER1("bg/underground1.jpg"),
	BG_UNDER2("bg/underground2.jpg"),
	BG_UNDER3("bg/underground3.jpg"),
	BG_VILNIUS("bg/vilnius.jpg"),
	
	CLOUD1("clouds/cloud1.png"),
	CLOUD2("clouds/cloud2.png"),
	CLOUD3("clouds/cloud3.png"),
	CLOUD4("clouds/cloud4.png"),

	BLOOD1("blood/blood1.png", 1, 6),
	BLOOD2("blood/blood2.png", 3, 3),
	BLOOD3("blood/blood3.png", 4, 4),
	BLOOD4("blood/blood4.png", 4, 4),
	BLOOD5("blood/blood5.png", 4, 4),
	BLOOD6("blood/blood6.png", 5, 5),
	
	BULLET1("bullet/mark1.png"),
	BULLET2("bullet/mark2.png"),
	BULLET3("bullet/mark3.png", 3, 2),
	BULLET4("bullet/mark4.png", new Rect(5,6,33,33), new Rect(42,6, 79, 35), new Rect(100,2,151,49), new Rect(160, 0, 206,44), 
			new Rect( 212, 5, 274, 63), new Rect(300, 6, 373, 62)), //TODO

	TARGET2("targets/rabbit2.png"),
	
	SMOKE1("smoke/smoke1.png", 8, 5),
	SMOKE1_1("smoke/smoke1_1.png", 8, 2),
	SMOKE1_2("smoke/smoke1_2.png", 8, 4),
	SMOKE2("smoke/smoke2.png", 8, 4),
	SMOKE2_1("smoke/smoke2_1.png", 8, 2),
	SMOKE2_2("smoke/smoke2_2.png", 8, 1),
	SMOKE3("smoke/smoke3.png", 8, 5),
	SMOKE3_1("smoke/smoke3_1.png", 8, 1),
	
	
	GUN_SHOT1("guns/shotgun/shot1.png", 5, 3),
	GUN_MACHINE1("guns/machinegun/machine1.png", 2, 2),
	GUN_MACHINE2("guns/machinegun/machine2.png", 2, 2);
	

	
	private String fName;
	private int cols;
	private int rows;
	private Rect[] rects;

	ImgRs(String fName) {
		this(fName, 1,1);
	}
	
	ImgRs(String fName, Rect ... rects) {
		this.fName = fName;
		this.cols = 0;
		this.rows = 0;
		this.rects = rects;
	}
	
	ImgRs(String fName, int cols, int rows) {
		this.fName = fName;
		this.cols = cols;
		this.rows = rows;
		this.rects = null;
	}

	public String getFileName() {
		return fName;
	}
	
	public Rect[] getRects() {
		return rects;
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getRows() {
		return rows;
	}

}
