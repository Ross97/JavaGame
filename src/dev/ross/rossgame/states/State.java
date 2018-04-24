package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Game;
import dev.ross.rossgame.Handler;

//abstract class
public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	
	//Class
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g); //allows drawing to screen
	
}
