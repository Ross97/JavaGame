package dev.ross.rossgame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.gfx.Text;
import dev.ross.rossgame.world.World;

public class MenuState extends State {
	
	private World menu;

	//Add the world
	public MenuState(Handler handler) {
		super(handler);
		menu = new World(handler, "res/worlds/menu.txt");
	}
	
	//Tick and set the gameState when left click is pressed
	public void tick() {
		if(handler.getMouseManager().isLeftPressed())			
			State.setState(handler.getGame().gameState);
	}

	//Render the world
	public void render(Graphics g) {
		menu.render(g);
		
		//Show the menu text
		Text.drawString(g, "FINDER!" , handler.getWidth()/2 - 110, 125, Color.WHITE, Assets.font_size48);
		Text.drawString(g, "Go find gold!" , handler.getWidth()/2 - 115, 650, Color.YELLOW, Assets.font_size28);
	}

}
