package mc322.game.util;

import java.util.ArrayList;
import mc322.game.composites.dungeon.Dungeon;

public interface IPathfinder {
	public ArrayList<int[]> findPath(int[] source, int[] target, Dungeon dg);
}
