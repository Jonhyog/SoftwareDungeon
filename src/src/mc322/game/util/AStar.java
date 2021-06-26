package mc322.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import mc322.game.composites.IEntity;
import mc322.game.composites.dungeon.Dungeon;

public class AStar implements IPathfinder {
	private int normaTaxista(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
	
	private ArrayList<Node> generateNeighbourNodes(Node father, Dungeon dg, int[] source, int target[]) {
		int[] fatherPosition = father.getPosition();
		int[][] posicoesVizinhos = new int[][] {
			{fatherPosition[0] + 1, fatherPosition[1]},
			{fatherPosition[0] - 1, fatherPosition[1]},
			{fatherPosition[0], fatherPosition[1] + 1},
			{fatherPosition[0], fatherPosition[1] - 1},
			};
		ArrayList<Node> vizinhos = new ArrayList<Node>();
		Node novoNo;
		for (int i = 0; i < posicoesVizinhos.length; i++) {
			if (dg.isValidPosition(posicoesVizinhos[i][0], posicoesVizinhos[i][1])) {
				novoNo = new Node(posicoesVizinhos[i]);
				novoNo.setFather(father);
				novoNo.setGCost(father.getGCost() + 1);
				novoNo.setHCost(normaTaxista(posicoesVizinhos[i], target));
				novoNo.setFCost();
				vizinhos.add(novoNo);
			}
		}
		
		return vizinhos;
	}
	
	private boolean isInQueue(PriorityQueue<Node> fPrio, Node no) {
		int[] posNo = no.getPosition(), posNoFila;
		for (Node noFila : fPrio) {
			posNoFila = noFila.getPosition();
			
			if (posNoFila[0] == posNo[0] && posNoFila[1] == posNo[1])
				return true;
		}
		return false;
	}
	
	
	private Node getFromQueue(PriorityQueue<Node> fPrio, Node no) {
		int[] posNo = no.getPosition(), posNoFila;
		for (Node noFila : fPrio) {
			posNoFila = noFila.getPosition();
			
			if (posNoFila[0] == posNo[0] && posNoFila[1] == posNo[1])
				return noFila;
		}
		return null;
	}
	
	private ArrayList<int[]> generatePath(Node target) {
		if (target == null)
			return null;
		
		ArrayList<int[]> caminho = new ArrayList<int[]>();
		for (Node no = target; no.getFather() != null; no = no.getFather()) {
			caminho.add(no.getPosition());
		}
		
		Collections.reverse(caminho);
		return caminho;
	}
	
	public ArrayList<int[]> findPath(int[] source, int[] target, Dungeon dg) {
		PriorityQueue<Node> fPrio = new PriorityQueue<Node>(10, new NodeComparator());
		ArrayList<Node> vizinhos;
		Node atual = null;
		boolean encontrou = false;
		int[] pos, size = dg.getSize();
		boolean[][] visitados = new boolean[size[1]][size[0]];
		
		Node inicio = new Node(source);
		inicio.setFather(null);
		inicio.setGCost(normaTaxista(source, source));
		inicio.setHCost(normaTaxista(source, target));
		inicio.setFCost();
		
		fPrio.add(inicio);
		
		while (true) {
			if (fPrio.isEmpty())
				break;
			
			atual = fPrio.remove();
			pos = atual.getPosition();
			visitados[pos[1]][pos[0]] = true;
			
			if (pos[0] == target[0] && pos[1] == target[1]) {
				encontrou = true;
				break;
			}
			
			vizinhos = generateNeighbourNodes(atual, dg, source, target);
			for (Node vizinho : vizinhos) {
				pos = vizinho.getPosition();
				
				IEntity celula = dg.getTile(pos[0], pos[1]); // FIX-ME
				if (visitados[pos[1]][pos[0]] || celula.isSolid())
					continue;
				
				if (isInQueue(fPrio, vizinho)) {
					Node mesmaPos = getFromQueue(fPrio, vizinho);
					if (vizinho.getGCost() < mesmaPos.getGCost()) {
						mesmaPos.setFather(atual);
						mesmaPos.setGCost(vizinho.getGCost());
						mesmaPos.setFCost();						
					}
				} else {
					fPrio.add(vizinho);
				}
			}
		}
		
		if (!encontrou)
			return null;
		
		return generatePath(atual);
	}
}
