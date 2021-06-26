package mc322.game.composites.enemies;

import java.awt.Graphics2D;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public abstract class Enemy extends DynamicEntity {
	protected Movement enemyMovement;
	
	protected Enemy() {
		setSolida(false);
		setType("Enemy");
		this.ticks = 0;
		this.n = 0;
		this.minimunDistance = 1;
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
	
	public void render(Graphics2D g) {
		Sprite text = animation.getCurrentFrame();
		g.drawImage(text.getTexture(), x * 32, y * 32, text.getSizeX(), text.getSizeY(), null);
	}
	
	public void update(KeyManager key) {
		IDungeon fatherCell = (IDungeon) father; //FIX-ME
		animation.tick();
		
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
		
		if (caminho != null && n == caminho.size()) {
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
	
	private void lookInDirection(int xSource, int xTarget) {
		if (xTarget - xSource == 0)
			return;
		else if (xTarget - xSource > 0)
			animation.flipSprites(false);
		else
			animation.flipSprites(true);
	}
	
	public void move(int x, int y) {
		try {
			int[] lastPos = getPosition();
			IDungeon fatherCell = (IDungeon) father; //FIX-ME
			fatherCell.moveEntity(this, new int[] {x, y});
			lookInDirection(lastPos[0], x);
			n++;
		} catch(DungeonException e){
			System.out.println(e.getMessage());
			return;
		}
		setPosition(x, y);
	}
}
