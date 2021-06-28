package mc322.game.composites.enemies;

import mc322.game.composites.IEntity;

public class Virus extends Enemy {
	
	public Virus() {
		super();
		this.life = 12;
		this.attack = 4;
		this.range = 2;
	}
	
	@Override
	public void addEntity(IEntity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntity(IEntity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		super.update();
	}
}
