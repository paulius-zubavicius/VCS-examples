package com.vcs.ex.jfx.game.utils;

import java.io.File;

import com.vcs.ex.jfx.game.resources.ImgRs;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImgLoader {

	public static final String MEDIA_DIR = "media/imgfx/";

	public static Image img(ImgRs img) {
		return loadImg(img.getFileName());
	}

	public static Image[] sprite(ImgRs imgRs) {
		if (imgRs.getCols() > 0 && imgRs.getRows() > 0) {
			return loadSpriteCR(imgRs.getFileName(), imgRs.getCols(), imgRs.getRows());
		}

		if (imgRs.getRects() != null) {

			// TODO
			return null;
		}

		throw new RuntimeException("" + imgRs);
	}

	private static Image loadImg(String fileName) {
		return new Image(new File(MEDIA_DIR + fileName).toURI().toString());
	}

	private static Image[] loadSpriteCR(String fileName, int cols, int rows) {

		Image stripImg = loadImg(fileName);
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
