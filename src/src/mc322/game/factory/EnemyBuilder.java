package mc322.game.factory;

import mc322.game.composites.enemies.*;
import mc322.game.gfx.Assets;

public class EnemyBuilder {
	public static boolean isEnemy(String hero) {
		switch (hero) {
		case "bug":
		case "erro":
		case "ponteiro":
		case "virus":
			return true;
		default:
			break;
		}
		
		return false;
	}
	
	public static Enemy buildEnemy(Assets gameAssets, String enemyName) {
		Enemy enemy = null;
		
		switch (enemyName) {
			case "bug":
				enemy = new Bug(gameAssets.getSprite(enemyName));
				break;
			case "erro":
				enemy = new Erro(gameAssets.getSprite(enemyName));
				break;
			case "ponteiro":
				enemy = new Ponteiro(gameAssets.getSprite(enemyName));
				break;
			case "virus":
				enemy = new Virus(gameAssets.getSprite(enemyName));
				break;
			default:
				if (enemy == null)
					System.out.println("FIX-ME"); // Lanca Excecao
				break;
		}
		enemy.setMovement(new EnemyMovement());
		return enemy;
	}
}
