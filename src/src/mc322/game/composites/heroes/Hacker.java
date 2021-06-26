package mc322.game.composites.heroes;

import java.awt.Graphics2D;

import mc322.game.composites.Entity;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public class Hacker extends Hero implements IHero {
	
	public Hacker() {
		super();
		this.life = 10;
		this.attack = 2;
		this.range = 5;

		this.ticks = 0;
		this.n = 0;
		this.minimunDistance = 0;
	}
	
	// Fim de movimento com mouse
	@Override
	public void addEntity(Entity ent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeEntity(Entity ent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public void update(KeyManager key) {
		super.update(key);
	}
}
