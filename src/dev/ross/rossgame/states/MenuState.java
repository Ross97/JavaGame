package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.gfx.Assets;

public class MenuState extends State {
	
	public MenuState(Game game) {
		super(game);
	}
	
	public void tick() {
	}

	public void render(Graphics g) {
		g.drawImage(Assets.dirt, 0, 0, null);
	}

}
