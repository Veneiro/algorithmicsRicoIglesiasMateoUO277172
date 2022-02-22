package algstudent.s1;

import java.io.IOException;

import algstudent.s0.MatrixOperations;

public class MatrixOperationsTimes {

	public static void main(String[] arg) throws IOException {
		int times = Integer.parseInt(arg[0]);
		long inicio1, fin1;
		long inicio2, fin2;

		System.out.println("# \t | sumDiagonal1\t | sumDiagonal2");
		for (int i = 10; i < Integer.MAX_VALUE; i *= 3) {
			MatrixOperations mo = new MatrixOperations(i, 1, 5);
			inicio1 = System.currentTimeMillis();
			for (int j = 0; j < times; j++) {
				mo.sumDiagonal1();
			}
			fin1 = System.currentTimeMillis();
			long tiempo1 = fin1 - inicio1;
			inicio2 = System.currentTimeMillis();
			for (int j = 1; j < times; j++) {
				mo.sumDiagonal2();
			}
			fin2 = System.currentTimeMillis();
			long tiempo2 = fin2 - inicio2;
			System.out.println("n: " + i + "\t | " + tiempo1 + "\t\t | " + tiempo2);
		}
	}
}
