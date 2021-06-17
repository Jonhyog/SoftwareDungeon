package mc322.game.composites;

import mc322.game.composites.heroes.Hero;

public abstract class Movement {
    public Movement(){}

	public abstract void move(int chave, Hero entidade);
}