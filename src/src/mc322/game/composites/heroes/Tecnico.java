package mc322.game.composites.heroes;

import java.awt.Graphics2D;

import mc322.game.composites.IEntity;

public class Tecnico extends Hero implements IHero {
	public Tecnico() {
		super();
		this.life = 22;
		this.attack = 4;
		this.range = 4;
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
	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public void update() {
		super.update();
	}
}
