/**
 * 
 */
package algstudent.tromino;

/**
 * @author UO277172
 *
 */
public class Tromino {

	private int size;
	private int x;
	private int y;
	private static int[][] board;
	private int trominoCounter = 1;

	private int inix;
	private int iniy;

	static Tromino t;

	/**
	 * @param args
	 */
	public static void main(int[] args) {
		t = new Tromino(args[0], args[1], args[2]);
		t.setBoard(new int[t.getSize() + 1][t.getSize() + 1]);
		t.doTromino(t.getSize(), t.getX(), t.getY());
		//t.print();
	}

	/**
	 * Constructor for Tromino class a = 4 b = 2 k = 0 Complexity -> O(1) Times
	 * Complexity -> O(n2)
	 * 
	 * @param size
	 * @param x
	 * @param y
	 */
	Tromino(int size, int x, int y) {
		this.size = size;
		this.x = x;
		this.y = y;
		this.inix = x;
		this.iniy = y;
	}

	private void place(int x1, int y1, int x2, int y2, int x3, int y3) {
		trominoCounter++;
		board[x1][y1] = trominoCounter;
		board[x2][y2] = trominoCounter;
		board[x3][y3] = trominoCounter;
	}

	void doTromino(int s, int x, int y) {
		int r = 0, c = 0;
		if (s == 2) { // Base case
			trominoCounter++;
			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					if (board[x + i][y + j] == 0) {
						board[x + i][y + j] = trominoCounter;
					}
				}
			}
			return;
		}
		for (int i = x; i < x + s; i++) {
			for (int j = y; j < y + s; j++) {
				if (board[i][j] != 0) {
					r = i;
					c = j;
				}

			}
		}

		if (r < x + s / 2) { // Top Half
			
			// First Quadrant
			if (c < y + s / 2) { 
				place(x + s / 2, y + (s / 2) - 1, x + s / 2, y + s / 2,
						x + s / 2 - 1, y + s / 2);
			} 
			
			// Second Quadrant
			else if (c >= y + s / 2) { 
				place(x + s / 2, y + (s / 2) - 1, x + s / 2, y + s / 2,
						x + s / 2 - 1, y + s / 2 - 1);
			}
		} else if (r >= x + s / 2) { // Bottom Half
			
			// Third Quadrant
			if (c < y + s / 2) { 
				place(x + (s / 2) - 1, y + (s / 2), x + (s / 2), y + s / 2,
						x + (s / 2) - 1, y + (s / 2) - 1);
				
			// Fourth Quadrant
			} else if (c >= y + s / 2) { 
				place(x + (s / 2) - 1, y + (s / 2), x + (s / 2),
						y + (s / 2) - 1, x + (s / 2) - 1, y + (s / 2) - 1);
			}
		}
		
		doTromino(s / 2, x, y + s / 2);
		doTromino(s / 2, x, y);
		doTromino(s / 2, x + s / 2, y);
		doTromino(s / 2, x + s / 2, y + s / 2);
		board[t.getInix()][t.getIniy()] = -1;
		return;
	}

	void print() {
		for (int i = 0; i < t.getSize(); i++) {
			for (int j = 0; j < t.getSize(); j++) {
				if (j == t.getSize() - 1) {
					System.out.println(board[i][j]);
					System.out.println();
					System.out.println();
				} else {
					System.out.print(board[i][j] + "\t");
				}
			}
		}
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(int[][] board) {
		Tromino.board = board;
	}

	/**
	 * @return the inix
	 */
	public int getInix() {
		return inix;
	}

	/**
	 * @param inix the inix to set
	 */
	public void setInix(int inix) {
		this.inix = inix;
	}

	/**
	 * @return the iniy
	 */
	public int getIniy() {
		return iniy;
	}

	/**
	 * @param iniy the iniy to set
	 */
	public void setIniy(int iniy) {
		this.iniy = iniy;
	}

}
