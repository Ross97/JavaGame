package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Tree;
import dev.ross.rossgame.entities.creatures.Player;
import dev.ross.rossgame.tiles.Tile;
import dev.ross.rossgame.world.World;

public class GameState extends State {

	private World world;
	
	//constructor
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	public void tick() {
		world.tick();
		if(handler.getMouseManager().isRightPressed())			
			State.setState(handler.getGame().menuState);
	}

	public void render(Graphics g) {
		world.render(g);
	}

}
