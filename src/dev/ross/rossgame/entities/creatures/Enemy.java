package dev.ross.rossgame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;

public class Enemy extends Creature {

	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
		
		//Bounds for collision
		bounds.x = 20;
		bounds.y = 30;
		bounds.width = 32;
		bounds.height = 32;
	}
	
	public void tick() {
		//Movement for enemy
		move(); //from Creature
		yMove = -speed;
	}
	
	
	//Draw the enemy
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)x, (int)y, (int)DEFAULT_CREATURE_WIDTH, (int)DEFAULT_CREATURE_HEIGHT, null); //x and y from Entity class

		//draw Collision box (settings above)
		g.setColor(Color.blue);
		g.fillRect(	(int) x,
					(int) y,
					bounds.width,
					bounds.height);
					
	}

	@Override
	public void die() {
		System.out.println("Enemy killed!");
	}
	

}
