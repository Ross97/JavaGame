package dev.ross.rossgame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		try {
			//load in the image (path is image location)
			return ImageIO.read(ImageLoader.class.getResource(path));
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null; //avoids error
	}
}
