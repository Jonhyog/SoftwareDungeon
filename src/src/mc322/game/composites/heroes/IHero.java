package mc322.game.composites.heroes;

public interface IHero {
	public void receiveMovement(int chave);
	public void receiveMovement(int pos[]);
	public void attack(int[] target);
}
