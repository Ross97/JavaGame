package dev.ross.rossgame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.ross.rossgame.Handler;

//protected = private but extended classes have access
//abstract = every class must implement their own version of this (here every entity implements die())
public abstract class Entity {
	
	//Setup protected variables
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected int health;
	protected boolean active = true;
	protected boolean isEnemy = false;
	
	//Constructor to initialize every entity
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		//Set the health and collision bounds (10 is default, can change in class)
		health = 10;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	//All classes that extend must implement:
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void die();
	
	//If entity is hurt, lose health and die
	public void hurt(int amount) {
		health -= amount;
		
		if(health <= 0){
			active = false;
			die();
		}
	}
	
	//Set the collision bounds using the defined bounds x and y alongside the width and height
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	

	//Check the entity for collisions
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		
		//Loop through each entity and check if it collides with another entity (not itself)
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			
			//prevents checking itself
			if(e.equals(this)) 
				continue;
			
			//check all other entities for collision
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset)))
				return true;
		}
		return false;
	}
	
	
	
	//Getters & Setters
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isEnemy() {
		return isEnemy;
	}



}
