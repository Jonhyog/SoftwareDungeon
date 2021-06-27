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
import mc322.game.scenes.sceneManager.SceneManager;
import mc322.game.util.GameStats;
import mc322.game.util.loaders.ImageLoader;

public class SelectionScene extends JPanel implements Scene, ActionListener {
	private static final long serialVersionUID = -3229560690189897234L;
	
	private SceneManager sceneMan;
	private int width, height;
	private Assets gameAssets;
	private boolean initialized = false;
	
	public SelectionScene(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		System.out.println("SelectionScene: loading");
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
		BufferedImage img = ImageLoader.loadImage("res/textures/selecaoJogador.png");
			
		graficos.fillRect(0, 0, 640, 640);
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
	}
	
	@Override
	public void setCallback(SceneManager sceneMan) {
		this.sceneMan = sceneMan;
		
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
	public void actionPerformed(ActionEvent e) {
		GameStats.setHeroClass(e.getActionCommand());
        sceneMan.setCurrent("Jogo");
	}
	
	@Override
	public void initScene() {
		JButton bttEng, bttTi, bttHac, bttEst;
		
		// Selecao do Engenheiro
		bttEng = new JButton("Engenheiro");
		bttEng.setBounds(80, 272, 144, 48);
		bttEng.addActionListener(this);
		bttEng.setActionCommand("engenheiro");
		bttEng.setFocusable(false);
		super.add(bttEng);
		
		// Selecao do Tecnico
		bttTi = new JButton("Tecnico");
		bttTi.setBounds(416, 272, 144, 48);
		bttTi.addActionListener(this);
		bttTi.setActionCommand("tecnico");
		bttTi.setFocusable(false);
		super.add(bttTi);
		
		// Selecao do Hacker
		bttHac = new JButton("Hacker");
		bttHac.setBounds(80, 528, 144, 48);
		bttHac.addActionListener(this);
		bttHac.setActionCommand("hacker");
		bttHac.setFocusable(false);
		super.add(bttHac);
		
		// Selecao do Estagiario
		bttEst = new JButton("Estagiario");
		bttEst.setBounds(416, 528, 144, 48);
		bttEst.addActionListener(this);
		bttEst.setActionCommand("estagiario");
		bttEst.setFocusable(false);
		super.add(bttEst);
		
		System.out.println("\tBotao Selecionar: ok");
		System.out.println("SelectionScene: ok");
		
	}
}