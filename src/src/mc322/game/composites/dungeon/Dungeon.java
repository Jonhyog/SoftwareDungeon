package mc322.game.composites.dungeon;

import java.awt.Graphics2D;
import java.util.ArrayList;

import mc322.game.composites.Cell;
import mc322.game.composites.Entity;
import mc322.game.composites.StaticEntity;
import mc322.game.composites.dungeon.exceptions.DungeonException;
import mc322.game.composites.dungeon.exceptions.InvalidMovement;
import mc322.game.composites.dungeon.exceptions.InvalidPosition;
import mc322.game.input.KeyManager;
import mc322.game.util.IPathfinder;

public class Dungeon extends StaticEntity implements IDungeon {
	private int i, j;
	private Entity[][] tiles;
	private Entity jogador;
	private boolean turnoJogador = true;
	private boolean toggleTurnChange = false;
	public boolean entitiesUpdating = false;
	private IPathfinder pathFinder;
	
	public Dungeon () {
		tiles = null;
		this.i = 0;
		this.j = 0;
	}
	
	public void setSize(int x, int y) {
		this.x = x; // Posicao nao eh necessaria na caverna ent podemos usar x e y
		this.y = y; // Mas x e y se referem a posicao da entidade
		this.tiles = new Entity[y][x];
	}
	
	public void setJogador(Entity jogador) {
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
	
	public Entity getTile(int x, int y) throws DungeonException {
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
	
	public void moveEntity(Entity ent, int[] target) throws DungeonException {
		int[] source = ent.getPosition();
		// FIX-ME: CAST NAO DEVE SER FEITO - add QueueRemoval a Entity
		Cell sourceTile = (Cell) getTile(source[0], source[1]);
		Entity targetTile = getTile(target[0], target[1]);
		
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
	public void addEntity(Entity ent) {
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
	public void removeEntity(Entity ent) { // FIX-ME: CAST NAO DEVE SER FEITO
		int pos[] = ent.getPosition();
		Cell cell = (Cell) getTile(pos[0], pos[1]);
		cell.queueRemoval(ent);
	}

	@Override
	public void setCallback(Entity father) {
		this.father = father;
		// NO JOGO TALVEZ NAO SEJA NECESSARIO
		// SUBSTITUIR POR STUB?
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
	public void update(KeyManager key) {
		toggleUpdating(false);
		for (int posY = 0; posY < y; posY++) {
			for (int posX = 0; posX < x; posX++) {
				getTile(posX, posY).update(key);
			}
		}
		
		if (!isPlayerTurn() && !this.entitiesUpdating) {
			requestNextTurn();
		}
		
		nextTurn();
	}
	
	public void handleAttack(Entity attacker, int[] target) {
		Entity targetTile = getTile(target[0], target[1]);
		
//		if (targetTile.isSolid())
//			throw new InvalidMovement("Movimento para tile solido nao eh valido");
		
		targetTile.updateLife(-attacker.getDamage());
		this.entitiesUpdating = true;
	}
}
