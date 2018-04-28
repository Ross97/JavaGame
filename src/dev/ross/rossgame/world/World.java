package dev.ross.rossgame.world;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.EntityManager;
import dev.ross.rossgame.entities.Tree;
import dev.ross.rossgame.entities.creatures.Enemy;
import dev.ross.rossgame.entities.creatures.Player;
import dev.ross.rossgame.items.ItemManager;
import dev.ross.rossgame.tiles.Tile;
import dev.ross.rossgame.utils.Utils;

public class World {
	private Handler handler;
	private int width, height; //how big the world is
	private int spawnX, spawnY;
	private int[][] tiles; //store tile IDs and XY in here
	
	
	//Entities & Items
	private EntityManager entityManager;
	private ItemManager itemManager;

	//load world file
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		if(path=="res/worlds/world1.txt") {
			 for(int i=0; i < 5; i ++)
			 	entityManager.addEntity(new Tree(handler, i*300, i*100));
			
			entityManager.addEntity(new Enemy(entityManager, handler, 300, 300, 100, 100));
			entityManager.addEntity(new Enemy(entityManager, handler, 500, 500, 100, 100));
		}
			
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		entityManager.tick();
		itemManager.tick();
	}
	
	//render each tile that is on screen
	public void render(Graphics g) {
		
		//what tiles user can see
		int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILEWIDTH); //returns bigger num		
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILEHEIGHT); 	
		int yEnd = (int) Math.min(width, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++){
				getTile(x,y).render(g, 
						(int) (x*Tile.TILEWIDTH - handler.getCamera().getxOffset()), 
						(int) (y*Tile.TILEHEIGHT - handler.getCamera().getyOffset()) );
			}
			
		} 
		//Render entities and items
		itemManager.render(g);
		entityManager.render(g);
	}
	
	
	public Tile getTile(int x, int y) {
		
		//prevent bugs if player leaves map
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
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
	
	
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
