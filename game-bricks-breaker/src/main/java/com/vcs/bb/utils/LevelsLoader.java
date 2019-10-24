package com.vcs.bb.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.Level;
import com.vcs.bb.game.model.enums.BrickType;

public class LevelsLoader {

	private static String[] dataFiles = { "level0.dat", "level1.dat", "level2.dat", "level3.dat", "level4.dat",
			"level5.dat" };

	public static List<Level> loadLevels() {

		List<Level> result = new ArrayList<>();

		for (String levelFileName : dataFiles) {
			try {
				URI uri = Thread.currentThread().getContextClassLoader().getResource(levelFileName).toURI();
				result.add(loadLevel(Files.readAllLines(Paths.get(uri))));

			} catch (IOException | URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}

		return result;
	}

	private static Level loadLevel(List<String> lines) {

		Level l = new Level();
		l.setName(lines.remove(0));
		l.setSpeed(Double.parseDouble(lines.remove(0)));

		loadBricks(lines, l);
		return l;
	}

	private static void loadBricks(List<String> lines, Level l) {
		for (int line = 0; line < lines.size(); line++) {
			String bricksLine = lines.get(line);

			for (int col = 0; col < bricksLine.length(); col++) {

				if (bricksLine.charAt(col) != ' ') {
					l.getBricks().add(createBrick(BrickType.valueOfByChar(bricksLine.charAt(col)), line, col));
				}
			}
		}
	}

	private static Brick createBrick(BrickType type, int line, int col) {
		int x = Physics.BRICK_MARGIN + col * (Physics.BRICK_W + Physics.BRICK_MARGIN);
		int y = Physics.BRICK_MARGIN + line * (Physics.BRICK_H + Physics.BRICK_MARGIN);

		return new Brick(type, x, y, Physics.BRICK_W, Physics.BRICK_H);
	}

}
