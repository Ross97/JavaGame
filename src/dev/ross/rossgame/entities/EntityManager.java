package dev.ross.rossgame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.creatures.Player;

public class EntityManager {
	private Handler handler;
	private Player player;
	
	//arrayList for adding or removing entities
	private ArrayList<Entity> entities; 
	
	//Constructor that will create the entities arrayList and add the player
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;

		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	

	//Iterate through the entities and tick them
	public void tick() {
		Iterator<Entity> i = entities.iterator();
		while(i.hasNext()) {
			Entity e = i.next(); 
			e.tick();
			
			//Remove entities that are 'dead'
			if(!e.isActive())
				i.remove();
		}
		entities.sort(renderSorter);
	}
	
	//Render every entity
	public void render(Graphics g) {
		for(Entity e : entities)
			e.render(g);
		
		//Ensure inventory is rendered above everything else
		player.renderLast(g);
	}
	
	//Function to add entities
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	//Comparator class to see what to render first
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		//compares two entities position to decide which to render first
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1; 
			return 1;
		}
		
	};


	
	//Getters & Setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
