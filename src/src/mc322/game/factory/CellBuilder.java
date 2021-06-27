package mc322.game.factory;

import mc322.game.composites.Cell;
import mc322.game.composites.IEntity;
import mc322.game.gfx.Animation;
import mc322.game.gfx.Assets;
import mc322.game.gfx.IAnimation;

public class CellBuilder {
	
	private static String defaultTile = "piso2";
	private static boolean defaultSolid = false;
	
	public static String getDefaultTile() {
		return CellBuilder.defaultTile;
	}
	
	public static boolean getDefaultSolid() {
		return CellBuilder.defaultSolid;
	}
	
	public static IEntity buildCell(Assets gameAssets, int id) {
		Cell cell = new Cell();
		String name = gameAssets.getName(id);
		IAnimation anim = new Animation();
		
		anim.addFrame(gameAssets.getSprite(name), 10);
		cell.connectAnimation("idle", anim);
		cell.setSolida(gameAssets.getSprite(name).isSolid());
		
		return cell;
		
	}
}
