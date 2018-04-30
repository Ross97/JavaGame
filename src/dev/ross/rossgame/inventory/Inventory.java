package dev.ross.rossgame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.gfx.Text;
import dev.ross.rossgame.items.Item;

//Class to store the players inventory
public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	private boolean goldFound = false;
	
	//Constructor creates a new arrayList of type Item
	public Inventory(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	//Open & close the inventory using F
	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F))
			active = !active;
		if(!active)
			return;
		
		//Check for desired gold level
		for(Item i : inventoryItems) {
			if(i.getName() == "Gold" && i.getCount() >= 15)
				goldFound = true;
		}
	}
	
	//Render the inventory to the player's screen
	public void render(Graphics g){
		if(!active)
			return;
		
		//For drawing the inventory in bottom left
		int linespace = 30;
		int tabwidth = 100;
		int x = 5;
		int y = handler.getHeight()-70;
		
		//Draw border and background
		g.setColor(Color.black);
		g.fillRect(0, y-40, 200, 110);
		g.setColor(Color.blue);
		g.fillRect(0, y-30, 190, 100);
				
		//Display inventory text
		Text.drawString(g, "Inventory:", x, y, Color.WHITE, Assets.font_size28);
		for(Item i : inventoryItems) {
			Text.drawString(g, i.getName(), x, y + linespace, Color.YELLOW, Assets.font_size28);
			Text.drawString(g, Integer.toString(i.getCount()), x + tabwidth, y + linespace, Color.ORANGE, Assets.font_size28);
			linespace = linespace + linespace;
		}
		
		//If desired gold has been found
		if(goldFound) {
			g.setColor(Color.black);
			g.fillRect(handler.getWidth()/2-130,  handler.getHeight()-50, 295, handler.getHeight()-50);

			Text.drawString(g, ">15+ gold found<", handler.getWidth()/2-130, handler.getHeight()-30, Color.WHITE, Assets.font_size28);
			Text.drawString(g, " Level Complete!", handler.getWidth()/2-130, handler.getHeight()-1, Color.WHITE, Assets.font_size28);
		}
	}
	
	//Allows adding items by iterating over inventory items and adding to count
	public void addItem(Item item){
		//Go through each item
		for(Item i : inventoryItems){
			//Add to count if already exists 
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		//New item, add it
		inventoryItems.add(item);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
