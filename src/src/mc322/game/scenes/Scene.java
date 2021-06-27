package mc322.game.scenes;

import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.sceneManager.SceneManager;

public interface Scene {
	public void setCallback(SceneManager sceneMan);
	public void connectAssets(Assets gameAssets);
	public void initScene(Assets gameAssets);
	public void connectInputSource(KeyManager key, MouseManager mouse);
	public void update();
	public void render();
}
