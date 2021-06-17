package mc322.game.composites.heroes;

import mc322.game.composites.Movement;

public class HeroMovement extends Movement {
	public void move(int chave, Hero entidade) {
		int[] pos = entidade.getPosition();
		
        if (chave == 'w')
            entidade.move(pos[0], pos[1] - 1);
        if (chave == 's')
            entidade.move(pos[0], pos[1] + 1);
        if (chave == 'a')
            entidade.move(pos[0] - 1, pos[1]);
        if (chave == 'd')
            entidade.move(pos[0] + 1, pos[1]);
	}
}
