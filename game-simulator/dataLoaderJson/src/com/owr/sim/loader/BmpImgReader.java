package com.owr.sim.loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@Deprecated
public class BmpImgReader {

	public static void readBmp(String bmpName) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(bmpName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		
		img.getHeight();
		img.getWidth();
		// you should stop here
		//byte[][] green = new byte[30][40];
		for (int x = 0; x < 30; x++) {
			for (int y = 0; y < 40; y++) {

				int color = img.getRGB(x, y);
				// alpha[x][y] = (byte)(color>>24);
				// red[x][y] = (byte)(color>>16);
				//green[x][y] = (byte) (color >> 8);
				// blue[x][y] = (byte)(color);
			}
		}
		//byte[][] inputData = green;
	}

}
