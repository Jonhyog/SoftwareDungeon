package mc322.game.composites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public class Cell extends StaticEntity {
	private boolean solida;
	private ArrayList<Entity> entitys;
	private Stack<Entity> removeStack;
	
	public Cell() {
		this.entitys = new ArrayList<Entity>();
		this.removeStack = new Stack<Entity>();
	}
	
	public Cell(Sprite texture, boolean solida) {
		this.entitys = new ArrayList<Entity>();
		this.removeStack = new Stack<Entity>();
		this.texture = texture;
		this.solida = solida;
	}
	
	public void setSolida(boolean solida) {
		this.solida = solida;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		for (Entity ent : entitys) {
			ent.setPosition(x, y);
		}
	}
	
	public void moveEntity(Entity ent, int[] target) {
		try {
			Dungeon fatherDungeon = (Dungeon) father;
			fatherDungeon.moveEntity(ent, target);
		} catch(Exception e){
			System.out.println("Failed To Move 2");
			return;
		}
		removeStack.add(ent); // Remocao nao pode ocorrer durante iteracao 
	}
	
	@Override
	public void addEntity(Entity ent) {
		entitys.add(ent);
		ent.setCallback(this);
	}

	@Override
	public void removeEntity(Entity ent) {
		entitys.remove(ent);
		// FIX-ME: TRATAR ERRO CASO TENTE REMOVER ENT N PRESENTE
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(texture.getTexture(), x * 32, y * 32, texture.getSizeX(), texture.getSizeY(), null);
		for (Entity ent : entitys) {
			ent.render(g);
		}	
	}

	@Override
	public void update(KeyManager key) {
		for (Entity ent : entitys) {
			ent.update(key);
//			System.out.println(ent.getPosition()[0] + " " + ent.getPosition()[1]);
		}
		
		if (!removeStack.empty())
			for (Entity ent : removeStack)
				removeEntity(ent);
	}
}
