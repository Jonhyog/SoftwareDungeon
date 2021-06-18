package mc322.game.composites.dungeon.exceptions;

public class DungeonException extends RuntimeException {

	private static final long serialVersionUID = 3478729607552156297L;
	
	public DungeonException() {
		super();
	}
	
	public DungeonException(String message) {
		super(message);
	}
}
