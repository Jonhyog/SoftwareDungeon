package mc322.game.composites.enemies;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;

public abstract class Enemy extends DynamicEntity {
	protected Movement enemyMovement;
	
	protected Enemy() {
		setSolida(false);
	}
	
	public void setMovement(Movement enemyMovement) {
		this.enemyMovement = enemyMovement;
	}
}
