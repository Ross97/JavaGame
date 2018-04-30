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

	//If right click, set menu
	public void tick() {
		world.tick();
		if(handler.getMouseManager().isRightPressed())			
			State.setState(handler.getGame().menuState);
	}

	//Render everything in the world
	public void render(Graphics g) {
		world.render(g);
		
		//Draw player Health HUD
		g.setColor(Color.black);
		g.fillRect(handler.getWidth()-190, 0, 200, 30);
		Text.drawString(g, "Health: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getHealth()), handler.getWidth() - 190, 25, Color.BLUE, Assets.font_size28);
	}

}
