package mc322.game.composites;

import java.awt.Point;
import java.util.NoSuchElementException;

import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.heroes.IHero;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.GameScene;
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.GameStats;

public class GameControler {
	private IHero jogador;
	private IDungeon dg;
	private boolean handleAttack = false;
	private boolean handleMovement = false;
	private boolean handleMouse = false;
	public GameScene game;
	private SceneManager sceneMan = null;
	private KeyManager key;
	private MouseManager mouse;
	
	public void connectKeyInputSource(KeyManager key) {
		this.key = key;
	}
	
	public void connectMouseInputSource(MouseManager mouse) {
		this.mouse = mouse;
	}
	
	public void connectSceneManager(SceneManager sceneMan) {
		this.sceneMan = sceneMan;
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
		}
		
		int[] playerPos = dg.getPlayerPosition();
		int[] saida = dg.getSaida();
		
		if (playerPos[0] == saida[0] && playerPos[1] == saida[1]) {
			GameStats.increaseScore(100);
			game.nextLevel();
		}
		
		key.clearKeys();
		mouse.clearKeys();
	}
}
