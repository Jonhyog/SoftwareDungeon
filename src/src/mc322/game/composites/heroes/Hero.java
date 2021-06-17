package mc322.game.composites.heroes;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;

public abstract class Hero extends DynamicEntity {
	protected Movement heroMovement;
	
	protected Hero() {
		setSolida(false);
	}
	
	public void setMovement(Movement heroMovement) {
		this.heroMovement = heroMovement;
	}
}
