package mc322.game.composites.enemies;

import mc322.game.composites.Entity;
import mc322.game.input.KeyManager;

public class Virus extends Enemy {
	
	public Virus() {
		super();
		this.life = 10;
		this.attack = 2;
		this.range = 3;
	}
	
	@Override
	public void addEntity(Entity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntity(Entity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(KeyManager key) {
		super.update(key);
	}
}
