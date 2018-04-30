package dev.ross.rossgame.states;

import java.awt.Color;
import java.awt.Graphics;


import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.gfx.Text;
import dev.ross.rossgame.world.World;

//Main game level
public class GameState extends State {

	private World world;
	
	//Constructor to find level file
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	//If right click, show menu
	public void tick() {
		world.tick();
		if(handler.getMouseManager().isRightPressed())			
			State.setState(handler.getGame().menuState);
	}

	//Render everything in the world
	public void render(Graphics g) {
		world.render(g);
		
		//Draw player HUD
		Text.drawString(g, "Health: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getHealth()), handler.getWidth() - 200, 50, Color.WHITE, Assets.font_size28);
	}

}
