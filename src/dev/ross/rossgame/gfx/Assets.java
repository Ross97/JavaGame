package dev.ross.rossgame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	//load in all assets
	
	//create bufferedImages
	public static BufferedImage player, dirt, grass, stone, tree, button;
	
	private static final int w = 32, h = 32; 
	
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		player = sheet.crop(0, 0, w, h);
		dirt = sheet.crop(w, 0, w, h);
		grass = sheet.crop(w*2, 0, w, h);
		stone = sheet.crop(w*3, 0, w, h);
		tree = sheet.crop(0, h, w, h);
		button = sheet.crop(w, h, w, h);
	}
}
