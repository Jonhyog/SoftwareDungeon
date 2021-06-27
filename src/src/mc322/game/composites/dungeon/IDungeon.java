package mc322.game.composites.dungeon;

import java.util.ArrayList;

import mc322.game.composites.IEntity;
import mc322.game.util.IPathfinder;

public interface IDungeon extends IEntity {
	public void connectPathfinder(IPathfinder pathFinder);
	public void moveEntity(IEntity ent, int[] target);
	public int[] getPlayerPosition();
	public void setSaida(int x, int y);
	public int[] getSaida();
	public IEntity getTile(int x, int y);
	public void setSize(int x, int y);
	public void setJogador(IEntity jogador);
	public boolean isPlayerTurn();
	public void requestNextTurn();
	public void toggleUpdating(boolean value);
	public void handleAttack(IEntity attacker, int[] target);
    public ArrayList<int[]> findPath(int[] source, int[] target);
}
