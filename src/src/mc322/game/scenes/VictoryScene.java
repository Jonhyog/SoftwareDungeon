package mc322.game.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.GameStats;
import mc322.game.util.loaders.ImageLoader;

public class VictoryScene extends JPanel implements Scene {
	private SceneManager sceneMan;
	private int width, height;
	private Assets gameAssets;
	private boolean initialized = false;
	
	public VictoryScene(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		System.out.println("VictoyScene: loading");
		super.setSize(width, height);
		super.setLayout(null);
		super.setDoubleBuffered(true);
		super.setOpaque(false);
		super.setMaximumSize(new Dimension(width, height));
		super.setPreferredSize(new Dimension(width, height));
		super.setMinimumSize(new Dimension(width, height));
		super.setFocusable(false);
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public void connectInputSource(KeyManager key, MouseManager mouse) {
	}
	
	public void connectAssets(Assets gameAssets) {
		this.gameAssets = gameAssets;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
//		BufferedImage img = ImageLoader.loadImage("res/textures/telaInicial.png");
		
		graficos.fillRect(0, 0, 640, 640);
//		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
			
	}
	
	@Override
	public void update() {
		render();
	}

	@Override
	public void render() {
		repaint();
	}

	@Override
	public void setCallback(SceneManager sceneMan) {
		this.sceneMan = sceneMan;
		
	}

	@Override
	public void initScene() {
		JLabel victory = new JLabel("Vitória!");
		JLabel score = new JLabel("Pontuação: " + GameStats.getScore());
		
		victory.setBounds(width/2 - 50, height/2 - 50, 100, 50);
		victory.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		victory.setForeground(new Color (255, 255, 255));
		score.setBounds(width/2 - 50, height/2 + 50, 250, 50);
		score.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		score.setForeground(new Color (255, 255, 255));
		super.add(victory);
		super.add(score);
		
		System.out.println("\tTexto: ok");
		System.out.println("GameOverScene: ok");
		
	}
}
