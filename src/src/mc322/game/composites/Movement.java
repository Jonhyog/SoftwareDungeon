package mc322.game.composites;

import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import mc322.game.input.KeyManager;

public class Movement {
    public Movement(){

    }

	public void movement(int chave, Entity entidade) {
        if (chave == 'w')
            entidade.move(x, y - 1);
        if (chave == 's')
            entidade.move(x, y + 1);
        if (chave == 'a')
            entidade.move(x - 1, y);
        if (chave == 'd')
            entidade.move(x + 1, y);
	}
}