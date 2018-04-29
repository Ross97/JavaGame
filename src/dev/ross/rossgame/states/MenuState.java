package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Handler;


import dev.ross.rossgame.world.World;

public class MenuState extends State {
	
	//Setup fields for the menu

	private World menu;

	public MenuState(Handler handler) {
		super(handler);
		
		//Add the background
		menu = new World(handler, "res/worlds/menu.txt");

	}
	
	//Tick the button and set the gameState when left click is pressed
	public void tick() {
		
		
		if(handler.getMouseManager().isLeftPressed())			
			State.setState(handler.getGame().gameState);
	}

	//Render the world and button
	public void render(Graphics g) {
		menu.render(g);
	
	}

}
