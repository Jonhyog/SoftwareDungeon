package mc322.game.factory;

import mc322.game.composites.dungeon.Dungeon;
import mc322.game.composites.dungeon.IDungeon;

public class DungeonBuilder {
	
	public static IDungeon buildDungeon(int[] size, int[] saida) {
		IDungeon dg = new Dungeon();
		dg.setSize(size[0], size[1]);
		dg.setSaida(saida[0], saida[1]);
		return dg;
	}
}
