package dev.ross.rossgame;


public class Launcher {
	public static void main(String[] args){
		//store game object in variable called game
		Game game = new Game("RossGame", 500, 500);
		game.start();
	}
}
