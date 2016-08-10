package com.jcs.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

	public final int width, height;
	public final int row, column;
	public final int[] pixels;

	public SpriteSheet(String path) throws IOException {
		BufferedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));

		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (pixels[i] & 0xff) / 64;
		}

		row = -1;
		column = -1;
	}

	public SpriteSheet(String path, int r, int c) throws IOException {
		BufferedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));

		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (pixels[i] & 0xff) / 64;
		}
		row = r;
		column = c;
	}
}