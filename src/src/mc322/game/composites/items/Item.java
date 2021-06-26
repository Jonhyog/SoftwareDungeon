package mc322.game.composites.items;

import java.util.ArrayList;

import mc322.game.composites.Entity;
import mc322.game.composites.StaticEntity;
import mc322.game.gfx.Sprite;
import mc322.game.util.observer.*;

public abstract class Item extends StaticEntity implements ISubject, IObject {
	private ArrayList<IObject> observers;
	private Sprite altTexture;
	
	protected Item(Sprite texture) {
		setTexture(texture);
		setType("Item");
		observers = new ArrayList<IObject>();
	}
	
	public void setAlternativetexture(Sprite altTexture) {
		this.altTexture = altTexture;
	}
	
	protected void changeTexture() {
		Sprite temp = texture;
		texture = altTexture;
		altTexture = temp;
	}
	
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
	
	public void actionPerformed() {
		setSolida(!solid);
		changeTexture();
	}
	
	public void interact(Entity ent) {
		notifyListeners();
	}
}
