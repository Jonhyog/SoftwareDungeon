package mc322.game.composites.enemies;

import java.awt.Graphics2D;
import java.util.Set;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.gfx.Sprite;

public abstract class Enemy extends DynamicEntity {
	protected Movement enemyMovement;
	
	protected Enemy() {
		setSolida(false);
		setType("Enemy");
		setCurrentAnim("idle");
		this.ticks = 0;
		this.n = 0;
		this.minimunDistance = 1;
	}
	
	public void setMovement(Movement enemyMovement) {
		this.enemyMovement = enemyMovement;
	}
	
	private void attack(int[] target) {
		System.out.println("Inimigo Atacando X: " + target[0] + " Y: " + target[0]);
		root.handleAttack(this, target);
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
		Sprite text = animations.get(currentAnim).getCurrentFrame();
		g.drawImage(text.getTexture(), x * 32, y * 32, text.getSizeX(), text.getSizeY(), null);
	}
	
	public void update() {
		animations.get(currentAnim).tick();
		
		if (!isAlive()) {
			root.removeEntity(this);
			return;
		}
		
		if (root.isPlayerTurn()) {
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
			root.toggleUpdating(true);
			nextPosition();
		} else if (isInAttackRange()){
			attack(caminho.get(0));
			root.toggleUpdating(true);
		} else {
			askForPath(root.getPlayerPosition());
		}
		ticks++;
	}
	
	private void flipFrames(boolean value) {
		Set<String> keys = animations.keySet();
		
        for(String key: keys){
            animations.get(key).flipSprites(value);
        }
	}
	
	private void lookInDirection(int xSource, int xTarget) {
		if (xTarget - xSource == 0)
			return;
		else if (xTarget - xSource > 0)
			flipFrames(false);
		else
			flipFrames(true);
	}
	
	public void move(int x, int y) {
		try {
			int[] lastPos = getPosition();
			root.moveEntity(this, new int[] {x, y});
			lookInDirection(lastPos[0], x);
			n++;
		} catch(DungeonException e){
			System.out.println(e.getMessage());
			return;
		}
		setPosition(x, y);
	}
}
