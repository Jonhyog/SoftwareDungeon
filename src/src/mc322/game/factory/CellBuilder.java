package mc322.game.factory;

import mc322.game.composites.Cell;
import mc322.game.composites.Entity;
import mc322.game.gfx.Assets;

public class CellBuilder {
	
	private static String defaultTile = "piso2";
	private static boolean defaultSolid = false;
	
	public static String getDefaultTile() {
		return CellBuilder.defaultTile;
	}
	
	public static boolean getDefaultSolid() {
		return CellBuilder.defaultSolid;
	}
	
	public static Entity buildCell(Assets gameAssets, int id) {
		Cell cell = new Cell();
		String name = gameAssets.getName(id);
		cell.setTexture(gameAssets.getSprite(name));
		cell.setSolida(gameAssets.getSprite(name).isSolid());
		
		return cell;
		
	}
}
