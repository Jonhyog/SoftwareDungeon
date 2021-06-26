package mc322.game.composites.items;

import java.awt.Graphics2D;

import mc322.game.composites.Entity;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public class Coffe extends Item {
	
	private static int healLifePoint = 3;
	
	public Coffe(Sprite texture) {
		super(texture);
		setType("Coffe");
	}

	@Override
	public void addEntity(Entity ent) {
		return;
	}

	@Override
	public void removeEntity(Entity ent) {
		return;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(texture.getTexture(), x * 32, y * 32, texture.getSizeX(), texture.getSizeY(), null);
	}

	@Override
	public void update(KeyManager key) {
		// TODO Auto-generated method stub
	}
	
	public void interact(Entity ent) {
		if (ent.getType().equals("Hero")) {			
			ent.updateLife(Coffe.healLifePoint);
			father.removeEntity(this);
		}
	} 
}
