package mc322.game.composites.dungeon.exceptions;

public class InvalidMovement extends DungeonException {

	private static final long serialVersionUID = -2550860891789876616L;
	
	public InvalidMovement() {
		super();
	}
	
	public InvalidMovement(String message) {
		super(message);
	}
}
