package mc322.game.composites;

public abstract class Movement {
    public Movement(){}

	public abstract void move(int chave, DynamicEntity entidade);
	public abstract void move(int[] target, DynamicEntity entidade);
}