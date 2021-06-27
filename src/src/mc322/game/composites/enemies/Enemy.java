package mc322.game.composites.enemies;

import java.awt.Graphics2D;
import java.util.Set;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.gfx.Sprite;
import mc322.game.util.GameStats;

public abstract class Enemy extends DynamicEntity {
	protected Movement enemyMovement;
	protected boolean attacked = false;
	
	protected Enemy() {
		setSolida(false);
		setType("Enemy");
		setState("idle");
		this.ticks = 0;
		this.n = 0;
		this.minimunDistance = 1;
	}
	
	public void setMovement(Movement enemyMovement) {
		this.enemyMovement = enemyMovement;
	}
	
	protected void resetPath() {
		super.resetPath();
		this.attacked = false;
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
	
	protected boolean isReachable() {
		return range * 3 >= caminho.size();
	}
	
	public void render(Graphics2D g) {
		Sprite text = animations.get(state).getCurrentFrame();
		int fatorX = 0, fatorY = 0;
		
		if (text.getSizeX() > 32)
			fatorX = (text.getSizeX() - 32);
		if (text.getSizeY() > 32)
			fatorY = text.getSizeY() - 32;
		
		g.drawImage(text.getTexture(), x * 32 - fatorX, y * 32 - fatorY, text.getSizeX(), text.getSizeY(), null);
	}
	
	public void update() {
		animations.get(state).tick();
		
		if (!isAlive()) {
			root.removeEntity(this);
			GameStats.increaseScore(life);
			return;
		}
		
		if (this.isAttacking) {
			if (animations.get(state).finishedLoop()) {
				setAttacking(false);
				setState("idle");
			}
			root.toggleUpdating(true);
			return;
		}
		
		if (root.isPlayerTurn()) {
			resetPath();
			return;
		}
		
		if (n == this.range || attacked) {
			return;
		}
		
		if (caminho != null && n == caminho.size()) {
			return;
		}
		
		if (caminho != null && !isReachable())
			return;
		
		if (knowsPath() && isInRange()) {
			root.toggleUpdating(true);
			nextPosition();
		} else if (isInAttackRange()){
			int[] target = caminho.get(0);
			int[] pos = getPosition();
			
			lookInDirection(pos[0], target[0]);
			attack(target);
			this.attacked = true;
			setAttacking(true);
			setState("atk");
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