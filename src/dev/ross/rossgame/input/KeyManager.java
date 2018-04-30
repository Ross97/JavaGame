package dev.ross.rossgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener is used for receiving keyboard events (keystrokes)
public class KeyManager implements KeyListener {
	
	//Store the keys in booleans
	private boolean[] keys, justPressed, cantPress;
	private final static int sizeOfKeys = 128;
	
	//Individual keys
	public boolean up, down, left, right, aUp, aDown, aLeft, aRight;
	
	//Create arrays for each key state
	public KeyManager() {
		keys = new boolean[sizeOfKeys];
		justPressed = new boolean[sizeOfKeys];
		cantPress = new boolean[sizeOfKeys];
	}
	
	//Update out arrays to see what keys are being / have been pressed
	public void tick() {
		
		//Go through each key
		for(int i = 0;i < keys.length;i++)
		{
			if(cantPress[i] && !keys[i])
				cantPress[i] = false;
			else if(justPressed[i]){
				cantPress[i] = true;
				justPressed[i] = false;
			}
			
			//Determine which key was just pressed
			if(!cantPress[i] && keys[i])
				justPressed[i] = true;
		}

		//Name each key
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
	}

	//Called from inventory class
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
	

	
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}