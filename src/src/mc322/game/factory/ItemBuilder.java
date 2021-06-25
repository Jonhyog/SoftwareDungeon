package mc322.game.factory;

import mc322.game.composites.items.*;
import mc322.game.gfx.Assets;

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
		switch (name) {
			case "cafe":
				item = new Coffe(gameAssets.getSprite(name));
				break;
			case "chave":
				item = new Key(gameAssets.getSprite(name));
				break;
			case "portaTrancada":
				item = new Door(gameAssets.getSprite(name));
				break;
			default:
				if (item == null)
					System.out.println("FIX-ME: Item Desconhecido " + name); // Lanca Excecao
				break;
		}
		item.setSolida(gameAssets.getSprite(name).isSolid());
		return item;
	}
}
