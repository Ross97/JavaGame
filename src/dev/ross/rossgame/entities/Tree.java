package dev.ross.rossgame.entities;

import java.awt.Graphics;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.items.Item;
import dev.ross.rossgame.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y){
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);
		
		health = 7;
		
		//Set tree boundaries
		bounds.x = 10;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.5f);
	}

	public void tick() {}
	
	//Add the item pickup when dead
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.treeItem.createNew((int)x,(int)y));
	}
	
	//Draw the tree
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
	}

}
