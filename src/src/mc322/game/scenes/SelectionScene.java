package mc322.game.scenes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;
import mc322.game.input.MouseManager;
import mc322.game.scenes.sceneManager.SceneManager;

public class SelectionScene extends JPanel implements Scene, ActionListener {
	private static final long serialVersionUID = -3229560690189897234L;
	
	private SceneManager sceneMan;
	private int width, height;
	
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
		super.setMinimumSize(new Dimension(width, height));
		super.setFocusable(false);
	}
	
	public void connectInputSource(KeyManager key, MouseManager mouse) {
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
			
		graficos.fillRect(0, 0, 640, 640);
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
		String action = e.getActionCommand();
        if (action.equals("Selecionar")) {
        	sceneMan.setCurrent("Jogo");
        }
	}
	
	@Override
	public void initScene(Assets gameAssets) {
		JButton bttEng, bttTi, bttHac, bttEst;
		gameAssets.getSprite("selecaoJogador");
		bttEng = new JButton("Engenheiro");
		bttEng.setBounds((width+100)/2 - 50, (height-50)/2 - 50, 100, 50);
		bttEng.addActionListener(this);
		bttEng.setActionCommand("Selecionar");
		bttEng.setFocusable(false);
		super.add(bttEng);
		bttTi = new JButton("Tecnico");
		bttTi.setBounds((width-100)/2 - 50, (height-50)/2 - 50, 100, 50);
		bttTi.addActionListener(this);
		bttTi.setActionCommand("Selecionar");
		bttTi.setFocusable(false);
		super.add(bttTi);
		bttHac = new JButton("Hacker");
		bttHac.setBounds((width-100)/2 - 50, (height+50)/2 - 50, 100, 50);
		bttHac.addActionListener(this);
		bttHac.setActionCommand("Selecionar");
		bttHac.setFocusable(false);
		super.add(bttHac);
		bttEst = new JButton("Estagiario");
		bttEst.setBounds((width+100)/2 - 50, (height+50)/2 - 50, 100, 50);
		bttEst.addActionListener(this);
		bttEst.setActionCommand("Selecionar");
		bttEst.setFocusable(false);
		super.add(bttEst);
		System.out.println("\tBotao Selecionar: ok");
		System.out.println("SelectionScene: ok");
		
	}
}