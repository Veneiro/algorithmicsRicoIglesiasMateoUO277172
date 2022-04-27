package algstudent.s7.heuristic1;

import java.nio.file.Paths;
import java.util.Scanner;

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
	private int N_IMGS = 10;
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Noise level - Gaussian sigma

	public boolean is_running;
	public String[] menu_items;

	public int n_real, n_bad;
	public ImageAverager img_avger;

	public static void main(String[] args) {
		// Measurements
		ImageAveragerBench ia = new ImageAveragerBench();
		ia.run();
		// measureAlgorithms();
	}

	public ImageAveragerBench() {
		// Generating and testing a single dataset instance
		n_bad = (int) ((PERCENTAGE_BAD / 100.) * N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad,
				S_NOISE);
		is_running = true;
		menu_items = new String[] { "Greedy Base", "Backtracking Base",
				"Backtracking with Balancing", "Greedy Times",
				"Backtracking Default Times",
				"Backtracking with Balancing Times" };
	}// end constructor

	public void run() {
		while (is_running) {
			menu_show();
			menu_user_input();
		}
	}

	public void menu_show() {
		System.out.println("MEASUREMENTS");
		int i = 1;// iterator
		// foreach menu_items
		for (String item : menu_items) {
			if (i == 1) {
				System.out.println("Base Tests: ");
				System.out.println(i + ") " + item);
				i++;
			} else if (i == 4) {
				System.out.println();
				System.out.println("Times measurement: ");
				System.out.println(i + ") " + item);
				i++;
			} else {
				System.out.println(i + ") " + item);
				i++;
			}
		}
		System.out.println();
		System.out.println("0) Exit");
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		System.out.println("Introduce your selection: ");
	}// end menu_show

	public void menu_user_input() {
		// get user input
		@SuppressWarnings("resource")
		Scanner user_input = new Scanner(System.in);
		String next = user_input.next();
		switch (next) {
		case "1":
			measureGreedy();
			pressAnyKeyToContinue();
			break;
		case "2":
			measureBackDef();
			pressAnyKeyToContinue();
			break;
		case "3":
			measureBackNB();
			pressAnyKeyToContinue();
			break;
		case "4":
			System.out.println(
					"Introduce the max number of iterations you want to do: ");
			Scanner num1 = new Scanner(System.in);
			int sizeNum1 = Integer.valueOf(num1.next());
			measureAlgorithmsGreedy(sizeNum1);
			pressAnyKeyToContinue();
			break;
		case "5":
			System.out.println(
					"Introduce the max number of iterations you want to do: ");
			Scanner num2 = new Scanner(System.in);
			int sizeNum2 = Integer.valueOf(num2.next());
			measureAlgorithmsBacktrackingNB(sizeNum2);
			pressAnyKeyToContinue();
			break;
		case "6":
			System.out.println(
					"Introduce the max number of iterations you want to do: ");
			Scanner num3 = new Scanner(System.in);
			int sizeNum3 = Integer.valueOf(num3.next());
			measureAlgorithmsBacktrackingYB(sizeNum3);
			pressAnyKeyToContinue();
			break;
		case "0":
			System.out.println("Program Succesfully exited");
			is_running = false;
			break;
		default:
			System.out.println("Invalid input, try again");
			break;
		}
	}// end menu_user_input

	private void pressAnyKeyToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	private void measureGreedy() {
		System.out.print("TESTING GREEDY:\n");
		img_avger.splitSubsetsGreedy(1000);
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Counter: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_G);
	}

	private void measureBackDef() {
		System.out.print("TESTING BACKTRACKING WITHOUT BALANCING:\n");
		img_avger.splitSubsetsBacktracking();
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Counter: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);
	}

	private void measureBackNB() {
		System.out.print("TESTING BACKTRACKING BALANCING:\n");
		img_avger.splitSubsetsBacktracking(1);
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Counter: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_BP);
	}

	private void measureAlgorithmsBacktrackingYB(int number) {
		// TODO
		int[] it = new int[number];
		int i = 0;
		for (int size = 2; size <= Integer.MAX_VALUE && i < it.length; size++) {
			it[i] = size;
			i++;
		}

		System.out
				.println("\n\nTime Measurement: Backtracking with balancing\n");
		for (int n : it) {
			this.N_IMGS = n;
			n_bad = (int) ((PERCENTAGE_BAD / 100.) * N_IMGS);
			n_real = N_IMGS - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad,
					S_NOISE);
			long t = measureTimesBacktrackingYB(img_avger, it);
			System.out.println("n=" + n + "\t|\tTIME= " + t + "\t " + "|ZNCC= "
					+ img_avger.zncc());
		}
	}

	private void measureAlgorithmsGreedy(int number) {
		// TODO
		int[] it = new int[number];
		int i = 0;
		for (int size = 2; size <= Integer.MAX_VALUE && i < it.length; size++) {
			it[i] = size;
			i++;
		}
		System.out.println("\n\nTime Measurement: Greedy\n");
		measureTimesGreedy(img_avger, it);
	}

	private void measureAlgorithmsBacktrackingNB(int number) {
		// TODO
		int[] it = new int[number];
		int i = 0;
		for (int size = 2; size <= Integer.MAX_VALUE && i < it.length; size++) {
			it[i] = size;
			i++;
		}

		System.out.println(
				"\n\nTime Measurement: Backtracking without balancing\n");
		
		for (int n : it) {
			this.N_IMGS = n;
			n_bad = (int) ((PERCENTAGE_BAD / 100.) * N_IMGS);
			n_real = N_IMGS - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad,
					S_NOISE);
			long t = measureTimesBacktrackingNB(img_avger, it);
			System.out.println("n=" + n + "\t|\tTIME= " + t + "\t " + "|ZNCC= "
					+ img_avger.zncc());
		}
	}

	public long measureTimesBacktrackingYB(ImageAverager img,
			int[] iterations) {
		long t, t1, t2;
		t = 0;
		for (int repetition = 1; repetition <= 10; repetition++) {
			t1 = System.currentTimeMillis();
			img.splitSubsetsBacktracking(2);
			t2 = System.currentTimeMillis();
			t += t2 - t1;
		}
		return t;
	}

	public void measureTimesGreedy(ImageAverager img, int[] iterations) {
		long t, t1, t2;
		for (int n : iterations) {
			t = 0;
			for (int repetition = 1; repetition <= 10; repetition++) {
				t1 = System.currentTimeMillis();
				img.splitSubsetsGreedy(n);
				t2 = System.currentTimeMillis();
				t += t2 - t1;
			}

			System.out.println(
					"n=" + n + "\t|\tTIME= " + t + "\t " + "|ZNCC= " + img.zncc());
		}
	}

	public long measureTimesBacktrackingNB(ImageAverager img,
			int[] iterations) {
		long t, t1, t2;
		t = 0;
		for (int repetition = 1; repetition <= 10; repetition++) {
			t1 = System.currentTimeMillis();
			img.splitSubsetsBacktracking();
			t2 = System.currentTimeMillis();
			t += t2 - t1;
		}
		return t;
	}
}