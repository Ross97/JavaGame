package dev.ross.rossgame.tiles;
import dev.ross.rossgame.gfx.Assets;

public class BushBerryTile extends Tile {

	public BushBerryTile(int id) {
		super(Assets.bushBerry, id);
	}
	
	public boolean isSolid() {
		return true;
	}
}
