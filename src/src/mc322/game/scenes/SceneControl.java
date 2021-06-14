package mc322.game.scenes;

import mc322.game.composites.Entity;
import mc322.game.input.KeyManager;

public class SceneControl {
	
	private Entity entitys;
	
	public SceneControl() {
		entitys = null;
	}
	
	public void setEntitys(Entity entitys) {
		this.entitys = entitys;
	}
	
	public void sendCommand(KeyManager key) {
		// VALIDAR COMANDOS ANTES?
		entitys.update(key);
	}
}
