package mc322.game.composites;

import mc322.game.gfx.Sprite;

public abstract class StaticEntity implements Entity {
	protected int x, y;
	protected Entity father;
	protected Sprite texture;
	protected boolean solid;
	
	public void setTexture(Sprite texture) {
		this.texture = texture;
	}
	
	public void setCallback(Entity father) {
		this.father = father;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSolida(boolean solid) {
		this.solid = solid;
	}
	
	public boolean isSolid() {
		return this.solid;
	}
	
	public int[] getPosition() {
		return new int[] {x, y};
	}
}
