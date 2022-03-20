package algstudent.s4;

import java.util.ArrayList;
import java.util.List;

public class MapColoring {

	static List<Node> nodes = new ArrayList<Node>();
	static List<Colour> colours = new ArrayList<Colour>();

	public static void main(String[] args) {
		FileUtil.loadFileNodes("files/borders.txt", nodes);
		FileUtil.loadFileColours("files/colors.txt", colours);
		doColouring(nodes);
		printColours();
	}

	private static void printColours() {
		for (Node node : nodes) {
			System.out.print(node.getName() + ": ");
			System.out.println(node.getColour().getColour());
			System.out.println(" Neighbours: ");
			for (String s : node.getNeighbours()) {
				try {
				System.out.println(" -" + s + ": " + getNodeColour(s).getColour());
				} catch (Exception e) {}
			}
			System.out.println();
			System.out.println();
		}
	}

	private static void doColouring(List<Node> nodes) {
		int checker = 0;
		for (Node node : nodes) {
			for (Colour colour : colours) {
				if(checker == 1) {
					checker = 0;
					break;
				}
				node.setColour(colour);
				for (String neigh : node.getNeighbours()) {
					if ((node.getColour().equals(getNodeColour(neigh))
							&& !((getNodeColour(neigh).getColour())
									.equals("No Colour")))) {
						checker = 0;
						break;
					} else {
						checker = 1;
					}
				}
			}
		}
	}

	private static Colour getNodeColour(String neigh) {
		for (Node node : nodes) {
			if (node.getName().equals(neigh.trim())) {
				return node.getColour();
			}
		}
		return null;
	}

}
