package algstudent.s1;

public class Vector4 {

	static int[] v;

	public static void main(String[] args) {
		int repetitions = Integer.parseInt(args[0]);
		long t1, t2;
		for (int n = 10; n < Integer.MAX_VALUE; n *= 5) {
			v = new int[n];
			Vector1.fillIn(v);
			int sum = 0;
			t1 = System.currentTimeMillis();
			for (int rep = 1; rep < repetitions; rep++) {
				sum = Vector1.sum(v);
			}
			t2 = System.currentTimeMillis();
			System.out.printf("SIZE=%d TIME %d microseconds SUM=%d REPETITIONS=%d\n", n,
					t2 - t1, sum, repetitions);
		}
	}

}
