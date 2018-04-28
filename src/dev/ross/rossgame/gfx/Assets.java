package dev.ross.rossgame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	//load in all assets
	
	//create bufferedImages
	public static BufferedImage player, playerAngry, 
								enemy, enemyAngry, gold,
								dirt, grass, stone, tree, button, grassPlant, bush, bushBerry;
	
	private static final int w = 32, h = 32; 
	
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		player = sheet.crop(0, 0, w, h);
		playerAngry = sheet.crop(w*2, h, w, h);
		
		enemy = sheet.crop(2*w, h*2, w, h);
		enemyAngry = sheet.crop(3*w, h*2, w, h);
		gold = sheet.crop(0, h*3, w, h);
		
		dirt = sheet.crop(w, 0, w, h);
		grass = sheet.crop(w*2, 0, w, h);
		grassPlant = sheet.crop(w*3, w, w, h);
		stone = sheet.crop(w*3, 0, w, h);
		tree = sheet.crop(0, h, w, h);
		bush = sheet.crop(0, h*2, w, h);
		bushBerry = sheet.crop(w, h*2, w, h);
		button = sheet.crop(w, h, w, h);
	}
}
