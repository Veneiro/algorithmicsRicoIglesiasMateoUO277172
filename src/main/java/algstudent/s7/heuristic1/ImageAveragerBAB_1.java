package algstudent.s7.heuristic1;

import java.util.ArrayList;
import java.util.Arrays;

import algstudent.s7.util.bab.BranchAndBound;
import algstudent.s7.util.bab.Node;

public class ImageAveragerBAB_1 extends BranchAndBound {

	public ImageAveragerBAB_1(ImageAvg node) {
		rootNode = node;
	}
}

class ImageAvg extends Node {

	private ImageAvg master;
	private int actual;
	private int asignaciones[];

	/**
	 * Constructor for ImageAvg class
	 */
	public ImageAvg() {
		this.actual = -1;
	}

	/**
	 * Constructor for ImageAvg class
	 * @param padre
	 * @param cjto
	 */
	public ImageAvg(ImageAvg padre, int cjto) {
		this.master = padre;
		this.actual = padre.actual + 1;
		this.asignaciones = Arrays.copyOf(padre.asignaciones,
				padre.asignaciones.length);
		this.asignaciones[actual] = cjto;
		calculateHeuristicValue();
	}

	/**
	 * Heuristic to do same as backtracking without balancing
	 */
	@Override
	public void calculateHeuristicValue() {}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		ImageAvg iab;
		
		for (int i = 0; i < 3; i++) {
			iab = new ImageAvg(this, i);
			result.add(iab);
		}
		return result;
	}

	@Override
	public boolean isSolution() {
		return (heuristicValue == 0) ? true : false;
	}

//	public ImageAverager getImageAverager() {
//		return this.master;
//	}

}
