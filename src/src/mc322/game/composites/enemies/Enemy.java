package mc322.game.composites.enemies;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.input.KeyManager;

public abstract class Enemy extends DynamicEntity {
	protected Movement enemyMovement;
	
	protected Enemy() {
		setSolida(false);
	}
	
	public void setMovement(Movement enemyMovement) {
		this.enemyMovement = enemyMovement;
	}
	
	private void attack(int[] target) {
		System.out.println("Inimigo Atacando X: " + target[0] + " Y: " + target[0]);
		IDungeon fatherCell = (IDungeon) father;
		fatherCell.handleAttack(this, target);
		n = this.range;
	}
	
	private boolean isInAttackRange() {
		if (caminho == null)
			return false;
		if (caminho.size() == minimunDistance)
			return true;
		return false;
	}
	
	public void update(KeyManager key) {
		IDungeon fatherCell = (IDungeon) father; //FIX-ME
		
		if (!isAlive()) {
			father.removeEntity(this);
			return;
		}
		
		if (fatherCell.isPlayerTurn()) {
			resetPath();
			return;
		}
		
		if (n == this.range) {
			return;
		}
		
		if (knowsPath() && isInRange()) {
			fatherCell.toggleUpdating(true);
			nextPosition();
		} else if (isInAttackRange()){
			attack(caminho.get(0));
			fatherCell.toggleUpdating(true);
		} else {
			askForPath(fatherCell.getPlayerPosition());
		}
		ticks++;
	}
	
	public void move(int x, int y) {
		try {
			IDungeon fatherCell = (IDungeon) father; //FIX-ME
			fatherCell.moveEntity(this, new int[] {x, y});
			n++;
		} catch(DungeonException e){
			System.out.println(e.getMessage());
			return;
		}
		setPosition(x, y);
	}
}
