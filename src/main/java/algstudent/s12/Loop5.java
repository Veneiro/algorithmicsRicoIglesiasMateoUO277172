package algstudent.s12;

public class Loop5 {

	public static long loop5(int n) { //O(n3logn)
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= i; j++)
				for (int k = 1; k <= j; k++)
					for (int l = 1; l <= k; l *= 2)
						cont++;
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
		long cont = 0;
		int repetitions = Integer.parseInt(arg[0]);

		for (int n = 1; n <= Integer.MAX_VALUE; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetition = 1; repetition <= repetitions; repetition++) {
				cont += loop5(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "\tTIME=" + (t2 - t1) + "\tCONT="
					+ cont + "\trepetitions=" + repetitions);
		} // for

	} // main
} // class