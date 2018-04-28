package dev.ross.rossgame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Entity;
import dev.ross.rossgame.entities.EntityManager;
import dev.ross.rossgame.gfx.Assets;

public class Enemy extends Creature {
	//Entities
	private EntityManager entityManager;
	private float playerX, playerY;

	public Enemy(EntityManager manager, Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
		
		this.entityManager = manager;
		
		//Bounds for collision
		bounds.x = 20;
		bounds.y = 30;
		bounds.width = 32;
		bounds.height = 32;
	}
	
	public void tick() {
		//Movement for enemy
		move(); //from Creature

		playerX = entityManager.getPlayer().getX();
		playerY = entityManager.getPlayer().getY();
		
		if(playerY > y)
			yMove = +speed;
		else
			yMove = -speed;
		
		if(playerX > x)
			xMove = +speed;
		else
			xMove = -speed;
		
	//	System.out.println("P: " + playerX + " " + playerY);
	//	System.out.println("E: " + x + " " + y);
	}
	
	
	//Draw the enemy
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), (int)DEFAULT_CREATURE_WIDTH, (int)DEFAULT_CREATURE_HEIGHT, null); //x and y from Entity class

		/*
		//draw Collision box (settings above)
		g.setColor(Color.blue);
		g.fillRect(	(int) x,
					(int) y,
					bounds.width,
					bounds.height);
					*/
					
	}

	@Override
	public void die() {
		System.out.println("Enemy killed!");
	}
	

}
