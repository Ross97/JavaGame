package dev.ross.rossgame.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.ross.rossgame.Handler;
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
	
	//Display the inventory 
	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F))
			active = !active;
		if(!active)
			return;
		
		//Print the inventory
		System.out.println("\n\nInventory:");
		for(Item i : inventoryItems)
			System.out.println(i.getName() + " " + i.getCount());
	}
	
	public void render(Graphics g){}
	
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
