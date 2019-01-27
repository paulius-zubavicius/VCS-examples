package com.vcs.fx.canvas.snake.utils;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * @author paulius@zubavicius.lt
 *
 */
public class SpriteLoader {

	public static Image[] loadSprite(String fileName, int cols, int rows) {

		Image stripImg = new Image(new File(fileName).toURI().toString());
		PixelReader pr = stripImg.getPixelReader();

		int spriteW = (int) (stripImg.getWidth() / cols);
		int spriteH = (int) (stripImg.getHeight() / rows);

		Image[] result = new Image[cols * rows];

		for (int rI = 0; rI < rows; rI++) {
			for (int cI = 0; cI < cols; cI++) {
				result[cols * rI + cI] = readImageByCord(pr, cI, rI, spriteW, spriteH);
			}
		}

		return result;
	}

	private static Image readImageByCord(PixelReader pr, int xIndex, int yIndew, int spriteW, int spriteH) {
		WritableImage wImg = new WritableImage(spriteW, spriteH);
		PixelWriter pw = wImg.getPixelWriter();

		for (int y = 0; y < spriteH; y++) {
			for (int x = 0; x < spriteW; x++) {
				pw.setColor(x, y, pr.getColor(x + spriteW * xIndex, y + spriteH * yIndew));
			}
		}

		return wImg;
	}
}
