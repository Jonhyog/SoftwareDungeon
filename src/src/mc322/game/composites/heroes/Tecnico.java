package mc322.game.composites.heroes;

import java.awt.Graphics2D;
import java.util.NoSuchElementException;

import mc322.game.composites.Cell;
import mc322.game.composites.Entity;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public class Tecnico extends Hero implements IHero {
	public Tecnico(Sprite sprite) {
		super();
		this.texture = sprite;
		this.life = 10;
		this.attack = 2;
		this.range = 2;
	}
	
	@Override
	public void addEntity(Entity ent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeEntity(Entity ent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(texture.getTexture(), x * 32, y * 32, texture.getSizeX(), texture.getSizeY(), null);
	}

	@Override
	public void update(KeyManager key) {
		super.update(key);
	}
}
