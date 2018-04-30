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
	
	//Constructor creates a new arrayList of type Item
	public Inventory(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	//Open/close the inventory 
	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F))
			active = !active;
		if(!active)
			return;
	}
	
	//Render the inventory to the player's screen
	public void render(Graphics g){
		if(!active)
			return;
		
		int linespace = 50;
		int tabwidth = 100;
					
		Text.drawString(g, "Inventory:", 50, 500, Color.WHITE, Assets.font_size28);
		for(Item i : inventoryItems) {
			Text.drawString(g, i.getName(), 50, 500 + linespace, Color.WHITE, Assets.font_size28);
			Text.drawString(g, Integer.toString(i.getCount()), 50 + tabwidth, 500 + linespace, Color.WHITE, Assets.font_size28);
			linespace = linespace + linespace;
		}
	}
	
	//Allows adding items by iterating over inventory items and adding to count
	public void addItem(Item item){
		//Go through each item
		for(Item i : inventoryItems){
			//Add if correct
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
