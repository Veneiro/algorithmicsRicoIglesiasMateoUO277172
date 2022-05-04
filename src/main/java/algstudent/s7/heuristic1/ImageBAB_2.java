package algstudent.s7.heuristic1;

import java.util.ArrayList;
import java.util.Arrays;

import algstudent.s7.util.bab.BranchAndBound;
import algstudent.s7.util.bab.Node;

public class ImageBAB_2 extends BranchAndBound {

	public ImageBAB_2(ImageNode2 root) {
		this.rootNode = root;
	}
	
	public void branchAndBound() {
		super.branchAndBound(rootNode);
	}
}

class ImageNode2 extends Node {

	private int actual;
	private int asignaciones[];
	private ImageAverager avg;

	/**
	 * Constructor for ImageAvg2 class
	 */
	public ImageNode2(ImageAverager avg) {
		this.actual = -1;
		this.avg = avg;
		this.asignaciones = new int[avg.getNumberOfImages()];
	}

	/**
	 * Constructor for ImageAvg2 class
	 * @param padre
	 * @param cjto
	 */
	public ImageNode2(ImageNode2 padre, int cjto) {
		this.parentID = padre.ID;
		this.depth = padre.depth + 1;
		this.avg = padre.avg;
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
	public void calculateHeuristicValue() {
		if(isSolution()) {
			avg.generateHalfImages(asignaciones);
			heuristicValue = (int) (-avg.zncc() * (1000000));
		} else {
			heuristicValue = -1000000;
		}
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		ImageNode2 iab;
		
		for (int i = 0; i < 3; i++) {
			iab = new ImageNode2(this, i);
			result.add(iab);
		}
		return result;
	}

	@Override
	public boolean isSolution() {
		return (actual == avg.getNumberOfImages()-1) ? true : false;
	}
	
	@Override
	public String toString() {
		String a = "";
		for (int asignacion: asignaciones) {
			a += " " + asignacion;
		}
		return "Actual: " + actual + " - Asignaciones: " + a + " - Heuristico: " + getHeuristicValue();
	}

}
