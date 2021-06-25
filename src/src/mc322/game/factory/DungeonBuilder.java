package mc322.game.factory;

import java.util.Scanner;

import mc322.game.composites.Entity;
import mc322.game.gfx.Assets;
import mc322.game.util.loaders.DungeonLoader;

public class DungeonBuilder {
	private int x, y, i, j;
	private String dungeonMap;
	private String[][] tiles;
	private Scanner map;
	private String[] line;
	
	public void setDungeonMap(String dungeonPath) {
		this.dungeonMap = dungeonPath;
	}
	
	public int[] getSize() {
		return new int[] {x, y};
	}
	
	public void loadDungeonTiles(Assets gameAssets) {
		this.map = DungeonLoader.loadDungeon(dungeonMap);
		
		this.line = map.nextLine().split(" ");
		this.x = Integer.parseInt(line[0]);
		this.y = Integer.parseInt(line[1]);
		
		this.tiles = new String[y][x];
		this.i = 0;
		this.j = 0;
		
		int a = 0;
		while (map.hasNextLine()) {
			tiles[a] = map.nextLine().split(" ");
			a++;
		}
		
		this.map.close();
	}
	
	public int nextCell() {
		if (j >= x) {
			i++;
			j = 0;
		}
		
		int id = Integer.parseInt(tiles[i][j]);
		j++;
		return id;
		
	}
}
