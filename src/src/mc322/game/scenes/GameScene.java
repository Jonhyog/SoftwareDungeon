package mc322.game.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import mc322.game.composites.Entity;
import mc322.game.composites.GameControler;
import mc322.game.composites.dungeon.Dungeon;
import mc322.game.composites.heroes.IHero;
import mc322.game.composites.items.Item;
import mc322.game.factory.CellBuilder;
import mc322.game.factory.DungeonBuilder;
import mc322.game.factory.EnemyBuilder;
import mc322.game.factory.HeroBuilder;
import mc322.game.factory.ItemBuilder;
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
		builder.setDungeonMap("res/dungeons/dungeon.csv");
		builder.setSep(";");
		builder.loadDungeon(gameAssets);
		
		this.gameCtrl = new GameControler();
		
		int[] size = builder.getSize();
		this.dg = new Dungeon();
		dg.setSize(size[0], size[1]);
		System.out.println("\tDungeon (" + size[0] + ", " + size[1] + "): Loading");
		
		Entity cell;
		for (int i = 0; i < size[1]; i++) {
			for (int j = 0; j < size[0]; j++) {
				int id = builder.nextCell();
				String name = gameAssets.getName(id);
				cell = CellBuilder.buildCell(gameAssets, id);
				cell.setTexture(gameAssets.getSprite(name));
				cell.setSolida(gameAssets.getSprite(name).isSolid());
				cell.setPosition(j, i);				
				dg.addEntity(cell);
			}
		}
		
		ArrayList<String[]> entidades = builder.getEntidade();
		for (String[] entidade : entidades) {
			Entity ent = null;
			String name = entidade[0];
			int x = Integer.parseInt(entidade[1]);
			int y = Integer.parseInt(entidade[2]);
			
			cell = dg.getTile(x, y);
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
					item.setAlternativetexture(gameAssets.getSprite("portaAberta"));
				}
				ent = item;
			}
			
			if (ent != null) {
				ent.setPosition(x, y);
				cell.addEntity(ent);
			}
		}
		
		IPathfinder pathFinder = new AStar();
		dg.connectPathfinder(pathFinder);
		gameCtrl.setDungeon(dg);
		System.out.println("\tCaverna: ok");
		System.out.println("GameScene: ok");
	
	}
	
	
}
