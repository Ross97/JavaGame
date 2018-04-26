package dev.ross.rossgame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;
import dev.ross.rossgame.UI.ClickListener;
import dev.ross.rossgame.UI.UIImageButton;
import dev.ross.rossgame.UI.UIManager;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.world.World;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		//allows us to add a play button
		uiManager.addObject(new UIImageButton(250, 250, 128, 64, Assets.button, 
					new ClickListener() {
						@Override
						public void onClick() {
							handler.getMouseManager().setUIManager(null); //unset manager
							State.setState(handler.getGame().gameState);
						}
					}));
	}
	
	public void tick() {
		uiManager.tick();
		
		if(handler.getMouseManager().isLeftPressed())
			State.setState(handler.getGame().gameState);

	}

	public void render(Graphics g) {
		uiManager.render(g);
		
		//draw Mouse Pointer
		g.setColor(Color.black);
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 5, 5);
	}

}
