package mc322.game.client;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import mc322.game.displays.GameWindow;
import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.GameOverScene;
import mc322.game.scenes.GameScene;
// import mc322.game.input.MouseManager;
import mc322.game.scenes.MenuScene;
import mc322.game.scenes.Scene;
import mc322.game.scenes.SelectionScene;
import mc322.game.scenes.VictoryScene;
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.Clock;

public class Game {
	
	private String title;
	private int width, height;
	private boolean running;
	
	private GameWindow main;
	private KeyManager key;
	private MouseManager mouse;
	private Clock clock;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	private Assets gameAssets;
	
	private SceneManager sceneMan;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	public void start() {
		init();
		running = true;
		runLoop();
	}
	
	private void loadAssets() {
		gameAssets = new Assets();
		gameAssets.setSpriteSheet("res/textures/tiles.png");
		gameAssets.setMap("res/textures/tiles.txt");
		gameAssets.loadSprite();
	}
	
	private void createScenes() {
		Scene cena;
		sceneMan = new SceneManager();
		sceneMan.setDisplay(main);
		
		// MenuScene
		cena = new MenuScene(width, height);
		cena.connectAssets(gameAssets);
		sceneMan.addScene("Menu", cena);
		
		// GameScene
		cena = new GameScene(width, height);
		cena.connectAssets(gameAssets);
		sceneMan.addScene("Jogo", cena);
		
		// Selecao
		cena = new SelectionScene(width, height);
		cena.connectAssets(gameAssets);
		sceneMan.addScene("Selecao", cena);
		
		// Game Over
		cena = new GameOverScene(width, height);
		cena.connectAssets(gameAssets);
		sceneMan.addScene("GameOver", cena);
		
		// VictoryScene
		cena = new VictoryScene(width, height);
		cena.connectAssets(gameAssets);
		sceneMan.addScene("Victory", cena);
		
		sceneMan.setCurrent("Menu");
	}
	
	private void setCursor(BufferedImage img) {
		try {
			main.setCursor(toolkit.createCustomCursor(img , new Point(0, 0), "img"));
		} catch(Exception e){}
	}
	
	private void init() {
		System.out.println("Iniciando Jogo");
		
		main = new GameWindow(title, width, height);
		key = new KeyManager();
		mouse = new MouseManager();
		main.addKeyListener(key);
		main.addMouseListener(mouse);
		
		loadAssets();
		main.setIconImage(gameAssets.getSprite("bug1").getTexture());
		createScenes();
		setCursor(gameAssets.getSprite("cursor").getTexture());
		
		sceneMan.setInputSource(key, mouse);
	}
	
	private void runLoop() {
		clock = new Clock(60);
		clock.start();
		
		System.out.println("Iniciando loop");
		while (running) {
			if (clock.canRun())
				sceneMan.update(key);
		}
	}
}
