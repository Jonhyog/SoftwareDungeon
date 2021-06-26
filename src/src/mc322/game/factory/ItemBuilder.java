package mc322.game.factory;

import mc322.game.composites.items.*;
import mc322.game.gfx.Animation;
import mc322.game.gfx.Assets;
import mc322.game.gfx.IAnimation;

public class ItemBuilder {
	public static boolean isItem(String name) {
		switch (name) {
		case "cafe":
		case "chave":
		case "portaTrancada":
			return true;
		default:
			break;
		}
		
		return false;
	}
	
	public static Item buildItem(Assets gameAssets, String name) {
		Item item = null;
		IAnimation anim = new Animation();
		
		switch (name) {
			case "cafe":
				item = new Coffe(gameAssets.getSprite(name + "1"));
				anim.addFrame(gameAssets.getSprite("cafe1"), 15);
				anim.addFrame(gameAssets.getSprite("cafe2"), 15);
				anim.addFrame(gameAssets.getSprite("cafe3"), 15);
				
				item.connectAnimation(anim);
				item.setSolida(gameAssets.getSprite(name + "1").isSolid());
				break;
			case "chave":
				item = new Key(gameAssets.getSprite(name));
				item.setSolida(gameAssets.getSprite(name).isSolid());
				break;
			case "portaTrancada":
				item = new Door(gameAssets.getSprite(name));
				item.setSolida(gameAssets.getSprite(name).isSolid());
				break;
			default:
				if (item == null)
					System.out.println("FIX-ME: Item Desconhecido " + name); // Lanca Excecao
				break;
		}
		return item;
	}
}
