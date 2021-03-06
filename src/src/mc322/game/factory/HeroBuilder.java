package mc322.game.factory;

import mc322.game.composites.heroes.*;
import mc322.game.gfx.Animation;
import mc322.game.gfx.Assets;
import mc322.game.gfx.IAnimation;

public class HeroBuilder {
	
	public static boolean isHero(String hero) {
		switch (hero) {
		case "hacker":
		case "engenheiro":
		case "estagiario":
		case "tecnico":
			return true;
		default:
			break;
		}
		
		return false;
	}
	
	public static Hero buildHero(Assets gameAssets, String hero) {
		Hero jogador = null;
		IAnimation anim = new Animation();
		IAnimation animAtk = new Animation();
		
		switch (hero) {
			case "hacker":
				jogador = new Hacker();
				anim.addFrame(gameAssets.getSprite("hacker1"), 15);
				anim.addFrame(gameAssets.getSprite("hacker2"), 15);
				anim.addFrame(gameAssets.getSprite("hacker3"), 15);
				
				animAtk.addFrame(gameAssets.getSprite("hackerAtk1"), 15);
				animAtk.addFrame(gameAssets.getSprite("hackerAtk2"), 15);
				animAtk.addFrame(gameAssets.getSprite("hackerAtk3"), 15);
				break;
			case "engenheiro":
				jogador = new Engenheiro();
				anim.addFrame(gameAssets.getSprite("engenheiro1"), 15);
				anim.addFrame(gameAssets.getSprite("engenheiro2"), 15);
				anim.addFrame(gameAssets.getSprite("engenheiro3"), 15);
				
				animAtk.addFrame(gameAssets.getSprite("engenheiroAtk1"), 15);
				animAtk.addFrame(gameAssets.getSprite("engenheiroAtk2"), 15);
				animAtk.addFrame(gameAssets.getSprite("engenheiroAtk3"), 15);
				break;
			case "estagiario":
				jogador = new Estagiario();
				anim.addFrame(gameAssets.getSprite("estagiario1"), 15);
				anim.addFrame(gameAssets.getSprite("estagiario2"), 15);
				anim.addFrame(gameAssets.getSprite("estagiario3"), 15);
				
				animAtk.addFrame(gameAssets.getSprite("estagiarioAtk1"), 15);
				animAtk.addFrame(gameAssets.getSprite("estagiarioAtk2"), 15);
				animAtk.addFrame(gameAssets.getSprite("estagiarioAtk3"), 15);
				break;
			case "tecnico":
				jogador = new Tecnico();
				anim.addFrame(gameAssets.getSprite("tecnico1"), 15);
				anim.addFrame(gameAssets.getSprite("tecnico2"), 15);
				anim.addFrame(gameAssets.getSprite("tecnico3"), 15);
				
				animAtk.addFrame(gameAssets.getSprite("tecnicoAtk1"), 15);
				animAtk.addFrame(gameAssets.getSprite("tecnicoAtk2"), 15);
				animAtk.addFrame(gameAssets.getSprite("tecnicoAtk3"), 15);
				break;
			default:
				break;
		}
		
		jogador.connectAnimation("idle", anim);
		jogador.connectAnimation("atk", animAtk);
		jogador.connectMovement(new HeroMovement());
		return jogador;
	}
}
