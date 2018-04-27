package dev.ross.rossgame.entities.creatures;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Entity;
import dev.ross.rossgame.tiles.Tile;

public abstract class Creature extends Entity {
	
	
	public static final float DEFAULT_SPEED = 3;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height); //super passes to Entity
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	
	//Moves the creature, checks where it will be moving (place bounds there)
	public void move() {
		
		if(!checkEntityCollisions(xMove, 0))
			moveX();
		
		if(!checkEntityCollisions(0, yMove))
			moveY();
	}
	
	
	//Check bounding box for collision
	public void moveX() {
		
		//moving right
		if(xMove > 0) { 
			int tempX = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			//checks right of bound box
			if(!collisionWithTile(tempX, (int) (y+bounds.y) / Tile.TILEHEIGHT ) &&
					!collisionWithTile(tempX, (int) (y+bounds.y+bounds.height) / Tile.TILEHEIGHT )) {
				x += xMove;
			}else {
				x = tempX * Tile.TILEWIDTH - bounds.x - bounds.width - 1; //get pixel co-ords (1 prevents bug)
			}
		}
		
		//moving left
		else if (xMove < 0) {
			int tempX = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			//checks left of bound box
			if(!collisionWithTile(tempX, (int) (y+bounds.y) / Tile.TILEHEIGHT ) &&
					!collisionWithTile(tempX, (int) (y+bounds.y+bounds.height) / Tile.TILEHEIGHT )) {
				x += xMove;
			}else {
				x = tempX * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x ; //get pixel co-ords 
			}
		}
		
	}
	
	public void moveY() {
		
		//going up
		if(yMove < 0) {
			int tempY = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH, tempY) &&
					!collisionWithTile((int) (x+bounds.x+bounds.width) / Tile.TILEWIDTH, tempY)	) {
				y += yMove;
			}else {
				y = tempY * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y; //for better collision
			}
		}
		
		//going down
		else if(yMove > 0) {
			int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH, tempY) &&
					!collisionWithTile((int) (x+bounds.x+bounds.width) / Tile.TILEWIDTH, tempY)	) {
				y += yMove;
			}else {
				y = tempY * Tile.TILEHEIGHT - bounds.height - bounds.y -1; //for better collision
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
	//Getters and Setters
	public float getxMove() {
		return xMove;
	}
	
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
