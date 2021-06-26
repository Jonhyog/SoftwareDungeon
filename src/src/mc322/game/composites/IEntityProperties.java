package mc322.game.composites;

import mc322.game.composites.dungeon.IDungeon;
import mc322.game.gfx.Sprite;

public interface IEntityProperties {
	public void setPosition(int x, int y);
	public void setSolida(boolean solid);
	public void setTexture(Sprite sprite);
	public void setCallback(IDungeon root);
	public int[] getPosition();
	public int getDamage();
	public boolean isSolid();
	public String getType();
}
