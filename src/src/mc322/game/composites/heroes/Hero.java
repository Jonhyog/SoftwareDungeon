package mc322.game.composites.heroes;

import java.awt.Graphics2D;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;
import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public abstract class Hero extends DynamicEntity {
	protected Movement heroMovement;
	
	protected Hero() {
		setSolida(false);
	}
	
	public void setMovement(Movement heroMovement) {
		this.heroMovement = heroMovement;
	}
	
	public void render(Graphics2D g) {
		Sprite text = animation.getCurrentFrame();
		g.drawImage(text.getTexture(), x * 32, y * 32, text.getSizeX(), text.getSizeY(), null);
	}
	
	private void passTurn() {
		IDungeon fatherCell = (IDungeon) father;
		fatherCell.requestNextTurn();
		resetPath();
	}
	
	public void update(KeyManager key) {
		IDungeon fatherCell = (IDungeon) father; //FIX-ME
		animation.tick();
		
		if (!fatherCell.isPlayerTurn()) {
			resetPath();
			return;
		}
		
		if (knowsPath() && isInRange()) {
			ticks++;
			fatherCell.toggleUpdating(true);
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
			System.out.println("Nao alcanço essa posicão");
		}
	}
	
	public void attack(int[] target) {
		System.out.println("Atacando X: " + target[0] + " Y: " + target[1]);
		IDungeon fatherCell = (IDungeon) father;
		fatherCell.handleAttack(this, target);
		passTurn();
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
			IDungeon fatherCell = (IDungeon) father;
			fatherCell.moveEntity(this, new int[] {x, y});
			setPosition(x, y);
			lookInDirection(lastPos[0], x);
			n++;
		} catch(DungeonException e){
			System.out.println(e.getMessage());
			return;
		}
	}
}
