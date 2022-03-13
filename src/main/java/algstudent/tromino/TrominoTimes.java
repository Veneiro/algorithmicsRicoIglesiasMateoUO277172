package algstudent.tromino;

public class TrominoTimes {

	private static long t1;
	private static long t2;
	private static int n;

	public static void main(String args[]) {
		t1 = 0;
		t2 = 0;
		n = 16;
		while (true) { // 5 sec.
			t1 = System.currentTimeMillis();
			int[] v = new int[] {n, 0, 0};
			Tromino.main(v);
			t2 = System.currentTimeMillis();
			System.out.println(" n=" + n + "  Time=" + (t2 - t1));
			n = n * 2;
		}
	}
}
