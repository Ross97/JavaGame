package dev.ross.rossgame.states;

import java.awt.Graphics;


import dev.ross.rossgame.Handler;
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
	}

}
