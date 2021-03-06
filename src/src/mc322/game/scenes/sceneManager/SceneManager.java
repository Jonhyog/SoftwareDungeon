package mc322.game.scenes.sceneManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Hashtable;

import mc322.game.displays.GameWindow;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.Scene;
import mc322.game.scenes.sceneManager.exceptions.SceneManagerException;
import mc322.game.scenes.sceneManager.exceptions.SceneNotFound;

public class SceneManager implements ISceneManager {
	private Scene currentScene;
	private String currentName;
	private GameWindow main;
	private Hashtable<String, Scene> scenes;
	private MouseManager mouse;
	private KeyManager key;
	
	public SceneManager() {
		currentScene = null;
		currentName = "null";
		scenes = new Hashtable<String, Scene>();
	}
	
	private void setCurrentScene(Scene scene) {
		this.currentScene = scene;
	}
	
	private void setCurrentSceneName(String name) {
		this.currentName = name;
	}
	
	public void setDisplay(GameWindow main) {
		this.main = main;
	}
	
	public void setInputSource(KeyManager key, MouseManager mouse) {
		this.key = key;
		this.mouse = mouse;
	}
	
	public void addScene(String name, Scene cena) {
		scenes.put(name, cena);
		cena.setCallback(this);
	}
	
	public void setCurrent(String name) throws SceneManagerException {
		System.out.println("Mudando Cena: " + currentName +" -> " + name);
		Scene novaScene = scenes.get(name);
		
		if (novaScene == null)
			throw new SceneNotFound();
		
		if (!novaScene.isInitialized())
			novaScene.initScene();
		
		removeCurrent();
		setCurrentScene(novaScene);
		setCurrentSceneName(name);
		conectScene2Display();
	}
	
	private void conectScene2Display() {
		if (main != null) {
			currentScene.connectInputSource(key, mouse);
			main.add((Component) currentScene, BorderLayout.PAGE_START);
			main.pack();
		}
	}
	
	private void removeCurrent() {
		if (currentScene != null) {
			main.remove((Component) currentScene);
			main.revalidate();
		}
	}
	
	public void update(KeyManager key) {
		currentScene.update();
	}
}
