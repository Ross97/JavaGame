package dev.ross.rossgame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;

public class MenuState extends State {
	
	public MenuState(Handler handler) {
		super(handler);
	}
	
	public void tick() {
		if(handler.getMouseManager().isLeftPressed())
			State.setState(handler.getGame().gameState);
	}

	public void render(Graphics g) {
		//draw Mouse Pointer
		g.setColor(Color.black);
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 5, 5);
	}

}
