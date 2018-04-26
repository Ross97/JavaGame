package dev.ross.rossgame.entities;

import dev.ross.rossgame.Handler;

//Class for all static entities (trees, rocks etc will extend this class)

public abstract class StaticEntity extends Entity {
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x , y, width, height);	
	}
}
