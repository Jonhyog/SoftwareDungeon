package mc322.game.util;

public class GameStats {
	private static String heroClass = "hacker";
	private static int score = 0;
	
	public static void setHeroClass(String heroClass) {
		GameStats.heroClass = heroClass;
	}
	
	public static void resetScore() {
		GameStats.score = 0;
	}
	
	public static void increaseScore(int n) {
		GameStats.score += n;
	}
	
	public static String getHeroClass() {
		return GameStats.heroClass;
	}
	
	public static int getScore() {
		return GameStats.score;
	}
}
