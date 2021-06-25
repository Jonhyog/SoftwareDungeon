package mc322.game.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import mc322.game.composites.Entity;
import mc322.game.composites.GameControler;
import mc322.game.composites.dungeon.Dungeon;
import mc322.game.composites.heroes.IHero;
import mc322.game.factory.CellBuilder;
import mc322.game.factory.DungeonBuilder;
import mc322.game.factory.EnemyBuilder;
import mc322.game.factory.HeroBuilder;
import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.AStar;
import mc322.game.util.IPathfinder;

public class GameScene extends JPanel implements Scene {
	
	private static final long serialVersionUID = 1453255777619821491L;
	
	private SceneManager sceneMan;
	private int width, height;
	private SceneControl sceneCtrl;
	private Dungeon dg;
	private GameControler gameCtrl;
	
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
		super.setMinimumSize(new Dimension(width, height));
		super.setFocusable(false);
		
		this.sceneCtrl = new SceneControl();
	}
	
	public void connectInputSource(KeyManager key, MouseManager mouse) {
		gameCtrl.connectKeyInputSource(key);
		gameCtrl.connectMouseInputSource(mouse);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
		
		graficos.setColor(new Color(0, 0, 0));
		graficos.fillRect(0, 0, width, height);
		dg.render(graficos);
	}
	
	@Override
	public void update(KeyManager key) {
		dg.update(key);
		//FIX
		gameCtrl.connectKeyInputSource(key);
		gameCtrl.update();
		render();
	}

	@Override
	public void render() {
		repaint();
	}

	@Override
	public void setCallback(SceneManager sceneMan) {
		this.sceneMan = sceneMan;
	}

	@Override
	public void initScene(Assets gameAssets) {
		DungeonBuilder builder = new DungeonBuilder();
//		builder.setDungeonMap("res/dungeons/labirinto.dg");
		builder.setDungeonMap("res/dungeons/dungeon2.dg");
		builder.loadDungeonTiles(gameAssets);
		
		this.gameCtrl = new GameControler();
		
		int[] size = builder.getSize();
		this.dg = new Dungeon();
		dg.setSize(size[0], size[1]);
		System.out.println("\tDungeon (" + size[0] + ", " + size[1] + "): Loading");
		for (int i = 0; i < size[1]; i++) {
			for (int j = 0; j < size[0]; j++) {
				// NOVA CONSTRUCAO DA CELULA
				int id = builder.nextCell();
				String name = gameAssets.getName(id);
				Entity ent = null, cell;
				cell = CellBuilder.buildCell(gameAssets, id);
				if (HeroBuilder.isHero(name)) {
					ent = HeroBuilder.buildHero(gameAssets, name);
					cell.setTexture(gameAssets.getSprite(CellBuilder.getDefaultTile()));
					cell.setSolida(false);
					dg.setJogador(ent);
					gameCtrl.setJogador((IHero) ent); // FIX
				} else if (EnemyBuilder.isEnemy(name)) {
					ent = EnemyBuilder.buildEnemy(gameAssets, name);
					cell.setTexture(gameAssets.getSprite(CellBuilder.getDefaultTile()));
					cell.setSolida(false);
				} else {
					cell.setTexture(gameAssets.getSprite(name));
					cell.setSolida(gameAssets.getSprite(name).isSolid());
				}
				
				dg.addEntity(cell);
				if (ent != null) {
					cell.addEntity(ent);
				}
				cell.setPosition(j, i);
				// FIM DA NOVA CONSTRUCAO
			}
		}
		
		IPathfinder pathFinder = new AStar();
		dg.connectPathfinder(pathFinder);
		gameCtrl.setDungeon(dg);
		System.out.println("\tCaverna: ok");
		System.out.println("GameScene: ok");
	
	}
	
	
}
