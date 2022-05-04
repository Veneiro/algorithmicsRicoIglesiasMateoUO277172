package algstudent.s7.heuristic1;

import java.nio.file.Paths;

public class BranchAndBound2Main {

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
		private int N_IMGS = 7;
		private static double PERCENTAGE_BAD = 25; // %
		private static double S_NOISE = 5.0; // Noise level - Gaussian sigma

		public boolean is_running;
		public String[] menu_items;

		public int n_real, n_bad;
		public ImageAverager img_avger;

		public static void main(String[] args) {
			new BranchAndBound2Main().run();
		}

		private void run() {
			// Generating and testing a single dataset instance
			n_bad = (int) ((PERCENTAGE_BAD / 100.) * N_IMGS);
			n_real = N_IMGS - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad,
					S_NOISE);
			ImageBAB_1 bab = new ImageBAB_1(new ImageNode1(img_avger));
			double inicio = System.currentTimeMillis();
			bab.branchAndBound();
			double fin = System.currentTimeMillis();
			ImageNode1 bestNode = (ImageNode1) bab.getBestNode();
			bab.printSolutionTrace();
			System.out.println("Time: " + (fin - inicio) + " ms" );
		}
}
