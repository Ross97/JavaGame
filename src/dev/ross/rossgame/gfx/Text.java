package dev.ross.rossgame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text {
	public static void drawString(Graphics g, String text, int xPos, int yPos,Color c, Font font) {
		g.setColor(c);
		g.setFont(font);
		int x = xPos;
		int y = yPos;
		
		g.drawString(text, x, y);
		
	}
}
