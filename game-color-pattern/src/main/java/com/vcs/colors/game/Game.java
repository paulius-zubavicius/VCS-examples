package com.vcs.colors.game;

import com.vcs.colors.game.levels.Level;
import com.vcs.colors.game.levels.LevelFlow;
import com.vcs.colors.game.levels.LevelHandler;
import com.vcs.colors.game.pattern.ColorPattern;
import com.vcs.colors.game.pattern.line.ColoredLine;

/**
 * The game class responsible for game flow
 * 
 * @author owr
 *
 */
public class Game {

	private static final int MAX_LOST_FOR_LOOSE = 10;

	private Level currentLevel;
	private LevelHandler levelHandler;
	private ColorPattern colorPattern;
	private int lostForLoose = 0;

	public Game() {

		resetGamePlay();
	}

	public ColorPattern getColorPatternOfLevel() {
		if (colorPattern == null) {
			colorPattern = levelHandler.getRandomPatternImpl(currentLevel);
		}

		return colorPattern;
	}

	public GameResult resetGamePlay() {

		// init values of valid and not valid examples
		levelHandler = new LevelHandler();

		// Go back to the beginning
		currentLevel = Level.LEVEL0;

		// Reset missed counter
		lostForLoose = 0;

		// get random pattern
		colorPattern = null;

		return new GameResult(LevelFlow.ANSWER_WAS_VALID, Level.levelAsProgress(currentLevel), lostForLoose);
	}

	public GameResult playerInput(ColoredLine coloredLine) {

		if (getColorPatternOfLevel().isValid(coloredLine)) {
			lostForLoose = 0;
			return tryToGoToNextLevel();
		} else {
			lostForLoose++;
			return playCurrentLevelAgain();
		}
	}

	private GameResult playCurrentLevelAgain() {
		LevelFlow lf = null;

		if (lostForLoose < MAX_LOST_FOR_LOOSE) {
			lf = LevelFlow.ANSWER_WAS_NOTVALID;
		} else {
			resetGamePlay();
			lf = LevelFlow.LOOSER;
		}

		return new GameResult(lf, Level.levelAsProgress(currentLevel), lostForLoose);
	}

	private GameResult tryToGoToNextLevel() {

		if (Level.isItLast(currentLevel)) {
			return theWinner();
		} else {
			return goToNextLevel();
		}
	}

	private GameResult goToNextLevel() {
		// Calc next level
		currentLevel = Level.nextLevel(currentLevel);

		// get random colorPattern by level
		colorPattern = levelHandler.getRandomPatternImpl(currentLevel);

		return new GameResult(LevelFlow.ANSWER_WAS_VALID, Level.levelAsProgress(currentLevel), lostForLoose);
	}

	private GameResult theWinner() {
		return new GameResult(LevelFlow.WINNER, Level.levelAsProgress(currentLevel), lostForLoose);

	}

}
