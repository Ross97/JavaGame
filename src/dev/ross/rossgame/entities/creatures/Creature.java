package dev.ross.rossgame.entities.creatures;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Entity;
import dev.ross.rossgame.tiles.Tile;

public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 3;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		
		super(handler, x, y, width, height);  //super passes up to Entity class
		
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	
	//Moves the creature, after checking where it will be moving for collisions (places bounds there)
	public void move() {
		
		if(!checkEntityCollisions(xMove, 0))
			moveX();
		
		if(!checkEntityCollisions(0, yMove))
			moveY();
	}
	
	//Checks if the collision entity is solid
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//Check bounding box for collision
	public void moveX() {
		
		//Creature moving right
		if(xMove > 0) { 
			int tempX = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			//Checks to the right of the bounds
			if(		!collisionWithTile(tempX, (int) (y+bounds.y) / Tile.TILEHEIGHT ) &&
					!collisionWithTile(tempX, (int) (y+bounds.y+bounds.height) / Tile.TILEHEIGHT )) {
				x += xMove;
			} else {
				x = tempX * Tile.TILEWIDTH - bounds.x - bounds.width - 1; //Move back 1 pixel to prevent bug
			}
		}
		
		//Creature moving left
		else if (xMove < 0) {
			int tempX = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			//Checks to the left of the bounds
			if(!collisionWithTile(tempX, (int) (y+bounds.y) / Tile.TILEHEIGHT ) &&
					!collisionWithTile(tempX, (int) (y+bounds.y+bounds.height) / Tile.TILEHEIGHT )) {
				x += xMove;
			}else {
				x = tempX * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x ; //get pixel co-ords 
			}
		}
		
		//Out of map
		if(x < -bounds.width)
			x = -bounds.width;
		if(y < -bounds.height)
			y = -bounds.height;
		
	}
	
	//Check bounding box for collision
	public void moveY() {
		
		//Creature moving up
		if(yMove < 0) {
			int tempY = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH, tempY) &&
					!collisionWithTile((int) (x+bounds.x+bounds.width) / Tile.TILEWIDTH, tempY)	) {
				y += yMove;
			}else {
				y = tempY * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y; //for better collision
			}
		}
		
		//Creature moving down
		else if(yMove > 0) {
			int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH, tempY) &&
					!collisionWithTile((int) (x+bounds.x+bounds.width) / Tile.TILEWIDTH, tempY)	) {
				y += yMove;
			}else {
				y = tempY * Tile.TILEHEIGHT - bounds.height - bounds.y -1; //Move back 1 pixel to prevent bug
			}
		}
	}
	

	
	
	//Getters & Setters
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
