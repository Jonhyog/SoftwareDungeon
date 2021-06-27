package mc322.game.composites.items;

import mc322.game.composites.IEntity;
import mc322.game.gfx.Sprite;

public class Door extends Item {

	public Door(Sprite texture) {
		super(texture);
		setType("Door");
	}

	@Override
	public void addEntity(IEntity ent) {
		return;
	}

	@Override
	public void removeEntity(IEntity ent) {
		return;
	}

//	@Override
//	public void render(Graphics2D g) {
//		g.drawImage(texture.getTexture(), x * 32, y * 32, texture.getSizeX(), texture.getSizeY(), null);
//	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public void actionPerformed() {	
		setSolida(!solid);
		setState("aberta");
	}
}
