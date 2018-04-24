package dev.ross.rossgame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;

public class Player extends Creature {

	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
		
		
		//Bounds for collision for player
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
	}


	public void tick() {
		getInput();
		move(); //from Creature
		handler.getCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		
		
	}

	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null); //x and y from Entity class
		
		
		//draw Collision box (settings above)
		g.setColor(Color.blue);
		g.fillRect(	(int) (x + bounds.x - handler.getCamera().getxOffset()),
					(int) (y + bounds.y - handler.getCamera().getyOffset()),
					bounds.width,
					bounds.height);
	}
	
}
