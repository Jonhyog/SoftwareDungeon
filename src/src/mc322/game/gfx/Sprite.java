package mc322.game.gfx;

import java.awt.image.BufferedImage;

public class Sprite {
	private String name;
	private BufferedImage texture;
	private int id, sizeX, sizeY;
	
	public Sprite() {
		this.texture = null;
		this.sizeX = 0;
		this.sizeY = 0;
	}
	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	
	public BufferedImage getTexture() {
		return this.texture;
	}
	
	public int getSizeX() {
		return this.sizeX;
	}
	
	public int getSizeY() {
		return this.sizeY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
