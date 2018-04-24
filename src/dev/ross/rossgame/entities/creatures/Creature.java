package dev.ross.rossgame.entities.creatures;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Entity;
import dev.ross.rossgame.tiles.Tile;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height); //super passes to Entity
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public float getxMove() {
		return xMove;
	}
	
	
	public void move() {
		moveX();
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
			}
		}
		
		//moving left
		else if (xMove < 0) {
			int tempX = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			//checks left of bound box
			if(!collisionWithTile(tempX, (int) (y+bounds.y) / Tile.TILEHEIGHT ) &&
					!collisionWithTile(tempX, (int) (y+bounds.y+bounds.height) / Tile.TILEHEIGHT )) {
				x += xMove;
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
			}
		}
		
		//going down
		else if(yMove > 0) {
			int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH, tempY) &&
					!collisionWithTile((int) (x+bounds.x+bounds.width) / Tile.TILEWIDTH, tempY)	) {
				y += yMove;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
	//Getters and Setters
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
