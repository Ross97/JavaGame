package dev.ross.rossgame.entities.creatures;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.gfx.Assets;

public class Player extends Creature {

	
	public Player(Game game, float x, float y) {
		super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
	}


	public void tick() {
		getInput();
		move(); //from Creature
		game.getCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up)
			yMove = -speed;
		if(game.getKeyManager().down)
			yMove = speed;
		if(game.getKeyManager().left)
			xMove = -speed;
		if(game.getKeyManager().right)
			xMove = speed;
		
		
	}

	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)(x - game.getCamera().getxOffset()), (int)(y - game.getCamera().getyOffset()), width, height, null); //x and y from Entity class
	}
	
}
