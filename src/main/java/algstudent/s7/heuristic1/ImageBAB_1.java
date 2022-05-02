package algstudent.s7.heuristic1;

import java.util.ArrayList;
import java.util.Arrays;

import algstudent.s7.util.bab.BranchAndBound;
import algstudent.s7.util.bab.Node;

public class ImageBAB_1 extends BranchAndBound {

	public ImageBAB_1(ImageNode node) {
		rootNode = node;
	}
}

class ImageNode extends Node {

	private ImageNode master;
	private int actual;
	private int asignaciones[];

	/**
	 * Constructor for ImageNode class
	 */
	public ImageNode() {
		this.actual = -1;
	}

	/**
	 * Constructor for ImageNode class
	 * @param padre
	 * @param cjto
	 */
	public ImageNode(ImageNode padre, int cjto) {
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
		ImageNode iab;
		
		for (int i = 0; i < 3; i++) {
			iab = new ImageNode(this, i);
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
