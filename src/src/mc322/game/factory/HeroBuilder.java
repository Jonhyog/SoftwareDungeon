package mc322.game.factory;

import mc322.game.composites.heroes.*;
import mc322.game.gfx.Assets;

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
		
		switch (hero) {
			case "hacker":
				jogador = new Hacker(gameAssets.getSprite(hero));
				break;
			case "engenheiro":
				jogador = new Engenheiro(gameAssets.getSprite(hero));
				break;
			case "estagiario":
				jogador = new Estagiario(gameAssets.getSprite(hero));
				break;
			case "tecnico":
				jogador = new Tecnico(gameAssets.getSprite(hero));
				break;
			default:
				if (jogador == null)
					System.out.println("FIX-ME"); // Lanca Excecao
				break;
		}
		jogador.setMovement(new HeroMovement());
		return jogador;
	}
}
