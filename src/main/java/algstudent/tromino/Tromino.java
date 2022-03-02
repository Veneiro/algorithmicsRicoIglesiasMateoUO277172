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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tromino t = new Tromino(4, 1, 1);
		t.setBoard(new int[t.getSize()][t.getSize()]);
		t.doTromino(t.getSize(), t.getX(), t.getY());
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

	void doTromino(int s, int x, int y) {
		if (s == 2) { // Base case
			board[this.x][this.y] = -1;
			
			if (x < board.length / 2) {
				if (y < board[0].length / 2) { //First Quadrant
					for (int i = 0; i < board.length / 2; i++) {
						for (int j = 0; j < board.length / 2; j++) {
							if(board[i][j] != board[x][y]) {
								board[i][j] = 2;
							}
						}
					}
				} else if (y >= board[0].length / 2) { //Third Quadrant
					for (int i = board.length / 2; i < board.length; i++) {
						for (int j = 0; j < board.length / 2; j++) {
							if(board[i][j] != board[x][y]) {
								board[i][j] = 3;
							}
						}
					}
				}
			} else if (x >= board.length / 2) {
				if (y < board[0].length / 2) { //Second Quadrant
					for (int i = 0; i < board.length / 2; i++) {
						for (int j = board.length / 2; j < board.length; j++) {
							if(board[i][j] != board[x][y]) {
								board[i][j] = 4;
							}
						}
					}
				} else if (y >= board[0].length / 2) { //Fourth Quadrant
					for (int i = board.length / 2; i < board.length; i++) {
						for (int j = board.length / 2; j < board.length; j++) {
							if(board[i][j] != board[x][y]) {
								board[i][j] = 5;
							}
						}
					}
				}
			}
//			if (!(x + 1 > (board.length / 2) - 1
//					|| (y + 1 > (board.length / 2) - 1))) {
//				board[x + 1][y + 1] = 1;
//				board[x + 1][y] = 1;
//				board[x][y + 1] = 1;
//			}
//			if (!(x + 1 > (board.length / 2) - 1
//					|| (y + 1 > (board.length / 2) - 1))) {
//				board[x - 1][y + 1] = 1;
//				board[x - 1][y] = 1;
//				board[x][y + 1] = 1;
//			}
//			if (!(x + 1 > (board.length / 2) - 1
//					|| (y + 1 > (board.length / 2) - 1))) {
//				board[x + 1][y - 1] = 1;
//				board[x + 1][y] = 1;
//				board[x][y - 1] = 1;
//			}
//			if (!(x + 1 > (board.length / 2) - 1
//					|| (y + 1 > (board.length / 2) - 1))) {
//				board[x - 1][y - 1] = 1;
//				board[x - 1][y] = 1;
//				board[x][y - 1] = 1;
//			}
		} else {
			if (x < board.length / 2) {
				if (y < board[0].length / 2) {
					board[board[0].length / 2][1] = 1;
					board[board[0].length / 2][board[0].length / 2] = 1;
					board[1][board[0].length / 2] = 1;
					doTromino(s / 2, this.x, this.y);
					doTromino(s / 2, 1, board.length / 2);
					doTromino(s / 2,board.length / 2, 1);
					doTromino(s / 2, board.length / 2, board.length / 2);
				} else if (y >= board[0].length / 2) {
					board[(board[0].length / 2) - 1][1] = 1;
					board[board[0].length / 2][board[0].length / 2] = 1;
					board[1][board[0].length / 2] = 1;
					doTromino(s / 2, this.x, this.y);
					doTromino(s / 2, 1, board.length / 2);
					doTromino(s / 2,board.length / 2, 1);
					doTromino(s / 2, board.length / 2, board.length / 2);
				}
			} else if (x >= board.length / 2) {
				if (y < board[0].length / 2) {
					board[board[0].length / 2][1] = 1;
					board[board[0].length / 2][board[0].length / 2] = 1;
					board[1][(board[0].length / 2) - 1] = 1;
					doTromino(s / 2, this.x, this.y);
					doTromino(s / 2, 1, board.length / 2);
					doTromino(s / 2,board.length / 2, 1);
					doTromino(s / 2, board.length / 2, board.length / 2);
				} else if (y >= board[0].length / 2) {
					board[(board[0].length / 2) - 1][1] = 1;
					board[board[0].length / 2][(board[0].length / 2) - 1] = 1;
					board[1][board[0].length / 2] = 1;
					doTromino(s / 2, this.x, this.y);
					doTromino(s / 2, 1, board.length / 2);
					doTromino(s / 2,board.length / 2, 1);
					doTromino(s / 2, board.length / 2, board.length / 2);
				}
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
