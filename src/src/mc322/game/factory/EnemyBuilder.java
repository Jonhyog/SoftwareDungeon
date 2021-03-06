package mc322.game.factory;

import mc322.game.composites.enemies.*;
import mc322.game.gfx.Animation;
import mc322.game.gfx.Assets;
import mc322.game.gfx.IAnimation;

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
		IAnimation anim = new Animation();
		IAnimation animAtk = new Animation();
		
		switch (enemyName) {
			case "bug":
				enemy = new Bug();
				anim.addFrame(gameAssets.getSprite("bug1"), 15);
				anim.addFrame(gameAssets.getSprite("bug2"), 15);
				anim.addFrame(gameAssets.getSprite("bug3"), 15);
				
				animAtk.addFrame(gameAssets.getSprite("bugAtk1"), 15);
				animAtk.addFrame(gameAssets.getSprite("bugAtk2"), 15);
				animAtk.addFrame(gameAssets.getSprite("bugAtk3"), 15);
				break;
			case "erro":
				enemy = new Erro();
				anim.addFrame(gameAssets.getSprite("erro1"), 15);
				anim.addFrame(gameAssets.getSprite("erro2"), 15);
				anim.addFrame(gameAssets.getSprite("erro3"), 15);
				
				animAtk.addFrame(gameAssets.getSprite("erroAtk1"), 15);
				animAtk.addFrame(gameAssets.getSprite("erroAtk2"), 15);
				animAtk.addFrame(gameAssets.getSprite("erroAtk3"), 15);
				break;
			case "ponteiro":
				enemy = new Ponteiro();
				anim.addFrame(gameAssets.getSprite("ponteiro1"), 15);
				anim.addFrame(gameAssets.getSprite("ponteiro2"), 15);
				anim.addFrame(gameAssets.getSprite("ponteiro3"), 15);
				
//				animAtk.addFrame(gameAssets.getSprite("hackerAtk1"), 15);
//				animAtk.addFrame(gameAssets.getSprite("hackerAtk2"), 15);
//				animAtk.addFrame(gameAssets.getSprite("hackerAtk3"), 15);
				break;
			case "virus":
				enemy = new Virus();
				anim.addFrame(gameAssets.getSprite("virus1"), 15);
				anim.addFrame(gameAssets.getSprite("virus2"), 15);
				anim.addFrame(gameAssets.getSprite("virus3"), 15);
				
				animAtk.addFrame(gameAssets.getSprite("virusAtk1"), 15);
				animAtk.addFrame(gameAssets.getSprite("virusAtk2"), 15);
				animAtk.addFrame(gameAssets.getSprite("virusAtk3"), 15);
				break;
			default:
				if (enemy == null)
					System.out.println("FIX-ME: Inimigo Desconhecido"); // Lanca Excecao
				break;
		}
//		enemy.setSolida(gameAssets.getSprite(enemyName + "1").isSolid()); Nao consigo atacar solidos
		enemy.connectAnimation("idle", anim);
		enemy.connectAnimation("atk", animAtk);
		enemy.setMovement(new EnemyMovement());
		return enemy;
	}
}
