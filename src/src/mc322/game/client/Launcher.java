package mc322.game.client;

public class Launcher {
	public static void main(String [] args) {
		Game game = new Game("Software Dungeon", 640, 640);
		game.start();
	}
}
