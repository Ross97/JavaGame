package dev.ross.rossgame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	//Setup Jframe and Canvas
	private JFrame frame;
	private Canvas canvas;
	
	//Data passed from Game()
	private String title;
	private int width, height;
	
	//Called from Game, passes gameName, width, and height
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = width;
		
		createDisplay();
	}
	
	//Setup the Jframe and Canvas to create the display
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close game when window closes
		frame.setResizable(false); //do not allow window resize
		frame.setLocationRelativeTo(null); //center of screen instead of side
		frame.setVisible(true); //show the game
		
		//create the canvas (locked dimensions)
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false); //JFrame is only thing with focus
		
		frame.add(canvas);
		frame.pack(); //resize to fully see canvas
	}
	
	
	//Getters & Setters
	public Canvas getCanvas() {
		return canvas; //(allows us to access Canvas from other classes)
	}
	
	public JFrame getFrame(){
		return frame; //returns the frame from this class
	}
}
