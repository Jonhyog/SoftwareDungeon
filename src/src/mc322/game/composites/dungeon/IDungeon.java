package mc322.game.composites.dungeon;

import java.util.ArrayList;

import mc322.game.composites.Entity;
import mc322.game.util.IPathfinder;

public interface IDungeon extends Entity{
	public void connectPathfinder(IPathfinder pathFinder);
	public void moveEntity(Entity ent, int[] target);
	public int[] getPlayerPosition();
	public boolean isPlayerTurn();
	public void requestNextTurn();
	public void toggleUpdating(boolean value);
	public void handleAttack(Entity attacker, int[] target);
    public ArrayList<int[]> findPath(int[] source, int[] target);
}
