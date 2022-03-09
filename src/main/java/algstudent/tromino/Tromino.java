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
	private int[][] board;
	private int trominoCounter = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tromino t = new Tromino(8, 0, 0);
		t.setBoard(new int[t.getSize()][t.getSize()]);
		t.doTromino(t.getSize(), 0, 0, t.getX(), t.getY());
		t.print();
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
	}

	void doTromino(int s, int sX, int sY, int x, int y) {
		if (s == 2) { // Base case
			board[this.x][this.y] = -1;

			for (int i = sX; i < sX + s; i++) {
				for (int j = sY; j < sY + s; j++) {
					if (i != x || j != y) {
						board[i][j] = trominoCounter;
					}
				}
			}
			trominoCounter++;

		} else {
			if (x < s / 2) {
				if (y < s / 2) { // First Quadrant
					board[s / 2][(s / 2) - 1] = trominoCounter;
					board[s / 2][s / 2] = trominoCounter;
					board[(s / 2) - 1][s / 2] = trominoCounter;
					trominoCounter++;
					doTromino(s / 2, 0,
							(s / 2) - 2, this.x, this.y);
					doTromino(s / 2, (s / 2) - 2,
							s / 2, (s / 2) - 1,
							s / 2);
					doTromino(s / 2, s / 2,
							(s / 2) - 2, s / 2,
							(s / 2) - 1);
					doTromino(s / 2, s / 2, s / 2,
							s / 2, s / 2);
				} 
//				else if (y >= s / 2) { // Third Quadrant
//					board[s / 2][(s / 2) - 1] = trominoCounter;
//					board[(s / 2) - 1][(s / 2)
//							- 1] = trominoCounter;
//					board[s / 2][s / 2] = trominoCounter;
//					trominoCounter++;
//					doTromino(s / 2, x - 1,
//							y - 1, x, y);
//					doTromino(s / 2, (s / 2) - 2,
//							(s / 2) - 2,
//							(s / 2) - 1,
//							(s / 2) - 1);
//					doTromino(s / 2, s / 2,
//							(s / 2) - 2, s / 2,
//							(s / 2) - 1);
//					doTromino(s / 2, s / 2, s / 2,
//							s / 2, s / 2);
//				}
//			} else if (x >= s / 2) { // Second Quadrant
//				if (y < s / 2) {
//					board[(s / 2) - 1][s / 2] = trominoCounter;
//					board[s / 2][s / 2] = trominoCounter;
//					board[(s / 2) - 1][(s / 2)
//							- 1] = trominoCounter;
//					trominoCounter++;
//					doTromino(s / 2, s / 2,
//							(s / 2) - 2, this.x, this.y);
//					doTromino(s / 2, (s / 2) - 2,
//							s / 2, (s / 2) - 1,
//							s / 2);
//					doTromino(s / 2, (s / 2) - 2,
//							(s / 2) - 2,
//							(s / 2) - 1,
//							(s / 2) - 1);
//					doTromino(s / 2, s / 2, s / 2,
//							s / 2, s / 2);
//				} else if (y >= s / 2) { // Fourth Quadrant
//					board[(s / 2) - 1][(s / 2)
//							- 1] = trominoCounter;
//					board[s / 2][(s / 2) - 1] = trominoCounter;
//					board[(s / 2) - 1][s / 2] = trominoCounter;
//					trominoCounter++;
//					doTromino(s / 2, s / 2, s / 2, this.x,
//							this.y);
//					doTromino(s / 2, (s / 2) - 2,
//							s / 2, (s / 2) - 1,
//							s / 2);
//					doTromino(s / 2, s / 2,
//							(s / 2) - 2, s / 2,
//							(s / 2) - 1);
//					doTromino(s / 2, (s / 2) - 2,
//							(s / 2) - 2,
//							(s / 2) - 1,
//							(s / 2) - 1);
//				}
			}
		}
	}

	void print() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (j == board[0].length - 1) {
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
		this.board = board;
	}

}
