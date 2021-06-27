package mc322.game.composites.heroes;

import java.awt.Graphics2D;
import java.util.Set;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.gfx.Sprite;

public abstract class Hero extends DynamicEntity implements IHero {
	protected Movement heroMovement;
	
	protected Hero() {
		setSolida(false);
		setType("Hero");
		setCurrentAnim("idle");
	}
	
	public void setMovement(Movement heroMovement) {
		this.heroMovement = heroMovement;
	}
	
	public void render(Graphics2D g) {
		Sprite text = animations.get(currentAnim).getCurrentFrame();
		int fatorX = 0, fatorY = 0;
		
		if (text.getSizeX() > 32)
			fatorX = (text.getSizeX() - 32);
		if (text.getSizeY() > 32)
			fatorY = text.getSizeY() - 32;
		
		g.drawImage(text.getTexture(), x * 32 - fatorX, y * 32 - fatorY, text.getSizeX(), text.getSizeY(), null);
	}
	
	private void passTurn() {
		root.requestNextTurn();
		resetPath();
		setAttacking(false);
		setCurrentAnim("idle");
	}
	
	public void update() {
		animations.get(currentAnim).tick();
		
		if (this.isAttacking) {
//			this.animAtk.tick();
			if (animations.get(currentAnim).finishedLoop()) {
				passTurn();
			}
		}
		
		if (!root.isPlayerTurn()) {
			resetPath();
			return;
		}
		
		if (knowsPath() && isInRange()) {
			ticks++;
			root.toggleUpdating(true);
			nextPosition();
		}
		
		if (n == this.range)
			passTurn();
		
		if (caminho != null && n == caminho.size()) {
			passTurn();
		}
	}
	
	public void receiveMovement(int chave) {
		heroMovement.move(chave, this);
	}
	
	public void receiveMovement(int[] target) {
		// heroMovement.move(target, this);
		askForPath(target);
	}
	
	private boolean isReachable() {
		return range >= caminho.size();
	}
	
	protected void askForPath(int pos[]) {
		super.askForPath(pos);
		if (caminho != null && !isReachable()) {
			caminho = null;
			System.out.println("Nao alcanco essa posicao");
		}
	}
	
	public void attack(int[] target) {
		askForPath(target);
		if (caminho == null || caminho.size() > this.range) {
			return;
		}
		
		int[] playerPos = getPosition();
		lookInDirection(playerPos[0], target[0]);
		System.out.println("Atacando X: " + target[0] + " Y: " + target[1]);
		setAttacking(true);
		setCurrentAnim("atk");
		root.handleAttack(this, target);
		resetPath();
		
	}
	
	private void flipFrames(boolean value) {
		Set<String> keys = animations.keySet();
		
        for(String key: keys){
            animations.get(key).flipSprites(value);
        }
	}
	
	private void lookInDirection(int xSource, int xTarget) {
		if (xTarget - xSource == 0) {
			return;			
		} else if (xTarget - xSource > 0) {
			flipFrames(false);
		} else {
			flipFrames(true);
		}
	}
	
	public void move(int x, int y) {
		try {
			int[] lastPos = getPosition();
			root.moveEntity(this, new int[] {x, y});
			setPosition(x, y);
			lookInDirection(lastPos[0], x);
			n++;
		} catch(DungeonException e){
			System.out.println(e.getMessage());
			return;
		}
	}
}
