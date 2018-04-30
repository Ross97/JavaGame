package dev.ross.rossgame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.gfx.Assets;

public class Item {
	
	//Handler stuff
	public static Item[] items = new Item[128];
	public static Item treeItem = new Item(Assets.tree, "Wood", 0);
	public static Item enemyItem = new Item(Assets.gold, "Gold", 1);
	
	//Class
	public static int ITEMWIDTH = 32, ITEMHEIGHT = 32; //Size of item drop
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id; //ID to be used for savings stats
	
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1; //inventory management
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		items[id] = this;
	}
	
	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	
	//Render the item dropped
	public void render(Graphics g) {
		if(handler == null) //prevent error
			return;
		render(g, (int) (x-handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()));
	}
	
	//Render the item dropped
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	//Create an item 
	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPostion(x,y);
		return i;
	}
	
	//Set the item's XY
	public void setPostion(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}

	
	
	//Getters & Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}


	public boolean isPickedUp() {
		return pickedUp;
	}
	
	
	
	
}
