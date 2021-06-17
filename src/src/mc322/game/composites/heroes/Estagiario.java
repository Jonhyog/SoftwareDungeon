package mc322.game.composites.heroes;

import java.awt.Graphics2D;
import java.util.NoSuchElementException;

import mc322.game.composites.Cell;
import mc322.game.composites.Entity;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public class Estagiario extends Hero {
	public Estagiario(Sprite sprite) {
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
		try {
			System.out.println("X: " + x + " Y: " + y);
			int chave = key.nextKey();
			heroMovement.move(chave, this);
		} catch (NoSuchElementException e) {
			// NAO HA NECESSIDADE DE ATUALIZAR SE
			// NAO HOUVE INPUT
			return;
		}
	}

	@Override
	public void move(int x, int y) {
		try {
			Cell fatherCell = (Cell) father;
			System.out.println("Attempting movement to " + x + " " + y);
			fatherCell.moveEntity(this, new int[] {x, y});
		} catch(Exception e){
			System.out.println("Failed To Move");
			return;
		}
		setPosition(x, y);
	}
}
