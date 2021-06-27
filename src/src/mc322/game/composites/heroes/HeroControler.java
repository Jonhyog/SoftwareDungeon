package mc322.game.composites.heroes;

import java.awt.Point;
import java.util.NoSuchElementException;

import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;

public class HeroControler {
	
	private IHero jogador;
	private boolean handleAttack = false;
	private boolean handleMovement = false;
	private boolean handleMouse = false;
	private KeyManager key;
	private MouseManager mouse;
	
	public HeroControler() {
		this.jogador = null;
	}
	
	public void connectJogador(IHero jogador) {
		this.jogador = jogador;
		jogador.connectControler(this);
	}
	
	public void connectKeyInputSource(KeyManager key) {
		this.key = key;
	}
	
	public void connectMouseInputSource(MouseManager mouse) {
		this.mouse = mouse;
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
					break;
				case 'm':
					toggleMovementHandler();
				default:
					break;
			}
		} catch (NoSuchElementException e) {
			// Nao eh necessario atualizar se nao houve input
			return;
		}
	}
	
	private int[] point2gridCoordinates(Point pos) {
		return new int[] {(pos.x / 32), (pos.y / 32) - 1};
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
			// Nao eh necessario atualizar se nao houve input
			return;
		}
	}
	
	public boolean isPlayerAlive() {
		return jogador.isAlive();
	}
	
	public void finishedAction() {
		jogador.passTurn();
	}
	
	public void controlPlayer(boolean turnoJogador) {
		
		if (turnoJogador) {			
			if (!handleMouse)
				handleKeys();
			else
				handleMouseEvents();
		}
		
		key.clearKeys();
		mouse.clearKeys();
	}
	
}
