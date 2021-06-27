package mc322.game.composites;

import java.util.ArrayList;

public abstract class DynamicEntity extends StaticEntity {
	protected int life, attack, range;
	protected int ticks, n, minimunDistance;
	protected ArrayList<int[]> caminho = null;

	public abstract void move(int x, int y);
	
	public void updateLife(int n) {
		System.out.println("life: " + life + " -> " + (life + n));
		this.life += n;
	}
	
	public int getDamage() {
		return this.attack;
	}
	
	protected boolean isAlive() {
		return life <= 0 ? false : true;
	}
	
	protected void resetPath() {
		this.caminho = null;
		this.n = 0;
	}
	
	protected boolean knowsPath() {
		if (caminho == null)
			return false;
		if (n >= caminho.size() - minimunDistance)
			return false;
		return true;
	}
	
	protected boolean isInRange() {
		return n < range;
	}
	
	protected boolean isReachable() {
		return range >= caminho.size();
	}
	
	protected void nextPosition() {
		if (ticks % 40 != 0) {
			return;
		}
		
		ticks = 0;
		int target[];
		
		target = caminho.get(n);
		move(target[0], target[1]);
		return;
	}
	
	protected void askForPath(int pos[]) {
		this.caminho = root.findPath(getPosition(), pos);
		if (caminho == null || n >= range)
			return;
		root.toggleUpdating(true);
	}
}
