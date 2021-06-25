package mc322.game.factory;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.game.composites.Entity;
import mc322.game.gfx.Assets;
import mc322.game.util.loaders.DungeonLoader;

public class DungeonBuilder {
	private int x, y, i, j;
	private String dungeonMap;
	private String[][] tiles;
	private ArrayList<String[]> entidades;
	private Scanner map;
	private int saidaX, saidaY;
	
	public void setDungeonMap(String dungeonPath) {
		this.dungeonMap = dungeonPath;
	}
	
	public int[] getSize() {
		return new int[] {x, y};
	}
	
	public void loadDungeon(Assets gameAssets) {
		String[] linha;
		this.map = DungeonLoader.loadDungeon(dungeonMap);
		
		linha = map.nextLine().split(" ");
		this.x = Integer.parseInt(linha[0]);
		this.y = Integer.parseInt(linha[1]);
		
		this.tiles = new String[y][x];
		this.i = 0;
		this.j = 0;
		
		linha = map.nextLine().split(" ");
		this.saidaX = Integer.parseInt(linha[0]);
		this.saidaY = Integer.parseInt(linha[1]);
		
		for (int a = 0; a < y; a++) {
			linha = map.nextLine().split(" ");
			for (int b = 0; b < x; b++) {
				tiles[a][b] = linha[b];
			}
		}
		
		this.entidades = new ArrayList<String[]>();
		while (map.hasNextLine()) {
			linha = map.nextLine().split(" ");
			entidades.add(linha);
		}	
		
		this.map.close();
	}
	
	public ArrayList<String[]> getEntidade() {
		return this.entidades;
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
