package mc322.game.scenes;

import mc322.game.composites.IEntity;
import mc322.game.input.KeyManager;

public class SceneControl {
	
	private IEntity entitys;
	
	public SceneControl() {
		entitys = null;
	}
	
	public void setEntitys(IEntity entitys) {
		this.entitys = entitys;
	}
	
	public void sendCommand(KeyManager key) {
		// VALIDAR COMANDOS ANTES?
		entitys.update();
	}
}
