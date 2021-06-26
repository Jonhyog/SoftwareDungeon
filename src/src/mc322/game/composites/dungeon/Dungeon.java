package mc322.game.composites.dungeon;

import java.awt.Graphics2D;
import java.util.ArrayList;

import mc322.game.composites.Cell;
import mc322.game.composites.IEntity;
import mc322.game.composites.StaticEntity;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.composites.dungeon.exceptions.InvalidMovement;
import mc322.game.composites.dungeon.exceptions.InvalidPosition;
import mc322.game.util.IPathfinder;

public class Dungeon extends StaticEntity implements IDungeon {
	private int i, j;
	private IEntity[][] tiles;
	private IEntity jogador;
	private boolean turnoJogador = true;
	private boolean toggleTurnChange = false;
	public boolean entitiesUpdating = false;
	private IPathfinder pathFinder;
	private int[] saida;
	
	public Dungeon () {
		tiles = null;
		this.i = 0;
		this.j = 0;
		setType("Dungeon");
	}
	
	public void setSaida(int x, int y) {
		this.saida = new int[] {x, y};
	}
	
	public void setSize(int x, int y) {
		this.x = x; // Posicao nao eh necessaria na caverna ent podemos usar x e y
		this.y = y; // Mas x e y se referem a posicao da entidade
		this.tiles = new IEntity[y][x];
	}
	
	public void setJogador(IEntity jogador) {
		this.jogador = jogador;
	}
	
	public int[] getPlayerPosition() {
		return this.jogador.getPosition();
	}
	
	public boolean isPlayerTurn() {
		return turnoJogador;
	}
	
	private void toggleTurnChange() {
		this.toggleTurnChange = !toggleTurnChange;
	}
	
	public void toggleUpdating(boolean value) {
		this.entitiesUpdating = value;
	}
	
	private void nextTurn() {
		if (this.toggleTurnChange) {
			System.out.println("Trocando turno");
			this.turnoJogador = !turnoJogador;
			toggleTurnChange();	
		}
	}
	
	public void requestNextTurn() {
		toggleTurnChange();
	}
	
	public int[] getSize() {
		return new int[] {x, y};
	}
	
	public IEntity getTile(int x, int y) throws DungeonException {
		if (!isValidPosition(x, y))
			throw new InvalidPosition("Posicao (" + x + ", " + y + ") eh invalida");
		
		return tiles != null ? tiles[y][x] : null;
	}
	
	public void connectPathfinder(IPathfinder pathFinder) {
		this.pathFinder = pathFinder;
	}
	
	@Override
	public ArrayList<int[]> findPath(int[] source, int[] target) {
		return pathFinder.findPath(source, target, this);
	}
	
	public void moveEntity(IEntity ent, int[] target) throws DungeonException {
		int[] source = ent.getPosition();
		// FIX-ME: CAST NAO DEVE SER FEITO - add QueueRemoval a Entity
		Cell sourceTile = (Cell) getTile(source[0], source[1]);
		IEntity targetTile = getTile(target[0], target[1]);
		
		if (targetTile.isSolid())
			throw new InvalidMovement("Movimento para tile solido nao eh valido");
		
		sourceTile.queueRemoval(ent);
		targetTile.addEntity(ent);
		ent.setPosition(target[0], target[1]);
		this.entitiesUpdating = true;
	}
	
	public boolean isValidPosition(int a, int b) {
		if ((a >= 0 && a < this.x) && (b >= 0 && b < this.y)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void addEntity(IEntity ent) {
		if (i >= y)
			return;
		tiles[i][j] = ent;
		ent.setCallback(this);
		j++;
		if (j >= x) {
			j = 0;
			i++;
		}
	}

	@Override
	public void removeEntity(IEntity ent) { // FIX-ME: CAST NAO DEVE SER FEITO
		int pos[] = ent.getPosition();
		Cell cell = (Cell) getTile(pos[0], pos[1]);
		cell.queueRemoval(ent);
	}

	@Override
	public void setCallback(IDungeon root) {
		this.root = root;
	}

	@Override
	public void render(Graphics2D g) {
		for (int posY = 0; posY < y; posY++) {
			for (int posX = 0; posX < x; posX++) {
				tiles[posY][posX].render(g);
			}
		}
	}

	@Override
	public void update() {
		toggleUpdating(false);
		for (int posY = 0; posY < y; posY++) {
			for (int posX = 0; posX < x; posX++) {
				getTile(posX, posY).update();
			}
		}
		
		if (!isPlayerTurn() && !this.entitiesUpdating) {
			requestNextTurn();
		}
		
		nextTurn();
	}
	
	public void handleAttack(IEntity attacker, int[] target) {
		IEntity targetTile = getTile(target[0], target[1]);
		
//		if (targetTile.isSolid())
//			throw new InvalidMovement("Movimento para tile solido nao eh valido");
		
		targetTile.updateLife(-attacker.getDamage());
		this.entitiesUpdating = true;
	}
}
