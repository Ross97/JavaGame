package dev.ross.rossgame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Entity;
import dev.ross.rossgame.entities.EntityManager;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.items.Item;

public class Enemy extends Creature {
	//Entities
	private EntityManager entityManager;
	private float playerX, playerY;
	private boolean nearby = false;
	private float distance;
	private boolean isAngry = false;

	public Enemy(EntityManager manager, Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
		
		this.entityManager = manager;
		isEnemy = true;
		//Bounds for collision
		bounds.x = 20;
		bounds.y = 30;
		bounds.width = 32;
		bounds.height = 32;
	}
	
	public void tick() {
		playerX = entityManager.getPlayer().getX();
		playerY = entityManager.getPlayer().getY();
		
		distance = (float) Math.sqrt((x-playerX)*(x-playerX) + (y-playerY)*(y-playerY));
		
		//Check if can see player
		if(distance < 200)
			nearby = true;
		else
			nearby = false;
		
		//Movement for enemy
		move(); //from Creature
		
		//See player, follow it
		if(nearby){ 
			isAngry = true;
			speed = 2;
			if(playerY > y)
				yMove = +speed;
			else
				yMove = -speed;
			
			if(playerX > x)
				xMove = +speed;
			else
				xMove = -speed;
		}
		else { //idle
			isAngry = false;
			yMove = 0;
			xMove = 0;
		}
			
	}
	
	
	//Draw the enemy
	public void render(Graphics g) {
		if(isAngry)
			g.drawImage(Assets.enemyAngry, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), (int)DEFAULT_CREATURE_WIDTH, (int)DEFAULT_CREATURE_HEIGHT, null); 
		else
			g.drawImage(Assets.enemy, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), (int)DEFAULT_CREATURE_WIDTH, (int)DEFAULT_CREATURE_HEIGHT, null);
		
		/*draw Collision box
		g.setColor(Color.blue);
		g.fillRect(	(int) x, (int) y,bounds.width,bounds.height);*/
					
	}

	@Override
	public void die() {
		System.out.println("Enemy killed!");
		handler.getWorld().getItemManager().addItem(Item.enemyItem.createNew((int)x,(int)y));
	}
	

}
