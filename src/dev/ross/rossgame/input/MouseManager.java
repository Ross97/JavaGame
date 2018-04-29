package dev.ross.rossgame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.ross.rossgame.UI.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {

	//Left click, right click, and mouse position
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	
	//For buttons
	private UIManager uiManager;
	
	//Constructor
	public MouseManager(){}
	
	//For UImanagerment (buttons etc)
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	//Getters
	public boolean isLeftPressed() {
		return leftPressed;
	}
	public boolean isRightPressed() {
		return rightPressed;
	}
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	
	
	//Functions
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}


	public void mousePressed(MouseEvent e) {
		
		//left click
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		
		//right click
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		
		//left click
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		
		//right click
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}

	//Auto generated functions for mouse stuff
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}

}
