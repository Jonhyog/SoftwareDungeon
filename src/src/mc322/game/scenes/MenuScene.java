package mc322.game.scenes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.sceneManager.ISceneManager;
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.loaders.ImageLoader;

public class MenuScene extends JPanel implements Scene, ActionListener{
	private static final long serialVersionUID = 7046553700240868429L;
	
	private ISceneManager sceneMan;
	private int width, height;
	private Assets gameAssets;
	private boolean initialized = false;
	
	public MenuScene(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		System.out.println("MenuScene: loading");
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
		BufferedImage img = ImageLoader.loadImage("res/textures/telaInicial.png");
		
		graficos.fillRect(0, 0, 640, 640);
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
			
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
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
        if (action.equals("Jogar")) {
        	sceneMan.setCurrent("Selecao");
        }
	}

	@Override
	public void initScene() {
		JButton btt = new JButton("Jogar");
		 
		btt.setBounds(width/2 - 50, height/2 - 50, 100, 50);
		btt.addActionListener(this);
		btt.setActionCommand("Jogar");
		btt.setFocusable(false);
		super.add(btt);
		System.out.println("\tBotao Jogar: ok");
		System.out.println("MenuScene: ok");
		
	}
}
