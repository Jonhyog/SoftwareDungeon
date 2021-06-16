package mc322.game.factory;

import mc322.game.composites.Cell;
import mc322.game.composites.Entity;
import mc322.game.composites.enemies.Bug;
import mc322.game.composites.heroes.Hacker;
import mc322.game.gfx.Assets;

public class CellBuilder {
	
	private static String defaultTile = "piso2";
	
	public Entity buildCell(Assets gameAssets, int id) {
		Cell cell = new Cell();
		String name = gameAssets.getName(id);
		// Deve verificar se eh solida
		switch (name) {
			case "hacker":
				Entity jogador = new Hacker(gameAssets.getSprite(name));
				cell.addEntity(jogador);
				cell.setTexture(gameAssets.getSprite(defaultTile));
				cell.setSolida(false);
				break;
			case "bug":
				Entity bug = new Bug(gameAssets.getSprite(name));
				cell.addEntity(bug);
				cell.setTexture(gameAssets.getSprite(defaultTile));
				cell.setSolida(false);
				break;
			default:
				cell.setTexture(gameAssets.getSprite(name));
				cell.setSolida(gameAssets.getSprite(name).isSolid());
				break;
		}
		return cell;
	}
}
