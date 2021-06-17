package mc322.game.composites;

public abstract class DynamicEntity extends StaticEntity {
	protected int life, attack, range;
	public abstract void move(int x, int y);
}
