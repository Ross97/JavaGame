package dev.ross.rossgame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//Static
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0); //set object to grass class
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	//Class
	
	
	//Constructor
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}

	public int getId() {
		return id;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
}
