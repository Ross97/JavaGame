package dev.ross.rossgame;

import dev.ross.rossgame.gfx.Camera;
import dev.ross.rossgame.input.KeyManager;
import dev.ross.rossgame.input.MouseManager;
import dev.ross.rossgame.world.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public Camera getCamera() {
		return game.getCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	//setters & getters 
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
