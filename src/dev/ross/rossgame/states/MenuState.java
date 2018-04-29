package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.UI.ClickListener;
import dev.ross.rossgame.UI.UIImageButton;
import dev.ross.rossgame.UI.UIManager;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.world.World;

public class MenuState extends State {
	
	//Setup fields for the menu
	private UIManager uiManager;
	private World menu;

	public MenuState(Handler handler) {
		super(handler);
		
		//Add the background
		menu = new World(handler, "res/worlds/menu.txt");

		//Setup the UIManager for buttons
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		//Add the play button (Start Game)
		uiManager.addObject(new UIImageButton(300, 300, 128, 64, Assets.button, 
					new ClickListener() {
						@Override
						public void onClick() {
							handler.getMouseManager().setUIManager(null); //unset manager
							State.setState(handler.getGame().gameState);
						}
					}));
	}
	
	//Tick the button and set the gameState when left click is pressed
	public void tick() {
		uiManager.tick();
		
		if(handler.getMouseManager().isLeftPressed())			
			State.setState(handler.getGame().gameState);
	}

	//Render the world and button
	public void render(Graphics g) {
		menu.render(g);
		uiManager.render(g);
	}

}
