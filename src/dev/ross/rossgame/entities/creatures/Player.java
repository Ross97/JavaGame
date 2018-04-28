package dev.ross.rossgame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.ross.rossgame.Handler;
import dev.ross.rossgame.entities.Entity;
import dev.ross.rossgame.entities.EntityManager;
import dev.ross.rossgame.gfx.Assets;

public class Player extends Creature {
	
	//Attack setting (to prevent spam attack)
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	private boolean playerAngry = false;
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT); 
		
		//Bounds for collision
		bounds.x = 20;
		bounds.y = 30;
		bounds.width = 32;
		bounds.height = 32;
	}


	public void tick() {
		//Movement
		getInput();
		move(); //from Creature
		handler.getCamera().centerOnEntity(this);
		
		//Attacking
		checkAttack();
	}
	
	private void checkAttack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		//prevent spam attack
		if(attackTimer < attackCooldown)
			return;
		
		Rectangle collision_bounds = getCollisionBounds(0, 0);
		Rectangle attack_rect = new Rectangle();
		
		int arSize = 100; //how close
		attack_rect.width = arSize;
		attack_rect.height = arSize;
		
		//Check attack keys (arrow keys)
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

	public void die() {
		System.out.println("You died!");
	}
	
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

	public void render(Graphics g) {
		if(!playerAngry)
			g.drawImage(Assets.player, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null); //x and y from Entity class
		else
			g.drawImage(Assets.playerAngry, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null); //x and y from Entity class
	}
	

	
}
