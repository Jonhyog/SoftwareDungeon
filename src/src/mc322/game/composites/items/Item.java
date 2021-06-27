package mc322.game.composites.items;

import java.awt.Graphics2D;
import java.util.ArrayList;

import mc322.game.composites.IEntity;
import mc322.game.composites.StaticEntity;
import mc322.game.gfx.Sprite;
import mc322.game.util.observer.*;

public abstract class Item extends StaticEntity implements ISubject, IObject {
	private ArrayList<IObject> observers;
	
	protected Item(Sprite texture) {
		setTexture(texture);
		setType("Item");
		setState("idle");
		observers = new ArrayList<IObject>();
	}
	
	public abstract void actionPerformed();
	
	public void addListener(IObject listener) {
		observers.add(listener);
	}
	
	public void removeListener(IObject listener) {
		observers.remove(listener);
	}
	
	public void notifyListeners() {
		for (IObject listener : observers) {
			listener.actionPerformed();
		}
	}
	
	public void render(Graphics2D g) {
		Sprite text = animations.get(state).getCurrentFrame();
		g.drawImage(text.getTexture(), x * 32, y * 32, text.getSizeX(), text.getSizeY(), null);
	}
	
	
	public void interact(IEntity ent) {
		notifyListeners();
	}
}
