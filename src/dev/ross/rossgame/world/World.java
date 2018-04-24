package dev.ross.rossgame.world;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.tiles.Tile;
import dev.ross.rossgame.utils.Utils;

public class World {
	private Game game;
	private int width, height; //how big the world is
	private int spawnX, spawnY;
	private int[][] tiles; //store tile IDs and XY in here
	
	//load world file
	public World(Game game, String path) {
		this.game = game;
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	//render each tile that is on screen
	public void render(Graphics g) {
		
		//what tiles users can see
		int xStart = (int) Math.max(0, game.getCamera().getxOffset() / Tile.TILEWIDTH); //returns bigger num		
		int xEnd = (int) Math.min(width, (game.getCamera().getxOffset() + game.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, game.getCamera().getyOffset() / Tile.TILEHEIGHT); //returns bigger num		
		int yEnd = (int) Math.min(width, (game.getCamera().getyOffset() + game.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++){
				getTile(x,y).render(g, 
						(int) (x*Tile.TILEWIDTH - game.getCamera().getxOffset()), 
						(int) (y*Tile.TILEHEIGHT - game.getCamera().getyOffset()) );
			}
			
		} 
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		
		if(t==null)
			return Tile.dirtTile;
		
		return t;
	}
	
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");//add each number to string array
		width = Utils.parseInt(tokens[0]); 
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				//convert xy into 1D array
				tiles[x][y] = Utils.parseInt(tokens[(x+y * width + 4)]); 
			}
		}
	}
}
