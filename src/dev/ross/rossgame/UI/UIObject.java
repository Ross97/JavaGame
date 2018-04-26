package dev.ross.rossgame.UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//abstract allows extending
public abstract class UIObject {
	
	protected float x,y ;
	protected int width, height;
	protected boolean hovering = false;
	protected Rectangle bounds;
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int)x, (int)y, width, height);
	}

	//to be implemented in each UI object
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	//is mouse over ui object
	public void onMouseMove(MouseEvent e) {
		if(bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}
	
	//if mouse is over UI, call onClick for the object's UI class
	public void onMouseRelease(MouseEvent e) {
		if(hovering)
			onClick();
	}
	
	
	
	//GETTERS AND SETTERS
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	
}
