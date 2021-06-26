package mc322.game.composites;

import java.awt.Graphics2D;

public interface IEntity extends IEntityProperties, IEntityCompositing {
	public void render(Graphics2D g);
	public void update();
	public void updateLife(int n);
	public void interact(IEntity ent);
}
