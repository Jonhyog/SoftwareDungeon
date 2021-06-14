package mc322.game.util.loaders;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DungeonLoader {
	public static Scanner loadDungeon(String path) {
		try {
			return new Scanner(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
