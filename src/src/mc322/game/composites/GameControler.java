package mc322.game.composites;

import java.awt.Point;
import java.util.NoSuchElementException;

import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.heroes.IHero;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;

public class GameControler {
	private IHero jogador;
	private IDungeon dg;
	// private ArrayList<Entity> inimigos;
	private boolean handleAttack = false;
	private boolean handleMovement = false;
	private boolean handleMouse = false;
	private KeyManager key;
	private MouseManager mouse;
	
	public void connectKeyInputSource(KeyManager key) {
		this.key = key;
	}
	
	public void connectMouseInputSource(MouseManager mouse) {
		this.mouse = mouse;
	}
	
	public void setJogador(IHero jogador) {
		this.jogador = jogador;
	}
	
	public void setDungeon(IDungeon dg) {
		this.dg = dg;
	}
	
	private void toggleMouseHandler() {
		this.handleMouse = !handleMouse;
	}
	private void toggleAttackHandler() {
		this.handleAttack = !handleAttack;
		toggleMouseHandler();
	}
	
	private void toggleMovementHandler() {
		this.handleMovement = !handleMovement;
		toggleMouseHandler();
	}
	
	private void handleKeys() {
		try {
			int chave = key.nextKey();
			switch (chave) {
				case 'w':
				case 's':
				case 'a':
				case 'd':
					jogador.receiveMovement(chave);
					break;
				case 'b':
					toggleAttackHandler();
//					int pos[] = jogador.getPosition();
//					jogador.attack(new int[] {pos[0], pos[1] + 1});
					break;
				case 'm':
					toggleMovementHandler();
				default:
					break;
			}
		} catch (NoSuchElementException e) {
			// NAO HA NECESSIDADE DE ATUALIZAR SENAO HOUVE INPUT
			return;
		}
	}
	
	private void handleMouseEvents() {
		try {
			Point pos = mouse.nextPoint();
			int[] convertedPos = point2gridCoordinates(pos);
			
			if (handleMovement) {
				jogador.receiveMovement(convertedPos);
				toggleMovementHandler();				
			} else if (handleAttack) {
				jogador.attack(convertedPos);
				toggleAttackHandler();
			}
		} catch (NoSuchElementException e) {
			// NAO HA NECESSIDADE DE ATUALIZAR SENAO HOUVE INPUT
			return;
		}
	}
	
	private int[] point2gridCoordinates(Point pos) {
		return new int[] {(pos.x / 32), (pos.y / 32) - 1};
	}
	
	public void update() {
		if (dg.isPlayerTurn()) {
			if (!handleMouse)
				handleKeys();
			else
				handleMouseEvents();
//			nextTurn();
		}
		key.clearKeys();
		mouse.clearKeys();
	}
}
