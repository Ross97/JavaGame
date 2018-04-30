package dev.ross.rossgame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Entity;
import dev.ross.rossgame.gfx.Assets;
import dev.ross.rossgame.inventory.Inventory;

public class Player extends Creature {
	
	//Add instance of inventory
	private Inventory inventory;

	//Attack setting (to prevent spamming attack)
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	private boolean playerAngry = false;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
		
		//Set stats, and add inventory
		health = 100;
		speed = 4;
		inventory = new Inventory(handler);
		
		//Bounds for collision
		bounds.x = 20;
		bounds.y = 30;
		bounds.width = 32;
		bounds.height = 32;	
	}

	//Update the player and inventory by getting input
	public void tick() {
		//Movement
		getInput();
		move(); //from Creature
		
		//Center the Camera on the player
		handler.getCamera().centerOnEntity(this);
		
		//Check if attack is valid
		checkAttack();
		
		//Update the inventory
		inventory.tick();
	}
	
	private void checkAttack() {
		
		//Timers to prevent insta-kills with spam attack
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		//prevent spam attack
		if(attackTimer < attackCooldown)
			return;
		
		//Setup bounds for collision/attack/hurt boxes
		Rectangle collision_bounds = getCollisionBounds(0, 0);
		Rectangle attack_rect = new Rectangle();
		Rectangle hurt_rect = new Rectangle();
		
		int arSize = 100; //how close to attack other entities
		int hurtSize = 70; //how close to be hurt
		
		//Setup attack and hurt boxes
		attack_rect.width = arSize;
		attack_rect.height = arSize;
		hurt_rect.width = hurtSize;
		hurt_rect.height = hurtSize;
		
		hurt_rect.x = (int) x + bounds.width/2;
		hurt_rect.y = (int) y + bounds.height/2;
		
		//Check if an enemy entity is within the hurtbox
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)) //ourself
				continue;
			
			//Enemy is within our hurtbox, lose health
			if(e.getCollisionBounds(0, 0).intersects(hurt_rect) && e.isEnemy())
				this.hurt(1);
		}
		
		//Check attack keys (arrow keys) with correct attack box
		if(handler.getKeyManager().aUp) {
			attack_rect.x = collision_bounds.x + collision_bounds.width/2 - arSize/2;
			attack_rect.y = collision_bounds.y - arSize;
			
		}else if (handler.getKeyManager().aDown) {
			attack_rect.x = collision_bounds.x + collision_bounds.width/2 - arSize/2;
			attack_rect.y = collision_bounds.y + collision_bounds.height;
			
		} else if (handler.getKeyManager().aLeft) {
			attack_rect.x = collision_bounds.x - arSize;
			attack_rect.y = collision_bounds.y + collision_bounds.height/2 - arSize/2;
			
		} else if (handler.getKeyManager().aRight) {
			attack_rect.x = collision_bounds.x + collision_bounds.width;
			attack_rect.y = collision_bounds.y + collision_bounds.height/2 - arSize/2;
		} 
		
		//not attacking
		else {
			playerAngry = false;
			return;
		}
		attackTimer = 0;	
		
		//attacking
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)) //itself
				continue;
			
			if(e.getCollisionBounds(0, 0).intersects(attack_rect)) {
				e.hurt(1);
				playerAngry = true;
			}
		}

	}

	//Game over
	public void die() {
		System.out.println("You died!");
		health = 0;
	}
	
	
	//Move according to input (WASD)
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	//Draw the player (depending on player mood)
	public void render(Graphics g) {
		if(playerAngry)
			g.drawImage(Assets.playerAngry, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null); //x and y from Entity class
		else
			g.drawImage(Assets.player, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null); //x and y from Entity class
		
		//Attack images
		if(handler.getKeyManager().aUp)
			g.drawImage(Assets.attack, (int)(x - handler.getCamera().getxOffset()) + bounds.x-3, (int)(y - handler.getCamera().getyOffset()), (int)DEFAULT_CREATURE_WIDTH/2, (int)DEFAULT_CREATURE_HEIGHT/2, null);
		if(handler.getKeyManager().aDown)
				g.drawImage(Assets.attack, (int)(x - handler.getCamera().getxOffset()) + bounds.x-3, (int)(y - handler.getCamera().getyOffset())+bounds.y, (int)DEFAULT_CREATURE_WIDTH/2, (int)DEFAULT_CREATURE_HEIGHT/2, null);
		if(handler.getKeyManager().aRight)
			g.drawImage(Assets.attack, (int)(x - handler.getCamera().getxOffset() + 40), (int)(y - handler.getCamera().getyOffset())+bounds.y/2 + 5, (int)DEFAULT_CREATURE_WIDTH/2, (int)DEFAULT_CREATURE_HEIGHT/2, null);
		if(handler.getKeyManager().aLeft)
			g.drawImage(Assets.attack, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset())+bounds.y/2 + 5, (int)DEFAULT_CREATURE_WIDTH/2, (int)DEFAULT_CREATURE_HEIGHT/2, null);
	}
	
	//Render on top of game
	public void renderLast(Graphics g) {
		inventory.render(g);
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	//For enemy
	public boolean getActive() {
		return active;
	}

	
}
