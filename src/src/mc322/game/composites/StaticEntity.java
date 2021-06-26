package mc322.game.composites;

import java.util.Hashtable;

import mc322.game.composites.dungeon.IDungeon;
import mc322.game.gfx.IAnimation;
import mc322.game.gfx.Sprite;

public abstract class StaticEntity implements IEntity {
	protected int x, y;
	protected IDungeon root;
	protected Sprite texture;
	protected Hashtable<String, IAnimation> animations = null; // FIX
	protected String currentAnim;
	protected boolean isAttacking = false;
	protected String type;
	protected boolean solid;
	
	public void setTexture(Sprite texture) {
		this.texture = texture;
	}
	
	public void connectAnimation(String name, IAnimation anim) {
		if (animations == null) {
			animations = new Hashtable<String, IAnimation>();
		}
		animations.put(name, anim);
		anim.flipSprites(false);
	}
	
	protected void setCurrentAnim(String name) {
		this.currentAnim = name;
	}
	
	protected void setAttacking(boolean state) {
		this.isAttacking = state;
	}
	
	public void setCallback(IDungeon root) {
		this.root = root;
	}
	
	protected void setType(String type) {
		this.type = type;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSolida(boolean solid) {
		this.solid = solid;
	}
	
	public String getType() {
		return this.type;
	}
	
	public boolean isSolid() {
		return this.solid;
	}
	
	public int[] getPosition() {
		return new int[] {x, y};
	}
	
	public void updateLife(int n) {
		return;
	}
	
	public int getDamage() {
		return 0;
	}
	
	public void interact(IEntity ent) {
		return;
	}
}
