package mc322.game.composites.heroes;

import java.awt.Graphics2D;
import java.util.Set;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.gfx.Sprite;
import mc322.game.util.GameStats;

public abstract class Hero extends DynamicEntity implements IHero {
	protected Movement heroMovement;
	protected HeroControler heroCtrl; 
	
	protected Hero() {
		setSolida(false);
		setType("Hero");
		setState("idle");
	}
	
	public void connectMovement(Movement heroMovement) {
		this.heroMovement = heroMovement;
	}
	
	public void connectControler(HeroControler heroCtrl) {
		this.heroCtrl = heroCtrl;
	}
	
	public boolean isAlive() {
		return life > 0;
	}
	
	public void updateLife(int n) {
		super.updateLife(n);
		GameStats.increasePlayerLife(n);
	}
	
	public void render(Graphics2D g) {
		Sprite text = animations.get(state).getCurrentFrame();
		int fatorX = 0, fatorY = 0;
		
		if (text.getSizeX() > 32)
			fatorX = (text.getSizeX() - 32);
		if (text.getSizeY() > 32)
			fatorY = text.getSizeY() - 32;
		
		g.drawImage(text.getTexture(),
				x * 32 - fatorX, y * 32 - fatorY,
				text.getSizeX(), text.getSizeY(), null);
	}
	
	public void passTurn() {
		setState("idle");
		root.requestNextTurn();
		resetPath();
		setAttacking(false);
	}
	
	public void update() {
		animations.get(state).tick();
		
		switch (state) {
			case "idle":
			case "movement":
				if (knowsPath() && isInRange()) {
					ticks++;
					root.toggleUpdating(true); // FIX
					nextPosition();
				}
				
				if (n == this.range)
					heroCtrl.finishedAction();
				
				if (caminho != null && n == caminho.size()) {
					heroCtrl.finishedAction();
				}
				
				break;
			case "atk":
				if (animations.get(state).finishedLoop()) {
					heroCtrl.finishedAction();
				}
				break;
			default:
				break;
		}
	}
	
	public void receiveMovement(int chave) {
		heroMovement.move(chave, this);
	}
	
	public void receiveMovement(int[] target) {
		// heroMovement.move(target, this);
		askForPath(target);
		setState("idle");
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
		setState("atk");
		root.handleAttack(this, target);
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
