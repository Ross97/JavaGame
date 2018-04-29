package dev.ross.rossgame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.ross.rossgame.Handler;

public class ItemManager {
	
	//Handler stuff
	private Handler handler;
	private ArrayList<Item> items;
	
	//Constructor to create the items arrayList
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	//Go through all items, tick them, and check if needs removing
	public void tick() {
		Iterator<Item> i = items.iterator();
		
		while(i.hasNext()) {
			Item item = i.next();
			item.tick();
			
			if(item.isPickedUp())
				i.remove();
		}
	}
	
	//Draw the items
	public void render(Graphics g) {
		for(Item i : items)
			i.render(g);
	}
	
	//Add the items
	public void addItem(Item i) {
		i.setHandler(handler);
		items.add(i);
	}
}
