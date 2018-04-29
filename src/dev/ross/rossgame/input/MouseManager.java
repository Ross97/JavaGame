package dev.ross.rossgame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class MouseManager implements MouseListener, MouseMotionListener {

	//Left click, right click, and mouse position
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;

	//Constructor
	public MouseManager(){}
	
	//When mouse moves, store X and Y
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	//Store clicks pressed
	public void mousePressed(MouseEvent e) {
		
		//left click
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		
		//right click
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	//Reset clicks to false
	public void mouseReleased(MouseEvent e) {
		//left click
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		
		//right click
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}

	//Auto generated functions for mouse stuff
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	
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


}
