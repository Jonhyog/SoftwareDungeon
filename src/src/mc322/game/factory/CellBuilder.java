package mc322.game.factory;

import mc322.game.composites.Cell;
import mc322.game.composites.Entity;
import mc322.game.gfx.Assets;

public class CellBuilder {
	
	private static String defaultTile = "piso2";
	
	public Entity buildCell(Assets gameAssets, int id) {
		Cell cell = new Cell();
		String name = gameAssets.getName(id);
		HeroBuilder heroBuilder = new HeroBuilder();
		EnemyBuilder enemyBuilder = new EnemyBuilder();
		
		if (heroBuilder.isHero(name)) {
			cell.addEntity(heroBuilder.buildHero(gameAssets, name));
			cell.setTexture(gameAssets.getSprite(defaultTile));
			cell.setSolida(false);
		} else if (enemyBuilder.isEnemy(name)) {
			cell.addEntity(enemyBuilder.buildEnemy(gameAssets, name));
			cell.setTexture(gameAssets.getSprite(defaultTile));
			cell.setSolida(false);
		} else {
			cell.setTexture(gameAssets.getSprite(name));
			cell.setSolida(gameAssets.getSprite(name).isSolid());
		}
		
		return cell;
	}
}
