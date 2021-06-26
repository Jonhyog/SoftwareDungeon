package mc322.game.composites.items;

import mc322.game.composites.IEntity;
import mc322.game.gfx.Sprite;

public class Coffe extends Item {
	
	private static int healLifePoint = 3;
	
	public Coffe(Sprite texture) {
		super(texture);
		setType("Coffe");
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
////		g.drawImage(texture.getTexture(), x * 32, y * 32, texture.getSizeX(), texture.getSizeY(), null);
//		Sprite text = animations.get(currentAnim).getCurrentFrame();
//		g.drawImage(text.getTexture(), x * 32, y * 32, text.getSizeX(), text.getSizeY(), null);
//	}

	@Override
	public void update() {
		animations.get(currentAnim).tick();
	}
	
	public void interact(IEntity ent) {
		if (ent.getType().equals("Hero")) {			
			ent.updateLife(Coffe.healLifePoint);
			root.removeEntity(this);
		}
	}

	@Override
	public void actionPerformed() {
		return;
	} 
}
