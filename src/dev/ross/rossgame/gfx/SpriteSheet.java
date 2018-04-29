package dev.ross.rossgame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	//BufferedImage
	private BufferedImage sheet;
	
	//Constructor
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	//Returns the portion we want to crop
	public BufferedImage crop(int x, int y, int w, int h) {
		return sheet.getSubimage(x, y, w, h);
	}
}
