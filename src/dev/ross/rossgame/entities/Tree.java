package dev.ross.rossgame.entities;

import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y){
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
		
	}

}
