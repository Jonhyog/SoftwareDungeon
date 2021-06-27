package mc322.game.composites.heroes;

public interface IHero {
	public void connectControler(HeroControler heroCtrl);
	public void receiveMovement(int chave);
	public void receiveMovement(int pos[]);
	public void attack(int[] target);
	public void passTurn();
	public boolean isAlive();
}
