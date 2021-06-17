package mc322.game.util;

public class Node {
	private Node father;
	private int[] position;
	private int gCost, hCost, fCost;
	
	public Node(int[] position) {
//		System.out.println("Criando no em X:" + position[0] + " Y:" + position[1]);
		this.position = position;
	}
	
	public void setGCost(int gCost) {
		this.gCost = gCost;
	}
	
	public int getGCost() {
		return this.gCost;
	}
	
	public void setHCost(int hCost) {
		this.hCost = hCost;
	}
	
	public int getHCost() {
		return this.hCost;
	}
	
	public void setFCost() {
		this.fCost = gCost + hCost;
	}
	public int getFCost() {
		return fCost;
	}
	
	public void setFather(Node father) {
		this.father = father;
	}
	
	public Node getFather() {
		return this.father;
	}
	
	public int[] getPosition() {
		return this.position;
	}
}
