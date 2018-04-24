package dev.ross.rossgame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	//constructor
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	//method to return portion of sprite
	public BufferedImage crop(int x, int y, int w, int h) {
		return sheet.getSubimage(x, y, w, h);
	}
}
