package dev.ross.rossgame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.ross.rossgame.display.Display;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.input.KeyManager;
import dev.ross.rossgame.states.GameState;
import dev.ross.rossgame.states.MenuState;
import dev.ross.rossgame.states.State;

//allow game ot run in a thread
public class Game implements Runnable {
	
	//create display object
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	//using buffers prevents flickering
	private BufferStrategy bs; //how it should draw to screen (uses buffers)
	
	//graphics object, allows to draw to canvas
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	
	
	//Input
	private KeyManager keyManager;
	
	//constructor
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	public void init() {
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager); //get JFrame and add key listener
		Assets.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);

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
		long timer = 0; //times until 1 second
		int ticks = 0; //for FPS counter
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			//fps counter
			if(timer >= 1000000000) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
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
