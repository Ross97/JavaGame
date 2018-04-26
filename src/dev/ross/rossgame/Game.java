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

//allow game ot run in a thread
public class Game implements Runnable {
	
	//create display object
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	//using buffers prevents flickering
	private BufferStrategy bs; //how it should draw to screen (uses buffers)
	
	//graphics object, allows to draw to canvas
	private Graphics g;
	
	//States
	public State gameState;
	public State menuState;
	
	
	//Input
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
	
	public void init() {
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager); //get JFrame and add key listener
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		
		handler = new Handler(this);
		camera = new Camera(handler, 0,0);
		
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);

	}

	
	//should run 60 times per second
	private void tick() { //update
		
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
	}
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//Clear the screen
		g.clearRect(0, 0, width, height);
		
		//Start drawing
		if(State.getState() != null)
			State.getState().render(g);
		

		
		//Stop drawing
		bs.show();
		g.dispose();
	}
	
	public void run() {
		//for threading
		init();
		
		int fps = 60;
		
		double timePerTick = 1000000000/fps; //1sec/fps, max time to run tick/render
		double delta = 0;
		long now; //current time
		long lastTime = System.nanoTime(); //returns time of pc 
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
			

		}
	
		stop();
	}
	
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
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		//pass game class into thread
		thread = new Thread(this);
		thread.start(); //calls run()
	}
	
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

}
