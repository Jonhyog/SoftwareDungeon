package mc322.game.composites;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import mc322.game.gfx.Sprite;

public class Cell extends StaticEntity {
	private ArrayList<IEntity> entitys;
	private Stack<IEntity> removeStack;
	
	public Cell() {
		this.entitys = new ArrayList<IEntity>();
		this.removeStack = new Stack<IEntity>();
		setType("Cell");
		setCurrentAnim("idle");
	}
	
	public Cell(Sprite texture, boolean solida) {
		this.entitys = new ArrayList<IEntity>();
		this.removeStack = new Stack<IEntity>();
		this.texture = texture;
		setSolida(solida);
		setType("Cell");
		setCurrentAnim("idle");
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		for (IEntity ent : entitys) {
			ent.setPosition(x, y);
		}
	}
	
	public boolean isSolid() {
		for (IEntity ent : entitys) {
			if (ent.isSolid())
				return ent.isSolid();
		}
		return this.solid;
	}
	
	public void moveEntity(IEntity ent, int[] target){
		root.moveEntity(ent, target);
		removeStack.add(ent); // Remocao nao pode ocorrer durante iteracao 
	}
	
	private void queueRemoval(IEntity ent) {
		removeStack.add(ent);
	}
	
	@Override
	public void addEntity(IEntity ent) {
		for (IEntity entidade : entitys) {
			entidade.interact(ent);
		}
		entitys.add(ent);
		ent.setCallback(this.root);
	}
	
	
	@Override
	public void removeEntity(IEntity ent) {
		queueRemoval(ent);
	}
	
	private void remove(IEntity ent) {
		entitys.remove(ent);		
	}

	@Override
	public void render(Graphics2D g) {
		Sprite text = animations.get(currentAnim).getCurrentFrame();
		g.drawImage(text.getTexture(), x * 32, y * 32, text.getSizeX(), text.getSizeY(), null);
		
		for (IEntity ent : entitys) {
			ent.render(g);
		}	
	}

	@Override
	public void update() {
		if (!removeStack.empty()) {
			for (IEntity ent : removeStack) {
				remove(ent);
			}
			removeStack.clear();
		}
		
		for (IEntity ent : entitys) {
			ent.update();
		}		
	}
	
	public void updateLife(int n) {
		for (IEntity ent : entitys) {
			ent.updateLife(n);
		}
	}
}
