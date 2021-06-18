package mc322.game.composites;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import mc322.game.composites.dungeon.Dungeon;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;

public class Cell extends StaticEntity {
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
		setSolida(solida);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		for (Entity ent : entitys) {
			ent.setPosition(x, y);
		}
	}
	
	public void moveEntity(Entity ent, int[] target){
		Dungeon fatherDungeon = (Dungeon) father;
		fatherDungeon.moveEntity(ent, target);
		removeStack.add(ent); // Remocao nao pode ocorrer durante iteracao 
	}
	
	@Override
	public void addEntity(Entity ent) {
//		System.out.println("Antes: ");
//		for (Entity enti : entitys) {
//			System.out.println(enti);
//		}
		entitys.add(ent);
//		System.out.println("Depois: ");
//		for (Entity enti : entitys) {
//			System.out.println(enti);
//		}
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
		}
		
		if (!removeStack.empty()) {
			for (Entity ent : removeStack)
				removeEntity(ent);
			removeStack.clear();
		}
			
	}
}
