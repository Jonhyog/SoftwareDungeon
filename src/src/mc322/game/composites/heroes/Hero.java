package mc322.game.composites.heroes;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.input.KeyManager;

public abstract class Hero extends DynamicEntity {
	protected Movement heroMovement;
	
	protected Hero() {
		setSolida(false);
	}
	
	public void setMovement(Movement heroMovement) {
		this.heroMovement = heroMovement;
	}
	
	public void update(KeyManager key) {
		IDungeon fatherCell = (IDungeon) father; //FIX-ME
		
		if (!fatherCell.isPlayerTurn()) {
			resetPath();
			return;
		}
		
		if (knowsPath() && isInRange()) {
			ticks++;
			fatherCell.toggleUpdating(true);
			nextPosition();
		}
	}
	
	public void receiveMovement(int chave) {
		heroMovement.move(chave, this);
	}
	
	public void receiveMovement(int[] target) {
		// heroMovement.move(target, this);
		askForPath(target);
	}
	
	public void attack(int[] target) {
		System.out.println("Atacando X: " + target[0] + " Y: " + target[1]);
		IDungeon fatherCell = (IDungeon) father;
		fatherCell.handleAttack(this, target);
		n++;
		if (n == this.range) {
			fatherCell.requestNextTurn();
			n = 0;
		}
	}
	
	public void move(int x, int y) {
		try {
			IDungeon fatherCell = (IDungeon) father;
			fatherCell.moveEntity(this, new int[] {x, y});
			setPosition(x, y);
			n++;
			if (n == this.range) {
				fatherCell.requestNextTurn();
				n = 0;
			}
		} catch(DungeonException e){
			System.out.println(e.getMessage());
			return;
		}
	}
}
