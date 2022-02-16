package s0;

public class MatrixOperations {

	private int[][] matrix;
	private int size;

	public void run(MatrixOperations mo) {
		// UNVEIL ONE OF THEM TO CHECK THAT IS WORKING

		System.out.println("Current Matrix: ");
		mo.write();
		System.out.println();
		System.out.println("Sum of the Elems in the Main Diagonal: ");
		System.out.println(sumDiagonal1());
		System.out.println();
		System.out.println("Sum of the Elems in the Secondary Diagonal: ");
		System.out.println(sumDiagonal2());
		System.out.println();
		System.out.println("Travel Path: ");
		travelPath(2, 2);
	}

	public MatrixOperations(int n, int min, int max) {
		this.size = n;
		matrix = new int[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = (int) ((Math.random() * (max + 1 - min)) + min);
			}
		}
	}

	public MatrixOperations(String fileName) {

	}

	public int getSize() {
		return this.size;
	}

	public void write() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (j == this.size - 1) {
					System.out.print(matrix[i][j] + "\n");
				} else {
					System.out.print(matrix[i][j] + "\t");
				}
			}
		}
	}

	public int sumDiagonal1() {
		int aux = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (i == j) {
					aux += matrix[i][j];
				}
			}
		}
		return aux;
	}

	public int sumDiagonal2() {
		int aux = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if ((i + j) == (matrix.length - 1)) {
					aux += matrix[i][j];
				}
			}
		}
		return aux;
	}

	public void travelPath(int i, int j) {
		System.out.println("ORIGINAL MATRIX");
		System.out.println();
		write();
		System.out.println("- - - - - - - - - - - - - - - - - - -");
		while (i < this.matrix.length & j < this.matrix[0].length & i >= 0
				& j >= 0 & matrix[i][j] != -1) {
			if (this.matrix[i][j] == 1) {
				matrix[i][j] = -1;
				i--;
			} else if (this.matrix[i][j] == 2) {
				matrix[i][j] = -1;
				j++;
			} else if (this.matrix[i][j] == 3) {
				matrix[i][j] = -1;
				i++;
			} else if (this.matrix[i][j] == 4) {
				matrix[i][j] = -1;
				j--;
			}
		}
		System.out.println();
		System.out.println("CONVERTED MATRIX");
		System.out.println();
		write();
	}
}
