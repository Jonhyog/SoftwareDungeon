package mc322.game.composites.enemies;

import mc322.game.composites.IEntity;

public class Bug extends Enemy {
	
	public Bug() {
		super();
		this.life = 10;
		this.attack = 2;
		this.range = 3;
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
