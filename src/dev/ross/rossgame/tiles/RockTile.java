package dev.ross.rossgame.tiles;


import dev.ross.rossgame.gfx.Assets;

public class RockTile extends Tile {

	public RockTile( int id) {
		super(Assets.stone, id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
