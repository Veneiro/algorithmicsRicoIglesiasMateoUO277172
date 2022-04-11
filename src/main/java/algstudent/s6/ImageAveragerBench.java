package algstudent.s6;

import java.nio.file.Paths;

public class ImageAveragerBench {

	// Benchmarking settings
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString()
			+ "/src/main/java/algstudent/s6/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString()
			+ "/src/main/java/algstudent/s6/einstein_1_256.png";
	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString()
			+ "/src/main/java/algstudent/s6/out_g/";
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString()
			+ "/src/main/java/algstudent/s6/out_bt/";
	private static String OUT_DIR_BP = Paths.get("").toAbsolutePath().toString()
			+ "/src/main/java/algstudent/s6/out_btp/";
	private static int N_IMGS = 10;
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Noise level - Gaussian sigma

	public static void main(String[] args) {

		int n_real, n_bad;
		ImageAverager img_avger;

		// Generating and testing a single dataset instance
		n_bad = (int) ((PERCENTAGE_BAD / 100.) * N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad,
				S_NOISE);

		System.out.print("TESTING GREEDY:\n");
		img_avger.splitSubsetsGreedy(1000);
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Counter: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_G);

		System.out.print("TESTING BACKTRACKING WITHOUT BALANCING:\n");
		img_avger.splitSubsetsBacktracking();
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Counter: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);

		System.out.print("TESTING BACKTRACKING BALANCING:\n");
		img_avger.splitSubsetsBacktracking(1);
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Counter: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_BP);
		// Measurements
		// TODO
//		int[] it = new int[500];
//		int i = 0;
//		for (int size = 2; size <= Integer.MAX_VALUE && i < it.length; size++) {
//			it[i] = size;
//			i++;
//		}
//		measureTimes(img_avger, it);
	}
	
	public static void measureTimes(ImageAverager img, int[] iterations) {
		long t,t1,t2;
		System.out.println("\n\nTime Measurement: Greedy\n");
		for (int n : iterations) {
			t = 0;
			for (int repetition = 1; repetition <= 10; repetition++) {
				t1 = System.currentTimeMillis();
				img.splitSubsetsGreedy(n);
				t2 = System.currentTimeMillis();
				t += t2-t1;
			}

			System.out.println("n=" + n + "\t|\tTIME=" + t + "\t|tZNCC=" + img.zncc());
		}
	}

}