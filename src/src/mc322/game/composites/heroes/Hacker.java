package mc322.game.composites.heroes;

import java.awt.Graphics2D;

import mc322.game.composites.IEntity;

public class Hacker extends Hero implements IHero {
	
	public Hacker() {
		super();
		this.life = 30;
		this.attack = 2;
		this.range = 5;

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
