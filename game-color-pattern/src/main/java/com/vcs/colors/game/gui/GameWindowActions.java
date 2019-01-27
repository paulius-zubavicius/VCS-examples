package com.vcs.colors.game.gui;

import java.util.List;

import com.vcs.colors.game.Game;
import com.vcs.colors.game.GameResult;
import com.vcs.colors.game.levels.Level;
import com.vcs.colors.game.pattern.line.ColoredItem;
import com.vcs.colors.game.pattern.line.ColoredLine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameWindowActions extends GameWindow {

	private static final int INPUT_MAX_SIZE = 6;

	private static final int RECT_SIZE = 15;
	private static final int SPACE_V = 5;
	private static final int SPACE_H = 3;
	private static final int MARGING = 5;

	private static final int INPUT_RECT_SIZE = 33;
	private static final int INPUT_SPACE_H = 5;
	private static final int INPUT_MARGING = 9;

	private static final Color EMPTY_COLOR = Color.WHITE;

	private Game game;
	private ColoredItem[] playerInput = new ColoredItem[INPUT_MAX_SIZE];

	public GameWindowActions(Pane mainPane, Game game) {
		super(mainPane);

		this.game = game;

		resetGamePlay();
	}

	@Override
	protected void resetGamePlay() {
		GameResult gr = game.resetGamePlay();
		for (int i = 0; i < playerInput.length; i++) {
			playerInput[i] = null;
		}
		// Do not allows to manipulate with total empty line of colors
		playerInput[0] = ColoredItem.values()[0];

		drawInput();
		updateResult(gr);
	}

	private void updateResult(GameResult gameResult) {
		String status = "";

		switch (gameResult.getStatus()) {
		case WINNER:
			status = "YOU WON !!!";
			break;
		case LOOSER:
			resetGamePlay();
			status = "GAME OVER :(";
			break;
		case ANSWER_WAS_VALID:
		case ANSWER_WAS_NOTVALID:
			status = "Missed " + gameResult.getMissed() + " / " + Level.values().length;
			break;
		default:
			break;
		}

		drawLinesBox(pnValid, game.getColorPatternOfLevel().getValidExample());
		drawLinesBox(pnNotValid, game.getColorPatternOfLevel().getNotValidExample());

		lblStatus.setText(status);

		prgBar.setProgress(gameResult.getProgress());

	}

	protected void drawInput() {

		GraphicsContext gc = pnInput.getGraphicsContext2D();

		// Clear
		gc.clearRect(0, 0, pnInput.getWidth(), pnInput.getHeight());

		// Frame
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRoundRect(1, 1, pnInput.getWidth() - 2, pnInput.getHeight() - 2, 10, 10);

		// Colored items
		gc.setLineWidth(2);

		int x;

		for (int i = 0; i < playerInput.length; i++) {

			if (playerInput[i] != null) {
				gc.setFill(translateColor(playerInput[i]));
			} else {
				gc.setFill(EMPTY_COLOR);
			}

			x = i * (INPUT_RECT_SIZE + INPUT_SPACE_H) + INPUT_MARGING;

			gc.fillRoundRect(x, INPUT_MARGING, INPUT_RECT_SIZE, INPUT_RECT_SIZE, 10, 10);
			gc.strokeRoundRect(x, INPUT_MARGING, INPUT_RECT_SIZE, INPUT_RECT_SIZE, 10, 10);
		}
	}

	private void drawLinesBox(Canvas cnv, List<ColoredLine> lines) {

		GraphicsContext gc = cnv.getGraphicsContext2D();
		// Clear
		gc.clearRect(0, 0, cnv.getWidth(), cnv.getHeight());

		// Frame
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRoundRect(1, 1, cnv.getWidth() - 2, cnv.getHeight() - 2, 10, 10);

		// Colored items
		gc.setLineWidth(2);

		List<ColoredItem> itemsOfLine;

		int x, y;

		for (int li = 0; li < lines.size(); li++) {
			itemsOfLine = lines.get(li).getEilute();
			for (int ii = 0; ii < itemsOfLine.size(); ii++) {

				gc.setFill(translateColor(itemsOfLine.get(ii)));

				x = ii * (RECT_SIZE + SPACE_H) + MARGING;
				y = li * (RECT_SIZE + SPACE_V) + MARGING;

				gc.fillRoundRect(x, y, RECT_SIZE, RECT_SIZE, 10, 10);
				gc.strokeRoundRect(x, y, RECT_SIZE, RECT_SIZE, 10, 10);
			}

		}

	}

	@Override
	protected void onMouseScrool(ScrollEvent event) {
		int clickedIndex = calcInputIndex(event.getX());

		if (playerInput[clickedIndex] != null) {
			playerInput[clickedIndex] = ColoredItem.getNextCycled(playerInput[clickedIndex]);

			drawInput();
		}

	}

	@Override
	protected void onMouseClicked(MouseEvent event) {
		if (MouseButton.PRIMARY.equals(event.getButton())) {
			int clickedIndex = calcInputIndex(event.getX());
			clickedIndex++;
			for (int i = 0; i < clickedIndex; i++) {
				if (playerInput[i] == null) {
					playerInput[i] = ColoredItem.values()[0];
				}
			}

			for (int i = clickedIndex; i < playerInput.length; i++) {
				playerInput[i] = null;
			}

			drawInput();
			return;
		}

		if (MouseButton.SECONDARY.equals(event.getButton())) {
			GameResult gameResult = game.playerInput(new ColoredLine(playerInput));
			updateResult(gameResult);

		}

	}

	@Override
	protected int calcPlayerInputWeight() {
		return INPUT_MAX_SIZE * INPUT_RECT_SIZE + INPUT_MARGING * 2 + INPUT_SPACE_H * (INPUT_MAX_SIZE - 1);
	}

	private int calcInputIndex(double x) {
		int size = (int) (pnInput.getWidth() / INPUT_MAX_SIZE);
		return (int) (x / size);
	}

	private Paint translateColor(ColoredItem coloredItem) {
		switch (coloredItem) {
		case BLUE:
			return Color.LIGHTSTEELBLUE;
		case GREEN:
			return Color.CHARTREUSE;
		case RED:
			return Color.LIGHTCORAL;
		case YELLOW:
			return Color.YELLOW;
		default:
			return Color.BLACK;
		}
	}

}
