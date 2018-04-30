package dev.ross.rossgame.entities.creatures;

import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.items.Item;

public class Enemy extends Creature {
	
	//Enemy properties
	private boolean nearby = false;
	private float distance;
	private boolean isAngry = false;
	
	//Store player X,Y for AI
	private float playerX, playerY;

	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 

		isEnemy = true;
		
		//Bounds for collision
		bounds.x = 20;
		bounds.y = 30;
		bounds.width = 32;
		bounds.height = 32;
		
		//Gives enemy a random speed less than 2.8
		speed = (float) (Math.random() * 2.8 + 1);
	}
	
	//Update the enemy, build simple AI
	public void tick() {
		
		//Get the player X and Y
		playerX = handler.getWorld().getEntityManager().getPlayer().getX();
		playerY = handler.getWorld().getEntityManager().getPlayer().getY();
		
		//Find the distance from the player
		distance = (float) Math.sqrt((x-playerX)*(x-playerX) + (y-playerY)*(y-playerY));
		
		//Check if can nearby player
		if(distance < 250)
			nearby = true;
		else
			nearby = false;
		
		//Movement for enemy
		move(); //from Creature
		
		//If can see alive player, follow it
		if(nearby && handler.getWorld().getEntityManager().getPlayer().getActive()){ 
			isAngry = true;
			
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
	}

	//If Enemy dies, add the itemDrop
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.enemyItem.createNew((int)x+50,(int)y));
	}
	

}
