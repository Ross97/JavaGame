package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.entities.creatures.Player;
import dev.ross.rossgame.tiles.Tile;
import dev.ross.rossgame.world.World;

public class GameState extends State {

	private Player player;
	private World world;
	
	//constructor
	public GameState(Game game) {
		super(game);
		player = new Player(game,100,100);
		world = new World(game, "res/worlds/world1.txt");
	}
	
	public void tick() {
		world.tick();
		player.tick();
	}

	public void render(Graphics g) {
		world.render(g);
		player.render(g);
		
	}

}
