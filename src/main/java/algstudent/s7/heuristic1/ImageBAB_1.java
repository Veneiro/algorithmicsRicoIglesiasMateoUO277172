package algstudent.s7.heuristic1;

import java.util.ArrayList;
import java.util.Arrays;

import algstudent.s7.util.bab.BranchAndBound;
import algstudent.s7.util.bab.Node;

public class ImageBAB_1 extends BranchAndBound {

	public ImageBAB_1(ImageNode1 root) {
		this.rootNode = root;
	}
	
	public void branchAndBound() {
		super.branchAndBound(rootNode);
	}
}

class ImageNode1 extends Node {
	private ImageNode1 padre;
	private int actual;
	private int asignaciones[];
	private ImageAverager avg;
	private int counter = 0;

	/**
	 * Constructor for ImageAvg2 class
	 */
	public ImageNode1(ImageAverager avg) {
		this.actual = -1;
		this.avg = avg;
		this.asignaciones = new int[avg.getNumberOfImages()];
	}

	/**
	 * Constructor for ImageAvg2 class
	 * @param padre
	 * @param cjto
	 */
	public ImageNode1(ImageNode1 padre, int cjto, int counter) {
		this.padre = padre;
		this.parentID = padre.ID;
		this.depth = padre.depth + 1;
		this.avg = padre.avg;
		this.actual = padre.actual + 1;
		this.asignaciones = Arrays.copyOf(padre.asignaciones,
				padre.asignaciones.length);
		this.asignaciones[actual] = cjto;
		this.counter = counter;
		calculateHeuristicValue();
	}
	
	@Override
	public int initialValuePruneLimit() {
		return 1000000;
	}
	
	public double getZNCC() {
		return this.avg.zncc();
	}
	
	public int getCounter() {
		return this.counter;
	}

	/**
	 * Heuristic to do same as backtracking without balancing
	 */
	@Override
	public void calculateHeuristicValue() {
		if(isSolution()) {
			avg.generateHalfImages(asignaciones, counter);
			heuristicValue = (int) (-avg.zncc() * (1000000));
		} else {
			if (padre == null) {
				heuristicValue = -1000000;
			}
			else {
				avg.generateHalfImages(padre.asignaciones, counter);
				double znccPadre = -avg.zncc();
				avg.generateHalfImages(asignaciones, counter);
				double znccHijo = -avg.zncc();
				if (znccPadre < znccHijo) {
					heuristicValue = 1000000;
				}
				else {
					heuristicValue = -1000000;
				}
			}
		}
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		ImageNode1 iab;
		
		for (int i = 0; i < 3; i++) {
			iab = new ImageNode1(this, i, counter+=1);
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
