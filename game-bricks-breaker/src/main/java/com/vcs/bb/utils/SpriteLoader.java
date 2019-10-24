package com.vcs.bb.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteLoader {

	public static BufferedImage loadImg(String fileName) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Image[] loadSprite(String resourceName, int cols, int rows) {

		BufferedImage img = loadImg(resourceName);

		int spriteW = (int) (img.getWidth() / cols);
		int spriteH = (int) (img.getHeight() / rows);

		Image[] result = new Image[cols * rows];

		for (int rI = 0; rI < rows; rI++) {
			for (int cI = 0; cI < cols; cI++) {
				result[cols * rI + cI] = readImageByCord(img, cI, rI, spriteW, spriteH);
			}
		}

		return result;
	}

	private static Image readImageByCord(BufferedImage img, int xIndex, int yIndew, int spriteW, int spriteH) {
		BufferedImage wImg = new BufferedImage(spriteW, spriteH, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < spriteH; y++) {
			for (int x = 0; x < spriteW; x++) {
				wImg.setRGB(x, y, img.getRGB(x + spriteW * xIndex, y + spriteH * yIndew));
			}
		}

		return wImg;
	}

}
