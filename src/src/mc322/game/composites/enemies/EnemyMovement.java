package mc322.game.composites.enemies;

import mc322.game.composites.DynamicEntity;
import mc322.game.composites.Movement;

public class EnemyMovement extends Movement{

	@Override
	public void move(int chave, DynamicEntity entidade) {
		int[] pos = entidade.getPosition();
		
		if (chave == 0)
            entidade.move(pos[0], pos[1] - 1);
        if (chave == 1)
            entidade.move(pos[0], pos[1] + 1);
        if (chave == 2)
            entidade.move(pos[0] - 1, pos[1]);
        if (chave == 3)
            entidade.move(pos[0] + 1, pos[1]);
	}

	@Override
	public void move(int[] target, DynamicEntity entidade) {
		// TODO Auto-generated method stub
		
	}
}
