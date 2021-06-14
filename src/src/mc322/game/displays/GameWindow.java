package mc322.game.displays;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	// Static
	private static final long serialVersionUID = -7092360426186796088L;
		
	// Instance
		
	public GameWindow(String title, int width, int height) {
		super(title);
		System.out.println("MainWindow: loading");
		super.setSize(width, height);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		System.out.println("MainWindow: ok");
	}
}
