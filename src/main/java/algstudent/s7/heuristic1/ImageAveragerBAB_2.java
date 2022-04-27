package algstudent.s7.heuristic1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import algstudent.s7.util.bab.BranchAndBound;
import algstudent.s7.util.bab.Node;

public class ImageAveragerBAB_2 extends BranchAndBound {

	public ImageAveragerBAB_2(ImageAvg2 node) {
		rootNode = node;
	}
}

class ImageAvg2 extends Node {

	private int actual;
	private int asignaciones[];

	/**
	 * Constructor for ImageAvg2 class
	 */
	public ImageAvg2() {
		this.actual = -1;
	}

	/**
	 * Constructor for ImageAvg2 class
	 * @param padre
	 * @param cjto
	 */
	public ImageAvg2(ImageAvg2 padre, int cjto) {
		this.actual = padre.actual + 1;
		this.asignaciones = Arrays.copyOf(padre.asignaciones,
				padre.asignaciones.length);
		this.asignaciones[actual] = cjto;
	}

	/**
	 * To generate a random array of positions
	 * 
	 * @param n Length of the array
	 * @return
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}

	/**
	 * Heuristic to do same as backtracking without balancing
	 */
	@Override
	public void calculateHeuristicValue() {}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		ImageAvg2 iab;
		
		for (int i = 0; i < 3; i++) {
			iab = new ImageAvg2(this, i);
			result.add(iab);
		}
		return result;
	}

	@Override
	public boolean isSolution() {
		return (heuristicValue == 0) ? true : false;
	}

}
