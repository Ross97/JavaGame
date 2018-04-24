package dev.ross.rossgame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	//constructor
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = width;
		
		createDisplay();
	}
	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close game when window closes
		frame.setResizable(false); 
		frame.setLocationRelativeTo(null); //center of screen instead of side
		frame.setVisible(true);
		
		//create the canvas (locked dimensions)
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false); //JFrame is only thing with focus
		
		frame.add(canvas);
		frame.pack(); //resize to fully see canvas
	}
	
	public Canvas getCanvas() {
		return canvas; //(allows us to access this var from other classes)
	}
	
	public JFrame getFrame(){
		return frame; //returns frame from this class
	}
}
