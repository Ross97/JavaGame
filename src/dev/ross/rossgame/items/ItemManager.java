package dev.ross.rossgame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.ross.rossgame.Handler;

public class ItemManager {
	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	//go through all items, tick them, and check if needs removing
	public void tick() {
		Iterator<Item> i = items.iterator();
		while(i.hasNext()) {
			Item item = i.next();
			item.tick();
			if(item.isPickedUp())
				i.remove();
			
		}
	}
	public void render(Graphics g) {
		for(Item i : items)
			i.render(g);
	}
	
	public void addItem(Item i) {
		i.setHandler(handler);
		items.add(i);
	}
}
