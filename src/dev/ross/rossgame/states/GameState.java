package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.entities.creatures.Player;
import dev.ross.rossgame.tiles.Tile;

public class GameState extends State {

	private Player player;
	
	//constructor
	public GameState(Game game) {
		super(game);
		player = new Player(game,100,100);
	}
	
	public void tick() {
		player.tick();
	}

	public void render(Graphics g) {
		player.render(g);
		Tile.tiles[0].render(g, 0, 0);
		Tile.tiles[2].render(g, 64, 0);
		
	}

}
