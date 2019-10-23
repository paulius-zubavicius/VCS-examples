package com.vcs.bb.game.levels;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.vcs.bb.game.Physics;
import com.vcs.bb.game.model.Brick;
import com.vcs.bb.game.model.BrickType;
import com.vcs.bb.game.model.Level;
import com.vcs.bb.game.model.Resolution;

public class LevelsLoader {

	private String[] dataFiles = { "level0.dat", "level1.dat", "level2.dat", "level3.dat" };

	public List<Level> loadLevels(Resolution res) {

		List<Level> result = new ArrayList<>();

		for (String levelFileName : dataFiles) {
			try {
				URL url = Thread.currentThread().getContextClassLoader().getResource(levelFileName);
				URI uri = url.toURI();

				List<String> lines = Files.readAllLines(Paths.get(uri));

				Level l = new Level();
				l.setName(lines.remove(0));
				l.setSpeed(Double.parseDouble(lines.remove(0)));

				for (int line = 0; line < lines.size(); line++) {
					String bricksLine = lines.get(line);

					for (int col = 0; col < bricksLine.length(); col++) {

						if (bricksLine.charAt(col) != ' ') {
							l.getBricks()
									.add(createBrick(BrickType.valueOfByChar(bricksLine.charAt(col)), line, col, res));
						}
					}
				}

				result.add(l);

			} catch (IOException | URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}

		return result;
	}

	private Brick createBrick(BrickType type, int line, int col, Resolution res) {
		int x = Physics.BRICK_MARGIN + col * (Physics.BRICK_W + Physics.BRICK_MARGIN);
		int y = Physics.BRICK_MARGIN + line * (Physics.BRICK_H + Physics.BRICK_MARGIN);

		return new Brick(type, x, y, Physics.BRICK_W, Physics.BRICK_H);
	}

}
