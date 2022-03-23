/**
 * 
 */
package algstudent.s5;

/**
 * @author UO277172
 *
 */
public class LevenshteinDistance {

	private static int[][] table;
	private static String A = "ABRACADABRA";
	private static String B = "BARCAZAS";
	private static int solution = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getSolution());
	}

	public static void d() {
		for (int i = 1; i < table.length; i++) {
			for (int j = 1; j < table[0].length; j++) {
				if (A.charAt(i) == (B.charAt(j))) {
					table[i][j] = table[i - 1][j - 1];
				} else {
					table[i][j] = 1 + Math.min(
							Math.min(table[i - 1][j - 1], table[i][j - 1]),
							table[i - 1][j]);
				}
			}
		}
	}

//	solution = table[table.length - 1][table[0].length - 1];
	/**
	 * public static void d(int i, int j) {
	 * 
	 * //Stop condition if(i > table.length - 1 || j > table[0].length - 1) {
	 * solution = table[table.length - 1][table[0].length - 1]; return; }
	 * 
	 * if (A.charAt(i - 1) == (B.charAt(j - 1))) { table[i][j] = table[i - 1][j
	 * - 1]; d(i+1, j+1); d(i+1, j); d(i, j+1); } else { table[i][j] = 1 +
	 * Math.min(Math.min(table[i - 1][j - 1], table[i][j - 1]), table[i -
	 * 1][j]); d(i+1, j+1); d(i+1, j); d(i, j+1); }
	 * 
	 * }
	 */

	public static void showTable() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				System.out.print(table[i][j] + "\t");
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	public static int getSolution() {
		table = new int[A.length() + 1][B.length() + 1];

		// ROW and COLUMN 0
		int counteri = 0;
		int counterj = 1;
		for (int i2 = 0; i2 < table.length; i2++) {
			for (int j2 = 0; j2 < table[0].length; j2++) {
				if (i2 == 0) {
					table[i2][j2] = counteri++;
				} else if (j2 == 0) {
					table[i2][j2] = counterj++;
				}
			}
		}

		d(); // Method call
		showTable(); // Showing the table
		return solution;
	}

	/**
	 * @param a the a to set
	 */
	public static void setA(String a) {
		A = a;
	}

	/**
	 * @param b the b to set
	 */
	public static void setB(String b) {
		B = b;
	}

}
