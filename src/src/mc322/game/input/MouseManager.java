package mc322.game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;

public class MouseManager implements MouseListener {
	
	private Queue<Point> clicksQueue;
	
	public MouseManager() {
		clicksQueue = new LinkedList<Point>();
	}
	
	public Point nextPoint() {
		return clicksQueue.remove();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point pos = e.getPoint();
		clicksQueue.add(pos);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
