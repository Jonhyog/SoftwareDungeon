package mc322.game.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mc322.game.composites.IEntity;
import mc322.game.composites.GameControler;
import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.heroes.IHero;
import mc322.game.composites.items.Item;
import mc322.game.factory.CellBuilder;
import mc322.game.factory.DungeonBuilder;
import mc322.game.factory.DungeonHandler;
import mc322.game.factory.EnemyBuilder;
import mc322.game.factory.HeroBuilder;
import mc322.game.factory.ItemBuilder;
import mc322.game.gfx.Assets;
import mc322.game.gfx.Sprite;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.AStar;
import mc322.game.util.GameStats;
import mc322.game.util.IPathfinder;

public class GameScene extends JPanel implements Scene {
	
	private static final long serialVersionUID = 1453255777619821491L;
	
	private SceneManager sceneMan;
	private int width, height;
	private SceneControl sceneCtrl;
	private IDungeon dg;
	private GameControler gameCtrl;
	private Assets gameAssets;
	private int currentLevel = 0;
	private boolean initialized = false;
	private String[] levels = {
			"res/dungeons/dungeon.csv",
			"res/dungeons/dungeon2.csv",
			"res/dungeons/dungeon3.csv",
			"res/dungeons/dungeon4.csv"};
	private JLabel score;
	
	public GameScene(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		System.out.println("GameScene: loading");
		super.setSize(width, height);
		super.setLayout(null);
		super.setDoubleBuffered(true);
		super.setOpaque(false);
		super.setMaximumSize(new Dimension(width, height));
		super.setPreferredSize(new Dimension(width, height + 80));
		super.setMinimumSize(new Dimension(width, height + 160));
		super.setFocusable(false);
		
		this.sceneCtrl = new SceneControl();
		
		this.gameCtrl = new GameControler();
		gameCtrl.game = this;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public void connectInputSource(KeyManager key, MouseManager mouse) {
		gameCtrl.connectKeyInputSource(key);
		gameCtrl.connectMouseInputSource(mouse);
	}
	
	public void connectAssets(Assets gameAssets) {
		this.gameAssets = gameAssets;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
		
		graficos.setColor(new Color(0, 0, 0));
		graficos.fillRect(0, 0, super.getWidth(), super.getHeight());
		dg.render(graficos);
		renderHUD(graficos);
	}
	
	@Override
	public void update() {
		dg.update();
		gameCtrl.update();
		render();
	}
	
	private void renderHUD(Graphics2D g) {
		int life = GameStats.getPlayerLife();
		int nCoracoes = life / 2;
		Sprite heart = gameAssets.getSprite("coracao");
		Sprite player = gameAssets.getSprite(GameStats.getHeroClass() + "1");
		
		g.setColor(new Color(255, 255, 255));
		g.drawLine(0, 640, super.getWidth(), 640);
		g.drawImage(player.getTexture(), 24, 650, player.getSizeX(), player.getSizeY(), null);
		for (int i = 0; i < nCoracoes; i++) {
			g.drawImage(heart.getTexture(),
					i * heart.getSizeX() + 64, 650,
					heart.getSizeX(), heart.getSizeY(), null);
		}
		
		score.setText("Points: " + Integer.toString(GameStats.getScore()));
	}
	
	@Override
	public void render() {
		repaint();
	}

	@Override
	public void setCallback(SceneManager sceneMan) {
		this.sceneMan = sceneMan;
		this.gameCtrl.connectSceneManager(sceneMan);
	}
	
	private IDungeon initDungeon(DungeonHandler dungeonHandler, Assets gameAssets) {
		int[] size = dungeonHandler.getSize();
		this.dg = DungeonBuilder.buildDungeon(dungeonHandler.getSize(), dungeonHandler.getSaida());
		System.out.println("\tDungeon (" + size[0] + ", " + size[1] + "): Loading");
		
		IEntity cell;
		for (int i = 0; i < size[1]; i++) {
			for (int j = 0; j < size[0]; j++) {
				int id = dungeonHandler.nextCell();
				String name = gameAssets.getName(id);
				cell = CellBuilder.buildCell(gameAssets, id);
				cell.setTexture(gameAssets.getSprite(name));
				cell.setSolida(gameAssets.getSprite(name).isSolid());
				cell.setPosition(j, i);
				dg.addEntity(cell);
			}
		}
		
		return dg;
	}
	
	private void addEntities2Dungeon(DungeonHandler dungeonHandler, IDungeon dg, Assets gameAssets) {
		IEntity cell;
		ArrayList<String[]> entidades = dungeonHandler.getEntidade();
		
		for (String[] entidade : entidades) {
			IEntity ent = null;
			String name = entidade[0];
			int x = Integer.parseInt(entidade[1]);
			int y = Integer.parseInt(entidade[2]);
			
			cell = dg.getTile(x, y);
			
			if (name.equals("hero")) {
				System.out.println("Criando Jogador: " + GameStats.getHeroClass());
				ent = HeroBuilder.buildHero(gameAssets, GameStats.getHeroClass());
				dg.setJogador(ent);
				gameCtrl.setJogador((IHero) ent);
			}
			
			if (HeroBuilder.isHero(name)) {
				System.out.println("Criando Jogador");
				ent = HeroBuilder.buildHero(gameAssets, name);
				dg.setJogador(ent);
				gameCtrl.setJogador((IHero) ent); // FIX
			} else if (EnemyBuilder.isEnemy(name)) {
				ent = EnemyBuilder.buildEnemy(gameAssets, name);
			} else if (ItemBuilder.isItem(name)){
				Item item = ItemBuilder.buildItem(gameAssets, name);
				if (name.equals("portaTrancada")) {
					Item chave = ItemBuilder.buildItem(gameAssets, entidade[3]);
					chave.setPosition(Integer.parseInt(entidade[4]), Integer.parseInt(entidade[5]));
					dg.getTile(Integer.parseInt(entidade[4]), Integer.parseInt(entidade[5])).addEntity(chave);
					chave.addListener(item);
				}
				ent = item;
			}
			
			if (ent != null) {
				ent.setPosition(x, y);
				cell.addEntity(ent);
			}
		}
	}
	
	public void nextLevel() {
		currentLevel++;
		if (currentLevel == levels.length) {
			sceneMan.setCurrent("GameOver");
			return;
		}
		loadLevel();
	}
	
	private void loadLevel() {
		DungeonHandler dungeonHandler = new DungeonHandler();
		dungeonHandler.setDungeonMap(levels[currentLevel]);
		dungeonHandler.setSep(";");
		dungeonHandler.loadDungeon(gameAssets);
		
		// Inicia Tiles
		this.dg = initDungeon(dungeonHandler, gameAssets);
		
		// Adiciona Entidades
		addEntities2Dungeon(dungeonHandler, dg, gameAssets);
		
		// Conecta PathFinder
		IPathfinder pathFinder = new AStar();
		dg.connectPathfinder(pathFinder);
		gameCtrl.setDungeon(dg);
	}
	
	
	@Override
	public void initScene() {
		loadLevel();
		
		// Display da Pontuacao
		score = new JLabel("Points: " + Integer.toString(GameStats.getScore()));
		score.setBounds(64, 670, 200, 30);
		score.setForeground(new Color (255, 255, 255));
		score.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		add(score);
				
		System.out.println("\tCaverna: ok");
		System.out.println("GameScene: ok");
		
		this.initialized = true;
	}
	
}
