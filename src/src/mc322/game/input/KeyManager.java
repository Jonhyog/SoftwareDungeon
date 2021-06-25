package mc322.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

public class KeyManager implements KeyListener {
	public boolean[] keys; // FIX-ME: DEVE SER PRIVADO
	private Queue<Character> keyQueue;
	
	public KeyManager() {
		keys = new boolean[256];
		keyQueue = new LinkedList<Character>();
	}
	
	public int nextKey() {
		return keyQueue.remove();
	}
	
	public void clearKeys() {
		keyQueue.clear();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("Adicionando ao Buffer: " + e.getKeyChar());
		keyQueue.add(e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		// System.out.println("Pressionou: " + e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		// System.out.println("Soltou: " + e.getKeyChar());
	}

}
