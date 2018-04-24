package dev.ross.rossgame.states;

import java.awt.Graphics;

import dev.ross.rossgame.Game;

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
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	public abstract void tick();
	public abstract void render(Graphics g); //allows drawing to screen
	
}
