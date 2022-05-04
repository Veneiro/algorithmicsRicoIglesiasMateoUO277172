package algstudent.test.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ImageAverager {

	private Image real_img, bad_img; // to store the main good and main bad
										// image
	private Image avg_img, quarter1, quarter2, quarter3, quarter4; // to store
																	// the final
																	// tests to
	// see if we improve the
	// previous results
	private Image[] dataset; // dataset to store all the images (good and bad
								// ones)
	private int[] sol; // to store the partial results (where I am putting the
						// pictures? 0->not assigned, 1->first half, 2->second
						// half
	private int[] bestSol; // to store the best solution
	private int width, height; // to store the width and height of the image
	// backtracking variables
	private int counter; // to store the number of times we assign an image to
							// half1, half2 or no group
	private int counterHalf1;
	private int counterHalf2;
	private int counterHalf3;
	private int counterHalf4;
	private double max_zncc = Double.NEGATIVE_INFINITY; // to store the best
														// ZNCC

	/**
	 * Constructor
	 * 
	 * @real_path path to the real image (pattern to find) on disk
	 * @bad_path path to the bad image on disk
	 * @n_real number of real images in the dataset (>= 1)
	 * @n_bad number of bad images in the dataset
	 * @s_noise standard deviation for noise
	 */
	public ImageAverager(String real_path, String bad_path, int n_real,
			int n_bad, double s_noise) {
		assert (n_real >= 1) && (n_bad < n_real);// assert at least one
													// reference image

		// load reference and bad images
		this.real_img = new Image(real_path);
		this.bad_img = new Image(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();

		// create the dataset as an array of unordered randomly chosen real and
		// bad images
		int total_imgs = n_real + n_bad; // the total number of images are the
											// good + the bad ones
		this.dataset = new Image[total_imgs]; // the data set for the total of
												// images
		this.sol = new int[total_imgs]; // we will use this variable during the
										// process 0->not assigned, 1->first
										// half, 2->second half
		this.bestSol = new int[total_imgs]; // we will use this variable to
											// store the best results
		int[] rand_index = this.randomIndexes(total_imgs); // random array of
															// positions to mix
															// images
		Image hold_img; // temp images
		int region = 0; // 0-up, 1-down, 2-left, 3-right
		for (int i = 0; i < n_real; i++) { // to save good images
			hold_img = new Image(this.width, this.height); // generate image
			hold_img.addSignal(this.real_img); // save the image
			hold_img.suppressRegion(region); // a half part of the image is
												// deleted
			hold_img.addNoise(s_noise); // add some noise
			this.dataset[rand_index[i]] = hold_img; // save image
			if (region == 3)
				region = 0;
			else
				region++;
		}
		region = 0;
		for (int i = n_real; i < n_real + n_bad; i++) { // to save bad images
			hold_img = new Image(this.width, this.height); // generate image
			hold_img.addSignal(this.bad_img); // save the image
			hold_img.invertSignal(); // corrupt the image
			hold_img.suppressRegion(region); // the fourth part of the image is
												// deleted
			hold_img.addNoise(s_noise); // add some noise
			this.dataset[rand_index[i]] = hold_img; // save image
			if (region == 3)
				region = 0;
			else
				region++;
		}
	}

	/**
	 * To generate a random array of positions
	 * 
	 * @param n Length of the array
	 * @return
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}

	/**
	 * Store resulting images for testing
	 * 
	 * @out_dir directory save the output images
	 */
	public void saveResults(String out_dir) {
		this.avg_img.save(out_dir + "/img_avg.png");
		this.quarter1.save(out_dir + "/quarter1_avg.png");
		this.quarter2.save(out_dir + "/quarter2_avg.png");
		this.quarter3.save(out_dir + "/quarter3_avg.png");
		this.quarter4.save(out_dir + "/quarter4_avg.png");
		for (int i = 0; i < this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_"
					+ this.bestSol[i] + ".png");
		}
	}

	/**
	 * @return the number of steps carried out by the algorithm to solve the
	 *         problem
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Computes the ZNCC between both image dataset halves
	 * 
	 * @return the computed ZNCC
	 */
	public double zncc() {
		return this.quarter1.zncc(this.quarter2)
				+ this.quarter3.zncc(this.quarter4);
	}

	/**
	 * Greedy algorithm: random instances for each half, the best one is the
	 * final solution
	 * 
	 * @n_tries number of random tries
	 */
	public void splitSubsetsGreedy(int n_tries) {
		for (int i = 0; i < n_tries; i++) {
			this.quarter1 = new Image(this.width, this.height);
			this.quarter2 = new Image(this.width, this.height);
			this.quarter3 = new Image(this.width, this.height);
			this.quarter4 = new Image(this.width, this.height);
			// TODO
			for (int j = 0; j < dataset.length; j++) {
				Random r = new Random();
				switch (r.nextInt(5)) {
				case 0:
					// Don't use it
					counter++;
					break;
				case 1:
					// Put in group 1
					counter++;
					quarter1.addSignal(dataset[j]);
					break;
				case 2:
					// Put in group 2
					counter++;
					quarter2.addSignal(dataset[j]);
					break;
				case 3:
					// Put in group 3
					counter++;
					quarter3.addSignal(dataset[j]);
					break;
				case 4:
					// Put in group 4
					counter++;
					quarter4.addSignal(dataset[j]);
					break;
				}
			}
			double aux = zncc();
			if (aux > max_zncc) {
				this.max_zncc = aux;
				this.avg_img = new Image(this.width, this.height);
				avg_img.addSignal(quarter1);
				avg_img.addSignal(quarter2);
				avg_img.addSignal(quarter3);
				avg_img.addSignal(quarter4);
			}
		}
	}

	/**
	 * Backtracking algorithm
	 * 
	 * @max_unbalancing: (pruning condition) determines the maximum difference
	 *                   between the number of images on each half set
	 */
	public void splitSubsetsBacktracking(int max_unbalancing) {
		// TODO
		counter = 0;
		this.quarter1 = new Image(this.width, this.height);
		this.quarter2 = new Image(this.width, this.height);
		this.quarter3 = new Image(this.width, this.height);
		this.quarter4 = new Image(this.width, this.height);
		backtrackingPruning(0, max_unbalancing);
	}

	private void backtrackingPruning(int level, int max_unbalancing) {
		if (level == dataset.length - 1) {
			double aux = zncc();
			if (aux > max_zncc) {
				this.max_zncc = aux;
				this.avg_img = new Image(this.width, this.height);
				avg_img.addSignal(quarter1);
				avg_img.addSignal(quarter2);
				avg_img.addSignal(quarter3);
				avg_img.addSignal(quarter4);
			}
		} else {
			// Don't use it
			counter++;
			backtracking(level + 1);

			// Put in group 1 if there is no unbalancing
			if (Math.abs(counterHalf2 - counterHalf1) < max_unbalancing
					&& Math.abs(counterHalf3 - counterHalf1) < max_unbalancing
					&& Math.abs(
							counterHalf4 - counterHalf1) < max_unbalancing) {
				counter++;
				counterHalf1++;
				quarter1.addSignal(dataset[level]);
				backtracking(level + 1);
				quarter1.removeSignal(dataset[level]);
			}

			// Put in group 2 if there is no unbalancing
			if (Math.abs(counterHalf2 - counterHalf1) < max_unbalancing
					&& Math.abs(counterHalf2 - counterHalf3) < max_unbalancing
					&& Math.abs(
							counterHalf2 - counterHalf4) < max_unbalancing) {
				counter++;
				counterHalf2++;
				quarter2.addSignal(dataset[level]);
				backtracking(level + 1);
				quarter2.removeSignal(dataset[level]);
			}

			// Put in group 3 if there is no unbalancing
			if (Math.abs(counterHalf1 - counterHalf3) < max_unbalancing
					&& Math.abs(counterHalf2 - counterHalf3) < max_unbalancing
					&& Math.abs(
							counterHalf4 - counterHalf3) < max_unbalancing) {
				counter++;
				counterHalf3++;
				quarter3.addSignal(dataset[level]);
				backtracking(level + 1);
				quarter3.removeSignal(dataset[level]);
			}

			// Put in group 4 is there is no unbalancing
			if (Math.abs(counterHalf1 - counterHalf4) < max_unbalancing
					&& Math.abs(counterHalf2 - counterHalf4) < max_unbalancing
					&& Math.abs(
							counterHalf3 - counterHalf4) < max_unbalancing) {
				counter++;
				counterHalf4++;
				quarter4.addSignal(dataset[level]);
				backtracking(level + 1);
				quarter4.removeSignal(dataset[level]);
			}
		}
	}

	/**
	 * Backtracking algorithm without balancing. Using a larger than the number
	 * of images in the dataset ensures no prunning
	 */
	public void splitSubsetsBacktracking() {
		// TODO
		counter = 0;
		this.quarter1 = new Image(this.width, this.height);
		this.quarter2 = new Image(this.width, this.height);
		this.quarter3 = new Image(this.width, this.height);
		this.quarter4 = new Image(this.width, this.height);
		backtracking(0);
	}

	private void backtracking(int level) {
		if (level == dataset.length - 1) {
			double aux = zncc();
			if (aux > max_zncc) {
				this.max_zncc = aux;
				this.avg_img = new Image(this.width, this.height);
				avg_img.addSignal(quarter1);
				avg_img.addSignal(quarter2);
				avg_img.addSignal(quarter3);
				avg_img.addSignal(quarter4);
			}
		} else {
			// Don't use it
			counter++;
			backtracking(level + 1);

			// Put in group 1
			counter++;
			quarter1.addSignal(dataset[level]);
			backtracking(level + 1);
			quarter1.removeSignal(dataset[level]);

			// Put in group 2
			counter++;
			quarter2.addSignal(dataset[level]);
			backtracking(level + 1);
			quarter2.removeSignal(dataset[level]);

			// Put in group 3
			counter++;
			quarter3.addSignal(dataset[level]);
			backtracking(level + 1);
			quarter3.removeSignal(dataset[level]);

			// Put in group 4
			counter++;
			quarter4.addSignal(dataset[level]);
			backtracking(level + 1);
			quarter4.removeSignal(dataset[level]);
		}
	}
}
