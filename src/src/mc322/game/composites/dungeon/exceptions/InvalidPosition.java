package mc322.game.composites.dungeon.exceptions;

public class InvalidPosition extends DungeonException {

	private static final long serialVersionUID = 7492543850917334713L;
	
	public InvalidPosition() {
		super();
	}
	
	public InvalidPosition(String message) {
		super(message);
	}
}
