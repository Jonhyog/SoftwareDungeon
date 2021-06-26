package mc322.game.gfx;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Scanner;

import mc322.game.util.loaders.ImageLoader;
import mc322.game.util.loaders.SpriteMapLoader;

public class Assets {
	
	private Hashtable<String, Sprite> spritesTable;
	private Hashtable<Integer, String> spritesIDs;
	
	private Scanner spriteMap;
	private BufferedImage spriteSheet;
	
	public Assets() {
		spritesTable = new Hashtable<String, Sprite>();
		spritesIDs = new Hashtable<Integer, String>();
	}
	
	public void setMap(String spriteMapPath) {
		this.spriteMap = SpriteMapLoader.loadSpriteMap(spriteMapPath);
	}
	
	public void setSpriteSheet(String spriteSheetPath) {
		this.spriteSheet = ImageLoader.loadImage(spriteSheetPath);
	}
	
	public void loadSprite() { 
		Sprite sprite;
		String[] line;
		String name;
		int xInicial, yInicial, xFinal = 0, yFinal = 0, id;
		boolean solid;
		
		while(spriteMap.hasNextLine()) {
			
			line = spriteMap.nextLine().split(" ");
			
			name = line[0];
			xInicial = Integer.parseInt(line[1]);
			yInicial = Integer.parseInt(line[2]);
			xFinal = Integer.parseInt(line[3]);
			yFinal = Integer.parseInt(line[4]);
			id = Integer.parseInt(line[5]);
			solid = Boolean.parseBoolean(line[6]);
			
			sprite = new Sprite();
			sprite.setName(name);
			sprite.setTexture(spriteSheet.getSubimage(xInicial, yInicial, xFinal - xInicial + 1, yFinal - yInicial + 1));
			sprite.setSizeX((xFinal - xInicial + 1) * 2);
			sprite.setSizeY((yFinal - yInicial + 1) * 2);
			sprite.setId(id);
			sprite.setSolid(solid);
			
			add(sprite);
			System.out.println("Loading Sprite - " + name + " id: " + id);
		}
	}
	
	public void add(Sprite sprite) {
		spritesTable.put(sprite.getName(), sprite);
		spritesIDs.put(sprite.getId(), sprite.getName());
	}
	
	public String getName(int id) {
		return spritesIDs.get(id);
	}
	
	public Sprite getSprite(String name) {
		return spritesTable.get(name);
	}
	
	public Sprite getSprite(int id) {
		return spritesTable.get(spritesIDs.get(id));
	}
}
