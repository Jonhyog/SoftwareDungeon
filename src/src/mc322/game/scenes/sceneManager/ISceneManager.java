package mc322.game.scenes.sceneManager;

import mc322.game.displays.GameWindow;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.Scene;
import mc322.game.scenes.sceneManager.exceptions.SceneManagerException;

public interface ISceneManager {
	public void setDisplay(GameWindow main);
	public void setInputSource(KeyManager key, MouseManager mouse);
	public void addScene(String name, Scene cena);
	public void setCurrent(String name) throws SceneManagerException;
	public void update(KeyManager key);
}
