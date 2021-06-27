package mc322.game.composites.items;

import mc322.game.composites.IEntity;
import mc322.game.gfx.Sprite;
import mc322.game.util.GameStats;

public class Coffe extends Item {
	
	private static int healLifePoint = 3;
	
	public Coffe(Sprite texture) {
		super(texture);
		setType("Coffe");
	}

	@Override
	public void addEntity(IEntity ent) {
		return;
	}

	@Override
	public void removeEntity(IEntity ent) {
		return;
	}


	@Override
	public void update() {
		animations.get(currentAnim).tick();
	}
	
	public void interact(IEntity ent) {
		if (ent.getType().equals("Hero")) {			
			ent.updateLife(Coffe.healLifePoint);
			root.removeEntity(this);
			GameStats.increaseScore(Coffe.healLifePoint);
		}
	}

	@Override
	public void actionPerformed() {
		return;
	} 
}
