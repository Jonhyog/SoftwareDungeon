package mc322.game.composites.heroes;

import java.awt.Graphics2D;

import mc322.game.composites.IEntity;

public class Hacker extends Hero implements IHero {
	
	public Hacker() {
		super();
		this.life = 20;
		this.attack = 4;
		this.range = 4;

		this.ticks = 0;
		this.n = 0;
		this.minimunDistance = 0;
	}
	
	// Fim de movimento com mouse
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
