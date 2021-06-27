package mc322.game.composites;

import mc322.game.composites.dungeon.IDungeon;
import mc322.game.composites.enemies.EnemiesControler;
import mc322.game.composites.heroes.HeroControler;
import mc322.game.scenes.GameScene;
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.GameStats;

public class GameControler {
	private IDungeon dg;
	public GameScene game;
	private SceneManager sceneMan = null;
	private HeroControler heroCtrl;
	private EnemiesControler enemiesCtrl;
	
	public void connectSceneManager(SceneManager sceneMan) {
		this.sceneMan = sceneMan;
	}
	
	public void setDungeon(IDungeon dg) {
		this.dg = dg;
	}
	
	public void connectHeroControler(HeroControler heroCtrl) {
		this.heroCtrl = heroCtrl;
	}
	
	public void connectEnemiesControler(EnemiesControler enemiesCtrl) {
		this.enemiesCtrl = enemiesCtrl;
	}
	
	public void update() {
		if (!heroCtrl.isPlayerAlive()) {
			sceneMan.setCurrent("GameOver");
			return;
		}
		
		heroCtrl.controlPlayer(dg.isPlayerTurn());
		
		int[] playerPos = dg.getPlayerPosition();
		int[] saida = dg.getSaida();
		
		if (playerPos[0] == saida[0] && playerPos[1] == saida[1]) {
			GameStats.increaseScore(100);
			game.nextLevel();
		}
		
	}
}
