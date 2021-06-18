package mc322.game.scenes;

import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;
import mc322.game.scenes.SceneManager.SceneManager;

public interface Scene {
	public void setCallback(SceneManager sceneMan);
	public void initScene(Assets gameAssets);
	public void update(KeyManager key);
	public void render();
}
