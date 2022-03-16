package algstudent.s4;

public class Node {

	
	private String name;
	private Colour colour;
	private String[] neighbours;

	public Node (String name, String[] neighbours, Colour colour) {
		this.name = name;
		this.neighbours = neighbours;
		this.colour = colour;
	}

	/**
	 * @return the name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the neighbours
	 */
	protected String[] getNeighbours() {
		return neighbours;
	}

	/**
	 * @param neighbours the neighbours to set
	 */
	protected void setNeighbours(String[] neighbours) {
		this.neighbours = neighbours;
	}

	/**
	 * @return the colour
	 */
	protected Colour getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	protected void setColour(Colour colour) {
		this.colour = colour;
	}
	
}
