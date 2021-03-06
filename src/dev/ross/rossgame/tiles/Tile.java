package dev.ross.rossgame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//Create an instance of each tile, and give them separate IDs
	public static Tile[] tiles = new Tile[128];
	public static Tile grassTile = new GrassTile(0); 
	public static Tile grassPlantTile = new GrassPlantTile(1);
	public static Tile dirtTile = new DirtTile(3);
	public static Tile rockTile = new RockTile(2);
	public static Tile bushTile = new BushTile(4);
	public static Tile bushBerryTile = new BushBerryTile(5);
	public static Tile buttonTile = new ButtonTile(6);
	
	//Tile width and height
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id; //for asset to draw
	
	//Constructor
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}

	public int getId() {
		return id;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
}
