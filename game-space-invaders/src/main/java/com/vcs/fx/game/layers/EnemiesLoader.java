package com.vcs.fx.game.layers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vcs.fx.game.spaceship.EnemySpaceShip;
import org.apache.commons.io.FileUtils;

import com.vcs.fx.game.model.Allies;
import com.vcs.fx.game.model.Point;
import com.vcs.fx.game.model.Rectangle;
import com.vcs.fx.game.model.Resolutions;

public class EnemiesLoader {

	private static final int W = 30;
	private static final int H = 30;

	private Random rnd = new Random();

	public List<EnemySpaceShip> load(Resolutions res) {

		List<EnemySpaceShip> ships = new ArrayList<>();

		List<String> lines = loadFormation();

		int longest = findLongestLine(lines);

		int x = 0;
		int y = 0;
		int sway = 0;
		int w = res.getW() / longest;
		for (String line : lines) {
			x = 0;
			sway = (5 + rnd.nextInt(20)) * (rnd.nextInt(2) == 0 ? -1 : 1);
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) != ' ') {
					ships.add(new EnemySpaceShip(new Rectangle(new Point(x * w, y * H), W, H), Allies.ENEMY,
							rnd.nextInt(24), sway));
				}
				x++;
			}
			y++;
		}

		return ships;

	}

	private int findLongestLine(List<String> lines) {
		int longest = 0;
		for (String line : lines) {
			if (longest < line.length()) {
				longest = line.length();
			}
		}
		return longest;
	}

	private List<String> loadFormation() {
		File f = new File("data/formation.data");
		try {
			return FileUtils.readLines(f, java.nio.charset.StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
