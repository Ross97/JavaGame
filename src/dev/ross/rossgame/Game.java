package dev.ross.rossgame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.ross.rossgame.display.Display;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.gfx.Camera;
import dev.ross.rossgame.input.KeyManager;
import dev.ross.rossgame.input.MouseManager;
import dev.ross.rossgame.states.GameState;
import dev.ross.rossgame.states.MenuState;
import dev.ross.rossgame.states.State;


public class Game implements Runnable { //Runnable allows threading with run()
	
	//create display object
	private Display display;
	private int width, height;
	public String title;
	
	//threading
	private boolean running = false;
	private Thread thread;
	
	//using buffers prevents flickering
	private BufferStrategy bs; //how it should draw to screen (uses buffers)
	
	//graphics object, allows to draw to canvas
	private Graphics g;
	
	//States (ie what level is the player on)
	public State gameState;
	public State menuState;
	
	//Input management
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private Camera camera;
	
	//Handler
	private Handler handler;

	
	//constructor
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	//Setup the display
	public void init() {
		display = new Display(title,width,height);
		
		display.getFrame().addKeyListener(keyManager); //get JFrame and add key listener
		display.getFrame().addMouseListener(mouseManager); //add mouseManager to JFrame
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager); //get Canvas and add key listener
		display.getCanvas().addMouseMotionListener(mouseManager); //add mouseManager to Canvas

		Assets.init(); //get our assets from the asset class
		
		//Create handler and camera objects
		handler = new Handler(this);
		camera = new Camera(handler, 0,0); //(handler,x,y)
		
		//Setup our states (levels)
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		
		//Set the state
		State.setState(menuState);
	}

	
	//Update the keyManager and tick the state
	private void tick() { 
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	
	//Render the game using BufferStrategy
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		
		//create a bufferStrategy if none exists
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//Clear the screen
		g.clearRect(0, 0, width, height);
		
		//Draw the state
		if(State.getState() != null)
			State.getState().render(g);
		
		//Stop drawing
		bs.show();
		g.dispose();
	}
	
	
	//Run the game using threads
	public void run() {
		init(); //for threading
		
		//Set FPS for the display
		int fps = 60;
		double timePerTick = 1000000000/fps; //max time to run tick/render (once per FPS)
		double delta = 0;
		long now; //current time
		long lastTime = System.nanoTime(); //returns time of pc 
		
		while(running) {
			//Calculate timings
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			//Render every frame
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
	
		//Stop running the thread
		stop();
	}
	
	//Start thread to run the game
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this); //pass game class into thread
		thread.start(); //calls run()
	}
	
	//Stop the thread of the game
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//Getters & Setters
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	


}
